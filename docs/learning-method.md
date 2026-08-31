# Learning Method

这份文档记录当前阶段的学习方法。目标不是“学过很多”，而是让知识变成能讲、能用、能复盘、能支撑求职的能力。

## Core Principle

使用“求职工程师式学习”，不要使用“学生式学习”。

每个知识点都要经过这个闭环：

> 输入 -> 复述 -> 应用 -> 输出 -> 回顾

判断一个知识点是否真正学到位，不看学习时长，而看能否完成三件事：

- 用自己的话讲清楚。
- 放进 `t-cex-trade` 的业务场景里。
- 回答面试官的追问。

## Five-Step Loop

### 1. Input

- 每次只学一个小主题，例如 Kafka consumer group、Redis cache aside、MySQL transaction isolation。
- 优先读官方文档、真实 JD、技术文章，再补视频。
- 不连续刷资料，不把收藏当学习。

### 2. Retell

- 合上资料，用自己的话讲一遍。
- 先用中文讲清楚，再提炼 2-3 句英文表达。
- 如果讲不顺，说明还没理解，不急着进入下一个主题。

### 3. Apply

- 问它在交易、支付、订单系统里解决什么问题。
- 映射到 `t-cex-trade`：哪个模块会用，为什么用，不用会怎样。
- 优先设计一个具体场景，而不是停留在概念。

### 4. Output

每学一个点，尽量留下四种输出：

- 一页中文笔记。
- 一个项目应用场景。
- 三个面试问题。
- 一段英文表达。

### 5. Review

- 第二天快速回顾 5-10 分钟。
- 周日统一复盘本周高频知识点。
- 遗忘是正常的，复用才会留下记忆。

## Four Diagnostic Questions

每学一个知识点，都回答：

1. 它解决什么问题？
2. 在交易、支付、订单系统里哪里会用？
3. 它有什么坑？
4. 面试官会怎么追问？

如果答不上来，不代表失败，而是找到了下一步学习入口。

## Example: Kafka

### Topic

Kafka consumer group and offset.

### Project Scenario

订单创建后发布 `OrderAcceptedEvent`，资产、撮合、通知等下游模块异步消费，避免下单接口承担所有后续处理。

### Interview Questions

- 为什么订单系统要用 Kafka？
- 消费失败怎么办？
- 如何避免重复消费？

### English Expression

> We use Kafka to decouple order submission from downstream settlement and notification processing.

## Example: Redis

### Topic

Redis idempotency key and cache.

### Project Scenario

下单接口使用幂等键避免用户重复提交订单；行情、订单状态等热点查询可以使用缓存降低数据库压力。

### Interview Questions

- Redis 在订单系统里适合存什么？
- 缓存和数据库不一致怎么办？
- 分布式锁有什么风险？

### English Expression

> Redis can be used for idempotency control, hot data caching, and lightweight rate limiting in backend services.

## Anti-Patterns

- 连续看 3 小时视频，但没有输出。
- 抄很多概念，但不能解释业务场景。
- 今天 Redis、明天 JVM、后天 Kubernetes，每个都浅尝。
- 只做中文笔记，不练英文表达。
- 只学习，不投射到项目和面试。

## Current Learning Rule

当前阶段采用：

> 学一个点 -> 写一页笔记 -> 造一个项目场景 -> 写三个面试问题 -> 英文说一遍

知识最终要沉淀到三个地方：

- 项目设计里。
- 面试问答里。
- 英文表达里。
