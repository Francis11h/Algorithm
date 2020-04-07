Spring 基础
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-----------------
SpringBoot 是什么 
-----------------

SpringBoot 
1.简化 Spring的开发的一个框架 整个spring技术栈的大整合 J2EE （Java 2 Platform Enterprise Edition）java平台企业版 开发的一站式解决方案 
2.使用嵌入式 Servlet容器 无需打成 war 包 可以直接打成jar包 用java命令 不需要单独安装Tomcat这类容器服务器了，maven 打出jar包直接跑起来就是个网站
3.有很多的 starters (启动器), 进行 自动的 依赖管理和版本控制		
	 
	比如 启动web      <artifactId>spring-boot-starter-"web"</artifactId>
	    jpa 		<artifactId>spring-boot-starter-"data-jpa"</artifactId>
		redis 		<artifactId>spring-boot-starter-"data-redis"</artifactId>
4. 无需配置 XML, 无代码生成, 开箱即用
5. 准生产环境时的运行监控
6. 与云计算的天然集成


----------------------------------
springboot与springmvc的区别是什么？
----------------------------------
Spring 框架就像一个家族，有众多衍生产品例如 boot、security、jpa等等。
但他们的基础都是Spring 的 "ioc" 和 "aop" 
ioc-------提供了依赖注入的容器 
aop-------解决了面向横切面的编程，
然后在此两者的基础上实现了其他延伸产品的高级功能。	"Spring的灵魂 : IOC 和 AOP"

Spring MVC是基于 Servlet 的一个 MVC 框架 主要解决 WEB 开发的问题，因为 Spring 的配置非常复杂，各种XML、 JavaConfig、hin处理起来比较繁琐。于是为了简化开发者的使用，从而创造性地推出了Spring boot，约定优于配置，简化了spring的配置流程



说得更简便一些:
Spring 最初利用“工厂模式”（DI）和“代理模式”（AOP）解耦应用组件。
大家觉得挺好用，于是按照这种模式搞了一个 MVC框架（一些用Spring 解耦的组件），用开发 web 应用（ SpringMVC ）。
然后有发现每次开发都写很多样板代码，为了简化工作流程，于是开发出了一些“懒人整合包”（starter），这套就是 Spring Boot。



所以，用最简练的语言概括就是：

Spring 是一个“引擎”
Spring MVC 是基于Spring的一个 MVC 框架 
Spring Boot 是基于Spring4的条件注册的一套快速开发整合包。


--- 
yml 
---
SpringBoot使用的一个全局配置文件 
配置文件的作用: 修改SpringBoot自动配置的默认值 







-----------
IoC 依赖反转
-----------
一句话: 不再new对象了 直接注入进来。。

工厂模式

IOC = Inversion of Control 反转资源获取的方向
DI Dependency Injection 依赖注入     是实现 IoC的一种方式

通俗/代码解释

原来: 我的service 需要调用 DAO(repository), service 就需要创建 DAO
现在: Spring 发现 你的 service 依赖于 dao, 于是 直接把 dao 注入到你的 service 里面 
	当前对象如果需要依赖另一个对象，只要打一个"@Autowired"注解，Spring就会自动帮你安装上。


核心原理: 就是 "工厂模式 + 反射 + 配置文件"
	就是 我需要的配置文件 通过反射 放到容器里(工厂里可以创建不同的容器(说白了就是个map))


----
Bean
----

spring进行IOC实现时使用的有两个概念：context上下文和bean。
如中间图所示，所有被spring管理的、由spring创建的、用于依赖注入的对象，就叫做一个bean。
Spring创建并完成依赖注入后，所有bean统一放在一个叫做context的上下文中进行管理。



-------------------------------
AOP java的动态代理机制是怎样的？ 
-------------------------------
AOP 就是 面向切面编程

核心原理: 使用 "动态代理模式" 的方式 在执行前后或出现异常后 做相关逻辑


使用 AOP来做
1. 事务处理
2. 权限判断
3. 日志


https://www.zhihu.com/question/48427693/answer/691483076

AOP就是面向切面编程。如右面的图，一般程序执行流程是从controller层调用service层、然后service层调用DAO层访问数据，最后在逐层返回结果。
这个是图中向下箭头所示的按程序执行顺序的纵向处理。
但是，一个系统中会有多个不同的服务，例如用户服务、商品信息服务等等，
每个服务的controller层都需要验证参数，都需要处理异常，如果按照图中红色的部分，
对不同服务的纵向处理流程进行"横切"，在每个切面上完成通用的功能，例如身份认证、验证参数、处理异常等等、
这样就不用在每个服务中都写相同的逻辑了，这就是AOP思想解决的问题。

AOP以功能进行划分，对服务顺序执行流程中的不同位置进行横切，完成各服务共同需要实现的功能。



-----------
面试中经常问到的bean的生命周期
-----------
先看绿色的部分，bean的创建过程：
第1步：调用bean的构造方法创建bean；
第2步：通过反射调用setter方法进行属性的依赖注入；
第3步：如果实现BeanNameAware接口的话，会设置bean的name；
第4步：如果实现了BeanFactoryAware，会把bean factory设置给bean；
第5步：如果实现了ApplicationContextAware，会给bean设置ApplictionContext；
第6步：如果实现了BeanPostProcessor接口，则执行前置处理方法；
第7步：实现了InitializingBean接口的话，执行afterPropertiesSet方法；
第8步：执行自定义的init方法；
第9步：执行BeanPostProcessor接口的后置处理方法。
这时，就完成了bean的创建过程。

