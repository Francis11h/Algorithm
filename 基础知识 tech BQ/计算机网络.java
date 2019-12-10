
计算机网络

-----------------------------------------------------------------------------
从输入URL到页面加载发生了什么        https://segmentfault.com/a/1190000006879700
                                 https://medium.com/@maneesha.wijesinghe1/what-happens-when-you-type-an-url-in-the-browser-and-press-enter-bb0aa2449c1a
    DNS解析(先找缓存)

    TCP连接

    发送HTTP请求

    服务器处理请求并返回HTTP报文

    浏览器解析渲染页面

    连接结束

    1.DNS解析 : 
        1.1 先查缓存Cache里有没有对应的
        DNS有高速缓存, 把 www.google.com 的ip记录到高速缓存一阵子，这样子避免了再找一大圈子。
                    浏览器缓存browser，系统缓存os，路由器缓存router，IPS服务器缓存Isp

        1.2             
        ISP 缓存中都没有 则由 ISP‘s DNS server initiates a DNS Query
        这个查找是分级的 
            先从本地域名服务器 Local-DNS server 到 
            根域名服务器 Root-DNS server 再到 
            顶级域名服务器 TLD(top-level domain)-DNS server 
            再到具体的 google.com 域名服务器 Authoritative-DNS server, 
            最后返回本地域名服务器

        

        DNS 负载均衡
            其实真实的互联网世界背后存在成千上百台服务器，大型的网站甚至更多。但是在用户的眼中，它需要的只是处理他的请求，哪台机器处理请求并不重要。
            DNS可以返回一个合适的机器的IP给用户，例如可以根据每台机器的负载量，该机器离用户地理位置的距离等等，这种过程就是DNS负载均衡


    2. browser 请求建立TCP连接 : (三次握手)

        client --------- SYN ------> server
        client <------ACK + SYN ---- server
        client --------- ACK ------> server

        HTTP协议是使用TCP作为其传输层协议的

    3. browser 发送HTTP请求

        发送HTTP请求的过程就是构建HTTP请求报文并通过TCP协议中发送到服务器指定端口 Port (HTTP协议80/8080, HTTPS协议443)。
        HTTP请求报文是由三部分组成: 请求行, 请求报头和请求正文。

        请求行 ：Method Request-URL HTTP-Version CRLF
               eg: GET index.html HTTP/1.1
               常用的Method有: GET, POST, PUT, DELETE, OPTIONS, HEAD。
        请求报头 ：
                请求报头允许客户端向服务器传递请求的附加信息和客户端自身的信息。
                常见的请求报头有: Accept, Accept-Charset, Accept-Encoding, Accept-Language, Content-Type, Authorization, Cookie, User-Agent等。
                    browser identification (User-Agent header)
                    types of requests that it will accept (Accept header)
                    connection headers asking it to keep the TCP connection alive for additional requests (Connection : Keep-Alive)
        请求正文 ： 
                当使用POST, PUT等方法时，通常需要客户端向服务器传递数据。
                现在的Web应用通常采用Rest架构，请求的数据格式一般为json。这时就需要设置Content-Type: application/json。

    4.服务器处理请求并返回HTTP报文
        HTTP响应报文也是由三部分组成: 状态码, 响应报头和响应报文。
        状态码
            状态码是由3位数组成，第一个数字定义了响应的类别，且有五种可能取值:

            1xx：指示信息–表示请求已接收，继续处理。

            2xx：成功–表示请求已被成功接收、理解、接受。
                200 Ok  request succeeded,requested object later in this msg

            3xx：重定向–要完成请求必须进行更进一步的操作
                301 Moved Permanently

            4xx：客户端错误–请求(request)有 语法错误 或 请求无法实现。
                400 Bad Request request msg not understood by server
                403 Forbidden                                           //服务器收到请求，但是拒绝提供服务
                404 Not Found requested document not found on this server

            5xx：服务器端错误–服务器未能实现合法的请求。
                505 HTTP Version Not Supported

        响应报头 ：  常见的响应报头字段有: Server, Connection...。
                
        响应报文
            服务器返回给浏览器的文本信息，通常HTML, CSS, JS, 图片等文件就放在这一部分。


    5. 浏览器解析渲染页面    显示出来

        首先浏览器解析 Html 文件构建 Dom 树
        解析 Css 文件构建渲染树
        然后 浏览器开始布局渲染树并将其绘制到屏幕上 这个过程比较复杂 涉及到两个概念: reflow(回流)和repain(重绘)。
            DOM节点中的各个元素都是以盒 Box 模型的形式存在, 这些都需要浏览器去计算其位置和大小等, 这个过程称为 relow;
            当盒模型的位置, 大小以及其他属性，如颜色,字体,等确定下来之后，浏览器便开始绘制内容，这个过程称为 repain
        JS 。。。

    6. 连接结束:(4次挥手)


英文版解释:
    1. the browser check the Cache for a DNS record to find the cossesponding ip address.
        broswer cache ----> OS cache ---> router cache ---> ISP cache

    2. if the requested URL is not in the cache, ISP‘s DNS server initiates a DNS Query 
        to find the IP address of the server that hosts www.google.com.

    3. browser initiates a TCP connection with the server.

        TCP/IP three-way handshake

            3.1 client sends a SYN packet to the server asking if it is open for new connections.
            3.2 If the server has open ports that can accept and initiate new connections, 
                it will response with an ACK of the SYN packet using a SYN/ACK packet.
            3.3 the client will receive the SYN/ACK packet and will acknowledge it by sending an ACK packet.

    4. browser sends an HTTP request to the web server.
        GET / POST 

    5. server handles the request and sends back a HTTP response.

        There are five types of statuses
            5.1  1xx indicates an informational message only
            5.2  2xx indicates Success of some kind
            5.3  3xx Redirects the client to another URL
            5.4  4xx indicates an error on the Client’s part
            5.5  5xx indicates an error on the Server’s part

    6. the browser display the HTML content




--------------
三次握手 四次挥手 
--------------
client --------- SYN ------> server
client <------ACK + SYN ---- server
client --------- ACK ------> server


client --------- FIN ------> server
client <-------- ACK ------- server
client <-------- FIN ------- server
client --------- ACK ------> server

为什么建立连接是三次握手，关闭连接确是四次挥手呢？

建立连接的时候， 服务器在LISTEN状态下，
收到建立连接请求的SYN报文后，把ACK和SYN放在一个报文里发送给客户端。

