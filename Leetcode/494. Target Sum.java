494. Target Sum

给定一个非负整数的列表a1,a2,...an，再给定一个目标S。
现在用+和-两种运算，对于每一个整数，选择一个作为它前面的符号。



输入: nums为 [1, 1, 1, 1, 1], S 为 3. 
输出: 5
解释: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

有5种方法让和为3.

最朴素的方法

do dfs and try both "+" and "-" at every position


class Solution {
    int result = 0;
    //main function we just need to do the dfs and try both '+' ans '-' at every position 
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        dfs(nums, S, 0, 0);
        return result;
    }
    
    //dfs  param 1.array 2. target 3.now index
    public void dfs(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) result++;
            return;
        }
        dfs(nums, target, index + 1, sum + nums[index]);
        dfs(nums, target, index + 1, sum - nums[index]);
    }
}








dp解法


