
92. Backpack

Given N items with size A_i, an integer M denotes the size of a backpack. 

How full you can fill this backpack?




f[i][w] == can we use first i items, to get the weight w?





class Solution {
	public int backPack(int m, int[] A) {
		if (A == null || A.length == 0 || m <= 0) return 0;
        int n = A.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
// 		dp[0][0] = true;
		for (int i = 0; i <= n; i++) dp[i][0] = true;
		for (int i = 1; i <= m; i++) dp[0][i] = false;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (j >= A[i - 1]) {			// =  不能少
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		for (int i = m; i >= 0; i--) 
			if (dp[n][i]) return i;

		return 0;
	}
}