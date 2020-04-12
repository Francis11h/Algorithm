操作系统

//https://techlarry.github.io/OS/%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F%E6%A6%82%E5%BF%B5/5%20CPU%20Scheduling/

-----------------
Basic Terminology
-----------------
what a computer program is : A computer program is a set of instructions that can be executed by the CPU.
A process is a program in execution.
A Thread (or a thread of execution) is a sequence of instructions that can be processed by a Single CPU core.




-----------------
Process vs Thread
-----------------
（1）线程是进程的一部分

（2）CPU调度的是线程

（3）系统为进程分配资源，不对线程分配资源

The most significant, practical difference is in how processes and threads Communicate.

In a multithreaded process, threads share memory. 
    Thus, many threads can access and modify the same memory, which may lead to bugs that are very difficult to find.

Processes don’t share memory in this way, 
    they have to use inter-process communication instead.
        Shared Memory   Semaphore
        Message passing Socket


-----------------
Stack vs Heap (Memory)
-----------------
在函数中定义的变量存在哪里 : Stack
malloc申请的是哪里的地址 : Heap

Stack : 局部变量、函数参数、返回地址
Heap : 动态分配的内存


-----------------
Virtual memory 
-----------------
Virtual Memory is a Storage-Allocation-Scheme in which Secondary-Memory(Disk) can be addressed as though it were part of main memory(RAM).

It maps memory addresses used by a program, called virtual addresses, into physical addresses in computer memory.


    All memory references within a Process are Logical addresses that are dynamically translated into physical addresses at run time. 
        This means that a process can be swapped in and out of main memory such that it occupies different places in main memory at different times during the course of execution.

    A process may be broken into number of pieces and these pieces need not be continuously located in the main memory during execution. 
        The combination of dynamic runtime address Translation and use of Page or segment Table permits this.


Page Fault – A page fault happens when a running program accesses a memory page 
                that is mapped into the Virtual-Address space, 
                but not loaded in Physical-Memory.

-------------
Demand Paging
-------------
定义 
the process of loading the page from Disk into Main Memory on Demand(whenever page fault occurs).

过程
1.  if CPU try to refer a page that is currently not available in the Main-memory(Physical-Memory),
        it generates an Interrupt(Page-Fault) indicating memory access fault.

2.  The OS puts the interrupted Process in a Blocking state. 
    for the execution to proceed the OS must bring the required page into the memory.

3.  the OS will search for the required page in the Logical-Address space.

4.  the required page will be brought from logical address space to Physical-Address space.
    The Page-Replacement-Algorithms are used for the decision making of replacing the page in physical address space.

5.  the Page-Table will updated accordingly.

6.  the signal will be sent to the CPU to continue the program execution and 
        it will place the process back into Ready state.


----------------------
Swapping and Thrashing
----------------------

Swapping a process out means removing all of its pages from memory, or marking them so that they will be removed by the normal page replacement process. 
Suspending a process ensures that it is not runnable while it is swapped out. 
At some later time, the system swaps back the process from the secondary storage to main memory. 
When a process is busy swapping pages in and out then this situation is called thrashing.

The system spends most of its time swapping pages rather than executing instructions.

So a good page replacement algorithm is required.


--------------------------
Page-Replacement-Algorithms
--------------------------

页面置换算法
1. FIFO 
    当发生缺页pagefault时, 把先进来的先淘汰.
2. LRU  Least Recently Used
    选择在最近一段时间内 "最久" 没有使用过的页, 把它淘汰.
3. LFU
    根据 页面 被访问的次数 来判断, 选择到当前时间为止 被访问次数 "最少" 的页 转换.

LRU is a cache eviction algorithm called least Recently used cache.
    It uses a hash-table to cache the entries and 
            a double-Linked-List to keep track of the access order

LFU is a cache eviction algorithm called least Frequently used cache.
    It requires three data structures. 
    One is a hash-table which is used to cache the key/values so that given a key we can retrieve the cache entry at O(1). 
    Second one is a double-Linked-List for each frequency of access. 
        The max frequency is capped at the cache size to avoid creating more and more frequency list entries. If we have a cache of max size 4 then we will end up with 4 different frequencies. Each frequency will have a double linked list to keep track of the cache entries belonging to that particular frequency.
    The third data structure would be to somehow link these frequencies lists.
        It can be either an array or another linked list so that on accessing a cache entry it can be easily promoted to the next frequency list in time O(1).

--------------------------
CPU-Scheduling-Algorithms
--------------------------


On modern operating systems it is Kernel-Level-Threads (Not!!!! processes) 
    that are in fact being scheduled by the operating system.   被调度的是 内核级 "线程" 不是 进程.

Whenever the CPU becomes idle, the operating system must select one of the processes in the Ready-queue(就绪队列) to be executed. 每当CPU空闲时, 操作系统就必须从就绪队列中选择一个进程来执行。


1. FIFO (FCFS 先来先服务)
    先进先出算法 同页面置换算法FIFO一样
    当发生进程调度时, 按照进程进入就绪队列的先后次序来选择
    即每次进程调度时, 将就绪队列的队首进程（第一个）投入运行

