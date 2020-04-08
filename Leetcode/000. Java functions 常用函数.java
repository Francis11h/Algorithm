000. 常用函数

String & Character

子串					str.substring(beginIndex, endIndex);	含begin不含end
					str.substring(beginIndex);				//只有一个参数, 就是开始参数到结束
删除头尾空白符     	str.trim();

ASCII				A:65 a:97

判单字符是	大写	 	Character.isUpperCase('char');			单个字符的判断是 Character类的方法
			小写		Character.isLowerCase('char');
			数字		Character.isDigit('char');
		字母或数字	Character.isLetterOrDigit('char');


可拼接字符串用 StringBuilder, 解决大量拼接字符串时产生很多中间对象问题 : 用StringBuffer(所有方法都加了synchronized, 保证线程安全)
					sb.append("www");
					sb.reverse();
					sb.deleteCharAt(index);
					sb.length();
					sb.toString();

字符串转字符数组		str.toCharArray();
字符数组转字符串		String.valueOf(charArrayName);

取出字符串中数字		Integer.valueOf(str.substring(begin, end));			Integer类方法


把字符串按降序排列	重写Collections类sort方法 Comparator后的<String> 类型不可少

					Collections.sort(ans, new Comparator<String>(){
						@Override
						public int compare(String o1, String o2) {
							return o2.compareTo(o1);
						}
					})







Array 

自定义排序			Arrays.sort(intervals, new Comparator<int[]>(){
			            @Override
			            public int compare(int[] a, int[] b) {
			                return a[0] - b[0];
			            }
			        });















Map

遍历Map k,v 都要		for (Map.Entry<String, Integer> entry : map.entrySet()) {
						System.out.println(entry.getKey() + entry.getValue());
					}

					map.ketSet();
					map.values();

取值没有默认			map.getOrDefault(keyName, 0);

增删改				map.put(key, value);
					map.get(key);
					map.remove(key);
					map.isEmpty();
					map.size();
					map.clear();








Queue<Integer> queue = new LinkedList<>();

queue.offer(val);
queue.poll();
queue.isEmpty();



Stack<Integer> stack = new Stack<>();
Stack<Character> stack1 = new Stack<>();

stack.push(val);
stack.pop();
stack.isEmpty();
stack.peek();


Heap
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
		@Override
		public int compare(Integer a, Integer b) {
			return b - a;
		}
	});

	
	minHeap.offer(val);
	minHeap.size();
	minHeap.poll();





java 输入

Scanner 类种 方法
next()  取得一个字符串
hasNext()   是否还有输入
nexInt()    将取得的字符串转换成int类型的函数
nextFloat()     将取得的字符串转换为float型
nextBoolean()   将取得的字符串转换为boolean型

Scanner取得输入的依据是空格符，包括 空格键，Tab键，Enter键，按下其中任意一键时，Scanner就会返回下一个输入。
所以 当输入的内容中包括空格时，显然，用Scanner就得不到完整的字符串

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt();
            String str = sc.next();
            for (int i = 0; i < m; i++) {
                System.out.println("输出");
                System.out.println(sc.nextInt());
            }
            System.out.println("666");
            System.out.println(str);
        }
    }
}




我叫韩子润， 首先 感谢您提供给我这次面试的机会

我目前  在于北卡莱罗纳州立大学 本科毕业于西北工业大学

专业是 计算机， 我的主要编程语言是 Java

我现在在做的项目 是一个 类似 google search 自动推荐的项目， 用hadoop mapreduce

有较强的 数据结构 算法 能力
对计算机基础知识 网络 操作系统 数据库等 有基本掌握





	Map<String, String> map1 = new TreeMap<>();	//TreeMap是用平衡树 Balanced BST实现的，key值是有序的
Map<Integer, Integer> map = new HashMap<>(); //更快，Hash table实现的， 但key值 无序

	map.put(key, value);
	map.remove(key);

	map.get(key);//得到key对应的value
	map.containsKey(key);
	map.containsValue(value);

	map.size();
	map.isEmpty();

	res = map.getOrDefault(key, 0);// 如果有key， 就取它的value; 没有就等于0
	


	//三种方式遍历map
	//1
	for (String key : map.keySet()) {
		System.out.println(key + " " + map.get(key));
	}
	//2
	for (Map.Entry<String, String> entry : map.entrySet()) {
		System.out.println(entry.getKey() + entry.getValue());
		//   需要是 加号 因为prinln输出的 都是 字符串 String 
	}
	//3
	//只要value
	for (String val : map.values()) {
		System.out.println(val);
	}


Set<Integer> set1 = new TreeSet<>();
Set<Integer> Set = new HashSet<>();

	set.add(5);
	set.remove(5);

	set.contains(value);

	//遍历集合中元素
	for (Integer num : set) {
		System.out.println(num);
	}
