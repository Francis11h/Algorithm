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
2. "权限判断"
3. 日志


https://www.zhihu.com/question/48427693/answer/691483076

AOP就是面向切面编程。如右面的图，一般程序执行流程是从controller层调用service层、然后service层调用DAO层访问数据，最后在逐层返回结果。
这个是图中向下箭头所示的按程序执行顺序的纵向处理。
但是，一个系统中会有多个不同的服务，例如用户服务、商品信息服务等等，
每个服务的controller层都需要验证参数，都需要处理异常，如果按照图中红色的部分，
对不同服务的纵向处理流程进行"横切"，在每个切面上完成通用的功能，例如身份认证、验证参数、处理异常等等、
这样就不用在每个服务中都写相同的逻辑了，这就是AOP思想解决的问题。

AOP以功能进行划分，对服务顺序执行流程中的不同位置进行横切，完成各服务共同需要实现的功能。



--------------------------
面试中经常问到的bean的生命周期
--------------------------

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




---------------------------------
简单说一下spring ioc容器初始化的过程
---------------------------------
1.定位并加载配置文件
2.解析配置文件中的bean节点，一个bean节点对应一个BeanDefinition对象（这个对象会保存我们在Bean节点内配置的所有内容，比如id，全限定类名，依赖值等等）
3.根据上一步的BeanDefinition集合生成（BeanDefinition对象内包含生成这个对象所需要的所有参数）所有非懒加载的单例对象，其余的会在使用的时候再实例化对应的对象。
4.依赖注入
5.后置处理


-----大白话理解

spring是一个ioc容器，容器就是放数据的，java里面的容器就是集合类。
ioc容器实际上就是个map（key，value），里面存的是
各种对象（key = 在xml里配置的bean节点, value = repository、service、controller、component）
在项目启动的时候会读取配置文件里面的bean节点，根据全限定类名"使用反射new对象"放到map里；
扫描到打上上述注解的类还是通过反射new对象放到map里。

这个时候map里就有各种对象了，接下来我们在代码里需要用到里面的对象时，再通过DI注入
（"@autowired"、resource等注解，xml里bean节点内的ref属性，项目启动的时候会读取xml节点ref属性根据id注入，也会扫描这些注解，根据类型或id注入；id就是对象名）。


至于为什么要这么做，要知道工厂模式（控制反转）解决了什么问题又带来了什么问题。

1.解决的问题：解决了类与类之间 强引用 关系的问题，强引用关系导致了代码的耦合性高，维护性差。
（控制反转的作用，举个例子，现在有个变量String name=“小明”，在两个类里都要用到小明这个字符串，
	如果不使用控制反转，在class A中要定义一个String name=“小明”；在class B中也要定义一个String name=“小明”；
	这个时候我需要把小明改成李四，那我需要找到所有用到“小明”的类然后一个一个去修改。
	实际上我们是定义一个静态常量，然后在需要用到的地方进行引用，
	这样的话当我们需要把小明修改成李四只需要去定义这个静态常量的那个类里去修改一次，所有引用到的地方都被修改了。
	这其实就是 控制反转，"定义静态常量的类就是工厂类"，"被定义的常量就是ioc容器内的各种对象"）

2.带来的问题：导致工厂类的代码冗长，每增加一个接口都要在工厂类里加一段代码。

3.为了解决工厂模式带来的这个问题，spring通过配置化的方式来解决，也就是上面说的xml配置文件（通过遍历bean节点的方式new对象）
 
4.随着时间的发展，程序员们又觉得使用xml配置文件很麻烦，因为项目大了之后，各种配置文件眼花缭乱，就开始了注解之路了，于是这个时候SpringBoot出现了......










CDN、异步消息



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
服务注册与发现								"Eureka", Zookeeper, Consul
服务调用									Rest, RPC, gRPC
服务熔断器								Hystrix, Envoy
负载均衡									"Ribbon", Nginx
服务接口调用（客户端调用服务的简化工具）		"Fegin"
消息队列									Kafka, "RabbitMQ", ActiveMQ
服务配置中心管理							SpringCloudConfig, Chef
服务路由 即API网关							"Zuul"
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


REST 相比 RPC 更加灵活, 服务提供方和调用方的依赖只靠一纸契约 不存在代码级别的强依赖 
这在强调"快速演化"的微服务环境下 显得更加合适

但是RPC的"效率高"，所以建议在多系统之间的内部调用采用RPC (因为 RPC 用 TCP/IP 而 RESTful 用 HTTP 而HTTP建立在TCP/IP之上 所以会慢)
	但是 在现今rpc不是一门值得提倡的技术
	因为 一旦你到了代码级这个高度了，也就被某一种技术，一种语言绑死了，并且rpc极力试图将远程调用包装成本地调用在下认为这种设计思想本身就是错误的，网络调用的情况比本地调用要复杂的多，并且网络调用本身应该也是无状态，幂等性的



对比 				Dubbo 			SpringCloud
服务注册中心		 Zookeeper 			SpringCloud Netflix "Eureka"
服务调用方式		 RPC 				Rest API
服务监控 			Dubbo-monitor		SpringBoot Admin
断路器			不完善				SpringCloud Netflix Hystrix
服务网关			无					SpringCloud Netflix Zuul
分布式配置		无					SpringCloud Config
服务跟踪			无					SpringCloud Sleuth
消息总线			无					SpringCloud Bus
数据流			无					SpringCloud Stream
批量任务			无					SpringCloud Task

------------ 
RESTful API
------------ 

"一句话: URL定位资源，用HTTP动词（GET,POST,DELETE,DETC）描述操作"