而关闭连接时，服务器收到对方的FIN报文时，仅仅表示"对方不再发送数据了但是还能接收数据"
而自己也"未必全部数据都发送给对方了"，所以己方可以立即关闭，也可以发送一些数据给对方后，再发送FIN报文给对方来表示同意现在关闭连接，
因此，己方ACK和FIN一般都会分开发送，从而导致多了一次。


------------------------
TIME_WAIT这个状态 为什么必须
------------------------
    假设最终的ACK丢失，主机2将重发FIN，主机1必须维护TCP状态信息以便可以重发最终的ACK，否则会发送RST，结果主机2认为发生错误。
    TCP实现必须可靠地终止连接的两个方向(全双工关闭)，主机1必须进入 TIME_WAIT 状态，因为主机1可能面 临重发最终ACK的情形。


-------
Protocol
-------
protocols define Format, Order of messages sent and received among network entities, 
    and Actions taken on message transmission / receipt.

----------------
Circuit-switching
----------------
    Reserved 
    the transmit rate is know and it will last for a long period, 
    we can reserve a link for this, 
    which can guarantee the transmission quality.

----------------
Packet-switching
----------------
    packet switching allows More users to use network!

    packet-switching: hosts break application-layer messages into packets

    Store and Forward: Entire packet must arrive at router before it can be transmitted

    Queuing and Loss:
        if arrival rate to link exceeds transmission rate of link for a period of time,
            packets will queue, wait to be transmitted on link
            lost happened when the buffer is full



-----------
OSI layers
-----------

    Application             HTTP FTP                            //supporting network applications
        Presentation        encryption, compression             //allow applications to interpret meaning of data
        Session             synchronization checkpointing       //synchronization, checkpointing, recovery of data exchange
    Transport               TCP UDP                             //process-process data transfer
    Network                 IP , routing protocols              //routing of datagram from source to destination
    Link                    ethernet                            //data transfer between neighboring network elements
    Physical                bits 'on the wire'

    layers make calls 每一层可以使用下层提供的服务并且向上层提供服务

    layers: each layer implements a service via its own internal-layer actions 
                relying on services provided by layer below

    why layering?
        explicit structure allows identification, relationship of complex system’s pieces
        modularization eases maintenance, updating of system




----------------------------------
CS 架构 client-server architecture
----------------------------------

    Server : 1. always-on host 2. permanent IP address 3. data centers for scaling

    Client : 1.communicate with server and Do not communicate directly with each other!
             2.may have dynamic IP address


---------------
Client & Server
---------------

client : the process that Initiates the communication
server : the process theat Waits to be contacted to begin the session.

-------
Sockets
-------
    Process sends / receives messages to / from its socket.

    socket 就像是一扇门,从application层通往transport层
    socket analogous to door

    Socket编程接口在设计的时候，就希望也能适应其他的网络协议.
    所以,socket的出现只是可以更方便的使用 TCP/IP协议栈而已,其对TCP/IP进行了抽象,形成了几个最基本的函数接口
    比如 create，listen，accept，connect，read和write等等.



--------------------
Addressing processes
--------------------

identifer includes both IP address and Port.
just IP address is not suffice cause that many processes can be running on the same host.


-------------------
non-persistent HTTP
-------------------

non-persistent HTTP
	at most One object sent over TCP connection
	connection then closed
	downloading multiple objects required multiple connections 一次只下一个object

	non-persistent HTTP 
		response time = 2RTT+ file transmission time
		one RTT to initiate TCP connection
		one RTT for HTTP request and first few bytes of HTTP response to return.

persistent HTTP    
	multiple objects can be sent over Single TCP connection between client, server  


----------
HTTP基本格式
----------
            Http request
    client ---------------> server
           <---------------
           Http response

    request : method + URL + header lines + ...
                e.g. GET /index.html HTTP/1.1\r\n 
                	 + header lines.. 
                	 + entity body...


    response : 
                e.g. HTTP/1.1 200 OK\r\n + header lines.. + data data data
          


-------
Http GET 与 POST 的区别

    GET把参数包含在URL中, POST通过 request body 传递参数.

    GET : input is uploaded in URL field of request line
          请求会把请求的参数拼接在URL后面,以 ? 分隔,多个参数之间用 & 连接;
          (浏览器和服务器会限制URL的长度,所以传输的数据有限,
            而且账户密码明文显示会不安全)

    POST : 请求会把提交的数据放在请求体中，不会在URL中显示出来
          (一般可以传输较大量的数据)

-------
Cookie
-------
user-server state　
can be used for
	authorization
	shopping carts
	recommendations
	user session state (Web e-mail)

how to keep “state”:
	protocol endpoints: maintain state at sender/receiver over multiple transactions
	cookies: http messages carry state


-------------------
Cookie Session 区别
-------------------

    session 在服务器端，cookie 在客户端（浏览器）
    session 的运行依赖 session id，而 session id 是存在 cookie 中的，
    也就是说，如果浏览器禁用了 cookie ，同时 session 也会失效
    （但是可以通过其它方式实现，比如在 url 中传递 session_id）

    用户验证这种场合一般会用 session 因此，维持一个会话的核心就是客户端的唯一标识，即 session id.

    Session是 服务端server 保存的一个 数据结构 , 用来跟踪用户的状态，这个数据可以保存在集群、数据库、文件中.

    Cookie是 客户端client 保存 用户信息.
 

-------------------------
Web Caches = Proxy Server
-------------------------

goal : satisfy client request Without involving Origin server

browser sends all HTTP requests to cache
	object in cache: cache returns object
		else cache requests object from origin server, then returns object to client


why Web caching?
	Reduce response time for client request
	Reduce traffic on an institution‘s access link

	cause increasing access link speed is costly

	
----
SMTP
----
delivery/storage to receiver’s server

mail access protocol: retrieval from server : POP IMAP HTTP

SMTP uses persistent connections
	HTTP: each object encapsulated in its own response message
	SMTP: multiple objects sent in multipart message


----
DNS
----
DNS(Domain Name System) is a Database that maintains 
                    the name of the website (URL) and the particular IP address it links to.

hostname : 类比 人名 

DNS services :
	hostname to IP address translation		名字映射到id
	host aliasing
	mail server aliasing
	load distribution

----------
P2P
----------


BitTorrent
file divided into 256Kb chunks
peers in torrent send / receive file chunks



-------------
HTTP vs HTTPS
-------------

    HTTP报文是包裹在TCP报文中发送的，服务器端收到TCP报文时会解包提取出HTTP报文。
    但是这个过程中存在一定的风险，HTTP报文是明文，如果中间被截取的话会存在一些信息泄露的风险。
    那么在进入TCP报文之前对HTTP做一次加密就可以解决这个问题了。HTTPS协议的本质就是HTTP + SSL(or TLS)。
    在HTTP报文进入TCP报文之前，先使用 SSL 对HTTP报文进行加密。(加密过程处在TCP 和 HTTP之间)

