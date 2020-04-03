JAVA 国内面试 基础知识点

Java支持多继承么
	绝不

-----------
Abstraction
-----------
In java, abstraction is achieved by interfaces and abstract classes

Java接口的作用
	针对这个 很通俗的问题
		例如我定义了一个接口, 但是我在继承这个接口的类中还要写接口的实现方法,
			那我不如直接就在这个类中写实现方法岂不是更便捷,还省去了定义接口

		回答:
		接口就是个 招牌
		比如 我去旅游 看到一个店 上面挂着 McDonald‘s -----> 我就知道 今天午饭有着落了
		McDonald‘s 就是 接口, 我们看到了这个接口, 就知道这个店会卖炸鸡腿和汉堡 (实现接口)

		为什么 我们必须要去定义一个接口, 这个店可以直接卖炸鸡腿啊(直接实现方法) 是的 这个店可以直接卖
		但是 没有挂招牌 所以我们就不能简单粗暴的直接冲进去叫服务员 给我个汉堡鸡腿套餐了

		要么 我们就要进去问: 你们这里卖不卖汉堡啊 卖不卖鸡腿啊 (这就是反射)
		很显然 这样子一家家的问 很麻烦 (反射性能很差)

		要么我们就要记住 某条街道多少号卖炸鸡 (硬编码), 很显然 我们要记住很多东西 (代码量剧增)
		而且 如果有新的店卖炸鸡腿 我们也不可能知道 (不利于扩展)



	Java 中, 接口类型可用来声明一个变量, 他们可以成为一个空指针, 或是被绑定在一个以此接口实现的对象
    
	    比如
	    @Autowired                                
		private CategoryService categoryService;  

		CategoryService 就是一个接口 用它来声明的变量叫 categoryService

		一个接口类型的引用reference 指向了 一个实现给接口的对象object 这是Java中多态现象的一种体现
		Java中接口不能被实例化 但是可以通过接口引用 指向一个对象 这样通过接口来调用方法可以屏蔽掉具体的方法实现

		接口是比抽象类更抽象的 

		abstract class 就是 不允许被new出来的类 因为更加抽象 但是它可以作为 引用类型

			举个例子
			abstract public class Canine extends Animal {	//犬类动物
				public void roam();	//徘徊
			}

			public class MakeCanine {
				public void go () {
					Canine c;
					c = new Dog();		正确 ✅ 因为 可以 赋值子类对象(new Dog()) 给 父类的引用(c), 对象 都是new出来的
					c = new Canine;		错误 ❌ 因为 抽象类不可以被new
				}
			}


------------
Polymorphism
------------
Java 多态 		
	多态指的是同一个事物等在不同情况下不同的表现, 体现在程序中 有三种
	1.统一的接口不同的实现

		举个例子 List是接口 指向 list 这个对象 (父类引用指向子类对象), 该对象可以用不同的 实现类 来new出来
			List<String> list = new ArrayList<>();
			List<String> list = new LinkedList<>();

	2. 子类 继承 父类 进行方法重写 Override
		子类 中定义某方法与其父类有相同的名称和参数, 我们说该方法被重写	
		Overriding means having two methods with the same method name and parameters
		One of the methods is in the Parent-class and the other is in the Child-class

	3. 同一个类中进行方法重载 Overload
		在一个类(1 1 1 一个类)中定义了多个同名的方法,它们或有不同的参数个数或有不	同的	参数类型
		Overloading occurs when two or more methods in One-class have the same method name but Different Parameters.

-------------------------
Inheritance Encapsulation
-------------------------

Java 继承 inheritance		Son extends Father 	||  Dog extends Animal
	 封装 encapsulation		改变 可访问的权限  private 变量 public getter() & setter() 










keyWords
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------------------
Comparable and Comparator 
-------------------------
if sorting of objects needs to be based on natural order then use Comparable 
	whereas if you sorting needs to be done on attributes of different objects, 
		then use Comparator in Java.

Comparable相当于“内部比较器”, 而Comparator相当于“外部比较器”


