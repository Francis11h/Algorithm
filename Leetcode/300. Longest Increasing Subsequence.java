300. Longest Increasing Subsequence

Input: [10,9,2,5,3,7,101,18]
Output: 4 
The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 


public class Solution {
    
    dp[i]: 前i个数字组成的序列的最长上升子序列长度 -> 原问题 dp[n]
    dp[i - 1] => dp[i] 看看它前一个状态可以推出来么
     改进！！！！！ fix 最后一个位置，使之有迹可循
    dp[i] 表示 必须以第i个数字结尾的 且是上升子序列的最大长度
    原问题在状态中， 且固定了最后一个位置

    dp[i] = Max{dp[j]} + 1。(nums[j] < nums[i], 1<=j<i)
    
    如果 nums[j] >= nums[i] 状态就无法转移 因为如果加一个小的数就不满足上升的条件了

    public int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int ans = 1;
        int[] dp = new int[nums.length]; // 0 ~ n - 1
        //initilization
        dp[0] = 1;
        //转移
        for (int i = 1; i < nums.length; ++i) { //穷举所有状态 dp[i]
            dp[i] = 1;  //都不可能小于1， 防止以后可能的答案是0，这样子会丢掉结果为1的
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]); // 打擂台
        }
        return ans;
    }
}

// O(n ^ 2) 

//求 最长上升子串
// dp[i] : 以i结尾的最长 包括substring
// dp[i] = dp[i - 1] + 1, if (nums[i - 1] < nums[i]);
// 不满足 nums[i - 1] < nums[i], 则设置dp[i] = 1
