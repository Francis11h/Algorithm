彻底理解  cookie session 

一句话: cookie是client端装sessionID的容器, session存在server端当key来match用户登陆等状态

------------------------------------------------
先理解 无状态 (人话说 就是 第二次来你无法识别它曾经来过)
------------------------------------------------
	http请求本身就是无状态的，基于C-S架构，客户端的每一次请求带有充分的信息能够让服务端识别

	HTTP协议最初的目的是用它发布和接收静态的HTML页面，浏览器请求一个网页，服务器就把这个网页传给它，之后就可以断开连接了。
	在1991年发布的HTTP 0.9里甚至连POST都没有，因为设计者根本就没考虑浏览器还要向服务器提交那么多信息。

	同样的，HTTP在设计之初就没有考虑过要保存会话状态。
	无论你之前访问过什么网页、提交过什么数据，你获得的网页都是一样的；
	不同的浏览器访问同一个网页所得到的结果也是完全一样的，本来我就是静态网页嘛。
	这就是所谓的「无状态」——访问URI得到的结果与上下文操作无关、与用户无关。

---------------------------------------------------------------------------
然而Web实在太成功了，虽然HTTP无状态的特性使它并不适合用来开发应用程序，却偏偏有人动歪脑筋，生生地往网页里塞标签或者用其他类似的技术让服务器动态生成网页。

但是，应用程序是上下文相关的，你在登录页里输入了uid和pwd登录成功了，在另一个页面中怎么知道你登录成功了呢？
这个时候就需要"保存「状态」"，在这个例子里就是你的 登录状态——是否登录成功了、谁登录成功了、什么时候登录成功的。

这些「状态」可以保存在浏览器上，1993年发明的"cookie" 使得HTTP服务器可以通过特殊的指令"在浏览器的硬盘上"保存一些数据，然后浏览器在每次访问HTTP服务器的时候把这些数据全部提交给服务器.
这种方式显而易见会带来问题：
	1.每次页面请求都会把cookies往服务器上送一次，浪费了带宽；
	2.状态数据存放在浏览器上确实是太危险了，一定会有人想办法把浏览器里的状态信息改掉来欺骗服务器，而且Cookies在HTTP请求中是以明文传输的，除非你用HTTPS。 
	3.Cookie的大小限制在4KB, 对于复杂的状态数据显然不够用。

所以，把所有状态数据都保存在浏览器端是不靠谱的，主流的做法是：
	1.浏览器向HTTP服务器发出"第一个请求时"，服务器"分配一个SessionID", 把这个 SessionID "存入浏览器的Cookies中"。
		这个SessionID一般是一个类似于GUID的字符串，几十个字节，也不多。
	2. 服务器上维持一个"会话状态数据库"（也可能是个内存表），用SessionID作为标识存放每一个会话的状态信息。
	3. 每一次浏览器发出请求时，都将这个SessionID向服务器提交一次，便于服务器根据它获得之前的会话数据，写入新的会话数据。





session 存在哪里？

1. 存在cookie 里 但是 不安全 
2. 所以 存在 文件服务器 or 数据库里 (因为session是可以序列化的)
3. session复制 但是会冗余 每个节点都有一分儿
4. 最优解: "缓存数据库" 不存在文件里 而是 完全存在内存之中 速度快 数据结构简单






--------
token
--------

很久以前 web基本上是 文档的浏览 不用记录是谁浏览的 
        每一个请求都是 新的http协议

随着 交互式web应用的兴起 比如在线购物网站 需要登录 
    我们就需要管理会话 必须记住是那些人登陆的系统 哪些人往自己购物车里放物品
    因为http请求是 无状态的 所以 相处的办法就是 给大家发一个 会话标识 (session id) 说白了就是个 随机字符串
    但是 每个人的字符串都不一样每次发http请求的时候 把这个字符串一并稍过来 就能区分谁是谁了


这样子 我们很 happy 但是 服务器不 happy  它要保存 几十万个sessionid 这严重限制了服务器的扩展能力
    比如 我们用两个机器组成了一个集群 小明通过机器A登陆了系统 那么sessionid 会保留在机器A上
    然后 小明的下一次请求被转发到机器B 而B没有存小明的id 这会儿怎么办？

    所以 就出现了新的问题 我们服务器为什么要存 session？
    不如让每个客户端自己存  但是 怎么验证客户端发给我的 session id 的确是我生成的呢？


小F已经登录了系统， 我给他发一个令牌(token)， 里边包含了小F的 user id， 
下一次小F 再次通过Http 请求访问我的时候， 把这个token 通过Http header 带过来不就可以了。

这里涉及加密问题


我就不保存session id 了， 我只是生成token , 然后验证token ， 我用我的CPU计算时间获取了我的session 存储空间 ！






Cookie


Cookies是一些存储在用户电脑上的小文件。
它是被设计用来保存一些站点的用户数据，这样能够让服务器为这样的用户定制内容，后者页面代码能够获取到Cookie值然后发送给服务器。
比如Cookie中存储了所在地理位置，以后每次进入地图就默认定位到改地点即可。




--------------------
Session的具体实现
--------------------
Session对应的类为javax.servlet.http.HttpSession类

HttpSession session = request.getSession();       // 获取Session对象
session.setAttribute("loginTime", new Date());     // 设置Session中的属性
 
out.println("登录时间为：" +(Date)session.getAttribute("loginTime"));      // 获取Session属性

// void setAttribute(String attribute, Object value)：设置Session属性。value参数可以为任何Java Object。通常为Java Bean。value信息不宜过大 
// String getAttribute(String attribute)：返回Session属性 Enumeration 
// getAttributeNames()：返回Session中存在的属性名 
// void removeAttribute(String attribute)：移除Session属性 
// String getId()：返回Session的ID。该ID由服务器自动创建，不会重复 
// long getCreationTime()：返回Session的创建日期。返回类型为long，常被转化为Date类型，例如：Date createTime = new Date(session.get CreationTime()) 
// long getLastAccessedTime()：返回Session的最后活跃时间。返回类型为long 
// int getMaxInactiveInterval()：返回Session的超时时间。单位为秒。超过该时间没有访问，服务器认为该Session失效 void 
// setMaxInactiveInterval(int second)：设置Session的超时时间。单位为秒 
// void putValue(String attribute, Object value)：不推荐的方法。已经被setAttribute(String attribute, Object Value)替代 
// Object getValue(String attribute)：不被推荐的方法。已经被getAttribute(String attr)替代 
// boolean isNew()：返回该Session是否是新创建的 
// void invalidate()：使该Session失效