Representational State Transfer（表象层状态转变）


1.每一个URL(统一资源定位符)代表一种资源
2.客户端和服务器之间,传递这种资源的某种表现层；
3.客户端通过四个HTTP动词（get、post、put、delete）, 对服务器端资源进行操作, 实现”表现层状态转化”

RESTful 6大原则
1. C-S 架构 
	数据存储在 Server端, Client端 只需使用就行. 	
	两端彻底分离的好处是 使 Client端 可移植性变强 Server端的拓展性变强. 两端单独开发 互不干扰.

2. 无状态 (人话说 就是 第二次来你无法识别它曾经来过)
	http请求本身就是无状态的，基于C-S架构，客户端的每一次请求带有充分的信息能够让服务端识别

3. "统一的接口"
	REST架构的"核心", 统一的接口对于RESTful服务非常重要。客户端只需要关注实现接口就可以，接口的可读性加强，使用人员方便调用

4. 一致的数据格式
	服务端返回的数据格式要么是XML，要么是Json（获取数据），或者直接返回状态码
	用 HTTP Status Code传递Server的状态信息	200 表示成功, 500 表示Server内部错误

5. 系统分层 Layer
	客户端通常无法表明自己是直接还是间接与端服务器进行连接，分层时同样要考虑安全策略。

6. 可缓存 Cache
	客户端可以缓存页面的响应内容





-------------------------------------
RPC 就是要"像调用本地的函数一样去调远程函数"
-------------------------------------

调本地函数: 变量的值 入栈出栈操作
1 int Multiply(int l, int r) {
2    int y = l * r;
3    return y;
4 }
5 
6 int lvalue = 10;
7 int rvalue = 20;
8 int l_times_r = Multiply(lvalue, rvalue);


远程调用时 我们要执行的函数体是在 另一台机器上的 即 Multiply 在另一个进程中
这就带来了几个新问题
1. Call ID 映射
	即 怎么告诉 远程机器 我们要调用 Multiply函数 而不是 Add / Minus
	本机的话 函数体是直接通过"函数指针"来指定的 ---> 调用Multiply ---> "编译器"就自动帮我们调用它相应的函数指针
	远程的话 两个进程的 地址空间完全不一样 所以 在RPC 中 所有的 函数 都得维护一个 ID, 且这个ID在所有进程中都是唯一确定的
	客户端在做远程过程调用时，必须附上这个ID。
	然后我们还需要在客户端client和服务端server"分别"维护一个 {函数 <--> Call ID} 的对应表。
	两者的表不一定需要完全相同，但"相同的函数对应的Call ID必须相同"。
	当客户端需要进行远程调用时，它就查一下这个表，找出相应的Call ID，然后把它传给服务端，服务端也通过查表，来确定客户端需要调用的函数，然后执行相应函数的代码。

2. 序列化和反序列化
	客户端怎么把参数值传给远程的函数呢？
	在本地调用中, 我们只需要把参数压到栈里,然后让函数自己去栈里读就行。
	但是在远程过程调用时，客户端跟服务端是不同的进程，"不能通过内存来传递参数"
	甚至有时候客户端和服务端使用的都不是同一种语言
	这时候就需要客户端把参数先转成一个"字节流 byte Stream"，传给服务端后，再把字节流转成自己能读取的格式。
	这个过程叫序列化和反序列化。同理，从服务端返回的值也需要序列化反序列化的过程。

3. 网络传输
	尽管大部分RPC框架都使用TCP协议，但其实UDP也可以,而gRPC干脆就用了HTTP2。
	Java的Netty也属于这层的东西 即 Netty只是网络通信框架，把Java Socket的API又封装了一次，使得你可以用最少的代码来完成网络通信这一任务


有了这三个机制，就能实现RPC了，具体过程如下:
// Client端 
// int l_times_r = Call(ServerAddr, Multiply, lvalue, rvalue)
1. 将这个调用映射为Call ID。这里假设用最简单的字符串当Call ID的方法
2. 将Call ID，lvalue和rvalue序列化。可以直接将它们的值以二进制形式打包
3. 把2中得到的数据包发送给ServerAddr，这需要使用网络传输层
4. 等待服务器返回结果
5. 如果服务器调用成功，那么就将结果反序列化，并赋给l_times_r

// Server端
1. 在本地维护一个Call ID到函数指针的映射call_id_map，可以用std::map<std::string, std::function<>>
2. 等待请求
3. 得到一个请求后，将其数据包反序列化，得到Call ID
4. 通过在call_id_map中查找，得到相应的函数指针
5. 将lvalue和rvalue反序列化后，在本地调用Multiply函数，得到结果
6. 将结果序列化后通过网络返回给Client

 















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

Eureka 属于 客户端发现



-----------------
Ribbon 负载均衡
-----------------
是 SpringCloud 中 "客户端"负载均衡器
RestTemplate, Feign, Zuul 都使用到了 ribbon
ribbon 实现软负载均衡 核心有三点

1. 服务发现   	发现依赖服务的列表  即 依据服务的名字 把该服务下 所有的实例都找出来
2. 服务选择规则   如何从多个服务中选择一个有效的
3. 服务监听      即  检测失效的服务 做到高效剔除

首先通过 ServerList 来获取所有的可用服务列表
然后 通过 ServerListFilter 过滤掉一部分地址
最后 通过 IRule 选择出一个实例

@LoadBalanced 这个注解


-----------------
Fegin 负载均衡
-----------------




-----------------
Hystrix 断路器
-----------------






-----------------
Zuul路由网关
-----------------