HTTPS过程
    HTTPS在传输数据之前需要客户端与服务器进行一个握手(TLS/SSL握手), 在握手过程中将确立双方加密传输数据的密码信息。
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












应用层 及以上 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
传输层 及以下



--------------------------------
Transport services and protocols
--------------------------------
传输层为运行在不同主机上的"进程"之间提供了"逻辑通信", 而网络层则提供了主机之间的逻辑通信
传输层协议是在"端系统"中而不是在网络路由器中实现的

provide Logical Communication between app "processes" running on different Hosts

    send side: breaks app messages into Segments, passes to network layer
    rcv side: Reassembles segments into messages, passes to app layer

-----------------------------
Multiplexing, Demultiplexing
-----------------------------
多路复用 与 多路分解
即 将网络层所提供的 主机到主机交付服务 扩展到 为在主机上运行的应用程序所提供的 "进程到进程"交付服务。

    假定你正坐在计算机前下载Web页面，同时还在运行一个FTP会话。
    此时你就有2个网络应用进程在运行，即一个FTP进程和一个HTTP进程。
    当计算机的传输层从底层的网络层接收数据时，它需要将所接收到的数据定向到这2个进程中的一个.

接收主机中的传输层通过一个 Socket 来传递数据。由于在任何一个时刻接收主机上可能有多个Socket，所以每个Socket都必须有唯一的标识符。
在每个传输层报文段datagram中包含了两个端口号Port字段，在接收端，传输层检查这些字段Port并标识出接收Socket，然后将报文段定向到该Socket

可以将一个Socket理解成两小段内存空间: 1.发送缓存和2.接收缓存,这两段存储空间通过Socket的变量名来标识


从源主机的不同Socket中收集数据块，
    井为每个数据块封装上首部信息(在多路分解时使用)从而生成报文段，
        然后将报文段传递到网络层的工作称为多路复用(multiplexing)

将传输层报文段中的数据放置到正确的Socket的工作称为多路分解(demultiplexing)，
    确切地说，多路分解其实是多路分发，或者说是数据流的分解。
    数据交付到特定Socket的工作也就是将数据放到正确的存储位置的过程。



传输层多路复用的要求:
    1.Socket有唯一标识符
    2.每个报文段有特殊字段来指示该报文段所要交付的Socket。
    这些特殊字段最重要的是源端口号字段("source Port number" field) 和 目的端口号字段("destination port number" field)。

multiplexing at sender
    handle data from multiple sockets, add transport header to network layer

demultiplexing at receiver
    use header info to deliver received segments to correct socket
        host uses IP addresses & Port numbers to direct segment to appropriate socket

    ====>
    无连接的多路复用与多路分解
    ---------------------
    "UDP Socket" 是由一个包含 "目的IP地址 destination" 和 "目的端口号Port" 的二元组 来标识的

    因此，如果两个UDP报文段有不同的源IP地址或源端口号，但具有相同的目的IP地址和目的端口号，
        那么这两个报文段将通过"相同"的目的Socket定向到相同的目的进程。

    面向连接的多路复用与多路分解
    -----------------------
    "TCP Socket "是由一个四元组(源IP地址，源端口号，目的IP地址，目的端口号)来标识的

        server host may support many simultaneous TCP sockets: 
        服务器 要区分请求从哪里来, 所以需要源ip和源头port





---------------------
UDP segment structure
---------------------
UDP 只是做了传输协议能够做的最少工作。除了多路复用/多路分解功能及少量的差错检测外，它几乎没有对 IP 增加别的东西。
    UDP 从应用进程得到数据，附加上多路复用/多路分解服务所需的"源端口号"和"目的端口号"字段，及两个其他的小字段，然后将形成的报文段交给网络层。
    网络层将该传输层报文段封装到一个 "IP数据报"中，然后尽力而为地将此数据报交付给接收主机。
    如果该报文段到达接收主机，则 UDP 使用"目的端口号"来将报文段中的数据交付给正确的应用进程。

UDP 首部只有 4 个字段，源和目的端口号、长度和校验和，每个字段由两个字节组成。 
    1.通过目的端口号可以使目的主机将应用数据交给运行在目的端系统中的相应进程(即执行多路分解功能)。
    2.而在返回消息时使用源端口号。 
    3.接收主机使用校验和来检查报文段中是否存在差错。 
    4. 长度字段指明了包括首部在内的 UDP 报文段长度(以字节为单位)。

    src port # | dest port #
        length | checksum

         application data 
            (payload)

---------------------
TCP segment structure
---------------------

        src port # | dest port #
            sequence number
        acknowledgement number
    head U A P R S F receive window
        checksum ...

            application data 
            (variable length)


----------------------
reliable data transfer
----------------------
仅考虑在一般情况下可靠数据传输的问题，仅考虑单向数据传输的情况，即数据传输是从发送方到接收方的

1.完全可靠信道上的可靠数据传输:rdt1.0
2.具有比特差错信道上的可靠数据传输:rdt2.0
    在分组的传输、传播或缓存的过程中，这种比特差错通常会出现在网络的物理部件中。
    error detection: checksum
    feedback: control msgs (ACK,NAK) from receiver to sender
    本质是 stop and wait protocol

3. rdt2.0有致命缺陷--> 确认信息(ACK, NAK)本身出错、引起重复的传输怎么办呢? 因此产生rdt2.1
    如果ACK/NAK出错，那么发送者直接 "重传当前的数据报", 发送者为数据报 添加字段:序号(sequence number)
        接收者抛弃重复的数据报
    sender
        seq # added to pkt(two seq. #’s (0,1) will suffice)
        must check if received ACK/NAK corrupted
    receiver
         must check if received packet is duplicate

4.具有比特bit差错的丢包"lose packet"信道上的可靠数据传输:rdt3.0
    现在假定除了比特受损外，底层信道还会"丢包"。

    发送者等待ACK足够的时间,然后重传(假如还是没有ACK)
    如果"数据包(orACK)"延迟(但没有丢失):  重传导致重复,"顺序号 seq# "的使用可以处理这种情况
    但是接收者必须指定所确认数据包的顺序号
    一般使用倒数的定时器(timer)

    可靠数据传输协议的要点:校验和、序号、定时器、肯定确认和否定确认、重传。

