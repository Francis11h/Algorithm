JAVA 国内面试 基础知识点




------------------------
面向对象 面向过程, OOD的优点
------------------------
面向对象是什么: 面向对象是一种 "对现实世界理解和抽象的方法"，是计算机编程技术发展到一定阶段后的产物。
面向过程 (Procedure Oriented): 是一种 "以过程为中心" 的编程思想。
	这些都是以什么正在发生为主要目标进行编程，不同于面向对象的是谁在受影响。
	缺点 就出来了 "复用性不佳" "拓展性不易" "耦合度太高"
	与面向对象明显的不同就是 "封装 Encapsulation"、"继承 Inheritance"、"类 Class"。

面向过程:	 定义函数, 定义数据 然后各种函数 数据的操作
随着软件变得越来越大,代码量越来越多 ----> 带来两个问题  1.命名冲突 2.代码重复 ----> 解决

代码重复--->我们可以用 函数里面调用函数 (把某些通用功能的代码抽出来成一个独立函数) ---->  封装 模块儿化
命名冲突--->我们就把这一堆函数们进行分类, 比如 整数 小数 分数...等 ----> "类 Class" 的概念 就呼之欲出了

----------------- 
Encapsulation 封装
-----------------
封装 encapsulation 也就是把客观事物封装成抽象的类.

封装的好处:
1. 功能被封装成了类，通过基类与派生类之间的一些机制（组合和继承），来提高代码的"复用性" (这一条就是 OOD最明显的优点),可以 模块儿化
2. 隐藏实现细节，提供公共的访问方式
	改变 可访问的权限  private 修饰成员变量 ---> 为什么 用private --> 可以在赋值之前应该先对数据进行判断 保证安全性
		public 修饰 getter() & setter() 方法


-----------------
Inheritance   继承 
-----------------
Java 继承 inheritance		Son extends Father 	||  Dog extends Animal
继承的概念: 
	它可以使用现有类的所有功能，并在无需重新编写原来类的情况下对这些功能进行扩展。
	通过继承创建的新类称为「子类」或「派生类」，被继承的类称为「基类」、「父类」或「超类」

继承的好处:
1.继承是传递的，"易于在现有代码的基础上构造和扩充"。
2.简化对事物的描绘，使得"层次"更加清晰。
3.减少代码冗余。
4.提高可维护性。



例子:

Number 数 {
    检测
    加
    减
    乘
    除
}
Integer 整数 {
    沿用上面数的设计
}
Decimal 小数 {
    沿用上面数的设计
}

所谓继承inheritance，就是把 数 这个类的整体设计，沿用给 整数，分数小数 这些类，
作为他们的 编写大纲 去编写加减乘除这些函数的 具体代码。
根据整数，分数，小数各自的性质，做出各自的调整

这时 数 这个类，如果你给它里面的加减乘除函数的写了一些很粗糙简单的代码，就叫做"父类 parent class"
子类们 继承extends 了父类（把代码进行了复杂化）
如果没写，那这个类其实就只是个设计图，叫做"抽象类 abstract class"
子类们 实现implements 了抽象类（把空空的设计变成了具体代码）

如果我们需要一个能自动实现结果四舍五入的小数计算类，同时又需要一个不需要的，怎么办呢，难道要写两个类吗？不要。
所以做出了("Object" = "instance" "实例对象")这一东西

小数类 {
    标识变量：是否四舍五入
    标识变量：是否限定小数点后位数
    构造函数 Constructor（设置上面的标识）
    加（会根据上面两个标识变量输出不同结果）
    减（会根据上面两个标识变量输出不同结果）
    乘（会根据上面两个标识变量输出不同结果）
    除（会根据上面两个标识变量输出不同结果）
}
这样，我们就写一个类，但是通过构造函数，把一份代码，构造出了行为稍微有点不同的两个实例供我们使用
这时候名词来了，不能进行实例化微调化的类，叫做"静态类 static class"，函数们的行为是固定的。
不能实例化的类，其实只是函数们的一个集合归纳，只是对函数进行了整理，功能的强大和编码的自由灵活度是不够的。

能够进行实例化，变化出各种行为各自不大一样的实例的类，我们一般就把它们叫做"类class"了，因为最常见。

-----------------
Polymorphism 多态
-----------------
多态的概念: 多个不同的对象对同一消息作出响应。同一消息"根据不同的对象"而"采用各种不同的方法"。
		  简单说就是一句话: 允许将子类类型的指针 赋值给 父类类型的指针
多态的好处: 主要是"利于扩展"。

Java 多态 		
	多态指的是同一个事物等在不同情况下不同的表现, 体现在程序中 有三种
	1.统一的接口不同的实现 (多态是为了接口的重用)

		举个例子 List是接口 指向 list 这个对象 (父类引用指向子类对象), 该对象可以用不同的 实现类 来new出来
			List<String> list = new ArrayList<>();
			List<String> list = new LinkedList<>();

	2. 子类 继承 父类 进行方法 重写/覆盖 Override
	 	Runtime 运行时 决定
		子类 中定义某方法与其父类"有相同的名称和参数", 我们说该方法被重写	
		Overriding means having two methods with the same method name and parameters
		One of the methods is in the Parent-class and the other is in the Child-class

	3. 同一个类中进行方法重载 Overload
		Compile 编译时 决定
		在一个类(1 1 1 "一个类)中定义了多个同名的方法"),它们或有不同的参数个数或有不	同的	参数类型
		Overloading occurs when two or more methods in One-class have the same method name but Different Parameters.


