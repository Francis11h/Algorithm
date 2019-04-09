062. Unique Paths

左上角 走到右下角 每次只能向右或者向下走, 问有多少种走法。

A robot is located at the top-left corner of a m x n grid.

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid.

How many possible unique paths are there?


dp[i][j] = the # of unique path from (0,0) to (i, j);

base case
	dp[0][0] = 1;
	dp[0][j] = 1;
	dp[i][0] = 1;

dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

dp[m - 1][n - 1];

注意 坐标最后是 (m - 1, n - 1);

class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 && n == 0) return 0;
        if (m == 0 || n == 0) return 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) 
            dp[i][0] = 1;
        for (int j = 1; j < n; j++) 
            dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

(3, 2)	3

	0	1	2
0 	0	1	1
1	1	2	3


(4, 3)	10

	0	1	2	3
0 	0	1	1	1
1	1	2	3	4
2	1	3	6	10