5. 流水线(pipelining)可靠数据传输协议 
    流水线技术对可靠数据传输协议提出了新的要求:
        必须增加序号范围: 因为每个传输的分组(不计算重传的)必须有一个唯一的序号，而且也许有多个在传输中的未确认的分组
        协议的发送方和接收方必须缓存多个分组: 发送方最低限度应当能缓冲那些已发送但没有确认的分组。接收方也需要缓存那些已正确接收的分组

        解决流水线的差错恢复有两种基本方法:"回退N步 GBN"和"选择重传"

   GBN (receiver only sends "cumulative ack")
       在GBN协议中，允许发送方发送多个分组(当有多个分组可用时)而不需等待确认，但它也受限于在流水线中未确认的分组数不能超过某个最大允许数N
       sender can have up to "N unacked packets" in pipeline
       当有"超时timeout"发生，出现丢失和过度时延分组时，发送方将"重传所有已发送但还未被确认"的分组。

   选择重传(SR) (rcvr sends "individual ack" for each packet)
       协议通过让发送方"仅重传那些它怀疑在接收方出错(即丢失或受损)的分组"而避免了不必要的重传。
       这种个别的、按需的重传要求接收方逐个地确认正确接收的分组。它也用"窗口长度N"来限制流水线中未完成、未被确认的分组数。
       ---> out-of-order: "buffer"



TCP通过哪些措施，保证传输可靠

flow control
congestion control




-----
Port
-----

端口,  Ip 地址 是房子, Port 是进房子的门, 一个Ip可以有 65536 (2 ^ 16) 个 端口 (0 - 65535 整数).


一台拥有IP地址的主机可以提供许多服务, HTTP（万维网服务）, FTP（文件传输）, SMTP（电子邮件）等
主机是怎样区分不同的网络服务呢?
显然不能只靠IP地址,因为IP地址与网络服务的关系是 一对多的关系
实际上是通过'IP地址+端口号'来区分不同的服务的





-----------------------------
TCP与UDP的概念, 相互的区别及优劣  TCP vs UDP
-----------------------------


    TCP service : 1. Reliable Transport  between sending and receiving process.
                  2. Flow Control        sender won‘t overwhelm receiver.
                  3. Congestion Control  throttle（截住）sender when network overloaded.
                  4. Connection-Oriented setup required between client and server processes
                  5. But Doesnot Provide timing, minimum throughput guarantee, security

    UDP service : Unreliable data transfer between sending and receiving process
    

    Securing TCP :  SSL （改进的tcp）




---------------------------------------
ICMP（Internet Control Message Protocol）
---------------------------------------
Internet控制报文协议,它是TCP/IP协议簇的一个子协议
用于在 IP主机 host,路由器 router 之间传递 "控制消息" :
     控制消息是指 网络通不通, 主机是否可达, 路由ICMP是否可用等 网络本身的消息

这些控制消息虽然并不传输用户数据, 但是对于用户数据的传递起着重要的作用
ICMP使用IP的基本支持, 就像它是一个更高级别的协议, 但是, ICMP实际上是IP的一个组成部分, 必须由每个IP模块实现.







传输层 + 应用层 及以上 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
网络层 + 链路层 及以下






------------------
forwarding routing
------------------
网络层NetworkLayer有两种重要的功能: "转发forwarding"  "选路routing"

Forwarding involves the transfer of a packet from an incoming link to an outgoing link Within a "single" router.
转发 是 单个路由器内部 传数据包

Routing involves "all" of a network‘s routers, whose collective interactions via routing protocols
determine the "paths" that packets take on their trips "from source to destination" node.
路由 是 从起点路由器到终点路由器的 多个 路由器之间 选择 最佳路径 的问题



----------------
Network Layer作用
----------------
https://zhuanlan.zhihu.com/p/61845038

transfer segments from sending to receiving host.
on sending side, "encapsulates" segments into "datagrams".
on receiving side, "extracts" the transport-layer segments, delivers segemnts to transport layer.
network layer protocols in "every" host, router.
router examines header fields in all IP datagrams passing through it


网络层向上之提供简单灵活，无连接connectionless的，尽最大努力交付的数据报datagram服务。
网络层不提供服务质量的承诺。
    也就是说，所传送的分组都有可能出错，丢失，重复和失序（即不按序到达终点），当然也不保证分组交换的时限，这就使网络中的路由器比较简单，且价格低廉。

----------------
Inside a Router
----------------
查找interface + switching fabric + queuing + discard policy

第一步Datagram Network(look up)

    longest prefix matching
    找 特定目的地 对应的 Link Interface 时 采用 最长前缀匹配
    when looking for "forwarding table" entry for given destination address, 
    use longest address prefix that matches destination address.

第二步Switching fabrics(forwarding)

    1.Switching via "memory": packet Copied to system’s memory, speed limited by memory bandwidth (2 bus crossings per datagram)
    2.via "bus":    datagram from input port memory to output port memory via a shared bus, switching speed limited by bus bandwidth(bus contention竞争)
    3.via "interconnection network": overcome bus bandwidth limitations.
                fragmenting datagram into fixed length cells, switch cells through the fabric.
第三步queuing(queuing)
    Input ports:
        when: fabric Slower than input ports combined -> queueing may occur at input queues
                then -----> queueing delay and loss due to "input buffer overflow"!

    Output ports:
        1.buffering when arrival rate via switch exceeds output line speed  到达速度超过出口速度时 缓冲buffer！

        2.Scheduling: choose next packet to send on link
            2.1 FIFO: send in order of arrival to queue
            2.2 Priority: send highest priority queued packet
            2.3 Round Robin: sending one complete packet from each class
            2.4 Weighted Fair Queuing: each class gets weighted amount of service in each cycle


----------------------------
IP fragmentation + reassembly
----------------------------
network links have MTU: largest possible link-level Frame.
                   最大传输单元(Maximum Transmission Unit)

    中间层 全部拆分, 最后才合并, IP header 用来复原顺序

    large IP datagram divided (“fragmented”) within net
    reassembled” Only at Final destination
    IP header bits used to identify, order related fragments




-------
Ip 地址
-------
IP address: 32-bit identifier for host, router interface
Interface: connection between host/router and physical link (router含多个, host含1-2个)

IP addresses associated with each interface. 即先要给物理接口(eth0, eth1...)分配ip地址 对应好了才能用


----------
子网 Subnet
----------
IP地址的划分:
    1.subnet part - high order bits
    2.host part - low order bits
