1195. Fizz Buzz Multithreaded

Implement a multithreaded version of FizzBuzz with four threads. 
The same instance of FizzBuzz will be passed to four different threads:


Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
Thread D will call number() which should only output the numbers.


解法1 用 semaphore 同 1115


class FizzBuzz {
    private int n; 

    Semaphore fizz = new Semaphore(0);
    Semaphore buzz = new Semaphore(0);
    Semaphore fizzbuzz = new Semaphore(0);
    Semaphore number = new Semaphore(1);

    public FizzBuzz (int n) {
        this.n = n;
    }

    // printBuzz.run() outputs "buzz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                releaseNext(i + 1);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                releaseNext(i + 1);
            }
        }
    }

     // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                releaseNext(i + 1);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
               number.acquire();
                printNumber.accept(i);
                releaseNext(i + 1); 
            }
        }
    }

    public void releaseNext(int i) {
        if (i <= n) {
            if (i % 3 == 0 && i % 5 == 0) fizzbuzz.release();
            else if (i % 3 == 0) fizz.release();
            else if (i % 5 == 0) buzz.release();
            else number.release();
        }
    }
}







方法二 用 Guarded Blocks

https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html


必须是 synchronized 方法

while (true) {
    while (不满足的条件) {
        wait();
    }
    //do something
    notifyAll();
}


In synchronized methods, a thread is allowed to keep running until it either leaves the method or it calls wait(). 
No other thread can run until this happens. 
This means that the thread can ensure it only ever gives up control if it is no longer its turn. 
这就是 为什么 我们要用 synchronized 的原因 因为 不用的话 会导致混乱
This is important for maintaining integrity of the current data state. 
In effect, it means we can give the illusion of things being atomic where there‘s multiple threads.

Within our program, "context switches" are tightly controlled.


class FizzBuzz {
    private int n;
    private int counter = 1;
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            while (counter <= n && (counter % 3 != 0 || counter % 5 == 0)) {
                wait();
            }
            if (counter > n) break;
            printFizz.run();
            counter++;
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            while (counter <= n && (counter % 5 != 0 || counter % 3 == 0)) {
                wait();
            }
            if (counter > n) break;
            printBuzz.run();
            counter++;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            while (counter <= n && (counter % 3 != 0 || counter % 5 != 0)) {
                wait();
            }
            if (counter > n) break;
            printFizzBuzz.run();
            counter++;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            while (counter <= n && (counter % 3 == 0 || counter % 5 == 0)) {
                wait();
            }
            if (counter > n) break;
            printNumber.accept(counter);
            counter++;
            notifyAll();
        }
    }
}


Threads can be in one of 4 states.
State 1) Currently running.
State 2) Waiting to obtain the monitor so it can run (This is a different type of blocking.)
State 3) Blocked, waiting to be notified.
State 4) Exited, will never run again.

2 and 3 are not the same thing, even though they seem similar. 
There are actually 2 different types of blocking / locking happening here.
a) A thread is not blocked, but it needs the monitor to be allowed to run    锁池状态 
b) A thread is actually blocked (called wait()) so will only run after notifyAll() has been called. 等待队列 This is state 3.
等待队列 通过 notify() 变为锁池状

In the standard model, the scheduler is able to move a thread from 1 to 2 and then another from 2 to 1 whenever it pleases. 
But synchronized prevents this from happening.
(And this distinction is probably why this feature of Java confuses so many people)

So now, it is useful to think about HOW threads move between the different states. 

a) All threads start in state 2.  i.e. they arenot blocked, but they do need to wait for the monitor.
b) When a thread in state 1 actually leaves the synchronized method, it releases the monitor, so any thread in state 2 (waiting) will be picked to run.
c) When a thread in state 1 (running) calls wait(), it puts itself into state 3 (blocked). 
    This releases the monitor (very important to realise, some people assume it holds the monitor while waiting), 
        and so any thread in state 2 (waiting) will be picked to run next.
d) When a thread calls notifyAll(), all threads that were in state 3 (blocked) are moved into state 2 (waiting). 
    This is important to understand. 
    They aren't actually being given the "right" to run immediately, they are just being moved out of state 3 and into state 2, and so will be eligible to run when transition b or c happens.


The transition that does not exist is arbitrary moves between 1 and 2. T
The scheduler is not allowed to force a thread in state 1 to stop running.
The thread has the right to run until it either calls wait() or exits the synchronized method. 
This gives us a lot of control over the state of the program.


