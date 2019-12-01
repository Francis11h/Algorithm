1143. Longest Common Subsequence

Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.



Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.



// we can use dynamic programming to slove this problem
// dp[i][j] indicates the LCS of 
//    the substring of A from index 0...i - 1 and the substring of B from index 0...j - 1

// dp[0][j] = 0, dp[i][0] = 0

class Solution {
    public int longestCommonSubsequence(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return 0;
        int m = s.length(), n = t.length();
        // 1. dp array
        int[][] dp = new int[m + 1][n + 1];                     // add one clo and one row for base case
        // 2. base case
        for (int j = 0; j <= n; j++) dp[0][j] = 0;
        for (int i = 0; i <= m; i++) dp[i][0] = 0;
        // 3. transformation & calculation order from left-up to right-down
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {       // i - 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 4. answer  dp[m][n]
        return dp[m][n];
    }
}




dp 四步走

第一步 确定状态: 即确定dp数组 是几维, 每个参数(i, j, dp[i][j]的cell里存的值)的意义
                
                e.g. 设f[i][j]为 A前i个字符A[0..i-1]和 B前j个字符[0..j-1] 的LCS的长度

第二步 转移方程:  e.g. LCS 的 :f[i][j] = max{f[i-1][j], f[i][j-1], 
                                          f[i-1][j-1]+1 when (A[i-1]=B[j-1])}

第三步 初始条件 (base case) 和 边界问题(最后结果是哪个cell)
                e.g. LCS:   f[0][j] = 0, j=0..n             空串 和任何串的最长公共子序列长度是0
                            f[i][0] = 0, i=0..m

第四步 计算顺序

        • f[0][0], f[0][1], ..., f[0][n]
        • f[1][0], f[1][1], ..., f[1][n]
        •...
        •...
        • f[m][0], f[m][1], ..., f[m][n]
        答案是f[m][n]
        
        • 此种时间复杂度(计算步数)O(MN)，空间复杂度(数组大小)O(MN)