什么是子网:
    device interfaces with "same subnet part" of IP address
    拥有相同子网部分的  接口(eth0,eth1.) 
    can physically reach each other without intervening router
    可以直接物理通信, 不需要通过 router

确定子网，将每个接口与其主机或路由器分离，创建隔离的网络孤岛，每个孤岛是一个子网
    to determine the subnets, detach each interface from its host or router, 
    creating islands of isolated networks，each isolated network is called a subnet

子网掩码
    subnet mask: /24
    前24位 确定一个子网, 它表明 32 比特中的最左侧 24 位特定义了子网地址. 

    3 台主机以及它们连接的路由器的"接口"都有一个形如 223.1.1.x/24 的 IP 地址

    IP地址比如 分别为  223.1.1.1 223.1.1.2 223.1.1.3 223.1.1.4 
        
        因此子网 223.1.1.1/24 是由 3 台主机"接口"和 1 个路由器"接口"组成的。

        互连这 3台主机的"接口"与路由器的一个"接口"的网络形成一个子网, 
        注意 子网 是由 接口 Interface地址(MAC地址) 构成 而不是

        一个子网的 IP 定义并不局限干连接多台主机到一台路由器接口的以太网段。路由器端口之间的点对点链路也可以构成子网。


          subnet part              |  host part
        11001000 00010111 00010000 |  00000000



------------------------------
How does a host get IP address
------------------------------
主机 怎么获得 IP地址？ 每次请求server

DHCP: Dynamic Host Configuration Protocol:

goal: allow host to dynamically obtain its IP address from "network server" when it joins network


DHCP overview:
    1. host broadcasts “DHCP discover” msg [optional]
    2. DHCP server responds with “DHCP offer” msg [optional]
    3. host requests IP address: “DHCP request” msg
    4. DHCP server sends address: “DHCP ack” msg

DHCP can return more than just allocated IP address on subnet:
返回的不仅仅是个IP地址 还有其他的信息
    1. address of "first-hop router "for client
    2. name and IP address of "DNS sever"
    3. network mask (indicating network versus host portion of address)

具体步骤
    connecting laptop needs its IP address, addr of first-hop router, addr of DNS server: use DHCP
    DHCP request encapsulated in "UDP", encapsulated in "IP", encapsulated in "802.1 Ethernet".
    Ethernet frame broadcast "(dest: FFFFFFFFFFFF)" on LAN, received at router running DHCP server
    Ethernet demuxed to IP demuxed, UDP demuxed to DHCP
    DCP server formulates(定制 制作) DHCP ACK containing client’s IP address, IP address of first-hop router for client, name & IP address of DNS server
        encapsulation of DHCP server, frame forwarded to client, demuxing up to DHCP at client
    client now knows its IP address, name and IP address of DSN server, IP address of its first-hop router





--------------------------------
NAT: network address translation
--------------------------------
ipv4协议提供的IP地址是有限的,为了解决IP地址不足的问题,于是就有了网络地址转换(NAT)
它的思想就是给"一个局域网络"分配"一个IP地址"就够了

motivation: 
    1.只需/能从ISP申请一个IP地址 IPv4地址耗尽
        range of addresses not needed from ISP: just one IP address for all devices
    2.本地网络设备IP地址的变更，无需通告外界网络
        can change addresses of devices in local network without notifying outside world
    3.变更ISP时，无需修改内部网络设备IP地址
        can change ISP without changing addresses of devices in local network
    4.内部网络设备对外界网络不可见，即不可直接寻址(安全)
        devices inside local net not explicitly addressable, visible by outside world (a security plus)

实现:
    1. 所有离开本地网络去往Internet的数据报的源IP地址需替换为
            相同的NAT IP地址: 138.76.29.7 以及不同的端口号。
            all datagrams leaving local network have same single source NAT IP address: 138.76.29.7,different source port numbers
    2. 本地网络内通信的IP数据报的源与目的IP地址均在子网 10.0.0/24 内。
        datagrams with source or destination in this network have 10.0.0/24 address for source, destination


----
Ipv6
----
IPv6 将 IP 地址长度从 32bit 增加到 128bit,这就确保再也不会用尽地址。

Transition from IPv4 to IPv6:
方法一: 最直接的引人IPv6节点的方法可能是双栈技术，即让IPv4节点也具有完整的Ipv6实现。

方法二: 隧道法:IPv6数据报 被当作IPv4数据报中的 有效载荷 携带 在IPv4路由器之间 
"tunneling": 
    IPv6 datagram carried as "payload" in IPv4 datagram among IPv4 routers




-----------------
Routing protocols
-----------------
determine “good” paths (equivalently, routes), from sending hosts to receiving host, through network of routers

“link state” algorithms: net topology, link costs known to "all" nodes 得全知道
在 LS 算法中，每个节点(经广播)与所有其他节点交换信息(with n nodes, E links, O(nE) msgs sent)
            但它仅告诉它们与它直接 相连链路的费用。

“distance vector” algorithms: router knows "physically-connected" neighbors, link costs to "neighbors"
在 DV 算法中，每个节点仅与它的直接相连邻居交换信息(exchange between neighbors only)
            但它为它的邻居提供了从其 自己到网络中(它所知道的)所有其他节点的最低费用"估计"。



----------------------
Dijsktra:   link state
----------------------
Dijkstra 算法是迭代算法，经算法的第 k 次迭代后，可知道到 k 个目的节点的最低费用路径。

Initialization:
    N‘={u}
    for all nodes v
        if v adjacent to u then D(v) = c(u,v)
        else D(v) = ∞
Loop:
    find w not in N‘ such that D(w) is a minimum (use minHeap)
        add w to N’
    update D(v) for all v adjacent to w and not in N‘: 
        D(v) = min( D(v), D(w) + c(w,v) )
    /* new cost to v is either old cost to v or known shortest path cost to w plus cost from w to v */
    until all nodes in N’


T : O(VlogV+ElogV)


-----------------------------
Bellman-Ford: distance vector
-----------------------------
动态规划
距离矢量算法是一种 "迭代的iterative"、"异步asynchronous" 和 "分布式distributed"的算法。

from time-to-time, each node sends its own distance vector "estimate" to "neighbors"
when x receives "new" DV estimate from neighbor, it updates its own DV using B-F equation
    Dx(y) ← min v{c(x,v) + Dv(y)} for each node y ∊ N
    方程中的 min，是指取遍 x 的"所有"邻居。



distributed:  each node notifies neighbors only when its DV changes

