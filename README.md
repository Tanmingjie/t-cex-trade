# t-cex-trade

`t-cex-trade` 是一个面向 Java 后端求职与 CEX 交易系统学习的入门项目，目标是实现一个简化版中心化交易所核心交易链路。

项目不追求覆盖完整交易所业务，而是聚焦高级 Java 后端岗位常考、CEX Java 岗位常见的核心能力：

- 订单生命周期与状态机
- 用户资产、可用余额、冻结余额与资金流水
- 限价单撮合、部分成交与成交记录
- Kafka 事件驱动、幂等消费与最终一致性
- MySQL 事务、锁、索引与账务正确性
- Redis 缓存、限流与热点数据处理
- 对账、补偿、异常恢复与可观测性

## 文档

- [项目背景与详细设计](docs/project-design.md)
- [功能学习地图](docs/learning-map.md)
- [OpenSpec SDD 配置](openspec/config.yaml)
- [首个 SDD 归档：bootstrap-sdd-cex-trade](openspec/changes/archive/2026-08-17-bootstrap-sdd-cex-trade/proposal.md)

## SDD 工作流

本项目使用 [OpenSpec](https://openspec.dev/) 管理后续需求、设计和实现任务。每个有业务含义的变更都应先创建 OpenSpec change，再进入编码。

推荐流程：

1. 提出变更：`$openspec-propose "要实现的功能"`
2. 审阅并调整 `proposal.md`、`specs/`、`design.md`、`tasks.md`
3. 按 `tasks.md` 实现代码并同步勾选任务
4. 校验：`openspec validate <change-name> --type change --strict`
5. 完成后归档，让能力规格沉淀到 `openspec/specs/`

## 本地开发

### 环境要求

- JDK 21
- Maven 3.9+
- Docker Desktop

### 启动基础设施

```powershell
docker compose up -d mysql redis kafka
```

默认本地服务：

- MySQL: `localhost:3306`，库名 `t_cex_trade`，用户 `t_cex`，密码 `t_cex`
- Redis: `localhost:6379`
- Kafka: `localhost:9092`

### 启动应用

```powershell
mvn spring-boot:run
```

启动后检查健康状态：

```powershell
Invoke-RestMethod http://localhost:8080/actuator/health
```

### 轻量验证

```powershell
mvn test
```

当前脚手架只验证 Spring Boot 应用上下文和模块边界，不包含资产、订单、撮合、清算等业务接口。

## 推荐开发节奏

1. 先实现资产、充值、下单冻结、撤单解冻。
2. 再实现订单状态机、内存订单簿和基础撮合。
3. 然后接入 Kafka 事件、幂等表、outbox 表和异常补偿。
4. 最后补 Redis、压测、监控、对账报告和 AI 辅助模块。

## 项目定位

简历描述可以参考：

> 基于 Java 21、Spring Boot、MySQL、Redis、Kafka 实现简化版 CEX 核心交易系统，支持用户资产、限价单下单/撤单、资金冻结/解冻、内存订单簿撮合、成交清算、Kafka 事件通知、幂等处理和对账补偿。