2. Round Robin 轮转法 
    每个就绪进程获得一小段CPU时间（时间片，time quantum）
    时间片用毕，这个进程被迫交出CPU，重新挂回到就绪队列

3. Priority Scheduling (优先权法)
    每个进程都有一个优先数（priority number）通常是个整型数
    选取就绪队列（双向链表）中 优先权 最高的进程

    Priblems
        进程饥饿（Starvation）优先权较低的就绪进程可能永远得不到CPU
    Solution 
        — Aging思想 : 就绪进程等在就绪队列里的时间，折算叠加到进程优先权。
                        因此，等待在就绪队列里的进程, 其优先权单调递增










---------
Dead Lock
---------
死锁是  "并发"进程   在竞争资源的过程   中产生的
根本原因 系统能够提供的资源个数比请求该资源的进程数要少

1. 互斥区 mutual exclusive. only one process at a time can use the resource.
2. 占有并等待 hold and wait. a process holding at least one resource is waiting to acquire additional resources held by other.
3. 不剥夺 non-preemptive
4. 环路等待 circular wait        有一种循环链，链中每一个进程已获得的资源
                                同时被链中下一个进程所请求。





java deadlock 实例

@Override
public void run() {
    "synchronized (lockA)" {
        System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + lockA + "\t 尝试获得: " + lockB);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        "synchronized (lockB) "{
            System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + lockB + "\t 尝试获得: " + lockA);
        }
    }
}


new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();


怎么解决？

1.  jps -l 命令 查看当前进程的进程号 
    (linux 里 ps -ef|grep XXX    ls -l)
    (jps = java ps)
2.  jstack + 该进程号 找死锁查看










---------------------------------------------
用户态和内核态的区别? 为什么要有用户态内核态区别?
---------------------------------------------


------------------------------------------------ 
在段页式分配中，CPU每次从内存中取一次数据需要几次访问内存
------------------------------------------------ 


至多三次吧
第一次访问是访问内存中的段表，从中取得页表始址；
第二次访问是访问内存中的页表，从中取出该页所在的物理块号，并将该块号与页内地址一起形成指令或数据的物理地址；
第三次访问才是真正从第二次访问所得的地址中，取出指令或数据。

cache






1. 进程的有哪几种状态，状态转换图，及导致转换的事件。



3. 进程通信的几种方式。
4. 线程同步几种方式。(一定要会写 生产者、消费者 问题，完全消化理解)
5. 线程的实现方式. (也就是用户线程与内核线程的区别)
6. 用户态和核心态的区别。
7. 用户栈和内核栈的区别。
8. 内存池、进程池、线程池。(c++程序员必须掌握)
9. 死锁的概念，导致死锁的原因.
10. 导致死锁的四个必要条件。
11. 处理死锁的四个方式。
12. 预防死锁的方法、避免死锁的方法。
13. 进程调度算法。


14. Windows内存管理的方式(块式、页式、段式、段页式).
15. 内存连续分配方式采用的几种算法及各自优劣。
16. 动态链接及静态链接.
17. 基本分页、请求分页储存管理方式。
18. 基本分段、请求分段储存管理方式。
19. 分段分页方式的比较各自优缺点。
20. 几种页面置换算法，会算所需换页数。(LRU用程序如何实现？)

22. 操作系统的四个特性。
23. DMA。
24. Spooling。
25. 外存分配的几种方式，及各种优劣。



局部性原理
        时间局部性
            如果程序中的某条指令一旦执行，不久以后该指令可能再次执行,
            如果某数据被访问过，不久以后该数据可能再次被访问,
            产生时间局部性的典型原因 : 在程序中存在着大量的  循环操作。

        空间局部性
            一旦程序访问了某个存储单元，在不久之后，其   附近的存储单元 也将被访问.
            即程序在一段时间内所访问的地址，可能集中在一定的范围之内.
            这是因为    指令通常是顺序存放、顺序执行的 + 数据也一般是以 向量、数组、表等形式 簇聚存储的。



