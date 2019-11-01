1115. Print FooBar Alternately

The same instance 同一个对象会被传到两个线程里 of FooBar will be passed to two different threads. 
Thread A will call foo() while thread B will call bar(). 
Modify the given program to output "foobar" n times.  即要保证顺序 总是 先foo后bar

 
如果是单线程很简单，顺序交替调用 foo() 和 bar() 各n次即可。
但是在多线程中，每个线程被执行的时间点并不能被控制，谁先谁后完全没有规律，
这就需要我们来对线程进行管理，控制他们的执行顺序才可以。

Semaphore 主要有两个方法：acquire()和release()，分别代表获取运行权限和释放运行权限

我们可以定义两个 Semaphore 对象s1和s2分别管理 foo() 和 bar() 的执行许可

当需要执行 foo() 时， s1 获取权限（s1.acquire），执行完打印操作后，
        为了不再继续执行 foo() 先不要释放s1的权限，而是释放s2的权限（s2.release）
        
        轮到 bar() 方法时获取s2的权限，执行完打印之后再释放s1的权限，一直循环到结束。


class FooBar {
    private int n;
    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);
    
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }
}