Bellman-Ford()
    Set all predecessors and distance estimates to undefined            O(V)
    Set source distance to zero
    //Relax everything enough times to find all shortest paths.
    for V – 1 iterations                                O(V) * O(E)
        for each edge i -> j 
            relax edge i -> j
    //See if there are any edges that could still relax 
    for each edge i -> Just                             O(E)
        if j.distance > i.distance + weight[ i ][ j ]
            return false
return true

T: O(VE)




--------------------------------------
intra-AS routing in the Internet
--------------------------------------
两个原因导致"层次的选路策略"：
    1.规模：随着路由器数目增长，选路信息的计算、存储及通信的开销逐渐增高。
    2.管理自治：一般来说，一个单位都会要求按自己的意愿运行路由器(如运行其选择的某 种选路算法)，或对外部隐藏其内部网络的细节。


aggregate routers into regions known as 
    “Autonomous Systems” (AS) 
    (a.k.a. "domains")

自治系统 AS 中的路由器一般使用相同的管理策略和技术控制，因此一个 AS 内部运行相同的选路协议
    例如 RIP(Routing Information Protocol)和 OSPF(Open Shortest Path First)。


intra-AS routing: routing among hosts, routers in same AS (“network”)



RIP(Routing Information Protocol))
----------------------------------
RIP 是一种距离向量DV协议，使用跳数作为其费用测度，即每条链路的费用为 1 或者经过一个路由器为 1 跳。
    IP 术语跳也表示沿着从源路由器到目的子网(包括目的子网)的最短路径所经过的子网数量。
每台路由器维护一张称为选路表的RIP表。
RIP 路由器一旦超过 180 秒没有监听到其邻居，则该邻居被认为"不可到达"。
    当发生这种情况时，RIP 修改本地选路表，根据 "Bellman-Ford" 方程更新其转发表
    然后通过向相邻路由器(那些仍然可达的路由器)发送通告来传播该信息。
路由器在 UDP 上使用端口 "520" 相互发送 RIP 请求与响应报文。




OSPF(Open Shortest Path First)
----------------------------------
开放最短路径优先协议 
OSPF 的核心是一个使用洪泛链路状态信息的链路状态协议和 "Dijkstra"最小费用路径算法
OSPF 协议消息直接封装在 "IP数据报"中广播。


使用 OSPF, 一台路由器构建了一幅关于"整个"自治系统的完整拓扑图。
    路由器在本地运行 Dijkstra 最短路径算法,以确定一个"以自身为根节点的到所有子网的最短路径树 Tree"。
使用 OSPF，路由器向自治系统内"所有其他路由器""广播"选路信息，而不仅仅是向其相邻路由器广播

优点:
    安全。OSFF 路由器之间的交换(如链路状态更新)都是经过鉴别的。
        security: all OSPF messages authenticated (to prevent malicious intrusion)
    允许多条相同费用的路径。当到达某目的地的多条路径具有相同的费用时，OSPF 允许使用多条路径。
        multiple same-cost paths allowed (only one path in RIP)
    对单播选路与多播选路的综合支持。    
        integrated uni- and multi-cast support
    支特在单个选路域内的层次结构。
        hierarchical OSPF in large domains.

    OSPF 的最重要优点是"具有按层次 Hierarchical 结构构造一个自治系统的能力"。
        "boundary" router:      边界路由器。边界路由器与属于其他自治系统的路由器交换选路信息
        "backbone" router:      主干路由器(非边界路由器)。这些路由器执行主干中的选路，但其自身不是区域边界路由器。
                                    在一个非主干区域内，内部路由器通过该区域中的主干路由器，从信息广播中知道存在通向其他区域的路由。
        "area border" routers:  区域边界路由器。这些路由器同时属于区域与主干两个区域
        "internal" routers:     内部路由器。这些路由器位于非主干区域且只执行 AS 内部选路





--------------------------- 
routing among the ISPs: BGP
---------------------------
自治系统之间选路使用 BGP(Border Gateway Protocol，边界网关协议)。
inter-AS routing: routing among AS’es

the "de facto" (实际上) inter-domain routing protocol


BGP 使用 eBGP 和 iBGP 向 AS 中的所有路由器发布路由。
根据这种发布，路由器可能获 得到达某个路由器的多条路径，在这种情况下，路由器必须在可能的路径中选择一条。


eBGP: "obtain获得" subnet reachability information from neighboring ASes.
iBGP: "propagate传播" reachability information to all AS-internal routers.

gateway routers run both eBGP and iBGP protools


BGP 顺序地调用下列规则进行路由选择:  switch case 4选1 优先级逐次降低

•路由被指派一个本地偏好值作为它们的属性之一，通俗地说就是由该 AS 的网络管理员设定一种策略。
    local preference value attribute: policy decision 
•在剩下的路由(所有都具有相同的本地偏好值)中，将选择具有最短 AS-PATH 的路由。
    shortest AS-PATH
•在剩下的路由(所有都具有相同的本地偏好和相同的 AS-PATH 长度)中，将选择最靠近 NEXT-HOP 路由器的路由。
    closest NEXT-HOP router: hot potato routing
•如果仍余下多条路由，路由器使用 BGP 标识符来选择路由
    additional criteria


prefix + attributes = “route”
    AS-PATH: list of ASes through which prefix advertisement has passed
    NEXT-HOP: indicates specific internal-AS router to next-hop AS







---------------------------------
Software defined networking (SDN)
---------------------------------
Combines "connection-oriented networking" and "routing overlay" technologies
优点:
1.Perform classification in "hardware", not software
2.Use high-speed forwarding "hardware", not software for forwarding packets
3.Avoid using routing protocols, allow managers to "specify" how to handle/route each flow
4.For "scalability", allow management applications, not humans, to configure and control the network devices


组成:
    Interface, abstractions for network control apps
        "network graph","RESTful API", "intent" 
            ------------------------------
            "statistics", "flow tables"
    Network-wide distributed, robust state management(a distributed Database)
        "Link-state info","host info", "switch info"
            ------------------------------
                "OpenFlow" "SNMP"
    Communication to/from controlled devices
    

OpenFlow protocol
---------------
OpenFlow: Provides a standard interface for programming the data plane switches
operates between controller, switch

TCP

three classes of OpenFlow messages:
1.controller-to-switch
        features: controller queries switch features, switch replies
        configure: controller queries/ sets switch configuration parameters
        modify-state: add, delete, modify flow entries in the OpenFlow tables
        packet-out: controller can send this packet out of specific switch port
2.asynchronous (switch to controller)
    packet-in: transfer packet (and its control) to controller. See packet- out message from controller
    flow-removed: flow table entry deleted at switch
    port status: inform controller of a change on a port.