在使用完bean需要销毁时，
会先执行DisposableBean接口的destroy方法，
然后在执行自定义的destroy方法。

这部分也建议阅读源码加深理解。












什么是控制反转（IOC）？什么是依赖注入？


请解释下Spring中的IOC？

BeanFactory和ApplicationContext有什么区别？
将Spring配置到你的应用中共有几种方法？

什么基于XML的配置？
什么基Java的配置？
怎样用注解的方式配置Spring？
描述Spring Bean的生命周期？
描述Spring中各种Bean的范围？
什么是Spring的嵌入beans？
Spring框架中的单例bean是否是线程安全的？
请举例说明如何用Spring注入一个Java的集合类？
请举例说明如何在Spring的Bean中注入一个java.util.Properties？
请解释Spring的Bean的自动生成原理？
请辨析自动生成Bean之间模块的区别？

如何开启基于基于注解的自动写入？
    请举例说明@Required注解？
    请举例说明@Autowired注解？
    请举例说明@Qualifier注解？
    
请说明构造器注入和setter方法注入之间的区别？
Spring框架中不同类型event有什么区别？
FileSystemResource和ClassPathResource有何区别？
请列举Spring框架中用了哪些设计模式？









Spring Cloud 微服务
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------
微服务概述, 什么是微服务
--------------------

微服务架构是一种结构模式/风格, 它
提倡"将单一应用程序划分/拆分成一组小的服务", 每个服务 运行在其独立的自己的"进程(不是线程)"中.

服务之间 互相调用 互相配合, 服务之间 采用"轻量级的通信机制" (通常是基于Http的"RESTful API") 互相沟通.
服务 有自己独立的 数据库等



-----------------
微服务技术栈
-----------------
天上飞的理念 必然有落地的实现


"微服务条目"								"落地技术"							"备注"

服务开发									SpringBoot Spring SpringMVC
服务的配置与管理							Netflix公司的archaius, 阿里的Diamond
服务注册与发现								Eureka, Zookeeper, Consul
服务调用									Rest, RPC, gRPC
服务熔断器								Hystrix, Envoy
负载均衡									Ribbon, Nginx
服务接口调用（客户端调用服务的简化工具）		Fegin
消息队列									Kafka, RabbitMQ, ActiveMQ
服务配置中心管理							SpringCloudConfig, Chef
服务路由 即API网关							Zuul
服务监控									Zabbix, Nagios, Metrics, Spectator
全链路追踪								ZipKin, Brave, Dapper
服务部署									Docker, OpenStack. Kubernetes
数据流操作开发包							SpringCloudStream （封装于Redis, Rabbit, Kafka等发送接收消息）
事件消息总栈								SpringCloud Bus



---------------- 
SpringCloud是什么
SpringBoot 和 SpringCloud, 请谈谈你对他们的理解
---------------------------------------------

官网:

your App	----->  SpringBoot	----->  SpringCloud	----->  SpringCloud Data Flow
					构建					协调 				连接
					Build 			    Coordinate 			Connect
					Anything			Anything			Anything


SpringCloud 基于SpringBoot 提供了 "一整套微服务的解决方案" 
包括 服务注册与发现, 配置中心, 全链路监控, 服务网关, 负载均衡, 熔断器等组件
= 微服务架构落地技术的集合体, 俗称 微服务全家桶

SpringBoot专注于开发单个个体微服务
SpringCloud将 SpringBoot开发的一个个单体的 微服务合并管理起来 为各个服务之间提供 ...... 等集成服务

......包括 配置管理 服务发现 断路器 路由 微代理 事件总线 全局锁 决策竞选 分布式会话等 









-------------------------------
SpringCloud和Dubbo 有哪些区别
-------------------------------
本质区别:
Dubbo 基于 RPC 远程过程调用
SpringCloud 是基于 RESTful API 调用


REST 相比 RPC 更加灵活, 服务提供方和调用方的依赖只靠一纸契约 不存在代码级别的强依赖 这在强调快速演化的微服务环境下 显得更加合适




对比 				Dubbo 			SpringCloud
服务注册中心		 Zookeeper 			SpringCloud Netflix Eureka
服务调用方式		 RPC 				Rest API
服务监控 			Dubbo-monitor		SpringBoot Admin
断路器			不完善				SpringCloud Netflix Hystrix
服务网关			无					SpringCloud Netflix Zuul
分布式配置		无					SpringCloud Config
服务跟踪			无					SpringCloud Sleuth
消息总线			无					SpringCloud Bus
数据流			无					SpringCloud Stream
批量任务			无					SpringCloud Task














-----------------
什么是 服务熔断 什么是服务降级
-----------------




-----------------------------------------------------------
eureka 和 zookeeper 都可以提供服务注册与发现的功能 请说说两个的区别
----------------------------------------------------------- 





-----------------
Cloud技术的五大神兽
-----------------







-----------------
Rest 微服务构建案例工程模块
-----------------



-----------------
Eureka服务注册与发现
-----------------


-----------------
Ribbon 负载均衡
-----------------



-----------------
Fegin 负载均衡
-----------------




-----------------
Hystrix 断路器
-----------------






-----------------
Zuul路由网关
-----------------








