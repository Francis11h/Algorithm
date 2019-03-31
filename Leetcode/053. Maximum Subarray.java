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