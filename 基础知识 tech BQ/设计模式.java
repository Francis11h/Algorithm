Design Pattern
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
设计模式 是站在 软件结构和软件系统来看待的 和特定语言无关

设计模式 一共三种类型 
1. 创建型模式: 从创建的角度来看。 						单例模式, 原型模式, 工厂模式 抽象工厂模式 建造者模式
2. 结构型模式: 站在软件的结构 怎么有伸缩性 弹性 扩展性。    装饰模式 代理模式
3. 行为型模式: 站在 方法这个角度   						模版方法模式



-------- 
单例 模式
-------- 
单例模式 singleton
采取一定的方法: 目的是 让整个软件系统系统里面的 某个类的 只能存在"一个对象实例" 
	且该类只提供一个获取其对象的方法, 该方法一般是静态的.

八种 
1.饿汉式 静态常量
2.饿汉式 静态代码块
3.懒汉式 线程不安全
4.懒汉式 线程安全 同步方法
5.懒汉式 线程安全 同步代码块
6.双重检查
7.静态内部类
8.枚举

---------------
1.饿汉式 静态常量

1) 构造器私有化 "make constructir private" 
	目的是 防止通过 new 来得到一个 实例 就是让 new 失效 因为 类外 访问不到
2) 类的内部创建对象
	该对象的类型 就是该类  
3) 向外暴露一个静态static的公共方法 一般命名为 getInstance()
	返回该内部对象

pros:类加载的时候 就完成了实例化 因此 避免了线程同步的问题
cons:没有达到 懒加载效果 lazy loading, 即 如果从始至终没有用过这个实例 那就会造成内存浪费


class Singleton {
    private Singleton(){};
    private final static Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance;
    }
}

public class SingletonHungerManStaticVariable {
    public static void main(String[] args) {

        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        //判定是否相同 双等号 == 比的是 内存地址
        System.out.println(instance1 == instance2);
        System.out.println("instance1.hashCode = " + instance1.hashCode());
        System.out.println("instance2.hashCode = " + instance2.hashCode());
    }
}


---------------
3.懒汉式 线程不安全

class Singleton {
    private Singleton() {};
    private static Singleton instance;

    //提供一个 静态方法 当使用到该方法时采取创建 instance 即懒汉式
    public static Singleton getInstance() {
        if (instance == null) { //等于null代表还没创建 此时才去创建它
            instance = new Singleton();
        }
        return instance;
    }
}

pros: 确实起到了 lazy loading 懒加载的效果, 因为 用到才创建 无浪费
cons: 但是只能在 单线程使用 
		因为 如果一个线程进入了 if (instance == null) 还没继续往下执行 
		而 此时 另一个线程 也进通过了这个判断 那就会产生多个 实例  所以 实际开发中并不能使用。


怎么变得多线程安全呢？ 给方法加个 synchronized 即可 
--------------------------------------
4.懒汉式 线程安全 加了synchronized同步方法

public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    public static synchronized SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
//        单线程  main线程的操作
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //多线程呢
        for (int i = 1; i <= 10; i++) {
            new Thread(SingletonDemo::getInstance, String.valueOf(i)).start();
        }
    }
}

pros: 保证线程安全
cons: 重锁 效率低



--------------------------------------
5.懒汉式 线程安全 加了synchronized同步代码块儿 

错误的版本 无法实现线程安全
class Singleton {
	private Singleton() {};
	private static Singleton instance;
	public static Singleton getInstance() {
		if (instance == null) {					//在这里判断时 它有可能多个线程已经进来了 
			synchronized(Singleton.class) {		//所以在此加同步没有实际意义
				instance = new Singleton();
			}
		}
		return instance;
	}
}

正确的版本 在 synchronized代码块儿前后 双重检查
class Singleton {
	private Singleton() {};
	private static Singleton instance;
	public static Singleton getInstance() {
		if (instance == null) {					
			synchronized(Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}		 
			}
		}
		return instance;
	}
}



--------------------------------------
6.DCL 双重检查 防止因 指令重排导致的 万分之一可能的错误

class Singleton {
	private Singleton() {};
	private static volatile Singleton instance;
	public static Singleton getInstance() {
		if (instance == null) {					
			synchronized(Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}		 
			}
		}
		return instance;
	}
}

优点：线程安全、调用效率高、能延迟加载。



-------- 
原型模式
-------- 
解决的是 怎么克隆一个对象 深拷贝 还是浅拷贝



--------
装饰模式
--------
解决 类爆炸的问题 防止 很多很多类



----------
模版方法模式
----------
怎么设计方法更合理




