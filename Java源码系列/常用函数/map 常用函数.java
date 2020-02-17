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
