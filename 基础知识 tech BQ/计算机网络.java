
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


--------------------------------
Transport services and protocols
--------------------------------

provide Logical Communication between app processes running on different Hosts

    send side: breaks app messages into Segments, passes to network layer
    rcv side: Reassembles segments into messages, passes to app layer

-----------------------------
Multiplexing, Demultiplexing
-----------------------------
multiplexing at sender
    handle data from multiple sockets, add transport header to network layer

demultiplexing at receiver
    use header info to deliver received segments to correct socket
        host uses IP addresses & Port numbers to direct segment to appropriate socket


---------------------
UDP segment structure
---------------------

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
TCP通过哪些措施，保证传输可靠

flow control
congestion control


---------------------------------------
ICMP（Internet Control Message Protocol）
---------------------------------------
Internet控制报文协议,它是TCP/IP协议簇的一个子协议
用于在 IP主机 host,路由器 router 之间传递 "控制消息" :
     控制消息是指 网络通不通, 主机是否可达, 路由ICMP是否可用等 网络本身的消息

这些控制消息虽然并不传输用户数据, 但是对于用户数据的传递起着重要的作用
ICMP使用IP的基本支持, 就像它是一个更高级别的协议, 但是, ICMP实际上是IP的一个组成部分, 必须由每个IP模块实现.



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



----------------------
How does a host get IP address
----------------------

7. 如何实现透明传输。
10. 分组转发算法。
13. RIP协议的概念 及算法。

16. Ping协议的实现原理，ping命令格式。


30. 阻塞方式和非阻塞方式，阻塞connect与非阻塞connect。(比较难，有兴趣可以了解)






------------------------
组播和多播的概念, IGMP 的用途
------------------------
https://www.cnblogs.com/xujian2014/p/5072215.html







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

Address Resolution Protocol
    provides a mechanism to translate network layer address(IP address) to link-layer address(MAC address)
    地址转换的           linux上看arp表(arp -v // arp -n)


ARP resolving is analogous to DNS.
    but DNS resolves host names for fosts anywhere in the Internet,
    whereas ARP resolves IP address only for hosts and router interfaces "on the same subnet"

    If a node is CA were to try to use ARP to resolve the IP address for a node in NC, ARP will return with an error


 但是 不同的 subnet之间 怎么传？
    用 router 一个router 有多个 IP 及对应的 MAC, 同时还有 "router table" 就可以了





--------------------------
CRC冗余校验算法，反码和检验算法
--------------------------
规定一个 G
D 上加上 G的位数-1 个 0
然后 除 
做 XOR 运算 


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



--------------------------
路由表Routering table 的内容
--------------------------





 








----------------

----------------






----------------

----------------






----------------

----------------






----------------

----------------


