Comparable
	若一个类实现了Comparable接口(实现该接口 意味着重写 "compareTo" 方法), 就意味着“该类支持排序”
	List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序

	public interface Comparable<T> {
	    public int compareTo(T o);
	}

	A comparable object is capable of comparing itself with another object. 
	The class itself must Implements the java.lang.Comparable interface to compare its instances.

Comparator 
	Comparator 是比较器接口

	public interface Comparator<T> {

	    int compare(T o1, T o2);

	    boolean equals(Object obj);
	}

	我们若需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)；那么，我们可以建立一个“该类的比较器”来进行排序。这个“比较器”只需要实现Comparator接口即可。


	//378. Kth Smallest Element in a Sorted Matrix 这道题 分别用了 Comparable / Comparator 构建 heap
	// 179. Largest Number 用了 Comparator / 建类的Comparator

-------
Static
-------

Static Variables. 全局变量
	Static variables are, essentially, global variables 就是全局变量
	All instances of the class share the same static variable. 一个类所有的实例共用这一个变量

同时 static声明的方法 不能调用 非static 声明的方法或变量

如果一个类要被声明为static的，只有一种情况，就是静态内部类 "static-nested-class"		Only nested classes can be static.


------------
Nested class
------------
官方定义 嵌套类(定义在别的类里面的类) 
Nested classes are divided into two categories: static and non-static. 
Nested classes that are declared static are simply called static nested classes. 
Non-static nested classes are called inner-classes.	//


how to instantiate??

	static inner classes can be initialized separately	//静态内部类可以单独初始化
		Inner i = new Outer.Inner();

	inner-class can only be initialized by the instance of the outer class // 普通的内部类不行 必须得有外部类的引用
		Outer o = new Outer();
		Inner i = o.new Inner();


区别 
	1.Nested static class doesn’t need reference of Outer class, 
		but Non-static nested class(Inner class) requires Outer class reference.
	2.Inner class(or non-static nested class) can access both static and non-static members of Outer class. 
		A static class cannot access non-static members of the Outer class. 
		It can access only static members of Outer class.




----------------------
Final/Finally/Finalize
----------------------

Final is a keyword.	Finally is a block.	Finalize is a method.

Final is used to apply Restrictions on class, method or variable.
	final variable value can‘t be changed   //final声明引用， 就不可改变这个引用了,不可再次初始化。
	final method can‘t be overridden 		//final声明方法，这个方法不可被子类重写overried。
	final class can’t be inherited 		    // final声明类， 这个类不能被继承


Finally is used to place important code, 
	it will be executed Whether Exception is handled or not.
	//Finally一般与try一起使用，在程序进入try块之后，无论程序是因为异常而中止或其它方式返回终止的, finally块的内容 一定会被执.
	//finally在return语句之后，跳转到上一级程序之前执行.

Finalize() is used to perform clean up processing just before object is garbage collected.




--------------------------
checked / unchecked exception
--------------------------
Java中的两种异常类型是什么？他们有什么区别？

派生于 Error 或者 RuntimeException 的异常称为unchecked异常 严重 无法控制
所有其他的异常成为checked异常 


“Throwable” is the parent class of the classes Error and Exception. 
 The class “RuntimeException” and its subclasses, the class “Error” and its child classes are the “Unchecked exceptions” 

 whereas, the remaining subclasses of the class “Exception” except “RuntimeException” are the checked exceptions. 

 The basic difference between checked and unchecked exception is that 
 	the checked exceptions are checked by the compiler whereas, 
 	the compiler does not check the unchecked exceptions.



---------------
Throws vs Throw
---------------

Java的异常处理是通过5个关键字来实现的：try，catch，throw，throws，finally

  Throws: Lists the exceptions a method could throw.  
		throws总是出现在一个函数头中，用来标明该成员函数可能抛出的各种异常。
			对大多数Exception子类来说，Java 编译器会强迫你声明在一个成员函数中抛出的异常的类型。
			如果异常的类型是Error或 RuntimeException， 或它们的子类，这个规则不起作用， 因为这在程序的正常部分中是不期待出现的。
			如果你想明确地抛出一个RuntimeException，你必须用throws语句来声明它的类型。  
	   
   
  Throw: Transfers control of the method to the exception handler. 
		throw总是出现在函数体中，用来抛出一个异常。
			程序会在throw语句后 立即终止........
			它后面的语句执行不到，然后在包含它的所有try块中（可能在上层调用函数中）从里向外寻找含有与其匹配的catch子句的try块



