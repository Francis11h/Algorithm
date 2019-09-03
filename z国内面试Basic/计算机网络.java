
计算机网络

1. 电路交换与分组交换的区别？ 优劣对比。

    packet switching allows More users to use network!

2. OSI有哪几层，会画出来，知道主要几层的各自作用。

    Application             HTTP FTP
        Presentation        encryption, compression  
        Session             synchronization checkpointing
    Transport               TCP UDP
    Network                 IP , routing protocols
    Link                    ethernet
    Physical                bits 'on the wire'

    layers make calls 每一层可以使用下层提供的服务并且向上层提供服务




3. TCP/IP有哪几层，会画出来，知道所有层数的作用，会列举各层主要的协议名称。
4. 硬件(MAC)地址的概念及作用。
5. ARP协议的用途 及算法、在哪一层上会使用arp？
6. CRC冗余校验算法，反码和检验算法。
7. 如何实现透明传输。
8. 知道各个层使用的是哪个数据交换设备。（交换机、路由器、网关）
9.  路由表的内容。
10. 分组转发算法。
11.IP报文的格式，格式的各个字段的含义要理解。
12. MTU的概念，啥叫路径MTU？MTU发现机制，TraceRoute(了解)。
13. RIP协议的概念 及算法。
14. ICMP协议的主要功能。
15. 组播和多播的概念，IGMP的用途。
16. Ping协议的实现原理，ping命令格式。
17.子网划分的概念，子网掩码。
18.IP地址的分类，如何划分的，及会计算各类地址支持的主机数。
19. DNS的概念，用途，DNS查询的实现算法。
    


20.TCP与UDP的概念，相互的区别及优劣。
21. UDP报文的格式，字段的意义。
22.TCP报文的格式，字段的意义。
23. TCP通过哪些措施，保证传输可靠？
24.三次握手，四次断开过程。
25.TIME_WAIT状态的概念及意义。
26. 滑动窗口协议 与 停止等待协议的区别。
27.TCP的流量控制和拥塞控制实现原理(会画拥塞控制的典型图)。
28. TCP的快速重传与快速恢复算法。
29. TFTP与FTP的区别。
30. 阻塞方式和非阻塞方式，阻塞connect与非阻塞connect。(比较难，有兴趣可以了解)
31.HTTP基本格式。

Cookie Session 区别
    session 在服务器端，cookie 在客户端（浏览器）
    session 默认被存在在服务器的一个文件里（不是内存）
    session 的运行依赖 session id，而 session id 是存在 cookie 中的，也就是说，如果浏览器禁用了 cookie ，同时 session 也会失效（但是可以通过其它方式实现，比如在 url 中传递 session_id）
    session 可以放在 文件、数据库、或内存中都可以。
    用户验证这种场合一般会用 session 因此，维持一个会话的核心就是客户端的唯一标识，即 session id

    Session是在服务端保存的一个数据结构，用来跟踪用户的状态，这个数据可以保存在集群、数据库、文件中；
    Cookie是客户端保存用户信息的一种机制，用来记录用户的一些信息，也是实现Session的一种方式。
 



从输入URL到页面加载发生了什么        https://segmentfault.com/a/1190000006879700
    DNS解析

    TCP连接

    发送HTTP请求

    服务器处理请求并返回HTTP报文

    浏览器解析渲染页面

    连接结束

1.DNS解析 ： 
    寻找哪台机器上有你需要资源的过程。当你在浏览器中输入一个地址时，例如 www.google.com，其实不是百度网站真正意义上的地址。
    互联网上每一台计算机的唯一标识是它的IP地址，但是IP地址并不方便记忆。用户更喜欢用方便记忆的网址去寻找互联网上的其它计算机，也就是上面提到的百度的网址。
    所以互联网设计者需要在 用户的 方便性与可用性 做一个权衡，这个权衡就是网址到IP地址的转换，这个过程就是DNS解析。
    它实际上充当了一个翻译的角色，实现了网址到IP地址的转换。

    这个解析是分级的 先从本地域名服务器 到 根域名服务器 再到 COM顶级域名服务器 再到具体的 google.com 域名服务器, 最后返回本地域名服务器

    同时有个 DNS高速缓存, 把 www.google.com 的ip记录到高速缓存一阵子，这样子避免了再找一大圈子。
                浏览器缓存，系统缓存，路由器缓存，IPS服务器缓存，根域名服务器缓存，顶级域名服务器缓存，主域名服务器缓存

    DNS 负载均衡
        其实真实的互联网世界背后存在成千上百台服务器，大型的网站甚至更多。但是在用户的眼中，它需要的只是处理他的请求，哪台机器处理请求并不重要。
        DNS可以返回一个合适的机器的IP给用户，例如可以根据每台机器的负载量，该机器离用户地理位置的距离等等，这种过程就是DNS负载均衡


