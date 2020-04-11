阻塞队列

一句话: 是个队列, 当队列空 从队列中获取元素 的操作会被阻塞, 当队列满 往队列中插入元素 的操作会被阻塞.

--->   MQ 消息中间件的核心底层原理


-----------------------------------------------------------------------------------------------------  
为什么要用 blocking queue

pros: 不需要关心 什么时候 需要阻塞wait线程 什么时候需要唤醒notify线程 一切由 blocking queue包办 


用在哪里 
1. 生产者 消费者模式
2. 线程池
3. 消息中间件





---------------------
种类 核心三种  总共7种
---------------------    
    
ArrayBlockingQueue      由数组结构组成的有界阻塞队列
LinkedBlockingQueue     由链表结构组成的有界阻塞队列 (但是大小默认为 Integer.MAX_VALUE 可以认为是无界的)
SynchronousQueue        不存储 元素的阻塞队列, 即单个元素的队列
                        即专属个人定制版  队列属于一个人 你生产了之后 我不取走 你不生产第二个, 我取走了 你马上生产第二个 
                        永远是生产一个消费一个有且只有一个

线程池的底层实现 用这三个



------- 
核心方法
------- 

方法类型    抛出异常        特殊值       阻塞         超时
插入        add(e)        offer(e)    put(e)      offer(e, time, unit)
移除        remove()      poll()      take()      poll(time, unit)
检查队首     element()     peek()      不可用       不可用



抛出异常 
    当队列满时 再往队列里add插入元素 会抛异常 java.lang.IllegalStateException: Queue full
    空了再取 也报异常       java.util.NoSuchElementException
    查元素 没有也报    查的是空不空 不空返回队首元素

特殊值
    。。。
阻塞
    。。。 不合法的话会阻塞 消息中间件会用到 阻塞着
超时
    blockingQueue.offer("a", 2, TimeUnit.SECONDS);

---------------------
SynchronousQueue 具体
---------------------

BlockingQueue<String> bq = new SynchronousQueue<>();

bq.put("a");
bq.take();




---------------
生产者 消费者模式
---------------

传统版
------------------


 *
 * 题目 一个初始值为0的变量 两个线程对其交替操作 一个加1一个减1 来5轮
 *  加一减一  一看就是 生产者消费者模式
 *

 *  多线程的企业级模版口诀
 *      1。 高内聚低耦合的情况下 线程 操纵 资源类
 *      2。 判断 干活 唤醒通知
 *      3。 严防多线程并发状态下的 虚假唤醒  "多线程的判断一定用while" 不是 if 官方Object类种说了wait方法必须用在loop中
 *
 *   synchronized 被 lock      替代
 *   wait         被 await     替代
 *   notify       被 signal    替代





阻塞队列版
------------------

https://github.com/Francis11h/Algorithm/blob/master/基础知识%20tech%20BQ/生产者%20消费者模式.java

volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用