--------------- 
接口  Interfaces 
--------------- 
Java接口的作用
	针对这个 很通俗的问题
		例如我定义了一个接口, 但是我在继承这个接口的类中还要写接口的实现方法,
			那我不如直接就在这个类中写实现方法岂不是更便捷,还省去了定义接口

		回答:
		"接口就是个 招牌 ----> 即 定义 这个东西 能干什么" 
		比如 我去旅游 看到一个店 上面挂着 McDonald‘s -----> 我就知道 今天午饭有着落了
		McDonald‘s 就是 接口, 我们看到了这个接口, 就知道这个店会卖炸鸡腿和汉堡 (实现接口)

		为什么 我们必须要去定义一个接口, 这个店可以直接卖炸鸡腿啊(直接实现方法) 是的 这个店可以直接卖
		但是 没有挂招牌 所以我们就不能简单粗暴的直接冲进去叫服务员 给我个汉堡鸡腿套餐了

		要么 我们就要进去问: 你们这里卖不卖汉堡啊 卖不卖鸡腿啊 (这就是反射)
		很显然 这样子一家家的问 很麻烦 (反射性能很差)

		要么我们就要记住 某条街道多少号卖炸鸡 (硬编码), 很显然 我们要记住很多东西 (代码量剧增)
		而且 如果有新的店卖炸鸡腿 我们也不可能知道 (不利于扩展)


	书面的理解
	Java 中, 接口类型可用来声明一个变量, 他们可以成为一个空指针, 或是被绑定在一个以此接口实现的对象
    
	    比如
	    @Autowired                                
		private CategoryService categoryService;  

		CategoryService 就是一个接口 用它来声明的变量叫 categoryService

		一个接口类型的引用reference 指向了 一个实现给接口的对象object 这是Java中多态现象的一种体现
		Java中接口不能被实例化 但是可以通过接口引用 指向一个对象 这样通过接口来调用方法可以屏蔽掉具体的方法实现


--------------------
抽象类 abstract class
--------------------
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

----------------- 
接口 和 抽象类 比较
-----------------
"接口"是比"抽象类"更抽象的

相同点 
	1. 都是 上层的抽象层 都实现了 java这门语言的 抽象功能
		In java, abstraction is achieved by "interfaces" and "abstract classes"
	2. 都不能被 实例化 即 都不能直接 new
	3. 都能包含抽象方法 (这些抽象方法 仅仅 用来描述 类所具备的功能, 而不提供具体实现)

不同
	1. 抽象类 中 可以写 非抽象方法 从而避免 在子类中重复书写 ---> "提高了代码的复用性" 这是 抽象类的优势
	   而 接口中 所有的方法 必须 都是抽象的 不能有任何具体的
	2. 一个类只能继承一个直接父类, 这个父类可以是具体的类也可是抽象类
	   而 一个类可以实现多个接口

所以 有了下面的问题的答案
---------------------- 
Java支持多继承么

绝不
类单继承的原因: 当子类 重写Override 父类方法的时候 即 子类隐藏父类的成员变量以及静态方法的时候,"JVM使用不同的绑定规则".
			如果一个类有多个直接的父类---> 那么会使绑定规则变得更复杂。
			所以为了简化软件的体系结构和绑定机制,java语言禁止多继承.
但是 java中
接口 可以多继承 即一个类可以实现多个接口
		是因为 接口中只有抽象方法 abtract method,没有静态方法 no static method 和 非常量的属性 no non-final attributes
		而 只有接口的实现类才会 重写Override 接口中方法
		因此一个类继承多个接口 也不会增加JVM的绑定机制和复杂度。


举例子: 如果Sparrow继承类Bird类, Boyin(波音)继承Airplane类
		Sparrow 和Boyin想使用 同样的fly方法那么是没有办法实现的 ---> 因为类的继承是单继承。
	   但是 如果Bird和Airplane是接口那么Sparrow和Boyin想使用同样的fly方法就很简单了
	   	再写一个接口让Sparrow和Boyin实现这个接口就可以

java中的实例:
		ArrayBlockingQueue由数组结构构成的有界阻塞队列  继承了 抽象队列 实现了 两个接口 阻塞队列接口 和 序列化接口

	   	public class ArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, java.io.Serializable { ..... }

--------------------------------------------
那 什么时候 用什么???  所以从 设计目的 区分

接口的设计目的----->是"对类的行为进行约束"（更准确的说是一种"有"约束，因为接口不能规定类不可以有什么行为）
	也就是提供一种机制，可以强制要求不同的类具有相同的行为。
它只约束了行为的有无，但不对如何实现行为进行限制。对 接口为何是约束 的理解，配合泛型食用效果更佳。

抽象类的设计目的----->是"代码复用"。
	当不同的类具有某些相同的行为(记为行为集合A)，且其中一部分行为的实现方式一致时（A的非真子集，记为B），可以让这些类都派生于一个抽象类。
	在这个抽象类中实现了B，避免让所有的子类来实现B，这就达到了代码复用的目的。
	而A减B的部分，留给各个子类自己实现。正是因为A-B在这里没有实现，所以抽象类不允许实例化出来（否则当调用到A-B时，无法执行）。

--------------------------------------------
更本质的解释 从java设计原则来看

接口是 "对动作" 的抽象, 而抽象类 则是"对根源"的抽象 


对于抽象类 比如男人 女人 我们可以设计 更高级别的抽象类 ---> 人
对于接口 我们可以 坐着吃饭,可以站着吃饭,可以用筷子吃饭,可以用叉子吃饭,甚至可以学三哥一样用手抓着吃饭
	那么 就可以把 这些吃饭的动作抽象成一个接口 ---> 吃饭
即 接口 定义男人可以干什么: 吃喝拉撒 抽象类 定义男人本质是什么: 人

