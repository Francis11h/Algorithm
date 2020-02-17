String str = "a string";

	str.length();			//字符串长度
	str.charAt(index);		//某一位置的字符

	//遍历字符串中每一个字符
	for (int i = 0; i < str.length(); i++) {
		char ch = str.charAt(i);
		//..
	}

	str.substring(beginIndex);	//只有一个参数, 就是开始参数到结束
	str.substring(beginIndex, endIndex); //beginIndex开始, 不包括endIndex
	str.equals(str2);		//判断字符串是否相等


	str.toLowerCase();
	str.toUpperCase();

	Char[] A = str.toCharArray(); //字符串专为等长的字符数组
	String.valueOf(。。。); //把。。。 变字符串


	String[] array = str.split(" "); //把一个字符串按 单个空格 &&分成多个字符串 并存入字符串数组
	String [] arr = str.split("\\s+");
		//String的split方法支持正则表达式；
		//正则表达式 \s 表示匹配任何 空白字符(单个空格，多个空格，tab制表符)
		// + 表示匹配一次或多次。


StringBuffer cur = new StringBuffer();
	cur.append("www");		//把指定的字符串追加到字符序列
	cur.reverse()			//反转
	cur.deleteCharAt();		//删除指定位置的
	cur.length();
	cur.toString();







Character.isLetterOrDigit(某字符 s.charAt(i))；


//把字符串按降序排列
Collections.sort(ans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });




A.indexOf(B) : 
	 B这个子字符串 第一次出现在 A字符串 的首字符下标，如果不包含，返回-1

