split(划分规则, 分割后的字符串个数)

String[] test = "a b c".split(" ", 2);
		test -----> ["a", "b c"]

String[] test = "a b c".split(" ");	
		test -----> ["a", "b", "c"]	



https://www.jianshu.com/p/3e2e85a0afb6



public final class String {
    implements java.io.Serializable, Comparable<String>, CharSequence {
    
    private final char value[];


	2. split(regex, limit)  根据正则表达式分割字符串 limit 限定分割后的字符串个数, 超过数量限制的情况下前 limit-1 个子字符串正常分割,最后一个子字符串包含剩下所有字符
							重载方法split(String regex)将limit设置为0

	

			public String[] split(String regex, int limit) {
		        /* fastpath if the regex is a

		        	单字符情况下regex不等于正则表达式的元字符(meta character)
		         (1)one-char String and this character is not one of the
		            RegEx's meta characters ".$|()[{^?*+\\", or

					双字符情况下regex第一个字符是反斜杠，第二个字符不是Unicode编码中的数字或字母

		         (2)two-char String and the first char is the backslash and
		            the second is not the ascii digit or ascii letter.
		         */
		        char ch = 0;
		        //注意 所有的正则里要表示反斜杠 需要用两个反斜杠来表示 转义！！！
		        if (((regex.value.length == 1 &&
		             ".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) ||
		             (regex.length() == 2 &&
		              regex.charAt(0) == '\\' &&
		              (((ch = regex.charAt(1))-'0')|('9'-ch)) < 0 &&
		              ((ch-'a')|('z'-ch)) < 0 &&
		              ((ch-'A')|('Z'-ch)) < 0)) &&
		            (ch < Character.MIN_HIGH_SURROGATE ||
		             ch > Character.MAX_LOW_SURROGATE))
		        {
		            int off = 0;
		            int next = 0;
		            boolean limited = limit > 0;
		            ArrayList<String> list = new ArrayList<>();
		            //第一次分割时，使用off和next，off指向每次分割的起始位置，next指向分隔符的下标，完成一次分割后更新off的值。
		            //当list的大小等于limit-1时，直接添加剩下子字符串
		            while ((next = indexOf(ch, off)) != -1) {
		                if (!limited || list.size() < limit - 1) {
		                    list.add(substring(off, next));
		                    off = next + 1;
		                } else {    // last one
		                    //assert (list.size() == limit - 1);
		                    list.add(substring(off, value.length));
		                    off = value.length;
		                    break;
		                }
		            }
		            // If no match was found, return this
		            //如果字符串不含分隔符，直接返回原字符串。
		            if (off == 0)
		                return new String[]{this};

		            // Add remaining segment
		            //如果字符串第一次分割完后没有数量没有达到limit-1，最终余下的字符串在第二次被添加。
		            if (!limited || list.size() < limit)
		                list.add(substring(off, value.length));

		            // Construct result
		            //在limit等于0的情况下，从最后一个子字符串往前数，所有的空字符串""都会被清除。
		            int resultSize = list.size();
		            if (limit == 0) {
		                while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
		                    resultSize--;
		                }
		            }
		            String[] result = new String[resultSize];
		            return list.subList(0, resultSize).toArray(result);
		        }
		        return Pattern.compile(regex).split(this, limit);
		    }

    }
}




附正则表达式用法：
	字符类匹配
	[…] 查找方括号之间的任何字符
	[^…] 查找任何不在方括号之间的字符
	[a-z] 查找任何从小写 a 到小写 z 的字符
	[A-Z] 查找任何从大写 A 到大写 Z 的字符
	[A-z] 查找任何从大写 A 到小写 z 的字符
	. 查找单个字符，除了换行和行结束符
	\w 查找单词字符，等价于[a-zA-Z0-9]
	\W 查找非单词字符，等价于[^a-zA-Z0-9]
	\s 查找空白字符
	\S 查找非空白字符
	\d 查找数字，等价于[0-9]
	\D 查找非数字字符，等价于[^0-9]
	\b 匹配单词边界
	\r 查找回车符
	\t 查找制表符
	\0 查找 NULL 字符
	\n 查找换行符


重复字符匹配
	{n,m} 匹配前一项至少n次，但不能超过m次
	{n,} 匹配前一项n次或更多次
	{n} 匹配前一项n次
	n？ 匹配前一项0次或者1次，也就是说前一项是可选的，等价于{0，1}
	n+ 匹配前一项1次或多次，等价于{1，}
	n* 匹配前一项0次或多次，等价于{0，}
	n$ 匹配任何结尾为 n 的字符串
	^n 匹配任何开头为 n 的字符串
	?=n 匹配任何其后紧接指定字符串 n 的字符串
	?!n 匹配任何其后没有紧接指定字符串 n 的字符串


匹配特定数字
	^[1-9]\d*$　 　 匹配正整数
	^-[1-9]\d*$ 　 匹配负整数
	^-?[0-9]\d*$　　 匹配整数
	^[1-9]\d*|0$　 匹配非负整数（正整数 + 0）
	^-[1-9]\d*|0$　　 匹配非正整数（负整数 + 0）
	^[1-9]\d*.\d*|0.\d*[1-9]\d*$　　匹配正浮点数
	^-([1-9]\d*.\d*|0.\d*[1-9]\d*)$　匹配负浮点数
	^-?([1-9]\d*.\d*|0.\d*[1-9]\d*|0?.0+|0)$　 匹配浮点数
	^[1-9]\d*.\d*|0.\d*[1-9]\d*|0?.0+|0$　　 匹配非负浮点数（正浮点数 + 0）
	^(-([1-9]\d.\d|0.\d[1-9]\d))|0?.0+|0$　　匹配非正浮点数（负浮点数 + 0）


匹配特定字符串
	^[A-Za-z]+$　匹配由26个英文字母组成的字符串
	^[A-Z]+$　　匹配由26个英文字母的大写组成的字符串
	^[a-z]+$　　匹配由26个英文字母的小写组成的字符串
	^[A-Za-z0-9]+$　　匹配由数字和26个英文字母组成的字符串
	^\w+$　　匹配由数字、26个英文字母或者下划线组成的字符串



