所以 在高级语言中 (java C#) 中 一个类只能继承一个抽象类( 因为 你不能 既是生物又是非什么)
但是 一个类 可以实现多个接口 因为 我们人可以 开车, 吃饭, 啪啪啪 --> 开车接口, 吃饭接口, 啪啪啪接口






-------------------------
匿名类，以及虚拟机内部的实现
-------------------------
anonymous class 是一种特殊的内部类
1. 它没有类名 在定义类的同时就生成 该类的一个实例对象
2. 一次性使用的类

编译器会给一个 xxxx$1这样一个类名,它是编译器给定的名称


使用 
1. 不取名字 直接使用其父类或接口的名字
	也就是说 该类是父类的子类 or 实现了一个接口

Arrays.sort(intervals, new Comparator<int[]>(){
    public int compare(int[] a, int[] b) {
        return a[0] - b[0];
    }
});

这里的 new Comparator 就是一个 匿名类。。。。。


new Runnable() {
	public void run() {
			//.....
	}
}
new Runnable 也是 一个匿名类


Runnable 接口是 函数式接口 java8 开始 函数式接口必须要用 "lambda 表达式"
不用的话 匿名内部类 也行 但是 又丑又难看。。。

() -> {}
拷贝 小括号
写死 右箭头
落地 大括号







keyWords
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------- 
Objective类
----------- 
所有类的基石
public class Object {
	//先有个private的native方法 来注册下表示可以调用其他的native方法
	private static native void registerNatives();
	static {
	    registerNatives();
	}
	// Returns the runtime class of this {@code Object}
	// 返回当前运行时对象的Class对象
	1. public final native Class<?> getClass();
	// 该方法返回对象的哈希码
	// 如果2个对象使用equals方法进行比较并且相同的话，那么这2个对象的hashCode方法的值也必须相等。
	2. public native int hashCode();
	// 比较两个对象是否相等。Object类的默认实现，即比较2个对象的内存地址是否相等 即与 == 一样
	// 注意点：如果重写了equals方法，通常有必要重写hashCode方法，这点已经在hashCode方法中说明了。 String类中 两者都重写Override了
	3. public boolean equals(Object obj) { return (this == obj); }
    // 创建并返回当前对象的一份拷贝
    // 对于任何对象 x，表达式 x.clone() != x 为true，x.clone().getClass() == x.getClass() 也为true。
    4. protected native Object clone() throws CloneNotSupportedException;
    // toString方法  Object对象的默认实现，即 输出类的名字@实例的哈希码的16进制
    5. public String toString() { return getClass().getName() + "@" + Integer.toHexString(hashCode()); } 
    // Wakes up a single thread that is waiting on this object's monitor.
    // 唤醒一个在此对象监视器上等待的线程(监视器相当于就是锁的概念)
    6. public final native void notify();
    // 跟notify一样，唯一的区别就是会唤醒在此对象监视器上等待的所有线程，而不是一个线程
    7. public final native void notifyAll();
    // wait方法会让当前线程等待直到另外一个线程调用对象的notify或notifyAll方法，或者超过参数设置的timeout超时时间。
    8. public final native void wait(long timeout) throws InterruptedException;
    // 多了一个nanos参数，这个参数表示额外时间（以毫微秒为单位，范围是 0-999999）
    9. public final void wait(long timeout, int nanos) throws InterruptedException {....}
    // 跟之前的2个wait方法一样，只不过该方法一直等待，没有超时时间这个概念。
    10. public final void wait() throws InterruptedException { wait(0); }
    // Object类的默认实现是不进行任何操作
    // 该方法的作用是实例被垃圾回收器回收的时候触发的操作，就好比 “死前的最后一波挣扎”。
    11. protected void finalize() throws Throwable { }
}



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
static 关键字的作用 一句话: 可以在"没有创建对象的情况下"来"直接用类名"来调用 方法 or 变量

Static Variables. 类变量 = 全局变量 
	static variables are, essentially, global variables 本质上就是全局变量
	All instances of the class share the same static variable. 一个类所有的实例共用这一个变量

同时 static声明的方法 不能调用 非static 声明的方法或变量
	static 方法 即为 类方法 就是说 调用之前 不需要创建出一个实例对象 可以直接通过 类名.该方法名的形式调用 例如 SingletonDemo.getInstance()


如果一个类要被声明为static的，只有一种情况，就是静态内部类 "static-nested-class"		Only nested classes can be static.

--------------
static 的底层原理 

JVM 的方法区 Method Area


我们的方法在调用的时候，是从方法区调用的，
但是堆内存不一样，堆内存中的成员变量 是随着对象的产生而产生。随着对象的消失而消失。
静态变量是所有线程共享的，所以不会消失。
这也就能解释上面static关键字的真正原因。



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
	it will always be executed Whether Exception is handled or not.
	//Finally一般与try一起使用，在程序进入try块之后，无论程序是因为异常而中止或其它方式返回终止的, finally块的内容 一定会被执.
	//finally在return语句之后，跳转到上一级程序之前执行.

Finalize() is used to perform clean up processing just before object is garbage collected.

--------------------------------
为什么String类是final(是不可变得)的 

1.如果字符串是可变的，那么(String interning 字符串)驻留 将不能实现，因为这样的话，如果变量改变了它的值，那么其它指向这个值的变量的值也会一起改变。
2. 如果字符串是可变的，那么会引起很严重的安全问题。
	譬如，"数据库的用户名、密码"都是以字符串的形式传入来获得数据库的连接，
	或者在socket编程中，"主机名和端口"都是以字符串的形式传入。
	因为字符串是不可变的，所以它的值是不可改变的，否则黑客们可以钻到空子，改变字符串指向的对象的值，造成安全漏洞。
3.因为字符串是不可变的，所以是"多线程安全"的，同一个字符串实例可以被多个线程共享。
	这样便不用因为线程安全问题而使用同步。
	字符串自己便是线程安全的。
4.因为字符串是不可变的，所以在它创建的时候HashCode就被缓存了，不需要重新计算。
	这就使得字符串很"适合作为Map中的key"，字符串的处理速度要快过其它的键对象。这就是HashMap中的键往往都使用字符串。
5.因为只有当字符串是不可变的，字符串池才有可能实现。
	字符串池的实现可以在运行时节约很多heap空间，因为不同的字符串变量都指向池中的同一个字符串。



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


------------
== 与 equals
------------

== 比较的是 内存地址 
String 类中 equals被重写了  



------------ 
synchronized
------------ 
https://www.jianshu.com/p/24b98cfcb626

简而言之 两点
A. 无论synchronized关键字加在方法上还是对象上，如果它作用的"对象是非静态"的，则它取得的"锁是对象"；
	如果synchronized作用的对象是"一个静态变量/方法"或"一个类"，则它取得的"锁是对类"，该类所有的对象同一把锁。

B. 每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。



具体一点

二、用synchronized锁定Instance
线程通过该方式得到的是实例对象锁，锁定的方法或者代码块为互斥访问区，只要有一个线程获得了实例对象锁，其他线程对该实例对象的互斥访问区的访问都将阻塞。这种情况下synchronized只锁定某个具体的对象，而对于该对象所属类的其他对象没有影响。分情况描述如下：

当两个并发线程访问同一个实例对象中的互斥访问区时，一个时间内只能有一个线程得到执行。 另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
当一个线程访问实例对象的一个互斥访问区时，另一个线程仍然可以访问该实例对象中的非互斥访问区代码块。
当一个线程访问实例对象的一个互斥访问区时，其他线程对实例对象中所有其它互斥访问区的访问将被阻塞。
当一个线程访问实例对象的一个互斥访问区时，它就获得了这个实例对象的对象锁。结果，其它线程对该实例对象所有同步代码部分的访问都被暂时阻塞。

三、synchronized锁定Instance的两种用法
这两种用法都是在"实例对象"上加锁，该实例对象包括该方法在内的任何synchronized修饰的方法，其他线程都不可进入，直到线程a释放锁，退出该方法。而该实例对象没有用synchronized修饰的方法，其他线程仍旧可以访问。

1.方法声明时使用
	public synchronized void synMethod(){
		//方法体
	}

2.方法内使用
	public void synMethod(){  
		synchronized(this){
		 	//一次只能有一个线程进入
		}
	}
四、用synchronized锁定Class对象
线程通过该方式锁定的是"当前对象的Class对象"，与锁定Instance不同的是，线程a获取到Class对象的锁定之后，
其他线程"对该Class的所有对象"实例互斥访问区的访问都将阻塞。

五、锁定Class对象的两种方法

1.静态方法声明时使用

public synchronized static void synMethod(){
	//方法体
}

2.方法内使用
class A {

}
public void synMethod(){  
	synchronized(A.class){    
	      //一次只能有一个线程进入
	}
}

----------------------- 
synchronized和lock的区别
----------------------- 

1. 原始构成
synchronized是关键字, 属于JVM层面 系统级别的
	汇编语言 
	monitorenter(底层通过monitor对象 来完成的 其实 wait/notify等方法也依赖于monitor对象)
	monitorexit
Lock 是具体类 (java.util.Concurrent.Locks.lock) 是api层面的锁

2. 使用方法
	synchronized不需要手动释放, 当synchronized代码块儿执行完后 系统会自动让线程释放对锁的占用
	ReentrantLock则需要用户手动去释放锁 没释放会deadlock try的上一行lock,finally里头unlock

3. 等待是否可中断
	synchronized不可中断 除非抛异常或者正常运行完成
	ReentrantLock 可中断 1. 设置超时方法 tryLock(long timeout, TimeUnit unit)
						2. lickInterruptibly() 放代码块儿中 调用interrupt()方法可中断
4. 是否公平
	synchronized默认非公平锁
	ReentrantLock两者都可以 默认非公平 构造方法可以传boolean值 可设为公平

5. 绑定多个条件的 Condition
	synchronized无这个说法
	ReentrantLock 用来实现分组唤醒需要唤醒的线程们 可以 精确唤醒 










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

------

运行时常量池（Runtime Constant Pool）是方法区的一部分，用于存储编译期就生成的字面常量、符号引用、；
e.g. : String维护了一个常量池，如果调用的字符“abc”已经在常量池中，则返回池中的字符串地址，否则，新建一个常量加入池中，并返回地址。

JVM方法区的相关参数，最小值：--XX:PermSize；最大值 --XX:MaxPermSize



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



------------------------ 
堆 Heap 的结构是什么样子的？
------------------------ 
堆 里面放的是 什么 ？ new new new 出来的那些 对象 

堆 内存分为三部分

Young Generation Space 		新生区
Tenure Generation Space 	养老区
Permanent Space 			永久区

Java 7 及以前 
新生区 又分为 伊甸区Eden(所有刚new出来的对象都在这里) 幸存S0区 幸存S1区
新生区发生 普通的GC
经过 15次 GC 才能进去养老区, 即创建完的对象 经常用的 才能进入养老区 
养老区发生重量级的GC: full-GC

注意了!!!!!!!!!!!!!!
养老区 到 永久区 是没有路的 但凡 养老区内存满了 经过full-GC 也没办法回收内存的话 直接会报 OutOfMemoryError 内存溢出


OOM
"java.lang.OutOfMemoryError: Java heap space" 
原因:
1.  JVM的堆内存设置不够, 可以通过参数 -Xms(初始内存大小) -Xmx(最大内存大小) 来调整 （这两个参数也是参数调优中用得最多的两个）
2.  代码中创建了大量 大对象, 并且长时间不能被垃圾回收, 即一直存在被引用 需要专业工具解决




--------------------------------
什么是堆中的永久区(Perm Gen space)?
--------------------------------
永久区存储的是一个 常驻内存区域, 用于存放JDK自身所携带的 Class, Interface的 元数据, 引用的jar包等。 
说白了 存放的就是所有线程共享的 "环境".

此区"没有垃圾回收", 关闭JVM才会释放此区域所占用的内存。 (没有说 一会儿要清理一波jar包。。。)

Perm Gen space  对应的是 Method Area 物理上就不在heap堆内存中 

--------但是 

jdk 1.6 及之前 有永久区, 常量池 1.6 在方法区
jdk 1.7  	  有永久区, 但在逐步 去除, 常量池 1.7 在堆
jdk 1.8 及之后 没有永久区了, 常量池 1.8 在元空间。 但是 元空间在哪里？ 依然是方法区 Method Area
	元空间的本质和永久代类似，都是对JVM规范中 方法区 的实现。
	不过元空间与永久代之间最大的区别在于：
		元空间并不在虚拟机中，而是使用本地内存。
		因此，默认情况下，元空间的大小仅受本地内存限制。

就是为什么要做这个转换？所以，最后给大家总结以下几点原因：

　　1、字符串存在永久代中，容易出现性能问题和内存溢出。
　　2、类及方法的信息等比较难确定其大小，因此对于永久代的大小指定比较困难，太小容易出现永久代溢出，太大则容易导致老年代溢出。
　　3、永久代会为 GC 带来不必要的复杂度，并且回收效率偏低。
　　4、Oracle 可能会将HotSpot 与 JRockit 合二为一。


------------------------
什么样儿的对象 会进入养老区?
------------------------
池 对象
连接池(但凡连接 用一条 连完放回)  线程池(新线程 用一条 线程死亡 放回)


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



---------- 
JVM参数调优
---------- 
其实就是调堆内存的大小

----Minor GC --|--full-GC---|
Eden | S0 | S1 | Old Memory | MetaSpace


MAT 是一个内存分析工具 分析dump文件 抓异常等


-------------------- 
GC是什么 (分代收集算法)
-------------------- 
次数上频繁收集Young区
次数上较少收集Old区
基本不动Perm区

四大算法:
1. 引用计数法 （已被淘汰)
	但凡有人引用过 计数++, 计数为0的时候回收.
		缺点: 1> 每一个对象创建 都得维护引用计数器 2> 无法处理循环引用(两个对象你引用我 我引用你, 计数就永远不会为0)