--------------------------------------
Enumeration接口 和 Iterator接口 的区别有哪些？
--------------------------------------

Iterator
	is an interface which belongs to collection framework.
	It allow us to traverse the collection, access the data element and remove the data elements of the collection.

	public interface Iterator<E> {
		boolean hasNext()
		Object next()
		void remove()
	}

	public interface Iterable<T> {
    	Iterator<T> iterator();
    	// Iterable 就是实现了 for each  的 补充版的 Iterator
    	// Iterable接口用于支持foreach的循环。Iterable接口只有一个方法，就是iterator()方法，返回集合的Iterator对象。所有实现Iterable接口的集合都可以使用foreach循环进行遍历。
    	default void forEach(Consumer<? super T> action) {
	        Objects.requireNonNull(action);
	        for (T t : this) {
	            action.accept(t);
	        }
	    }
	}

Iterators differ from enumerations in two ways:

	Iterators allow the caller to "remove elements" from the underlying collection during the iteration with well-defined semantics.
	在遍历过程中 允许删除元素
	
		Using Enumeration, you can Only traverse the Collection object. 
		But using Iterator, you can also remove an element while traversing the Collection.



Java容器中，所有的Collection子类会实现 Iteratable接口 以实现 foreach 功能
	Iteratable接口的实现 又依赖于 实现了Iterator的内部类





------- 
native
------- 

native 用来修饰方法 native方法称为 本地方法
只有函数声明 没有函数体, 即没有实际的内容
因为 该函数 是用 C/C++ 语言 在 另外的文件中编写,编写的规则遵循Java本地接口的规范(简称JNI)。
(简而言就是Java中声明的可调用的使用C/C++实现的方法)

比如 Thread类 当中有个 "private native void start0()" 方法


在 JVM 中 对应的是 
	1. 方法要执行 要先入栈: "本地方法栈 Native Method Stack"
	2. 方法出栈: 3java 无法实现了 因为就不是 java写的 然后求助于 "本地方法接口 Native Interface" (这个接口不是JVM的是我们的 操作系统 OS 的, C or C++的接口)
	3. 要访问第三方 比如 数据库 & Redis 我们 需要第三方的jar包 jdbc jedis, 这些 jar包 叫: 动态连接库。 这里就是图中的 "本地方法库"






JVM
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
HotSpot JDK的品牌
命令行里输:  java -version
会出现如下
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)

-------------------------------------------------
什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
-------------------------------------------------
JVM java virtual machine

JVM 是运行在 OS 之上的 它与硬件没有直接的交互  (使用“Java虚拟机” 就是为了支持与OS无关、在任何系统中都可以运行的程序)

-------------------"-------JVM---------"---------------------
															|
Class File    	------>			Class Loader 类加载器			|
									| |						|
									| |						|
	-------------------------------------------------		|						
	|		运行时数据区 Runtime Data Area				|		|
	|												|		|
	|	方法区			Java栈		本地方法栈		|		|
	|	Method Area		Stack 		Native Method   |		|
	|										Stack	|		|
	|	堆					PC寄存器					|		|
	|	Heap 		  Program Counter Register		|		|
	-------------------------------------------------		|
		| |					| |								|
		| |					| |								|
															|
		执行引擎												|
		Execution  --->	 本地方法接口							|
		Engine	   <---	 Native Interface 	<-- 本地方法库    |			   >>
															|
------------------------------------------------------------ 	


-------------------------------
java类是如何被虚拟机加载的？生命周期又是怎样的？
-------------------------------
通过 Class Loader 类加载器

Class Loader 只负责class文件的加载 至于其是否可以运行 由 Execution Engine 决定

			Class Loader
car.class -----------------> Car   ----->  再去实例化 Car car = new Car();
car.class 文件 通过 类加载器 加载到"内存"中 变成 Car Class 模版 


------------------
PC寄存器	(程序计数器)
------------------ 
每个 "线程" 都有一个 PC寄存器, 是"线程私有"的, 就是一个指针 
指向方法区中的 方法字节码(用来存储指向下一条指令的地址 = 即将要执行的指令代码)
由 执行引擎读取下一条指令 是一个非常小的内存空间 几乎可以忽略不计

