12. Divisor Substrings

Give a number N and digit number K find all serial substring is able to divisible n.
给一个 int n，长度 k, 问这个数（n）有多少长度为 k 的 subarray 能整除 n

Input: n = 120, k = 2
Output: 2
Explain:
120 -> 12 and 20
120 % 12 == 0 (O)
120 % 20 == 0 (O)

Input: n = 555, k = 1;
Output: 1
Explain:
555 -> 5, 5 and 5 (Duplicate so only count one 5)
555 % 5 == 0 (O)

Input: n = 2345, k = 2
Output: 0
Explain:
2345 -> 23, 34, 45
2345 % 23 != 0 (X)
2345 % 34 != 0 (X)
2345 % 45 != 0 (X)
 




import java.util.*;

public class Test {

    public static void main(String[] args) {
        System.out.println(helper("120", 2));
    }

    private static int helper(String s, int k) {
        int ans = 0;
        int num = Integer.parseInt(s);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            int cur = Integer.parseInt(s.substring(i, i + k));
            if (cur == 0) continue;             //corner case "000"
            if (num % cur == 0) set.add(cur);
        }

        return set.size();
    }
}
