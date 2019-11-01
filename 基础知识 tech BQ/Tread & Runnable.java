
package haveFunWithThread;

class Thread1 extends Thread {
    //如果只是想 起一条线程 没有什么其它的特殊需求 那么可以用继承Thread
    private String name;

    Thread1(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " running : " + i);
        }

        try {
            sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


方式一：
    自定义一个类继承Thread.
    子类重写run方法，把自定义线程的任务定义在run方法上。
    创建thread子类的对象，并且调用start方法开启线程。
方式二：
    自定义一个类去实现Runnable接口。
    实现了Runnable接口的run方法， 把自定义线程的任务定义在run方法上。
    创建Runnable实现类的对象。
    创建Thread对象，并且把Runnable实现类对象作为参数传递进去。
    调用thread对象的start方法开启线程。
    
疑问1： Runnable实现类对象是线程对象吗？
    runnable实现类的对象并不是一个线程对象，只不过是实现了Runnable接口的对象而已。
疑问2： 为什么要把Runnable实现类的对象作为参数传递给thread对象呢？作用是什么？
    作用： 是把Runnable实现类的对象的run方法作为了任务代码去执行了。





class Thread2 implements Runnable {
    private String name;

    Thread2 (String name) {
        this.name = name;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " running : " + i);
        }

        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        new Thread1("A").start();
        Thread1 mTh2 = new Thread1("B");
        mTh2.start();
        // start
        System.out.println(Thread.activeCount());
        new Thread(new Thread2("C")).start();
        new Thread(new Thread2("D")).start();

    }
}





















