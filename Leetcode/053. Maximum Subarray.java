53. Maximum Subarray

连续的
find the contiguous subarray (containing at least one number) 
which has the largest sum and return its sum.

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.


			[-2,1,-3,4,-1,2,1,-5,4]
prefixSum [0,-2,-1,-4,0,-1,1,2,-3,1]


class Solution {
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) 
			return 0;
		int prefixSum = 0;
		int minPrefixSum = 0;
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			prefixSum += nums[i];
			ans = Math.max(ans, prefixSum - minPrefixSum);            
			if (minPrefixSum > prefixSum)
				minPrefixSum = prefixSum;
		}
        return ans;
	}
}


/*//暴力枚举，left,right,for求和    TLE
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) { //subarray left begin
            for (int j = i; j < nums.length; j++) { //subarray right end
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }
}*/

/*//枚举 left, right points + prefix Sum求和）
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) { //subarray left begin
            int sum = 0;
            for (int j = i; j < nums.length; j++) { //subarray right end
                sum += nums[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}*/


/*// Greedy, only the prefix sum > 0, add them to the next start val
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}

//     [-2, 1, -3, 4, -1, 2, 1, -5, 4]
// max -2   1  1   4   4   5 6  6  5

// sum -2  1   -2 4    3   5 6  1   5

*/

//DP solution
/*
class Solution {
    public int maxSubArray(int[] nums) {
        //if(nums == null) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        // base case 
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}*/