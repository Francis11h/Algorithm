55. Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.







2020.1.10    
dp[i] = T / F means can we reach index i


class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;      //we can always reach first index
        
        for (int i = 1; i < n; i++) {
            dp[i] = false; // means i is unreachable
            for (int j = 0; j < i; j++) {   // all index before i can be our start
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];   
    }
}







Greedy



class Solution {
    public boolean canJump(int[] nums){
        if (nums == null || nums.length == 0) return false;
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (farest < i) return false;
            farest = Math.max(farest, i + nums[i]);
        }
        return true;
    }
}