2. 复制算法
	Minor-GC即普通GC 使用, 在新生区
	幸存0区 幸存1区 的复制交换, 复制必交换 谁空谁是to
		pros: 没有标记和清除的过程 效率较高 没有内存碎片
		cons: 需要两倍内存

3. 标记清除 
	full-GC, 用在养老区
	过程: Mark: 扫描一遍 对存活的对象进行标记 , Sweep: 再扫描一边 没有标记的全部清除掉
		pros: 一块儿内存空间即可 
		cons: 扫描两次耗时, 清除完有内存碎片 因为不连续
4. 标记压缩
	过程: Mark: 扫描一遍 对存活的对象进行标记 , Compact: 再扫描一边 并往一端滑动存活对象
		pros: 一块儿内存空间即可, 没有内存碎片 
		cons: 扫描两次耗时, 压缩有成本

--------------------------------
没有最好的算法 只有最合适的算法 ----> 分代收集算法 (即在不同的区域 用不同的算法)

新生代:
内存区域小, 对象存活率低 所以需要用复制算法: 因为该算法 效率高 同时由于内存不大 所以双倍内存也不会太大

老年代:
内存区域大, 对象存活率高 此时如果还用复制算法就不合适了 因为都是活的 再复制一遍 完全浪费资源。。。
标记清除和标记压缩一起用


