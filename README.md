# 毕业总结

分别用 100 个字以上的一段话，加上一幅图（架构图或脑图），总结自己对下列技术的关键点思考和经验认识:

- JVM
  ![QQ截图20220113151018.png](https://s2.loli.net/2022/01/13/bh2S7sO4qMAeH8F.png)

  #### 字节码：
  了解如何生成和查看字节码
  #### 类加载器
  - 类的生命周期（加载、链接（效验、准备、解析）、初始化、使用、卸载）
  - 三类加载器：启动类加载器、扩展类加载器、应用类加载器
  - 加载器特点：双亲委托、负责依赖、缓存加载 
  - 自定义加载器：核心点就是一个函数：defineClass。 这个函数读取class的字节流，生成对应的类。核心的使用方法就是，读取转化class为字节流，调用函数，生成。
  #### 内存模型
  堆内存结构
  ![w1作业3.png](https://s2.loli.net/2022/01/13/BdOGPxlfQAeKFto.png)
  
  #### GC
  - GC算法：串行（Serial）、并行（Parallel、ParNew）、并发（CMS、G1、ZGC、Shenandoah）
  - GC调优思路：尽量减少STW时间；权衡内存、吞吐量和延迟，根据场景业务，选择合适的GC算法
    

- NIO
  ![NIO.png](https://s2.loli.net/2022/01/13/ZXiAqlD62BcQSoh.png)

  #### Socket编程
  分别使用单线程、多线程、线程池来实现简单的http服务器，通过结果可以看出多线程和线程池的处理能力远强于单线程。

  #### IO模型
  - 同步异步是通信模式，阻塞、非阻塞是线程处理模式；
  - 五种IO模型：阻塞式IO、非阻塞式IO、IO复用、信号驱动IO、异步IO
    
  #### Netty
  - 基本属性：事件驱动、基于NIO、异步
  - 基本概念：Channel（通道）、ChannelFuture（Future接口）、Event & Handler（事件和处理器）、Encoder & Decoder（编码 解码器）、ChannelPipeline（事件处理器链）
  - Netty实现高性能：优化通信模型、优化网络
  - API网关：目前主流的网关有Zuul，Spring Cloud GateWay等，其中Zuul2使用了Netty进行了优化
  - 自定义实现API网关，主要使用Netty，实现服务器之间通信，添加过滤器和路由等


- 并发编程
  ![并发编程.png](https://s2.loli.net/2022/01/13/nwYPJx4ovh9TZH1.png)
  #### java多线程
  - Thread.start:创建新线程 Thread.run: 本线程调用
  - 守护线程：觉得JVM什么时候关闭
  #### 线程安全&线程池
  - volatile每次读写强制从主内存刷数据，尽量使用原子操作代替
  - final关键字，尽量用来替换java中的常量，提高线程安全
  - 创建一个固定大小的线程池 newFixedThreadPool()
  - 线程池创建策略，合理配置固定线程池大小
  #### 并发
  - 锁使用范围：降低锁粒度、细分锁粒度
  - 并发集合类：
    使用CopyOnWriteArrayList优化ArrayList和LinkedList
    使用ConcurrentHashMap优化HashMap和LinkedHashMap

- Spring 和 ORM 等框架
  ![Spring 和 ORM 等框架.png](https://s2.loli.net/2022/01/14/PLTEIYAewdgDurz.png)

  #### Spring和SpringBoot
  - Spring 框架6大模块，比较常用的Core、DataAccess、Testing、Web
  - Spring AOP 面向切面编程，学会使用字节码增强工具
  - Spring Bean 的生命周期、加载过程
  - SpringXML 配置原理和演化，熟练使用XML、注解
  - SpringBoot自动装配，约定大于配置，自动配置实践
  - SpringBootStarter自定义实现

  #### JDBC和ORM框架
  - 原生JDBC写法，常用数据库连接池
  - Hibernate/Mybatis 之间的优劣势比较
  - Spring事务 @Transactional注解的使用
  - Spring/SpringBoot集成Hibernate/Mybatis
  
  #### 其他框架
  - JAVA8 lambda表达式和流的使用
  - 使用Lombok简化开发代码
  - 使用Guava规范化代码开发
  
  #### 其他设计相关
  - 设计原则S.O.L.I.D
  - 23种设计模式
  - 单元测试回归的手段、重要性
  
- MySQL 数据库和 SQL
  ![MySQL数据库和SQL.png](https://s2.loli.net/2022/01/14/Q4RMp7uLvgYj2fc.png)
  
  #### Mysql
  - 关系数据库定义，常见数据库：Mysql、Oracle、SQL Server
  - 数据库设计三范式：
    1NF：消除重复数据，即每一列都是不可再分的基本数据项
    2NF：消除部分依赖，表中没有列只与主键的部分相关，即每一行都被主键唯一标识
    3NF：消除传递依赖，消除表中列不依赖主键，而是依赖表中的非主键列的情况，即没有列是与主
    键不相关的
  - 常用SQL语句包含三类：查询DQL，操作：DML，定义：DDL
  - Mysql存储引擎myisam和innodb，目前主流的使用innodb
  - innodb引擎默认的事务隔离级别是 可重复读: REPEATABLE READ，可能的问题：不保证没有幻读，需要加锁
  #### SQL优化
  - 主要从写入优化、数据更新、模糊查询优化、连接查询、索引和查询语句优化等方面综合考虑

- 分库分表
  ![分库分表.png](https://s2.loli.net/2022/01/15/K9YCr6IwNZJzWVb.png)
  #### 高可用和读写分离
  - 主从复制原理主要是主库写binlog、从库同步主库binlog
  - 主从模式主要有异步复制、半同步复制、组复制
  - 读写分离通过ShardingSphere-jdbc实现，还是存在倾入性较强的问题，改进通过Mycat等中间件减少侵入
  - 高可用常见策略：多主机部署、跨机房部署、两地三中心容灾高可用方案
  - 数据库拆分的原因：单机数据库已经不能满足微服务化的需求
  - 垂直拆分的缺点：库变多，管理更复杂难做；对业务系统侵入较强；
  - 水平拆分的缺点：数据迁移问题、一致性问题；
  - 相关常用中间件Mycat、 Apache ShardingSphere-Proxy
  #### 分布式事务
  - 主流框架Atomikos、narayana、Seata、ShardingSphere
  - XA事务保持强一致性，对应的柔性事务采用手动（TCC）/自动(AT)补偿的方式实现分布式事务

- RPC 和微服务
  ![RPC和微服务.png](https://s2.loli.net/2022/01/15/mG3xpdZWcMFf1U7.png)
  - RPC可以让我们想调用本地服务一样调用远程服务
  - 常用的RPC框架有Hessian、Dubbo等
  - Dubbo的应用场景：分布式服务化改造、开发平台搭建、业务中台建设
  - 分布式服务主要有配置中心、注册中心、元数据中心
  - 分布式服务主要的功能有服务注册和发现、集群的路由和负载均衡、服务的过滤、流控和监控
  - 微服务发展的趋势：微服务->服务网格->数据库网格->云原生
  - SpringCloud体系中常用的组件有：Eureka/Zuul/Zuul2/SpringCloud Gateway/Feign/Ribbon/Hystrix
  - 常用微服务工具：监控Zipkin/Elk 权限SpringSecurity/Shiro

- 分布式缓存
  ![分布式缓存.png](https://s2.loli.net/2022/01/15/zVWKA3o1HeNGaQc.png)
  - 适合使用缓存的数据：静态、准静态数据、中间状态数据、热数据、读远大于写的数据
  - 缓存的加载方式：启动时全量加载、使用时加载、延迟异步加载；
  - 缓存常见问题：穿透、击穿、雪崩
  - Redis基本的数据结构有String、hash、list、set、sorted set
  - Redis6之后改为多线程提升了性能
  - 与Springboot集成，引入 spring-boot-starter-data-redis
  - Redis高可用，使用Cluster集群、主从复制、主从切换sentinel
  - 内存网格Hazelcast的特性：分布式、高可用、可扩展、低延迟
  
  
- 分布式消息队列
  ![分布式消息队列.png](https://s2.loli.net/2022/01/15/MdAUXf35O9liFkb.png)
  
  - 消息队列的作用主要有：异步通信、系统结构、削峰平谷和可靠通信
  - 消息队列处理的模式有点对点模式和发布订阅模式
  - 消息协议主要有：JMS、AMQP、MQTT
  - 开源的消息队列中间件：ActiveMQ/RabbitMQ/Kafka/RocketMQ/Pulsar
  - kafka基本概念：broker、topic、partition、producer、consumer、consumer group
  - 目前使用较多的为RabbitMQ
  - 自定义MQ：基于内存->自定义队列->基于spring mvc完善->策略完善->技术完善
