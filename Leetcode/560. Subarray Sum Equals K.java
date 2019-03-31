find the total number of continuous subarrays whose sum equals to k

Input:nums = [1,1,1], k = 2
Output: 2

[1, 1]
	[1, 1]




nums			[1, 1, 1]
prefixSum	 [0, 1, 2, 3]


class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
        	return 0;
        int prefixSum = 0;
        Map <Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int num : nums) {
        	prefixSum += num;
    		count += map.getOrDefault(prefixSum - k, 0);
    		map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}











class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int ans = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            if (preSum.containsKey(sum - k)) {
                ans += preSum.get(sum - k);
            }
            
            if (preSum.containsKey(sum)) {
                preSum.put(sum, preSum.get(sum) + 1);
            } else {
                 preSum.put(sum, 1);
            }
        }
        return ans;
    }
}