java 8: CMS(Concurrent Mark Sweep)回收器
	并发，几乎不会暂停用户线程
java 9 --  G1 垃圾回收
java 13:  

-----------------------------------
在Java中，对象什么时候可以被垃圾回收 GC ？
-----------------------------------
1，对象没有引用
2，作用域发生未捕获异常
3，程序在作用域正常执u行完毕
4，程序执行了System.exit（）
5，程序发生意外终止（被杀进程等）
 
-------------------------- 
GC-root(类比 linux 左斜杠 /)
-------------------------- 

就是一组必须活跃的 引用, 每次都是以 gc-root 为起点向下搜 对象不可达 则需要gc

哪些对象可以作为 gc-root呢？ 
JVM 1.栈中的局部变量区 中的 引用对象
2.方法区中 类的静态属性 引用的对象
3.方法区中 常量 引用的对象
4.本地方法栈 JNI(native方法) 引用的对象


--------- 
垃圾回收器
--------- 

1. Serial 	串行		为单线程环境设计 且只使用一个线程进行垃圾回收 会暂停所有的用户线程 所以不适合服务器环境
2. Parallel 并行 	多个垃圾回收线程并行工作 程序还是要停
3. CMS 		并发 Concurrent Mark Sweep		并发 程序不用停 用户线程和收集垃圾线程 不一定是并行 可能交替之行
4. G1		G1。    G1 将堆内存分割成不同的区域 然后并发的对其进行垃圾回收


--------------------
怎么查看默认的 GC回收器

命令行输这个命令 "java -XX:+PrintCommandLineFlags -version"

输出结果的最后就是 -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
所以 java 8 默认的是 -XX:+UseParallelGC  并行parallel垃圾回收器

java 的 gc 回收 主要的类型有
UseSerialGC UseParallelGC UseConcMarkSweeoGC UseParNewGC  UseParallelOldGC UseG1GC  









多线程与高并发 (JUC Java.Util.Concurrent)
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------
并行parallel 并发Concurrent 
-------------------------- 
你吃饭吃到一半，电话来了，你一直到吃完了以后才去接，这就说明你不支持并发也不支持并行。
你吃饭吃到一半，电话来了，你停了下来接了电话，接完后继续吃饭，这说明你支持并发。
你吃饭吃到一半，电话来了，你一边打电话一边吃饭，这说明你支持并行。


并发的关键是"你有处理多个任务的能力"，不一定要同时。
并行的关键是你有同时处理多个任务的能力。
所以我认为它们最关键的点就是：是否是『同时』。

多线程在多核上是并发还是并行？

对于单核，多线程的多任务是在单cpu交替执行，属于并发；
对于多核，多线程的任务如果能够分布在多个cpu上同时执行，那么就是并行。


--- 
JMM 
---
三大特性
1.可见性 (可见性是 指 一个线程修改了 主物理内存中 的某变量的值 其他线程 会马上获得通知)
2.原子性 Atomic (即不可分割，完整性， 某个线程正在做某个具体业务的时候 中间不能被加塞儿 或 分割 需要整体完整.要么同时成功 要么同时失败)
3.有序性



JMM 是什么: "java内存模型" Java Memory Model 是一种抽象概念 并不真实存在 它描述的是一种规范(类似12生肖里面的 龙)
通过这组规范 定义了程序中各个变量(实例字段, 静态字段, 构成数组对象的元素)的访问方式

JMM关于同步的规定:
1. 线程解锁前, 必须把共享变量的值刷新回主内存
2. 线程加锁前, 必须读取主内存的最新值到自己的工作内存
3. 加锁解锁是同一把锁

