Java this 关键字用法


Within an instance method or a constructor, 
	this is a reference to the "current object" — 
		the object "whose method or constructor is being called". 

You can refer to any member of the current object from within an instance method or a constructor by using this.



The most common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name (because a class attribute is shadowed by a method or constructor parameter). If you omit the keyword in the example above, the output would be "0" instead of "5".

this can also be used to:

	Invoke current class constructor
	Invoke current class method
	Return the current class object
	Pass an argument in the method call
	Pass an argument in the constructor call






应用一：引用成员变量(实例变量 instance variable) 

//在一个类中，如果一个变量能够用来描述一个类的属性，那就定义为成员变量 即 类中 attribute 是 instance variable

public class Student {
	String name;	//定义一个成员变量(实例变量)name
	//..
	public void setName(String name) {	// 传进来一个参数 局部变量
		this.name = name;		//将局部变量的值传递给成员变量
	}
}




应用二：调用类的构造方法

Student方法有两个构造方法，一个没有参数，一个有参数。
在第一个没有带参数的构造方法中，使用了this(“Hello!”)这句代码，
		这句代码表示 使用 this 关键字 "调用类中的有一个参数的构造方法"。

public class Student { 	
	public Student() { 		//定义一个方法，名字与类相同故为构造方法
		this(“Hello!”);
	}
	public Student(String name) { //定义一个带形式参数的构造方法
	}
}



应用三：返回对象的值

this 关键字除了可以引用变量或者构造方法之外，还有一个重大的作用就是 "返回类的引用"

如在代码中，可以使用return this，来返回某个类的引用。此时这个this关键字就代表类的名称.

如代码在上面student类中使用return this，那么代码代表的含义就是return student。

可见，这个this关键字除了可以引用变量或者成员方法之外，还可以作为类的返回值，这才是this关键字最引人注意的地方。





应用四：把当前 类的 current object 即 当前类的实例 传进 方法里 



public class ParkingSpot {
    private Vehicle vehicle;
    private VehicleSize spotSize;
    private int row;
    private int spotNumber;

    public boolean isAvailable() {
        return vehicle == null;
    }
    /** Check if the spot is big enough and is available */
    public boolean canFitVehicle(Vehicle vehicle) {
        /**
         * Within an instance method or a constructor,
         * 	    this is a reference to the "current object" —
         * 	        the object "whose method or constructor is being called".
         */
        return isAvailable() && vehicle.canFitInSpot(this);
    }
}

