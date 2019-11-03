整体描述 Overall 

Its a full-stack webApp, which is an ordering system.
The backend and frontend are isloated by the Nginx 前端 and Tomcat 后端.    

前后端分离的核心思想
the Html pages of the front-end call the Restful-api developed by the back-end using ajax, 
and communication through Json


The back-end was developed by SpringBoot 
The web page of the back-end was builted by using the combination of Bootstrap + Freemarker + Jquery.
And the DB is based on SpringBoot JPA and a few of MyBatis.
The caching involves Redis‘s caching mechanism (Distributed-Session, Distributed-Lock)
Also use Websocket for message push 

And it is a WeChat based take-out service system, so the technology that related with weChat platform is necessary,
such as Scan code login + Template message push + WeChat Payment and Refund.




环境 
virtualBox and Linux centos 7.3




DB 设计

product_category 

product_info

order_detail

order_table




完全是按照 工业界的要求来设计的 webApp
学习了一套 工业界设计的模式 与 结构 
SpringBoot 架构 Entity Repository Service ServiceImpl Controller

充分理解了 面向接口编程 

接口继承接口 实现对方法的扩展


RESTful风格的接口返回的使用


project的难点 

orderServiceImplementation





　　数据组装成前端需要的样子（VO）：类中嵌套各种数据结构

　　数据转换成前端需要的样子（标签）：date->long并且去掉最后三位进行加工，标签使用

　　如果从前台接收的数据与后台数据不能保持一致，可以设计DTO包（数据传输对象（DTO)(Data Transfer Object)）





