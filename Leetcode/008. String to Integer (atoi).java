8. String to Integer (atoi)

字符串转换为整数

会找到 第一个非空的字符
	IF 找到 + - 号 则将该符号与之后面尽可能多的连续数字组合起来,作为该整数的正负号；
	IF 第一个非空字符是数字,则直接将其与之后连续的数字字符组合起来,形成整数.

	该字符串除了有效的整数部分之后也可能会存在多余的字符, 这些字符可以被忽略, 它们对于函数不应该造成影响

假如该字符串的第一个非空格字符不是一个有效整数字符 或者 正负号,  则不用转换。

越界的 返回 Integer.MAX_VALUE / Integer.MIN_VALUE


输入: "42"
输出: 42

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。因此无法执行有效的转换。


输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 因此返回 INT_MIN (−2^31) 


//自己写的 版本 就是 最简单的 
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        char ch = ' ';
        // 先找到第一位不为空格的字符
        for (; i < str.length(); i++) {
        	ch = str.charAt(i);
        	if (ch != ' ') break;
        }
        if (i == str.length()) return 0;

        //如果第一位不是数字或者正负号 则无意义
        if (!Character.isDigit(ch) && (ch != '-' && ch != '+')) return 0;

        boolean negative = false;
        if (ch == '-' || ch == '+') {
        	if (ch == '-') negative = true;
        	i++;
        }
        
        int start = i;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
        	i++;
        }	
        if (i == start) return 0; 				//不加的话 这个 test case "   -  " 过不了
        String num = str.substring(start, i);
        // int ans = Integer.valueOf(str.substring(start, i));

        //除了越界 上面的就都完成了 最起码拿到了个数字	

        // try{
        //     ans = Integer.parseInt(num);
        // }catch (Exception e){
        //     if(flag == -1)
        //         return Integer.MIN_VALUE;
        //     return Integer.MAX_VALUE;
        // }

        int ans = 0;
        for (int k = 0; k < num.length(); k++) {
        	int digit = num.charAt(k) - '0';
        	if (negative) {
        		// 乘10 之前 不能小于 -214748364 , -214748364 * 10 最多减 -8
        		if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && digit > 8)) {
        			return Integer.MIN_VALUE;
        		}
        		ans = ans * 10 - digit; 
        	} else {
        		// 乘10 之前 不能大于 214748364 , 214748364 * 10 最多加 7
        		if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && digit > 7)) {
        			return Integer.MAX_VALUE;
        		}
        		ans = ans * 10 + digit;
        	}
        }
        return ans;
    }
}




//优雅一点的代码

class Solution {
    public int myAtoi(String str) {
        int i = 0, j = 0;
        boolean negative = false;

        for (i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                break;
            } else if (ch == '-' || ch == '+') {
                negative = ch == '-';
                i++;
                break;
            } else if (ch != ' ') return 0;
        }
        
        for (j = i; j < str.length(); j++) {
            char ch = str.charAt(j);
            if (ch < '0' || ch > '9') break;
        }

        String num = str.substring(i, j);
        int ans = 0;
        for (int k = 0; k < num.length(); k++) {
            int digit = num.charAt(k) - '0';
            if (negative) {
                if (ans < Integer.MIN_VALUE / 10 || ans == Integer.MIN_VALUE / 10 && digit > 8) return Integer.MIN_VALUE;
                ans = ans * 10 - digit;
            } else {
                if (ans > Integer.MAX_VALUE / 10 || ans == Integer.MAX_VALUE/ 10 && digit > 7) return Integer.MAX_VALUE;
                ans = ans * 10 + digit;
            }
        }
        return ans;
    }
}






















