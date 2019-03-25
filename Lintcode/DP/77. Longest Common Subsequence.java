77. Longest Common Subsequence

return the length of LCS

Input: "ABCD" and "EACB"
Output:  2
Explanation: LCS is "AC"

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



1. dp
class Solution {
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] dp = new int[m + 1][n + 1];         // +1 是必须的
        //base case
        for (int i = 0; i < m; i++)
            dp[i][0] = 0;
        for (int j = 0; j < n; j++) 
            dp[0][j] = 0;
        
        for (int i = 1; i <= m; i++) {              // <= =等号是必须的
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
    /*  从0开始写 亦可, 加个判断 + continue 即可
   for (int i = 0; i <= m; i++) {      
        for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0) {
                dp[i][j] = 0;
                continue;
            }
        }
    }*/

O(mn)
O(mn)

+1 是 因为 dp[i][j] 是 A的前i个字符A[0...i-1], B的前j个字符 B[0...j-1]
    i 的取值 从 0 开始, 一直到 m, 共 m+1 个值       所以 for (int i = 1; i <= m; i++) 等号不可少
    i = 0, A是空串儿; i = m, 前m个字符即 A[0...m-1] 完整串儿

最后返回的是 dp[m][n].



2. dp优化
滚动数组
因为 每次计算只需要两行: previous and current row

class Solution {
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length(), n = B.length();
        int[] prev = new int[m + 1];         // +1 是必须的     
        
        for (int i = 1; i <= m; i++) {           
            int[] current = new int[m + 1];
            current[0] = 0;
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    current[j] = prev[j - 1] + 1;
                } else {
                    current[j] = Math.max(current[j - 1], prev[j]);
                }
            }
            prev = current;
        }
        return prev[m];
    }
}

Time Complexity: O(mn)
Space Complexity: O(m)


3. memorization recursive

class Solution {
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] memo = new int[m][n];
        return helper(A, B, m - 1, n - 1, memo);
    }
    
    public int helper(String A, String B, int l1, int l2, int[][] memo){
        int result;
        if (l1 < 0 || l2 < 0) {
            return 0;
        }
        if (memo[l1][l2] > 0) {
            return memo[l1][l2];
        }
        if (A.charAt(l1) == B.charAt(l2)) {
             result = 1 + helper(A, B, l1 - 1, l2 - 1, memo);
        } else {
            int m = helper(A, B, l1 - 1, l2, memo);
            int n = helper(A, B, l1, l2 - 1, memo);
            result =  Math.max(m,n);
        }
        memo[l1][l2] = result;
        return result;
    }
}



本题的两个 follow up：

fellow up 1：求出任意一个LCS
fellow up 2：求出所有的LCS