主内存 (硬件上插上的那个内存条 里头全是 共享变量 所有线程都可以访问)
工作内存(每个线程的私有数据 其他线程不可访问)

线程对变量的操作(读取 赋值等) 必须在其自己的工作内存中进行, 首先要先将变量从主内存拷贝到自己的工作内存空间,
然后对变量进行操作,操作完成后再将变量写回主内存。 每个线程自己的工作空间内存的仅仅是共享变量的拷贝副本。
因此 线程间的通信（传值） 必须通过主内存来完成。


"最终"的目的 ： 线程安全性 得到保证

--------
volatile
-------- 
volatile JVM 提供的轻量级的"同步"机制 就是 乞丐版的 synchronized

1. 保证可见性 (可见性是 指 一个线程修改了 主物理内存中 的某变量的值 其他线程 会马上获得通知)
2. 不保证原子性 (哪个保证原子性? synchronized的保证)
3. 禁止指令重排

为什么 不保证原子性 因为线程疯抢 线程太快了 纳秒级别 还没来得及通知 出现 写覆盖/丢失

------------------------------------- 
那么 如何解决 volatile 不保证原子性的问题？ 
------------------------------------- 
	1. synchronized 但是不好 杀鸡用牛刀 
	2. java.util.concurrent.atomic 中 AtomicInteger 中 getAndIncrement()方法每次加一
		但是 这个的原理 是什么 ？ 
		"CAS":

-------
指令重排
-------

计算机在执行程序时 为了提高性能 编译器和处理器常常会对指令做重拍 一般情况

	源代码 --> 编译器优化的重排 --> 指令并行的重排 --> 内存系统的重排 --> 最终指令的执行

单线程环境里 不用考虑重排的问题 程序确保最终执行结果和代码顺序执行的结果一致
处理器在进行重排序是必须要考虑智灵键的 "数据依赖性(即 先有你爹 后有你)"
多线程环境中线程交替执行, 由于编译器优化重排的存在, 两个线程中的变量能否保证一致性是无法确定的, 结果无法预测


所以 volatile 实现了 禁止指令重排的优化 从而避免多线程环境下 程序出现

乱序之行的现象
(具体使用了 Memory Barrier 这条 CPU 指令)



--------------------- 
你在哪里使用过 volatile
--------------------- 

单例模式 DCL (double check lock)
class Singleton {
	private Singleton() {};
	private static volatile Singleton instance;		// 加volatile
	public static Singleton getInstance() {
		if (instance == null) {						//synchronized 同步代码块儿 前后 都要检查
			synchronized(Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}		 
			}
		}
		return instance;
	}
}



----------------------------
CAS 比较并交换 compare and set
----------------------------
一句话: 比较 & 交换

再接着  CAS 是一条 CPU并发原语 它的功能是 判断内存某个位置的值是否为预期值, 如果 是 则更改为新值, 如果 不是 则不能更改, 这个过程是原子的。
CAS并发原语体现在JAVA语言中就是 sun.misc.Unsafe类中的各个方法
调用Unsafe类中的CAS方法, JVM会帮我们实现 CAS汇编指令。 
由于它是属于操作系统用语范畴, 是由若干条指令组成的, 用于完成某个功能的一个过程, 
并且原语的执行必须是连续的 在执行过程中不允许被中断(类比习大大的车队 谁敢加塞儿？), 所以不会造成数据不一致的问题 保证了原子性

cons: 
1. 因为有个 do while 
			如果 cas失败会一直进行尝试 如果长时间不成功 可能给CPU带来很大开销
2. 只能保证一个共享变量的原子操作
	对于 多个共享变量操作时 循环CAS无法保证 这时候可以用锁Lock来保证
3. "ABA" 问题
	"CAS ----> Unsafe ----> CAS底层思想 ----> ABA ----> 原子引用更新 ----> 如何规避ABA问题"

	一句话: 狸猫换太子再换回 中间太子受了伤害

	ABA 问题: CAS要取出数据并比较替换 但是 这是有一个时间差的 在这个时间差里数据会变化
			  比如 一个线程 one 从位置V取出A 
			  这时另一个线程two也从内存中同样位置取出A 并且线程two进行了操作将值变成了B 然后线程two又将位置V的数据变成A
			  此时线程one进行CAS操作发现内存中仍然是A 比较操作成功 然后线程one操作成功。 
			  此时 线程one 以为数据没有被人动过 但其实这个过程有猫腻 
	如何解决:
		理解 原子引用  atomicStampledReference
			+ 新增一种机制, 即修改版本号(类似时间戳)

		e.g.

			 A 	 版本号		A	版本号	  A	   版本号
		T1  100    1					  2020   2
		T2  100    1       101    2       100    3 

		但是 虽然 A 还是 100, 但是版本号 2 < 3 所以无法更改 

--------------------------------------
底层原理呢 为什么要用CAS而不是synchronized 

只需要说两点
1. 自旋锁
2. unsafe类

"getAndIncrement() 源码"
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


------------------ 
Wait, Sleep, Yield
------------------ 
这三个都能用来暂停线程的方法中,sleep()和yield()方法是定义在Thread类中,而wait()方法是定义在Object类中的.

1.wait() 是用来 线程间通信的
sleep() 是用于短时间暂停当前线程

2.更加明显的一个区别在于, 当一个线程调用wait()方法的时候, 会释放它锁持有的对象的锁.
但是调用sleep()方法的时候, 不会释放他所持有的锁.

3.wait() 还需要在 同步 synchronize/lock 方法块儿中使用 而sleep() 不需要
4. 进入wait状态的线程能够被 notify()和notifyAll() 线程唤醒，但是进入sleeping状态的线程不能被notify方法唤醒。

