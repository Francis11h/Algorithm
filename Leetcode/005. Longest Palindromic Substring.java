5. Longest Palindromic Substring
最长回文子串


Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.


dp[i][j]       substring  i - j is Palindromic or Not 
dp[i][j]        表示的是 String 的下标 i 到 j 是不是 回文串

dp[i][i] = true


    0 b  1 a  2 b  3 a   4 d
0 b   T    F    T    F    F              bab    

1 a        T    F    "T"  F              aba

2 b             "T"  F    F

3 a                  T    F

4 d                       T

    从 右下 i=len- 1 到 右上 
    一行一行 从 j=i 开始 


错误的 计算顺序 
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();                                   
        boolean[][] dp = new boolean[len][len];
        String res = null;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])
                if (dp[i][j] && (res == null || (j - i + 1 > res.length()))) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}

"aaaa"  的结果是 "aaa"

因为 一旦涉及 dp[i + 1][j - 1] 比如 dp[0][3] 要求 dp[1][2] 的值是已经确定的 所以 这就对 计算顺序 是有要求的


i 是 行     i + 1 是 在 i 的左边  
j 是 列     j - 1 是 在 j 的下边

就是说算某个位置时  它的左下角要知道 
计算顺序 应该是 从 下->上 所以 i 是 从 (len - 1) -> 0     大 到 小
                 左->右     j 是 从 i -> (len - 1)     小 到 大
                

正确的 计算顺序 
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int len = s.length();                                   
        boolean[][] dp = new boolean[len][len];
        String res = null;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && (res == null || (j - i + 1 > res.length()))) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}







