

## 7. 面试问题清单

### 缓存

#### Q1: Redis 缓存和数据库如何保持一致？

中文回答：
> when happend: 
只读缓存：先删改数据库的数据，再删改缓存， 如果删改缓存失败，数据就不一致了。TTL兜底，即使不一致，也只会不一致一段时间，再或者就是异步重试，先写入数据库，再删除缓存，如果删除失败，再放到MQ里面，消费者不断重试删除。
读写缓存：同步写回，先写缓存，再同步写回数据库。通过事务来保证一致性。
English answer:


#### Q2: 什么是缓存穿透、击穿、雪崩？

中文回答：

> 缓存穿透是请求的数据在缓存和数据库里都不存在，导致请求一直打到数据库；可以用空值缓存、参数校验或布隆过滤器缓解。缓存击穿是热点 key 过期后，大量请求同时访问数据库；可以用互斥锁、逻辑过期或热点 key 预热缓解。缓存雪崩是大量 key 同时过期或 Redis 故障，导致数据库压力突然暴涨；可以通过随机 TTL、多级缓存、限流和降级处理。

> 雪崩：缓存宕机的情况下：可以通过服务熔断去避免数据库也崩溃。数据库也可以增加限流，来避免崩溃。也可以再部署时通过主从节点的方式部署redis.

> 击穿：击穿是，某个热点数据在缓存中失效了，大量请求直接到达数据库。 solution：对于热点数据，不设置TTL，可以新增一个redis属性作为热点数据的TTL，用定时任务去校验这个属性更新热点数据缓存。
> 穿透：请求的数据在缓存和数据库中都不存在。solution：1）缓存空值。2）通过布隆过滤器去检查数据库，如果返回不存在，那么就不用访问数据库了。
English answer

#### Q3: 哪些数据不适合放 Redis？

中文回答：

> 复杂查询（多个查询参数，缓存命中率低），长久不用的冷数据（内存的成本远远高于磁盘）、体积过大的单体数据、以及对绝对安全性要求极高的核心资产数据（虽然有AOF日志，但通常配置成everysec，在极端情况下，依然可能丢失1s的数据）；Redis 更适合放热点查询、短生命周期状态、幂等 key、限流计数和临时配置缓存。

English answer:


### 分布式锁

#### Q4: Redis 分布式锁如何避免误删？

中文回答：

> 加锁时要写入唯一 value，例如 UUID 或 requestId。解锁时不能直接删除 key，而是先判断 value 是否属于当前请求，再删除。这个判断和删除最好用 Lua 脚本保证原子性，避免一个请求因为锁过期后误删另一个请求新加的锁。
set lock_key unique_value NX PX 30000
English answer:

> When acquiring a Redis lock, we should store a unique value such as a UUID or request ID. When releasing the lock, we should check whether the value still belongs to the current request before deleting it. This check-and-delete operation should be atomic, usually implemented with a Lua script, to avoid deleting another request's lock by mistake.

#### Q5: 锁过期时间怎么设置？

中文回答：

> 锁过期时间要大于正常业务执行时间，并留出一定缓冲；不能太短，否则业务没执行完锁就释放；也不能太长，否则故障后恢复慢。更稳妥的做法是缩短锁保护的代码范围，只保护真正的临界区，并结合超时、重试和业务幂等兜底。

English answer:

> The lock expiration time should be longer than the normal business execution time with some buffer. If it is too short, the lock may expire before the operation finishes; if it is too long, failure recovery becomes slow. A better practice is to keep the critical section small and combine the lock with timeout control, retries, and idempotency.

#### Q6: Redis 锁能不能保证金融账务一致性？

中文回答：

> 不能。Redis 锁只能作为分布式协调手段，降低并发冲突概率，但不能作为账务一致性的根基。金融账务一致性必须依赖数据库事务、行锁、唯一约束、流水记录、幂等设计和对账机制。Redis 锁可以辅助保护，但不能替代这些机制。

English answer:

> No. A Redis lock is only a distributed coordination mechanism. It can reduce concurrency conflicts, but it should not be the foundation of financial consistency. Financial correctness should be guaranteed by database transactions, row locks, unique constraints, ledger records, idempotency design, and reconciliation. Redis locks can help, but they cannot replace these mechanisms.

### 限流

#### Q7: 固定窗口、滑动窗口、令牌桶有什么区别？

中文回答：

> 固定窗口实现简单，比如每分钟最多 100 次，但窗口边界可能产生突刺。滑动窗口统计最近一段时间内的请求，更平滑但实现更复杂。令牌桶按固定速率生成令牌，允许一定突发流量，比较适合下单、查询这类需要兼顾吞吐和保护的接口。

English answer:

