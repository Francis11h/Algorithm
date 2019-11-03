JAVA 国内面试 基础知识点

Java支持多继承么
	绝不

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

Java 多态 polymorphism 		
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



Java 继承 inheritance		Son extends Father 	||  Dog extends Animal
	 封装 encapsulation		改变 可访问的权限  private 变量 public getter() & setter() 










什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
static关键字是什么意思？Java中是否可以覆盖(override)一个private或者是static的方法？ 
是否可以在static环境中访问非static变量？
Java中的方法覆盖(Overriding)和方法重载(Overloading)是什么意思？
Java中，什么是构造函数？什么是构造函数重载？什么是复制构造函数？


什么是值传递和引用传递
java创建线程有几种不同的方式？你喜欢哪一种？为什么？
概括的解释下线程的几种可用状态。
同步方法和同步代码块的区别是什么？
Java集合类框架的基本接口有哪些？
为什么集合类没有实现Cloneable和Serializable接口？
什么是迭代器(Iterator)？
Java中的HashMap的工作原理是什么？ConcurrentHashMap呢？
hashCode()和equals()方法的重要性体现在什么地方？
数组(Array)和列表(ArrayList)有什么区别？什么时候应该使用Array而不是ArrayList？
ArrayList和LinkedList有什么区别？
java的动态代理机制是怎样的？

如何权衡是使用无序的数组还是有序的数组？
Enumeration接口和Iterator接口的区别有哪些？
Java中垃圾回收有什么目的？什么时候进行垃圾回收？
finalize()方法什么时候被调用？析构函数(finalization)的目的是什么？
如果对象的引用被置为null，垃圾收集器是否会立即释放对象占用的内存？ 
Java堆的结构是什么样子的？什么是堆中的永久代(Perm Gen space)?
串行(serial)收集器和吞吐量(throughput)收集器的区别是什么？
在Java中，对象什么时候可以被垃圾回收？
JVM的永久代中会发生垃圾回收么？
Java中的两种异常类型是什么？他们有什么区别？
Java中Exception和Error有什么区别？
throw和throws有什么区别？
异常处理完成以后，Exception对象会发生什么变化？
异常处理的时候，finally代码块的重要性是什么？
	Finally一般与try一起使用，在程序进入try块之后，无论程序是因为异常而中止或其它方式返回终止的, finally块的内容 一定会被执.
	finally在return语句之后，跳转到上一级程序之前执行.
	




finally代码块和finalize()方法有什么区别？
java类是如何被虚拟机加载的？生命周期又是怎样的？