类比 火车每节车厢之间的 连接器, 来保证 第一节车厢后面跟的是第二节 即 程序的执行顺序 


------------------
Method Area 方法区
------------------

所有线程"共享"

所有定义方法的信息 之前 大 Car Class 类被加载后 就暂存在这里

该类中所有的 方法 都要放进来 不管什么 public private 
同时 引进的什么jar包什么的也全存于此

静态变量(类变量) + 常量 + 类信息(构造方法, 接口定义...) + 运行时的常量 + ...

But
实例变量 存在 堆内存中 和方法区无关

----------
这部分内存什么时候回收？

由于该内存 存的都是 所有的 环境。 所以在JVM停下来的时候才会回收, 生理周期非常长。



----- 
栈 区
----- 
先进后出 后进先出
栈内存 主管java程序的运行 是"线程私有"的 在线程创建时创建 线程结束栈内存也就释放了 所以不存在垃圾回收

8种基本数据类型(字符型char, 布尔型boolean, 数值型int, long, byte, double, float, short) + 实例对象的引用变量 + 实例方法 


栈桢 frame(就是栈中的一条儿数据) 主要存3种数据
本地变量(local variables): 输入参数 和 输出参数 以及 方法内部的变量
栈操作(operand stack): 记录 出入栈的操作
栈帧数据(frame data): 包括 类文件，类方法等

连接栈帧的 指针 === PC寄存器 


----------------------------
java.lang.StackOverflowError

一直压栈不出栈就会
e.g. 没有出口的 递归 必爆
	public static void test1() {
		test1();
	}



-------------------------------
堆 Heap 的结构是什么样子的？什么是堆中的永久代(Perm Gen space)?
-------------------------------
堆 里面放的是 什么 ？ new new new 出来的那些 对象 

堆 内存分为三部分

Young Generation Space 		新生区
Tenure Generation Space 	养老区
Permanent Space 			永久区

Java 7 及以前 
新生区 又分为 伊甸区(所有刚new出来的对象都在这里) 幸存0区 幸存1区
新生区发生 普通的GC
经过 15次 GC 才能进去养老区, 即创建完的对象 经常用的 才能进入养老区 
养老区发生重量级的GC: full-GC

注意了!!!!!!!!!!!!!!
养老区 到 永久区 是没有路的 但凡 养老区内存满了 经过full-GC 也没办法回收内存的话 直接会报 OutOfMemoryError 内存溢出



"java.lang.OutOfMemoryError: Java heap space" 
原因:
1.  JVM的堆内存设置不够, 可以通过参数 -Xms(初始内存大小) -Xmx(最大内存大小) 来调整 （这两个参数也是参数调优中用得最多的两个）
2.  代码中创建了大量 大对象, 并且长时间不能被垃圾回收, 即一直存在被引用 需要专业工具解决




---------------- 
JVM中堆和栈的区别
---------------- 
	栈Stack
		存 变量 int i ，引用 Scanner sc 中的 sc
		存取速度快
		每个线程都有一个栈
		GC 较为频繁
	
	堆HEAP
		存 实例对象 object（new后面的东西）new ArrayList<>()
		存取速度慢，因为要在程序运行起来之后    动态分配内存
		同一进程内线程共用堆
		GC 不频繁（特定条件下会触发，比如堆空间不够用了）


---------------
JVM的永久代中会发生垃圾回收么？
-------------------------------


-------------------------------
在Java中，对象什么时候可以被垃圾回收？
-------------------------------







-------------------------------
如果对象的引用被置为null，垃圾收集器是否会立即释放对象占用的内存？
-------------------------------


-------------------------------
java的动态代理机制是怎样的？
-------------------------------





-------------------------------

-------------------------------

-------------------------------

-------------------------------

-------------------------------

-------------------------------


















finalize()方法什么时候被调用？析构函数(finalization)的目的是什么？
 

串行(serial)收集器和吞吐量(throughput)收集器的区别是什么？

异常处理完成以后，Exception对象会发生什么变化？