5.另一个区别是Thread.sleep()方法是一个静态方法，作用在当前线程上；
	但是wait方法是一个实例方法，并且只能在其他线程调用本实例的notify()方法时被唤醒。
	另外，使用sleep方法时，被暂停的线程在被唤醒之后会立即进入就绪态（Runnable state)，
	但是使用wait方法的时候，被暂停的线程会首先获得锁（译者注：阻塞态），然后再进入就绪态。

	有一个易错的地方，以为 当调用t.sleep()的时候，会暂停线程t。这是不对的，因为Thread.sleep是一个静态方法，它会使当前线程而不是线程t进入休眠状态。
---->
回到yield()方法上来，与wait()和sleep()方法有一些区别，它仅仅释放线程所占有的CPU资源，从而让其他线程有机会运行，但是并不能保证某个特定的线程能够获得CPU资源。谁能获得CPU完全取决于调度器，在有些情况下调用yield方法的线程甚至会再次得到CPU资源。所以，依赖于yield方法是不可靠的，它只能尽力而为。

yield和sleep的主要是，yield方法会临时暂停当前正在执行的线程，来让有同样优先级的正在等待的线程有机会执行。如果没有正在等待的线程，或者所有正在等待的线程的优先级都比较低，那么该线程会继续运行。







Java 中的 锁
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
公平锁, 非公平锁, 可重入锁, 递归锁, 自旋锁, 独占锁（写锁）, 共享锁（读锁）, 互斥锁, 分段锁, 

----------------------------  
公平锁 fair , 非公平锁 unfair
----------------------------  
一句话: 公平 即按线程先来后到, 非公平 即允许线程加塞儿

公平锁: 指多个线程按照申请锁的顺序来获取锁, 类似排队打饭 先来后到 FIFO 即 ordering policy 顺序策略
非公平锁: 指 线程获取锁可以加塞儿, 在高并发情况下 有可能造成优先级反转 or 饥饿starvation 现象

JUC包中的 ReentrantLock的创建 可以指定构造函数的boolean类型来创建, "默认是 非公平锁".
非公平锁的 pros: 吞吐量大

synchronized 也是 非公平锁




--------------- 
可重入锁 = 递归锁
--------------- 
指的是 同一函数 外层函数 获得锁之后 内层函数仍能够获取该锁的代码
即 同一个线程 在外层方法获取锁之后, 再进入内层会自动获取锁
即 线程可以进入 任何一个它已经拥有的锁 所同步着的 代码块儿

一句话: 大白话 你家门口一把大锁就够了 开了大门的锁 相当于可以进 卧室 进卫生间

举例子:
public synchronized void method01() {
	method02()
	//do something
}

public synchronized void method02() {
	// do something
}

上述情况 只要获得了 method01的锁 method02的就自动获得 即可自由进出 method02

ReentrantLock/synchronized 本身就是两个典型的可重入锁


Lock lock = new ReentrantLock();

lock.lock();
try {
    // To Do
} catch (Exception e) {
    e.printStackTrace();
} finally {
    lock.unlock();
}


--------------- 
自旋锁 spinlock
--------------- 

指的是 尝试获得锁的线程不会立即wait阻塞 而是采用循环的方式去获取锁 
pros: 减少上下文切换的消耗

一句话: 比如一个人去问老师问题, 看有别的同学在问老师问题, 下去抽根儿烟 再回来看看, 要是还有人问 下去买瓶水, 再回来... 如此循环




------------------------- 
独占锁（写锁）, 共享锁（读锁）
------------------------- 
即 read/write lock

独占锁, 指该锁 只能被一个进程占用 ReentrantLock/synchronized 都是独占锁/互斥锁

共享锁, 指该锁可以被多个线程持有
对 ReentrantReadWriteLock 其读锁是共享锁, 其写锁是共享锁
读写锁可以保证 并发读 是非常高效的, 读写 写读 写写的过程是互斥的

//锁 一定是 代码模块儿话 自动化生成 不许手写。。
rwLock.writeLock().lock();
try {
    System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
    //模拟网络延迟 挺个0.3秒
    try { TimeUnit.MICROSECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
    map.put(key, value);
    System.out.println(Thread.currentThread().getName() + "\t 写入完成!!!! ");
} catch (Exception e) {
    e.printStackTrace();
} finally {
    rwLock.writeLock().unlock();
}




--------- 
Condition
--------- 
相当于 一把锁里有三把备用钥匙
private Lock lock  = new ReentrantLock();
private Condition c1 = lock.newCondition();
private Condition c2 = lock.newCondition();
private Condition c3 = lock.newCondition();

阻塞 await()
唤醒 signal()


// 1 判断
while (number != 1) {
    c1.await();
}
// 2 干活
for (int i = 1; i <= 5; i++) {
    System.out.println(Thread.currentThread().getName() + "\t" + i);
}
// 3 通知
number = 2;
c2.signal();




--------------- 
CountDownLatch
--------------- 

火箭发射倒计时 之前 所有的准备工作 都做好了 才能发射
就是 表示其他几个线程 都结束了 后面的 才能执行 

CountDownLatch countDownLatch = new CountDownLatch(6); //要等 几个线程结束
countDownLatch.countDown();		// 每次 减1
countDownLatch.await();			// 只有countDownLatch中计数减为0 await()后面的才可以执行 否则阻塞



--------------- 
CyclicBarrier
--------------- 
与 CountDownLatch 相反
表示 只有 集齐7科龙珠 才可以召唤神龙

CyclicBarrier(int parties, Runnable barrierAction)

CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
    System.out.println(Thread.currentThread().getName() + "\t ***召唤神龙");
});
.....
for ( 1 -> 7 ) {
	//前7个 都等着
	cyclicBarrier.await();
}



---------- 
Semaphore
---------- 
一句话: 抢车位

它的作用: 1.用于 多个共享资源的互斥使用 (多对多)(6个车抢3个车位) 2.另一个用于 并发线程数的控制 

与CountDownLatch(只能减), CyclicBarrier(只能加) 不同的是 semaphore 可以伸缩 到0再增 再到0...

Semaphore semaphore = new Semaphore(3); // 模拟3个停车位
semaphore.acquire();				// -1
semaphore.release();				// +1

他们三个 配上 线程池 可以打造功能很强的 高并发系统


--------------------------------- 
与 synchronized/ReentrantLock 区别 

