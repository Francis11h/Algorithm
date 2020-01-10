125. Backpack II

There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.

Whats the maximum value can you put into the backpack?


Example 1:

Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
Output: 9
Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9 
Example 2:

Input: m = 10, A = [2, 3, 8], V = [2, 5, 8]
Output: 10
Explanation: Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10 








和前一题类似，需要知道N个物品
– 是否能拼出重量W (W =0, 1, ..., M) – 对于每个重量W，最大总价值是多少

最后一步:最后一个物品(重量AN-1, 价值VN-1)是否进入背包
  
选择一:如果前N-1个物品能拼出W，最大总价值是V，前N个物品也能拼出 W并且总价值是V
选择二:如果前N-1个物品能拼出W- AN-1，最大总价值是V，则再加上最后一 个物品(重量AN-1, 价值VN-1)，能拼出W，总价值是V+VN-1




f[i][w] = valueOf (用前i个物品 拼出重量w时 最大的总价值) (-1 表示 不能拼出重量w)




class Solution {
	public int backPackII(int m, int[] A, int[] V) {
		if (A == null || V == null || A.length == 0 || V.length == 0 || m <= 0) return 0;
		int n = A.length;

		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) dp[n][0] = 0;	// use any item to form weight 0, value must be 0
		for (int j = 1; j <= m; j++) dp[0][j] = -1;	// cannot get weight > 0, since we use 0 item.

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (j >= A[i - 1] && dp[i - 1][j - A[i - 1]] != -1) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
        
        int res = 0;
		for (int j = m; j >= 0; j--) {
			if (dp[n][j] != -1)
				res = Math.max(res, dp[n][j]);
		}
		return res;
	}
}







// public class Solution {
//     /**
//      * @param m: An integer m denotes the size of a backpack
//      * @param A: Given n items with size A[i]
//      * @param V: Given n items with value V[i]
//      * @return: The maximum value
//      */
//     public int backPackII(int m, int[] A, int[] V) {
//         int n = A.length;
//         if (n == 0) {
//             return 0;
//         }
        
//         int[][] f = new int[n + 1][m + 1];
//         int i, j;
//         f[0][0] = 0;
//         for (j = 1; j <= m; j++) {
//             f[0][j] = -1; //-1 表示 价值拼不出来
//         }
        
//         for (i = 1; i <= n; i++) {
//             for (j = 0; j <= m; j++) {
//                 f[i][j] = f[i - 1][j]; // not using item i - 1
//                 // using items i - 1;
//                 if (j >= A[i - 1] && f[i - 1][j - A[i - 1]] != -1) {
//                     f[i][j] = Math.max(f[i][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
//                     // 价值 = 两者之间的最大值()
//                 }
//             }
//         }
        
//         int res = 0;
//         for (j = 0; j <=m; j++) {
//             if (f[n][j] != -1) { //前n个物品 不是一定要用第n个，而是已经考虑了所有n个物品的情况
//                 res = Math.max(res, f[n][j]);
//             }
//         }
//         return res;
//     }
// }


