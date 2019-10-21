removeExactOneDigit

remove exact one digit char from string s or t, so that s < t;

input: String s1,s2 (lower case letters and digits)
output: number of ways to remove the digit char.


string s和string t，移除这两个string中任意一个为数字的char，使得 s < t，问一共有多少种移除方法
Sol: 把所有为数字的char找出来，每一次移除一个试一下就好了




    String s1 = "heflo";
    String s2 = "hhllo";
    System.out.println(removeExactOneDigit(s1, s2));
    s1 = "h";
    s2 = "hhllo";
    System.out.println(removeExactOneDigit(s1, s2));
    s1 = "hf";
    s2 = "hhllo";
    System.out.println(removeExactOneDigit(s1, s2));
    s1 = "hi";
    s2 = "hhllo";
    System.out.println(removeExactOneDigit(s1, s2));


挨个减 即可


import java.util.*;

public class Test {

    public static void main(String[] args) {
        String s1 = "heflo";
        String s2 = "hhllo";
        System.out.println(removeExactOneDigit(s1, s2));
    }


    public static int removeExactOneDigit(String s1, String s2) {
        if(s1 == null && s2 == null) {
            return 0;
        }
        if(s1 == null) {
            return s2.length();
        }
        if(s2 == null) {
            return 0;
        }
        if(s1.length() == 0 && s2.length() == 0) {
            return 0;
        }
        if(s1.length() == 0) {
            return s2.length();
        } else if(s2.length() == 0) {
            return 0;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int s1Arrow = 0;
        int s2Arrow = 0;
        int res = 0;
        if(s1.charAt(0) < s2.charAt(0)) {
            res = (s1.length() - 1) + (s2.length() - 1);
            //consider the first two
            if(compareStringVal(s1.substring(1), s2) > 0) {
                res++;
            }
            if(compareStringVal(s1, s2.substring(1)) > 0){
                res++;
            }

        } else if (s1.charAt(s1Arrow) == s2.charAt(s2Arrow)) {
            res = removeExactOneDigit(s1.substring(1), s2.substring(1));
            if(compareStringVal(s1.substring(1), s2) > 0) {
                res++;
            }
            if(compareStringVal(s1, s2.substring(1)) > 0){
                res++;
            }
        } else {
            if(compareStringVal(s1.substring(1), s2) > 0) {
                res = 1;
            } else {
                res = 0;
            }
        }
        return res;
    }
    
    // 1 means s1 < s2   -1 means s1 > s2
    public static int compareStringVal(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int s1Arrow = 0;
        int s2Arrow = 0;
        while (s1Arrow < len1 && s2Arrow < len2) {
            if(s1.charAt(s1Arrow) < s2.charAt(s2Arrow)) {
                return 1;
            } else if (s1.charAt(s1Arrow) == s2.charAt(s2Arrow)) {
                s1Arrow++;
                s2Arrow++;
            } else {
                return -1;
            }
        }
        if(s1Arrow < len1)
            return -1;
        else
            return 1;
    }
}


