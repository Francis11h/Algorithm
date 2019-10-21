8.  GoodTuples

给定array, 找出所有连续三元素组成的tuple, 使得其中有且仅有两个元素相同。

Give an array
find the count of a pair number and a single number combination in a row of this array.

Target array is a[i - 1], a, a[i + 1]
Example：
Input: a = [1, 1, 2, 1, 5, 3, 2, 3]
Output: 3

[1, 1, 2] -> two 1 and one 2(O)
[1, 2, 1] -> two 1 and one 2(O)
[2, 1, 5] -> one 2, one 1 and one five(X)
[1, 5, 3] -> (X)
[5, 3, 2] -> (X)
[3, 2, 3] -> (O)






import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] A = new int[] {1, 1, 2, 3, 3, 3, 3, 3};

        System.out.println(helper(A));

    }

    public static int helper(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int ans = 0;
        int[] count = new int[10];

        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            if (i < 2) continue;
            if (i > 2) {
                count[nums[i - 3]]--;
            }
            for (int val : count) {
                if (val == 2) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }

}


第一道题：和面经里面的 good tuple 有点像，但是求的是distinct pair 也就是要a, a[i+1], a[i+2]都不一样有多少个。


import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] A = new int[] {1, 1, 2, 3, 3, 3, 3, 3};

        System.out.println(helper(A));

    }

    public static int helper(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int ans = 0;
        int[] count = new int[10];

        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            if (i < 2) continue;
            if (i > 2) {
                count[nums[i - 3]]--;
            }
            boolean isDistinct = true;
            for (int val : count) {
                if (val > 1) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                ans++;
            }
        }

        return ans;
    }

}








Different characters

intput: a = "aabdcreff"
output: 5
问a中存在多少a, a[i-1], a[i+1]都不同的情况

three chars distinct
给一个字符串s，问一共有多少组 s,s[i+1],s[i+2] 没有重复的字符
input： s="abcaaaaae"
output: 2    ("abc","bca")



public class Test {

    public static void main(String[] args) {
        String B = "aabdcreff";
        System.out.println(helper(B));
    }

    public static int helper(String s) {
        if (s == null || s.length() < 3) return 0;
        int ans = 0;
        int[] count = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch]++;

            if (i < 2) continue;
            if (i > 2) {
                char rmv = s.charAt(i - 3);
                count[rmv]--;
            }
            boolean isDistinct = true;
            for (int val : count) {
                if (val > 1) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                ans++;
            }
        }

        return ans;
    }
}

