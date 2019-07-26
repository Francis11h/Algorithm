ArrayList 三种构造方法


public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private static final int DEFAULT_CAPACITY = 10;
    //Shared empty array instance used for empty instances.
    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    //The array buffer into which the elements of the ArrayList are stored.
    transient Object[] elementData;

    private int size;

    1. 规定大小的 ArrayList

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

    2. 不规定的 默认为10

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    3. 用一个集合构建
    //？是“任意类”的意思，extends继承，E是指定类型
    public ArrayList(Collection<? extends E> c) {
        // 直接将原来的集合转换为数组，赋值给elementData
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    // 所以 3sum 里会有这种构建方式
    // Arrays.asList 就是把几个数变为集合
    // new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])) 

    // 具体 asList() 这里看
    // https://www.jianshu.com/p/2b113f487e5e
    // public class Arrays {

    //     public static <T> List<T> asList(T... a) {
    //         return new ArrayList<>(a);
    //     }       
    // }
}