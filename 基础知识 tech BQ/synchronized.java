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














