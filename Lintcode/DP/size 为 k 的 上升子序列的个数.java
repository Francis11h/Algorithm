// size 为 k 的 上升子序列的个数

// Input : nums[] = {2, 6, 4, 5, 7}, 
//             k = 3
// Output : 5
// The subsequences of size '3' are:
// {2, 6, 7}, {2, 4, 5}, {2, 4, 7},
// {2, 5, 7} and {4, 5, 7}.


// dp 想法 很直接
// dp[i][j] : count of increasing subsequences of size i ending with element nums[j];

// dp[i][j] = 1, where i = 1 and 1 <= j <= n;
// dp[i][j] = sum(dp[i - 1][j]), where 1 < i <= k, 1 <= j <= n and 
//                             nums[m] < nums[j] for i-1 <= m < j.


//      https://www.geeksforgeeks.org/count-number-increasing-subsequences-size-k/




class Solution {
    private static int numOfIncSubseqOfSizeK(int nums[], int n, int k) {
        int[][] dp = new int[k][n];
        int ans = 0;

        //count of IS of size 1, ending at each nums[i]
        //这里的 dp[i][j] 都减了1 因为 0-index base
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        //length signifies IS the size of (length + 1)
        for (int length = 1; length < k; length++) {
            // for each IS of size (length + 1) ending with nums[i]
            for (int i = 1; i < n; i++) {
                //count of IS of size (length + 1) ending with nums[i]
                dp[length][i] = 0;
                // j 代表 比 要求的size小一的 结尾
                for (int j = length - 1; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[length][i] += dp[length - 1][j];
                    }
                }
            }
        }
        for (int i = k - 1; i < n; i++) {
            ans += dp[k - 1][i];
        }
        return ans;
    } 


    public static void main(String[] args) { 
        int nums[] = {12, 8, 11, 13, 10, 15, 14, 16, 20}; 
        int n = nums.length; 
        int k = 4; 
  
        System.out.print("Number of Increasing Subsequences of size "
                + k + " = " + numOfIncSubseqOfSizeK(nums, n, k)); 
  
    }   
}




