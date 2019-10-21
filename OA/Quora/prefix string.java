
prefix string
10. isPrefix，给两个 str array a，b，判断b里面的所有str是不是都是a的str各种组合黏在一起而成的

Sol: 直接建立所有a里面str的permutation然后存入set里

    输入2个String数组： a:“one” "two" "three"      b: "onetwo" "one"     输出：true
    a:"one","twothree","four"     b:"one", "onetwo"    输出：false
    a:“one” "two" "three"      b: "onethree"    输出：false
    大概意思我猜测是，b里的每个String 都是由a里的前n个String顺序拼接组成。


import java.util.*;

public class Test {

    public static void main(String[] args) {
        String[] A = new String[]{"one", "two", "three"};
        String[] B = new String[]{ "onethree" };
        System.out.println(helper(A, B));
    }

    public static boolean helper(String[] A, String[] B) {
        Set<String> set = new HashSet<>();
        String now = "";
        for (String str : A) {
            now += str;
            set.add(now);
        }

        for (String str : B) {
            if (!set.contains(str)) {
                return false;
            }
        }
        return true;
    }
}