3.symmetric (misc)




--------------
广播和多播, IGMP 
--------------
https://www.cnblogs.com/xujian2014/p/5072215.html

广播 broadcast
--------------
    Flooding
        实现广播的最显而易见的技术是使用洪泛方法,该方法要求源节点向它的"所有"邻居发送一个分组的拷贝。
            当某节点接收了一个广播分组时，它复制该分组并向它的"所有"邻居转发。
    Spanning Tree
        首先对网络节点构造出一棵生成树。
            each node sends unicast join message to center node, message forwarded until it arrives at "a node already belonging to spanning tree".
        当一个源节点要发送一个广播分组时,它向所有属于该生成树的特定链路发送分组
        接收广播分组的节点则向生成树中的所有邻居转发该分组(它接收该分组的邻居除外)。
        生成树不仅"消除了冗余广播分组"，而且"能够被任何节点用于开始"广播分组

    广播协议在实践中通常被用于应用层和网络层

多播 multicast
-------------
    多播服务可以将多播分组"仅"交付给网络节点的一个"子集subset"

    问题
        1.how to identify the receivers of a multicast packet;
        2.how to address a packet sent to these receivers.

    goal: find a tree (or trees) connecting routers having local multicast group members.


在因特网中,表示一组接收方的单一标识就是一个 D类 多播地址. 与一个 D类地址相关联的多个接收方称为一个多播组.
多播组抽象是很简单的,但需要解决"组的形成"、"终结"问题, "组地址的选择"问题, "新主机如何加入组"(要么作为发送方，要么作为接收方)的问题, "组成员资格"问题等等。
对于因特网,多播组的管理由因特网组管理协议(Internet Group Management Protocol)实现.

IGMP（Internet Group Management Protocol）
----------------------------------------
    运行在一台主机与其直接相连的路由器之间
    IGMP 为一台主机提供了手段: 可让它通知与其相连的路由器,在本主机上运行的一个应用程序"如何加入"一个特定的多播组.

    多播选路算法:

        1.使用一棵"基于源的树"(source based) 进行多播选路
            为多播组中的每个源构建一棵多播选路树(Dijkstra’s algorithm)
            在实践中,使用 RPF(Reverse path forwarding)算法 来构造一棵多播转发树,以用于转发来自源节点的多播数据报。
                if (mcast datagram didnot received on incoming link on shortest path back to center) ignore/block the datagram.
                    prune(forwarding tree contains "subtrees" with no mcast group members, no need to forward datagrams down subtree)

        2.使用一棵 "组共享树"(group shard tree) 进行多播选路
            在使用基于中心方法来构造多播选路树, 具有属于多播组相连主机的边缘路由器向中心节点(经单播)发送加入消息.
            一个加入报文使用单播选路朝着中心转发,直到它到达已经属于多播树的一台路由器或到达这个中心
                steiner tree: minimum cost tree connecting all routers with attached group members
                    problem is NP-complete
                    not used in practice:1.computational complexity 2.monolithic: rerun whenever a router needs to join/ leave
                    
                center-based approach 选个点当中心




网络层 及以上 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
链路层 及以下





--------
链路层概述
--------

data-link layer has responsibility of transferring datagram from one node to "physically adjacent" node over a link

Link layer services(in each and every host)
    服务: 1.成帧 2.链路接入 3.可靠交付 4.流量控制 5.差错检测+纠正 6.半双工和全双工通信
    e.g.: framing, link access:
        链路层协议交换的数据单元称为"帧frame"，帧封装了一个网络层的"数据报datagram"。
        encapsulate datagram into "frame", adding header, trailer
    e.g.: reliable delivery between adjacent nodes

链路层协议的例子如"以太网"、"802.11 无线 LAN(也称为 WiFi)"、"PPP" 协议。

-------------
链路层在何处实现
-------------
1.在"路由器Router"中链路层是在"线路接口卡(network interface card NIC)"中实现的。
2.在"主机host"的链路层 "主体部分" 是在"网络适配器(network adapter)"中实现的，网络适配器也称为"网卡"，网络适配器的内核是链路层控制器芯片.
  
    在发送方，控制器取得了 IP 层的数据报，在链路层帧中封装该数据报，然后遵循链路接入协议将该传进通信链路中。
    在接收端，控制器接收整个帧，提取出网络层数据报。 
    链路层通常还包括 "软件部分" ，实现较高层次的链路层功能，如从网络层接收数据报，装配链路层寻址信息以及激活控制器硬件。

    因此，主机中链路层是一种"硬件(网卡)和软件(网卡驱动)的结合体".




--------------------------
数据传输中检测差错的 3 种技术:
--------------------------
1 奇偶校验 parity checking

    要发送的信息 D 有 d 个比特, 
        发送方只需包含一个附加的比特，选择附加比特的值，使得这 d+1 个比特(初始信息加上一个校验比特)中 1 的总数是偶数
        接收方只需要数一数接收的 d+1 比特中 1 的个数。
            如果发现了奇数个值为 1 的比特，接收方知道了至少出现了一个比特差错。
            但是如果出现了偶数个比特差错，显然这种方法无法检测这种错误

2 校验和 checksum
    在 TCP 和 UDP 协议中，对所有字段(包括首部和数据字段)都计算校验和
    IP 协议只对"头部"计算校验和。


3 CRC冗余校验算法 (广泛应用)，反码和检验算法

    有 d 比特的数据 D，发送节点要将它发送给接收节点。
        发送方和接收方首先必须协商规定一个 "r+1" 比特的生成多项式 G。(规定一个 G)
        D 上加上 G的位数-1 个 0
        然后 新D 除 G (所有 CRC 计算采用 模2(mod 2) 算术操作，在加法中不进位，在减法中不借位。两种操作等价于操作数的按位异或"(XOR)"。)
        得到 余数R 
        如果 R 非0 就代表出错




-------------------------------------- 
多路访问协议 (Multiple access protocols)
--------------------------------------  