虚拟内存的定义及实现方式
        
        物理内存 就是计算机的实际内存大小，由RAM芯片组成的。
        虚拟内存 则是虚拟出来的、使用磁盘代替内存。

        基于局部性原理，在程序装入时，可以将程序的一部分装入"内存RAM = main memory"，而将其余部分留在外存(磁盘)，就可以启动程序执行。
        在程序执行过程中，当所访问的信息不在内存时，发生缺页 page fault,由操作系统将所需要的部分调入内存 demand paging,然后继续执行程序。
        另一方面，操作系统将内存中暂时不使用的内容换出到外存上，从而腾出空间存放将要调入内存的信息。
        这样，系统好像为用户提供了一个比实际内存大得多的存储器，称为 虚拟存储器。

        虚拟存储器的特性
            多次性，是指无需在作业运行时一次性地全部装入内存，而是允许被  分成多次调入内存运行。
            对换性，是指无需在作业运行时一直常驻内存，而是允许在作业的运行过程中，进行   换进和换出。
            虚拟性，是指从逻辑上扩充内存的容量，使用户所看到的内存容量，远大于实际的内存容量。
             
        虚拟内存中，允许将一个作业分多次调入内存。
            釆用连续分配方式时，会使相当一部分内存空间都处于暂时或“永久”的空闲状态，造成内存资源的严重浪费，而且也无法从逻辑上扩大内存容量。
        虚拟内存的实现需要建立在   离散分配   的内存管理方式的基础上。
        虚拟内存的实现有以下三种方式：
            请求分页存储管理。   demand paging
            请求分段存储管理。   segment
            请求段页式存储管理。 segment page

            不管哪种方式，都需要有一定的 硬件支持.
            一般需要的支持有以下4个方面
                一定容量的内存和外存
                页表 Page Table 机制, 作为主要的数据结构 
                (缺页)中断机制 Page Fault , 当用户程序要访问的部分尚未调入内存, 则产生中断
                地址变换机构, 逻辑地址到物理地址的变换  Demand Paging







什么是内存泄漏？如何避免内存泄露？
        对于应用程序来说，当对象已经不再被使用，但是Java的垃圾回收器不能回收(对象仍被引用着)它们的时候，就产生了内存泄露。
            对象A引用对象B，A的生命周期（t1-t4）比B的生命周期（t2-t3）要长，当B在程序中不再被使用的时候，A仍然引用着B。
            在这种情况下，垃圾回收器是不会回收B对象的，这就可能造成了内存不足问题
        如何避免


大端存储 vs 小端存储
        1) Big-Endian   就是高位字节排放在内存的低地址端，低位字节排放在内存的高地址端。
            PowerPC、IBM、Sun
        2) Little-Endian    就是低位字节排放在内存的低地址端，高位字节排放在内存的高地址端。
            X86、DEC

        一般 操作系统都是小端，而通讯协议是大端的.

        e.g.
        16bit宽的数 0x1234
               内存地址        小端       大端 
            低 0x4000      低 0x34    高 0x12
            高 0x4001      高 0x12    低 0x34

        大端存储 因为第一个字节就是高位，从而很容易知道它是正数还是负数，对于一些数值判断会很迅速。
        小端存储 第一个字节是它的低位，符号位在最后一个字节，
            这样在做数值四则运算时从低位每次取出相应字节运算，最后直到高位，并且最终把符号位刷新，这样的运算方式会更高效。


        如何判断 :
            BOOL IsBigEndian()
            {
                int a = 0x1234;
                char b =  *(char *)&a;  //通过将int强制类型转换成char单字节，通过判断起始存储位置。即等于 取b等于a的低地址部分
                if( b == 0x12)
                {
                    return TRUE;
                }
                return FALSE;
            }

            Java(Java隐藏了 不允许依赖于大小端的代码,但是可以查)
                if(ByteOrder.nativeOrder()== ByteOrder.BIG_ENDIAN)
                    System.out.println("big endian");
                else System.out.println("little endian");
                
        如何转换 :
            uint32_t reversebytes_uint32t(uint32_t value){
                return (value & 0x000000FFU) << 24 | (value & 0x0000FF00U) << 8 | 
                    (value & 0x00FF0000U) >> 8 | (value & 0xFF000000U) >> 24; 
            }
        移位运算
            将低8位（0~8位）左移24位 变成了高8位（24~32位）+
            8~16位 左移8位 变成了（16~24位）+
            将原高8位和高16位右移 变成了新的低8位和低16位。


编译有依赖，怎么做到有序编译 =====> 拓扑排序


外部排序
        有一个1G文件含字符串, 内存只有100M，怎么排序 ======> 外部排序
                外部排序指的是 大文件 的排序，即待排序的记录存储在外存储器上，待排序的文件无法一次装入内存，
            需要在内存和外部存储器之间进行多次数据交换，以达到排序整个文件的目的。

            外部排序最常用的算法是 多路归并排序
                即将原文件分解成多个能够一次性装入内存的部分分别把每一部分调入内存完成排序。然后，对已经排序的子文件进行归并排序

            一般提到排序都是指 内排序，e.g. : 快速排序，堆排序，归并排序等，所谓内排序就是可以在 内存RAM 中完成的排序





linux
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


--------------------------------
linux 查看 内存
--------------------------------
"top"
top命令能够实时显示系统中各个进程的资源占用状况，类似于Windows的任务管理器


--------------------------------
linux中查看某个端口（port）是否被占用
--------------------------------


"lsof -i:端口号" 查看某个端口是否被占用 
    # lsof -i:8000

或者 
这里面的 t u 代表 tcp udp

netstat -ntlp                   //查看当前所有tcp端口·
netstat -ntulp |grep 80         //查看所有80端口使用情况·
netstat -ntulp | grep 3306      //查看所有3306端口使用情况·


要杀掉对应的进程可以使用 kill 命令

"kill -9 进程ID"
    # kill -9 26993