synchronized/ReentrantLock 都是“互斥锁” 相当于只存在一个临界资源,因此同时最多只能给一个线程提供服务.
在实际复杂的多线程应用程序中，可能存在"多个临界资源"，这时候我们可以借助Semaphore信号量来完成多个临界资源的访问



---------------------  
到底什么时候 该用什么锁？？
---------------------  

1.synchronized：

在资源竞争不是很激烈的情况下，偶尔会有同步的情形下，synchronized是很合适的。
原因在于，编译程序通常会尽可能的进行优化synchronize，另外可读性非常好。

2.ReentrantLock:
在资源竞争不激烈的情形下，性能稍微比synchronized差点点。
但是当同步非常激烈的时候，synchronized的性能一下子能下降好几十倍，而ReentrantLock确还能维持常态。

"高并发量情况下使用ReentrantLock。"

3. Semaphore
存在"多个临界资源"，这时候我们可以借助Semaphore信号量来完成多个临界资源的访问

4. Atomic
和上面的类似，不激烈情况下，性能比synchronized略逊，而激烈的时候，也能维持常态。激烈的时候，Atomic的性能会优于ReentrantLock一倍左右。但是其有一个缺点，就是只能同步一个值，一段代码中只能出现一个Atomic的变量，多于一个同步无效。因为他不能在多个Atomic之间同步













Java 中的 线程池
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------
java中4种创建线程的方式
--------------------
1. extends Thread
2. implements Runnable run()  但Runnable接口没有返回值 不抛异常 
3. 实现 Callable接口  call()    有返回值 会抛异常 
4. 通过线程池


线程池的作用 
线程复用, 因为 new Thread() 完后 都要GC 用线程池就可以复用, 同时 控制最大并发数量

线程池的底层就是 ThreadPollExecutor 类

Executor 
Executors 是其 工具类 类比 (Array Arrays, Collection Collections)

Executor 类比 Collection
	我们一般不用 一般用 Executor 下面细分的 ExecutorService
ExecutorService  类比 List


e.g.

ExecutorService threadPool = Executors.newFixedThreadPool();		// 一池固定数线程 　执行长期任务 性能好
ExecutorService threadPool = Executors.newSingleThreadExecutor()	// 一池一线程		  一个任务一个任务执行的场景
ExecutorService threadPool = Executors.newCachedThreadPool()		// 一池可变数线程	 执行很多短期异步的小程序 或者 负载较轻的服务器


//  模拟 10个用户来办理业务 每来一个用户就是一个来自外部的请求线程  线程只有5个 可以复用
ExecutorService threadPool = Executors.newFixedThreadPool(5);		
try {
    for (int i = 1; i <= 10; i++) {
        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 办理业务");
        });
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    threadPool.shutdown();
}



----------------- 
线程池的 7 大 参数
----------------- 
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }

public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {....}


1. corePoolSize 
	线程池中常住的核心线程数 (= 银行网点今天开了几个今日当值的柜台) 
	当线程池中的线程数目达到 corePoolSize 之后 就会把 再新来的 放到缓存队列

2. maximumPoolSize
	线程池中 能够容纳同时执行的最大线程数 此值 >= 1
	(= 银行网点最多可能同时开放的柜台数) 

3. keepAliveTime
	多余的空闲线程的存活时间
	(当前线程池数量超过 corePoolSize时, 当空闲时间 达到 keepAliveTime时,
		多余空闲线程会被销毁 知道剩下的线程数不大于corePoolSize为止)
	即 把之前的加班窗口取消除

4. unit
	设置存活时间的单位  是 分钟/秒/毫秒

5. workQueue
	阻塞队列 (= 银行网点 的 候客等待区)
	被提交但尚未被执行的任务在此等待

6. threadFactory
	表示 生成线程池中工作线程的 线程工厂,  用于创建线程 一般用默认的即可

7. handler
	拒绝策略 当所有能开的加班窗口都开了 侯客区都满了 之后 拒绝后面的全部请求 避免浪费时间





--------------- 
线程池的 底层原理
--------------- 

1. 创建了线程池后 等待提交过来的任务请求
2. 当 调用 execute() 方法 添加一个请求 线程池会做如下判断
	2.1 	如果正在运行的线程数量 < corePoolSize, 则马上创建线程运行这个任务
	2.2 	如果正在运行的线程数量 >= corePoolSize, 那么将这个任务放入阻塞队列
	2.3		如果队列也满了 且正在运行的线程数量 < maximumPoolSize, 那么还是要创建 非核心线程(即 加班柜台) 立即运行这个任务
	2.4 	如果队列也满了 且正在运行的线程数量 >= maximumPoolSize, 线程池用 包和拒绝策略来拒绝执行
3. 当一个线程完成任务了之后, 它会从队列中取下一个
4. 当一个线程无事可做 且等待时间超过 keepAliveTime, 则线程池会判断:
	4.1		如果当前线程运行的数量 > corePoolSize 那么这个线程会被停掉
	4.2		等线程池的所有任务完成后 线程数必然会收缩到 corePoolSize 大小



--------------- 
线程池的 4种 拒绝策略
--------------- 
AbortPolicy()  默认的 直接报异常  java.util.concurrent.RejectedExecutionException: 这生产环境中 可不敢用
CallerRunsPolicy()	不会抛弃任务 不会报异常  而是将任务回退给 上一层 然后  等它下次再来
DiscardOldestPolicy()	抛弃队列中等待时间最久的 然后把当前任务加入队列中再次尝试提交当前任务
DiscardPolicy()			直接丢弃任务


--------------- 
线程池用哪个？ 超级大坑
--------------- 
结论是 三个JDK直接给的 一个都不用。。。
要 手写改造
要用 ThreadPoolExecutor 来创建 否则 会有很多的 OOM OutOfMemoryError

ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


















杂项
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------









finalize()方法什么时候被调用？析构函数(finalization)的目的是什么？
 

串行(serial)收集器和吞吐量(throughput)收集器的区别是什么？

异常处理完成以后，Exception对象会发生什么变化？