有两种类型的网络链路:点对点(point-to-point)链路 和 广播(broadcast)链路
下面说的都是广播
因为所有的节点都能够传输帧，两个以上的节点可能会同时传输帧。当发生这种情况时， 传输的帧在所有的接收方处"碰撞"了。碰撞的结果是帧的丢失，以及广播信道的浪费。
所以 如何"安排信道" 就无比重要了
    1 信道划分协议    channel partitioning
        1.1 时分多路复用(TDM)按时间分配
        1.2 频分多路复用(FDM)按频率分配
    2 随机接入协议    random access
        在随机接入协议中，一个传输节点总是以信道的全部速率进行发送。
        当有碰撞时，涉及 碰撞的每个节点反复地重发它的帧，直到该帧顺利发送为止
        how to detect collisions

        how to recover from collisions (e.g., via delayed retransmissions)

        2.1 Slotted ALOHA
            //https://zhuanlan.zhihu.com/p/69263154
            N 个活跃节点时，时隙 ALOHA 的效率是 Np(1-p)^(N-1)
        2.2 ALOHA
        2.3 CSMA, CSMA/CD(collision detection) used in Ethernet, CSMA/CA(collision avoidance) used in 802.11
            发送前侦听信道
            如果与他人同时开始发送，则停止发送

    3 轮流协议    taking turns
        3.1 轮询协议 polling
            master node “invites” slave nodes to transmit in turn
        3.2 令牌传递协议 token passing
            control token passed from one node to next sequentially.



------------------------
硬件(MAC)地址的概念及作用
------------------------
MAC address = LAN address = pyhsical address  是 Link-Layer address

    it is not the host and routers that have link-layer address but Rather 
        their adapters("network interfaces") that have link-layer address

        就是 网卡地址 一个主机host 可以有多个 MAC地址

    6 bytes long hexadecimal  e.g.:  1A-23-F9-CD-06-9B


想要联网 必须得有这个 MAC 地址 
Each Interface connected to a LAN has a unique MAC address




------------------------------------
ARP协议的用途 及算法, 在哪一层上会使用arp
------------------------------------
ARP是 Link-Layer 数据链路层 上的
ARP 协议模块将使用一个 ARP 表, 取 "IP地址"作为输入 ====> 查表得到相应 的 "MAC地址"。

Address Resolution Protocol
    provides a mechanism to translate network layer address(IP address) to link-layer address(MAC address)
    地址转换的           linux上看arp表(arp -v // arp -n)


ARP resolving is analogous to DNS.
    but DNS resolves host names for fosts anywhere in the Internet,
    whereas ARP resolves IP address only for hosts and router interfaces "on the same subnet"

    If a node is CA were to try to use ARP to resolve the IP address for a node in NC, ARP will return with an error


 但是 不同的 subnet之间 怎么传？
    用 router 一个router 有多个 IP 及对应的 MAC, 同时还有 "router table" 就可以了



----------------
Switch vs Router
----------------

工作层次不同: Switch交换机 主要工作在数据链路层（第二层）
            Router路由器 工作在网络层（第三层）

转发依据不同: Switch交换机 转发所依据的对象时:MAC地址（物理地址）
            路由转发所依据的对象是:IP地址（网络地址）

主要功能不同: 交换机 主要用于组建局域网
            路由主要功能是将由交换机组好的局域网相互连接起来，或者接入Internet。
            交换机能做的，路由都能做。
            交换机不能分割广播域，路由可以。
            路由还可以提供防火墙的功能。
            路由配置比交换机复杂。

简而言之 Switch交换机 是看门大爷,Router路由 是邮局


Switch 有 self-learning功能
    frame destination, A’, location unknown: "flood"
    destination A location known: "selectively" send on just one link






--------------------- 
链路虚拟化: 网络作为链路层
--------------------- 
多协议标签交换 MPLS
Multiprotocol Label Switching 

通过来自虚电路网络的一个关键概念—"固定长度标签",可以改善 IP 路由器的转发速度。 
其目标是使用转发 IP 数据报的路由设备, 但却是基于固定长度标签和虚电路的技术,
让路由器"根据固定长度的标签"转发数据报(而不是目的地 IP 地址) ----> 从而加快转发速度("标签长度固定"、"较小的标签空间"这两个特点加快了查表速度)。

initial goal: high-speed IP forwarding using "fixed length label" (instead of IP address)








--------------------------
更完整版的 点击网页 涉及 IP寻址
--------------------------

第一大部分 DHCP, UDP, IP and Ethernet       
-----------------------------------
 (Dynamic Host Configuration Protocol)
要知道自己的IP:DHCP

1.connecting laptop needs to get its own "IP address", "addr of first-hop router", "addr of DNS server": use DHCP
2.DHCP request encapsulated in UDP, encapsulated in IP, encapsulated in 802.3 Ethernet
3.Ethernet frame broadcast (dest: FFFFFFFFFFFF) on LAN, received at router running DHCP server
4.Ethernet demuxed to IP demuxed, UDP demuxed to DHCP
5.DHCP server formulates定制 DHCP ACK containing client’s IP address, IP address of first-hop router for client, name & IP address of DNS server
6.encapsulation at DHCP server, frame forwarded ("switch learning") through LAN, demultiplexing at client
7.DHCP client receives DHCP ACK reply

---->   Client now has IP address, knows name & addr of DNS server, IP address of its first-hop router


第二大部分 DNS and ARP
--------------------
要知道对方的IP: DNS
要知道网卡地址: ARP (Address Resolution Protocol)

1.before sending HTTP request, need "IP address of www.google.com": DNS
2.DNSquery created, encapsulatedin UDP, encapsulated in IP, encapsulated in Eth. 
3.To send frame to router, need "MAC address of router interface": ARP
4.ARPquery broadcast,receivedby router, which replies with ARP reply giving MAC address of router interface
5.client now knows "MAC address of first hop router", so can now send frame containing DNS query


第三大部分 Intra-Domain Routing to the DNS server
------------------------------------------------
要创建 route table: RIP, OSPF, IS-IS and/or BGP

1. IP datagram containing DNSquery forwarded via LAN switch from client to 1st hop router.
2. IP datagram forwarded from campus network into comcast network, 
    routed (tables created by "intra-domain" RIP, OSPF, IS-IS and/or
                              "inter-domain" BGP routing protocols) to DNS server
3. demuxed分解 to DNSserver
4. DNSserver replies to client with IP address of www.google.com 



第四大部分 同上面了 TCP + HTTP
---------------------------

1. to send HTTP request, client first opens TCP socket to web server
2. TCP SYN segment (step 1 in 3-way handshake) inter-domain routed to web server
3. web server responds with TCP SYN ACK (step 2 in 3-way handshake)
4. TCP connection established!
5. HTTP request sent into TCP socket
6. IP datagram containing HTTP request routed to www. google. com
7. web server responds with HTTP reply (containing web page)
8. IP datagram containing HTTP reply routed back to client










16. Ping协议的实现原理，ping命令格式。
30. 阻塞方式和非阻塞方式，阻塞connect与非阻塞connect。(比较难，有兴趣可以了解)







































