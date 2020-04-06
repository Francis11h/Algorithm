自己写的一个类 多线程环境下 要用 

class MyData {
    volatile int number = 0;
    void addTo60() {
        this.number = 60;
    }
    //此时 number前面 是加了 volatile 修饰的 non-atomic 不保证原子性
    void addPlusPlus() {
        this.number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}



源码
----------------------------------------------------------------------------------------------------------------
"getAndIncrement() 源码"

/**
 * Atomically increments by one the current value.
 *
 * @return the previous value
 */
public final int getAndIncrement() {
	// this = 当前对象, valueOffset = 内存偏移量, 1 = 固定值 + 1
    return unsafe.getAndAddInt(this, valueOffset, 1);
}


"getAndAddInt()源码 "
//先获取 再加  var1 = this, var2 = valueOffset var4 = 1
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {	// 先do 是因为要先获得
        var5 = this.getIntVolatile(var1, var2);		//解决get操作 从主物理内存拿值
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));	// 如果 var2 == var5 则可以修改 如果修改成功 就跳出循环

    return var5;
}
---------------------------------------------------------------------------------------------------------------


首先为什么使用 getAndIncrement() 方法 能保证原子性？ 

	AtomicInteger类中
	这几行就巨关键

	private static final Unsafe unsafe = Unsafe.getUnsafe();
	private static final long valueOffset;
	static {
	    try {
	        valueOffset = unsafe.objectFieldOffset
	            (AtomicInteger.class.getDeclaredField("value"));
	    } catch (Exception ex) { throw new Error(ex); }
	}
	private volatile int value;

	该类的构造方法
	public AtomicInteger(int initialValue) {
	    value = initialValue;
	}

	1. Unsafe 类是java jar包 自带的类 是CAS的核心类 该类所有的方法就是 native 的
	2. 变量valueOffset 改变量表示在内存中的偏移地址, 因为 Unsafe 就是根据内存便宜地址获取数据的 
	3. value变量 由于有 volatile 其他线程就都可见


再接着  CAS 是一条 CPU并发原语 它的功能是 判断内存某个位置的值是否为预期值, 如果 是 则更改为新值, 如果 不是 则不能更改, 这个过程是原子的。
CAS并发原语体现在JAVA语言中就是 sun.misc.Unsafe类中的各个方法
调用Unsafe类中的CAS方法, JVM会帮我们实现 CAS汇编指令。 
由于它是属于操作系统用语范畴, 是由若干条指令组成的, 用于完成某个功能的一个过程, 
并且原语的执行必须是连续的 在执行过程中不允许被中断(类比习大大的车队 谁敢加塞儿？), 所以不会造成数据不一致的问题 保证了原子性









自己写的一个类
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // main done something
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t current data: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 6) + "\t current data: " + atomicInteger.get());
    }
}

-------------
源代码
/**
*	Atomically sets the value to the given updated value
* 	if the current value {@code ==} the expected value.
*/
public final boolean compareAndSet(int expect, int update) {
    return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
}



