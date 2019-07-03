000. 常用函数

String & Character

子串					str.substring(beginIndex, endIndex);	含begin不含end

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

取出字符串中数字		Integer.valueOf(str.substring(begin, end));			Integer类方法


把字符串按降序排列	重写Collections类sort方法 Comparator后的<String> 类型不可少
					Collections.sort(ans, new Comparator<String>(){
						@Override
						public int compare(String o1, String o2) {
							return o2.compareTo(o1);
						}
					})


ASCII	A:65 a:97







Map

遍历Map k,v 都要		for (Map.Entry<String, Integer> entry : map.entrySet()) {
						System.out.println(entry.getKey() + entry.getValue());
					}

取值没有默认			map.getOrDefault(keyName, 0);

增删改				map.put(key, value);
					map.get(key);
					map.remove(key);
					map.isEmpty();
					map.size();

Stack

Queue

