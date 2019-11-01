
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





