> A fixed window is simple, such as allowing 100 requests per minute, but it may allow traffic spikes around window boundaries. A sliding window counts requests in the recent time range and is smoother, but more complex. A token bucket generates tokens at a fixed rate and allows limited bursts, which is often suitable for order submission and query APIs.

#### Q8: 下单接口如何做限流？

中文回答：

> 下单接口可以按用户、IP、交易对、接口维度做组合限流。例如单用户每秒最多提交一定数量订单，异常 IP 更严格，热门交易对可以单独保护。限流只是保护层，后面还需要参数校验、风险控制、幂等、资产校验和订单状态机。

English answer:

> For an order submission API, we can apply rate limits by user, IP, trading pair, and endpoint. For example, each user can submit only a limited number of orders per second, suspicious IPs can have stricter limits, and hot trading pairs can have additional protection. Rate limiting is only a protection layer; we still need validation, risk checks, idempotency, balance checks, and an order state machine.

#### Q9: 限流后应该返回什么错误？

中文回答：

> 通常返回 `429 Too Many Requests`，并给出清晰错误码和提示。最好带上重试建议，例如 `Retry-After`，让客户端知道什么时候可以重试。对交易系统来说，不能让用户误以为订单已经成功，所以响应必须明确表达“请求被限流，订单未创建”。

English answer:

> Usually we return `429 Too Many Requests` with a clear error code and message. It is also helpful to return retry guidance, such as a `Retry-After` header. In a trading system, the response must clearly indicate that the request was rate-limited and no order was created, so the user does not misunderstand the result.

### 幂等

#### Q10: 下单接口如何设计幂等？

中文回答：

> 下单接口可以要求客户端传 `Idempotency-Key` 或 `clientOrderId`。服务端先检查这个 key 是否已经处理过，如果正在处理则返回处理中，如果已成功则返回之前的订单结果。如果没有处理过，就创建订单，并用数据库唯一约束兜底，确保同一个用户的同一个客户端订单号只能创建一次。
> 乐观锁机制，更新的时候带一个version字段，
English answer:

> An order submission API can require an `Idempotency-Key` or `clientOrderId`. The server checks whether the key has already been processed. If it is still processing, we return a processing state; if it has succeeded, we return the previous order result. If it is new, we create the order and use a database unique constraint as the final safeguard, so the same user and client order ID can create only one order.

#### Q11: Kafka 重复消费怎么处理？

中文回答：

> Kafka 语义下重复消费是正常情况，所以消费者必须设计成幂等。可以用事件 ID、业务单号或流水号作为去重 key，在数据库里记录已处理事件，或者通过业务唯一约束避免重复写入。处理成功后再提交 offset；如果失败，就允许重试，但重复重试不能造成重复清算或重复入账。

English answer:

> Duplicate consumption is normal in Kafka-based systems, so consumers must be idempotent. We can use an event ID, business ID, or ledger ID as the deduplication key, record processed events in the database, or rely on business unique constraints to prevent duplicate writes. The consumer should commit the offset only after successful processing, and retries must not create duplicate settlement or ledger entries.

#### Q12: Redis 幂等和数据库唯一约束如何配合？

中文回答：

> Redis 适合做快速拦截和短期状态记录，比如处理中、已成功、已失败；数据库唯一约束负责最终一致性兜底。正常情况下 Redis 可以减少重复请求进入业务逻辑，但如果 Redis 失效、key 过期或并发竞争，数据库唯一约束仍然能防止重复订单、重复流水或重复清算。

English answer:

> Redis is useful for fast filtering and short-term status tracking, such as processing, succeeded, or failed. The database unique constraint is the final consistency safeguard. In normal cases, Redis reduces duplicate requests entering the business logic. But if Redis fails, the key expires, or concurrent requests race, the database constraint can still prevent duplicate orders, ledger entries, or settlement records.

## 8. 今日复述模板

用中文讲一遍：

> Redis 在 Java 后端里常见有四类用途：缓存、分布式锁、限流、幂等。缓存解决热点读问题，但数据库仍然是事实来源；分布式锁解决多实例并发协调，但不能替代数据库事务；限流保护接口不被突发流量打垮；幂等保证重复请求、重试和重复消息不会造成重复业务效果。在交易系统里，Redis 适合做辅助控制，核心资产、订单、流水必须落数据库并可审计。

用英文讲一遍：

> In a Java backend system, Redis is commonly used for caching, distributed locking, rate limiting, and idempotency control. In trading or payment systems, Redis should be used as a supporting component, while the database remains the source of truth for balances, orders, transactions, and audit records.

## 9. 下一步衔接

- 把下单接口的幂等设计写进 OpenSpec。
- 决定 `clientOrderId`、`Idempotency-Key`、订单唯一约束的关系。
- 设计订单查询缓存是否必要，以及缓存失效策略。
- 周二继续 Kafka：topic、partition、consumer group、offset。
