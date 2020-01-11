

经典 dp版本。TLE

public class Solution {
    
    public int jump(int[] A) {
        // dp[i]: minimum jump to index i => dp[n - 1]
        // dp[i] = Min{dp[j] + 1} : dp[j] is ccessable && A[j] >= i - j
        
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) { //状态数
            dp[i] = Integer.MAX_VALUE;//代表为我这个dp[i] is not reachable
            for (int j = 0; j < i; j++) { //转移
                if (dp[j] + 1 < dp[i] && A[j] >= i - j) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[n - 1];
    }
}



2020.1.10 dp版本      TLE


// // dp[i] = valueOf(the min step we need to reach index i)

// // dp = min {dp[j] + 1}, when dp[j] is reachable and A[j] >= i - j (means we can reach i from j) 

class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;      //we can always reach first index
        
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE; // means i is unreachable
            for (int j = 0; j < i; j++) {   // all index before i can be our start
                if (dp[j] != Integer.MAX_VALUE && nums[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];   //u can assume that you can always reach the last index.
    }
}








Greedy

2 3 1 1 4


class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int jump = 0, curFarest = 0, end = 0;
        for (int i = 0; i < n - 1; i++) {       // 最后一个元素不能取 因为这里代表开始的点
            //找能跳的最远的
            curFarest = Math.max(curFarest, i + nums[i]);
            if (i == end) { //跳到边界 更新边界，并且步数 + 1
                jump++;
                end = curFarest;
            }
        }
        return jump;
    }
}


https://leetcode-cn.com/problems/jump-game-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-10/























