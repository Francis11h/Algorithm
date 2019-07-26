Java String To Integer

取出字符串中数字        Integer.valueOf(str.substring(begin, end));         Integer类方法
                     Integer.parseInt(str);







parseInt 方法


public final class Integer extends Number implements Comparable<Integer> {
	/**
     * A constant holding the minimum value an {@code int} can
     * have, -2<sup>31</sup>.
     */
    @Native public static final int   MIN_VALUE = 0x80000000;

    /**
     * A constant holding the maximum value an {@code int} can
     * have, 2<sup>31</sup>-1.
     */
    @Native public static final int   MAX_VALUE = 0x7fffffff;

    /**
     * All possible chars for representing a number as a String
     */
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };


    //....中间隔了一堆方法

    // parseInt()

    /**
     * Parses the string argument as a signed integer in the radix
     * specified by the second argument. 
     *
     * <li>Any character of the string is not a digit of the specified
     * radix, except that the first character may be a minus sign
     * {@code '-'} ({@code '\u005Cu002D'}) or plus sign
     * {@code '+'} ({@code '\u005Cu002B'}) provided that the
     * string is longer than length 1.

     * parseInt("0", 10) returns 0
     * parseInt("473", 10) returns 473
     * parseInt("+42", 10) returns 42
     * parseInt("-0", 10) returns 0
     * parseInt("-FF", 16) returns -255
     * parseInt("1100110", 2) returns 102
     * parseInt("2147483647", 10) returns 2147483647
     * parseInt("-2147483648", 10) returns -2147483648
     * parseInt("2147483648", 10) throws a NumberFormatException
     * parseInt("99", 8) throws a NumberFormatException
     * parseInt("Kona", 10) throws a NumberFormatException
     * parseInt("Kona", 27) returns 411787
     */
    public static int parseInt(String s, int radix)
                throws NumberFormatException
	{
       
        if (s == null) {
            throw new NumberFormatException("null");
        }
        //判断基数是否小于最小基数 2
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                                            " less than Character.MIN_RADIX");
        }
        //判断基数是否大于最大基数 36
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                                            " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;			//-2147483647
        int multmin;
        int digit;
        //字符串长度 必须大于0
        if (len > 0) {
        	//只有第一位可能是正负号 所以取出来判断
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;		// -2147483648
                } else if (firstChar != '+')		// 写的漂亮啊！ 如果不是负号 也不是正号 
                    throw NumberFormatException.forInputString(s);
                //如果只有符号位 那么也就抛出异常
                if (len == 1) // Cannot have lone "+" or "-"
                    throw NumberFormatException.forInputString(s);
                i++;
            }
            multmin = limit / radix;     // multmin : -214748364  radix = 10
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                // 返回指定基数中字符表示的数值。
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw NumberFormatException.forInputString(s);
                }
                // 乘radix之前先判断乘了不会越界
                if (result < multmin) {
                    throw NumberFormatException.forInputString(s);
                }
                result *= radix;
                // 减digit之前先判断减了不会越界
                if (result < limit + digit) {
                    throw NumberFormatException.forInputString(s);
                }
                result -= digit;				//都是按负数来计算
            }
        } else {
            throw NumberFormatException.forInputString(s);
        }
        return negative ? result : -result;
    }


 	// parseInt(String s)--内部调用parseInt(s,10)（默认为10进制）
	// 正常判断null，进制范围，length等
	// 判断第一个字符是否是符号位
	// 循环遍历确定每个字符的十进制值
	// 通过*= 和-= 进行计算拼接
	// 判断是否为负值 返回结果。


    public static int parseInt(String s) throws NumberFormatException {
        return parseInt(s,10);
    }

    // valueOf()

    public static Integer valueOf(String s, int radix) throws NumberFormatException {
        return Integer.valueOf(parseInt(s,radix));
    }

    public static Integer valueOf(String s) throws NumberFormatException {
        return Integer.valueOf(parseInt(s, 10));
    }

}













Character.digit(char ch, int radix)方法

public static int digit(int codePoint, int radix) {
    //基数必须再最大和最小基数之间
    if (radix < MIN_RADIX || radix > MAX_RADIX) {
        return -1;
    }
    
    if (codePoint < 128) {
        // Optimized for ASCII
        int result = -1;
        //字符在0-9字符之间.   '1' = 1
        if ('0' <= codePoint && codePoint <= '9') {
            result = codePoint - '0';
        }
        //字符在a-z之间.   'a' = 10
        else if ('a' <= codePoint && codePoint <= 'z') {
            result = 10 + (codePoint - 'a');
        }
        //字符在A-Z之间.    'A' = 10
        else if ('A' <= codePoint && codePoint <= 'Z') {
            result = 10 + (codePoint - 'A');
        }
        //通过判断result和基数大小，输出对应值
        //通过我们parseInt对应的基数值为10，
        //所以，只能在第一个判断（字符在0-9字符之间）
        //中得到result值 否则后续程序会抛出异常
        return result < radix ? result : -1;
    }
    return digitImpl(codePoint, radix);
}





















