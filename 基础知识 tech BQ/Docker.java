Docker
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------
Docker 一句话
-------------
解决了 "运行环境 runtime" 和 "配置问题" 的 "软件容器"


如果没有 Docker 自己要在本地安装 hadoop cluster等。。
    1. 可能需要dependency  java "jar包" 等等
    2. hadoop cluster 有很多 "xml文件"， 里面有很多 "参数 需要与本机吻合"   因为电脑名字不一样  francis or effy
    3. 启动指令  各种报错， 电脑和hadoop有同名的 dependency , dependency conflict  => jar A 1.0  与 jar A 1.2
    4. 拿到公司，同事要拿他的电脑 run 我们的hadoop， 那么就要再重新做一遍1-3， 重复劳动
		=> 耗时 耗力 重复性劳动 => docker 的意义就出来了

-----------------
Docker 与 VM 区别
-----------------
Docker 与 virtual machine
    相当于 轻量版的 VM
    VM 一定有自己的 "guest OS" 的， 而docker 直接建立在 "host OS" 上 

VM缺点
因为连硬件都模拟了
1. 资源占用多 2. 启动慢


问你写过 docker file吗？ 没不是我写的（不要骗面试官）， 我仅有过 使用的 experience，
但是我了解，我会用这个工具。
    => 用 docker build 了 个 Hadoop 环境， 以便在上面跑数据





-----------------
Docker基本组成
-----------------
三要素   
镜像 image
容器 container
仓库 repository

镜像就是一个只读的模版 类比 java里面的大Class 类
一个镜像可以来创建Docker容器, 一个镜像可以创建很多容器 即容器 = java中的 实例对象

Docker 利用容器独立运行一个或一组应用
它(Container)可以被 启动开始停止删除 每个容器相当于一个 集装箱, 相互隔离 保证安全的平台
可以把容器看成是一个 简单的 Linux环境 包括 (root用户权限, 进程空间 用户空间 网络空间) + 运行在其中的应用程序

仓库 Repository 是集中存放 镜像文件的 场所





------------- 
Docker基本命令
------------- 
docker [OPTIONS] COMMAND [arg...]

docker info
docker --helper


docker images   	列出本地主机上的镜像
docker search
docker pull + 名字	下载镜像
docker rmi


docker run [OPTIONS] IMAGE [COMMAND][ARG...]		新建 并启动容器
docker ps[OPTIONS]  								列出所有正在运行的容器
docker start 容器ID/容器名							启动容器
docker stop 容器ID/容器名							    停止容器


重要 
docker run -d 容器名							启动守护式容器 即后台启动
docker logs -f -t -tail 容器ID 				查看容器日志 
docker cp 容器ID:容器内路径 目的主机路径			从服务器到主机拷贝文件