2.TCP连接
    HTTP协议是使用TCP作为其传输层协议的

    HTTPS协议
        HTTP报文是包裹在TCP报文中发送的，服务器端收到TCP报文时会解包提取出HTTP报文。
        但是这个过程中存在一定的风险，HTTP报文是明文，如果中间被截取的话会存在一些信息泄露的风险。
        那么在进入TCP报文之前对HTTP做一次加密就可以解决这个问题了。HTTPS协议的本质就是HTTP + SSL(or TLS)。
        在HTTP报文进入TCP报文之前，先使用 SSL 对HTTP报文进行加密。(加密过程处在TCP 和 HTTP之间)

    HTTPS过程
        HTTPS在传输数据之前需要客户端与服务器进行一个握手(TLS/SSL握手)，在握手过程中将确立双方加密传输数据的密码信息。
        TLS/SSL使用了非对称加密，对称加密以及hash等。


        对称加密 非对称加密
            对称 : 在加密和解密时使用相同的密钥，或是使用两个可以简单地相互推算的密钥 (DES)
            非对称 :
            需要一对密钥, 一个是私人密钥, 另一个则是公开密钥.
            私钥只能由一方安全保管，不能外泄，而公钥则可以发给任何请求它的人。
            非对称加密使用这对密钥中的一个进行加密，而解密则需要另一个密钥。
            比如，你向银行请求公钥，银行将公钥发给你，你使用公钥对消息加密，那么只有私钥的持有人--银行才能对你的消息解密。
            与对称加密不同的是，银行不需要将私钥通过网络发送出去，因此安全性大大提高.

        SSL(Secure Sockets Layer 安全套接层),及其继任者传输层安全（Transport Layer Security，TLS）是为网络通信提供安全及数据完整性的一种安全协议。
        HTTPS相比于HTTP，虽然提供了安全保证，但是势必会带来一些时间上的损耗，如握手和加密等过程，是否使用HTTPS需要根据具体情况在安全和性能方面做出权衡。



3.发送HTTP请求

    它主要发生在客户端。发送HTTP请求的过程就是构建HTTP请求报文并通过TCP协议中发送到服务器指定端口(HTTP协议80/8080, HTTPS协议443)。
    HTTP请求报文是由三部分组成: 请求行, 请求报头和请求正文。

    请求行 ：Method Request-URL HTTP-Version CRLF
           eg: GET index.html HTTP/1.1
           常用的Method有: GET, POST, PUT, DELETE, OPTIONS, HEAD。
    请求报头 ：
            请求报头允许客户端向服务器传递请求的附加信息和客户端自身的信息。
            常见的请求报头有: Accept, Accept-Charset, Accept-Encoding, Accept-Language, Content-Type, Authorization, Cookie, User-Agent等。
    请求正文 ： 
            当使用POST, PUT等方法时，通常需要客户端向服务器传递数据。
            现在的Web应用通常采用Rest架构，请求的数据格式一般为json。这时就需要设置Content-Type: application/json。

4.服务器处理请求并返回HTTP报文
    HTTP响应报文也是由三部分组成: 状态码, 响应报头和响应报文。
    状态码
        状态码是由3位数组成，第一个数字定义了响应的类别，且有五种可能取值:

        1xx：指示信息–表示请求已接收，继续处理。

        2xx：成功–表示请求已被成功接收、理解、接受。

        3xx：重定向–要完成请求必须进行更进一步的操作。

        4xx：客户端错误–请求有语法错误或请求无法实现。

        5xx：服务器端错误–服务器未能实现合法的请求。

    响应报头 ：  常见的响应报头字段有: Server, Connection...。
            
    响应报文
        服务器返回给浏览器的文本信息，通常HTML, CSS, JS, 图片等文件就放在这一部分。


5. 浏览器解析渲染页面    显示出来




Http GET 与 POST 的区别

    GET把参数包含在URL中, POST通过 request body 传递参数.
    GET产生一个TCP数据包, 浏览器会把http header和data一并发送出去, 
    POST产生两个TCP数据包, 浏览器先发送header，服务器响应100 continue，浏览器再发送data，服务器响应200 ok（返回数据).


