---------------
生产者 消费者模式
---------------

传统版
------------------

/**
 *
 * 题目 一个初始值为0的变量 两个线程对其交替操作 一个加1一个减1 来5轮
 *  加一减一  一看就是 生产者消费者模式
 *
 *  多线程的企业级模版口诀
 *      1。 高内聚低耦合的情况下 线程 操纵 资源类
 *      2。 判断 干活 唤醒通知
 *      3。 严防多线程并发状态下的 虚假唤醒  多线程的判断一定用while 不是 if 官方Object类种说了wait方法必须用在loop中
 *
 *   synchronized 被 lock      替代
 *   wait         被 await     替代
 *   notify       被 signal    替代
 */


//资源类 尽量解耦
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1 判断
            while (number != 0) {
                //等待 不生产
                //等待 需要用condition
                condition.await();
            }
            // 2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t "+ number);
            // 3 通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1 判断
            while (number == 0) {
                //等待 不生产
                //等待 需要用condition
                condition.await();
            }
            // 2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t "+ number);
            // 3 通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Product_Consumer_TraditionalDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();
    }
}








阻塞队列版
------------------




package com.imooc.sell.product_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */

class MyResource {
    // 有多线程的交互必须要保证线程之间的可见性 用volatile
    // 多线程环境下 就不允许用 i++ ++i 一定会出错 刚才是加了锁 那没事儿。。
    private volatile boolean FLAG = true;                       //标志位  默认是开启的状态 即进行 生产 + 消费
    private AtomicInteger atomicInteger = new AtomicInteger();  //生产一个加一个  默认为0

    BlockingQueue<String> blockingQueue = null;
    // spring 里面 依赖注入 有两个 是必须要学会的 第一个 set get 设值注入
    // 第二个 构造注入 传的一定是 接口 传类就是菜鸡
    // 我怎么知道 你这个 blockqueue现在是什么?  从构造注入方法 传进来是哪个 就是哪个
    public MyResource (BlockingQueue<String> blockingQueue) {   //构造方法里写的传进来的是个接口  等下面传进来了 就确定了具体的实现类了
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());     // 写 足够抽象 查足够落地 往细节查
    }

    public void myProduct() throws Exception {
        String data = null;                          //这一定写外头 要不每次 while true 都新建引用 写外面才能保证复用
        boolean returnValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";        //高并发下的 i++ Atomically increments by one the current value.
            returnValue =  blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列 " + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列 " + data + "失败");
            }
            // 控制 这个 while 一秒钟生产一个 要不 疯狂打印。。。
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了， 表示 FLAG=false, 生产动作结束");
    }
    //消费
    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒没有取到蛋糕, 消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列中的蛋糕" + result + "成功");
        }

    }

    public void stop() throws Exception {
        this.FLAG = false;
    }
 }
public class Product_Consumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consume").start();

        //总共来个 5秒
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();

        System.out.println("5秒种时间到， 大老板 main线程叫停，活动结束");
        myResource.stop();
    }
}


