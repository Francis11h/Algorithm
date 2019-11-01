synchronized 使用的必要性证明


package haveFunWithThread;

/**
 * 通俗的例子 来表示 synchronized的必要性
 * 在这个程序中 Account 的 amount 会同时被多个线程 访问并且 修改， 这就是一个竞争资源
 * 为了 避免 一个线程的改动 被另一个线程所覆盖 所以 我们要对 所有对amount的修改访问 进行同步 即加上 synchronized关键字
 */
class Account {

    private String name;
    private float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return amount;
    }

    /**
     * 之所以要用一个临时变量首先存储 然后 sleep一段时间 然后再重新赋值给 amount
     * 是为了 模拟真是运行时的情况， 因为真是系统中 账户信息肯定存储在持久的媒介中 比如 RDBMS
     * 此处的 sleep相当于比较耗时的 数据库操作
     * 最后把 temp赋值给amount 相当于把 amount的改动写入数据库中
     * @param amt
     */
    public void deposit(float amt) {
        float temp = amount;
        temp -= amt;

        try {
            //模拟其它处理所需要的时间，比如刷新数据库等
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount = temp;
    }

    public void withdraw(float amt) {
        float temp = amount;
        temp += amt;

        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount = temp;
    }


}

public class Bank {
    private static int NUM_OF_THREAD = 1000;
    static Thread[] threads = new Thread[NUM_OF_THREAD];

    public static void main(String[] args) {
        Account acc = new Account("Francis", 1000.0f);

        for (int i = 0; i < NUM_OF_THREAD; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    acc.deposit(100.0f);
                    acc.withdraw(100.0f);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < NUM_OF_THREAD; i++) {
            try {
                // 等待 该线程运行结束 for走完 即所有的子线程就都结束了
                System.out.println(i + " " + Thread.activeCount());
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("Finally, %s's balance is: %f", acc.getName(), acc.getBalance()));
    }
}










/**
 * Java synchronized keyword marks a block or method a critical section.
 *
 * A critical section is where one and only one thread is executing at a time,
 * and the thread holds the lock for the synchronized section.
 *
 * Java synchronized block ||  Java synchronized method
 *
 */


lockObject is a reference to an Object whose lock associates with the monitor that the synchronized statements represent.

When a thread wants to execute synchronized statements inside the synchronized block,
It must acquire the lock on the lockObject‘s monitor.
At a time, only one thread can acquire the monitor of a lock object, so all other threads m,ust wait till this thread , currently acquired the lock, finish it‘s execution.

In this way, synchronized keyword guarantees that only one thread will bee executing the synchronized block statements at a time,
and thus prevent multiple threads from corrupting the shared data inside the block.

keep in mind that if a thread is put on Sleep(), then is does not release the lock.
At this sleeping time, no thrrad will be executing the synchronized block statements.


语法syntax
synchronized (lockObject) {
    // synchronized statements
}

例子 

1. 不加 synchronized 即两个线程可以随机访问 

package haveFunWithThread;
class  MathClass {
    void printNumbers(int n) throws InterruptedException {
//        synchronized (this) {
            for (int i = 1; i <= n; i++) {
                System.out.println(Thread.currentThread().getName() + " :: "+  i);
                Thread.sleep(500);
            }
//        }
    }
}
public class SyncTest {
    public static void main(String args[]) {
        MathClass mathClass = new MathClass();

        // first thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    mathClass.printNumbers(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // public Thread(Runnable target, String name)
        // Allocates a new object.
        // target : the object whose {@code run} method is invoked when this thread
        new Thread(r, "ONE").start();
        new Thread(r, "TWO").start();
    }
}

输出 就是 乱序
TWO :: 1
ONE :: 1
TWO :: 2
ONE :: 2
ONE :: 3
TWO :: 3


2. 加上 synchronized 即 一个线程要等前面一个结束 才可以访问 synchronized block
only one thread is allowed access and other thread has to wait until first thread is finished.





package haveFunWithThread;
class  MathClass {
    void printNumbers(int n) throws InterruptedException {
        synchronized (this) {
            for (int i = 1; i <= n; i++) {
                System.out.println(Thread.currentThread().getName() + " :: "+  i);
                Thread.sleep(500);
            }
        }
    }
}
public class SyncTest {
    public static void main(String args[]) {
        MathClass mathClass = new MathClass();

        // first thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    mathClass.printNumbers(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // public Thread(Runnable target, String name)
        // Allocates a new object.
        // target : the object whose {@code run} method is invoked when this thread
        new Thread(r, "ONE").start();
        new Thread(r, "TWO").start();
    }
}

按线程输出

ONE :: 1
ONE :: 2
ONE :: 3

TWO :: 1
TWO :: 2
TWO :: 3







Java synchronized method

The general syntax for writing a synchronized method is as follows. 
Here lockObject is a reference to an Object whose lock associates with the monitor that the synchronized statements represent.

语法syntax
<access modifier> synchronized method( parameters ) {
    // synchronized code
}


Similar to synchronized block, a thread MUST acquire the lock on the associated monitor object with synchronized method.
In case of synchronized method, the lock object is –

    1. ‘.class’ object – if the method is static.
    2. ‘this’ object – if the method is not static. 
        ‘this’ refer to reference to current object in which synchronized method is invoked.






package haveFunWithThread;
class MathClass2 {
    // synchronized 直接写在方法上
    synchronized void printNumbers(int n) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            System.out.println(Thread.currentThread().getName() + " :: "+  i);
            Thread.sleep(500);
        }
    }
}

public class SyncMethod {
    public static void main(String args[]) {
        MathClass2 mathClass = new MathClass2();

        // first thread
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    mathClass.printNumbers(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        
        new Thread(r, "ONE").start();
        new Thread(r, "TWO").start();
    }
}


ONE :: 1
ONE :: 2
ONE :: 3
 
TWO :: 1
TWO :: 2
TWO :: 3






