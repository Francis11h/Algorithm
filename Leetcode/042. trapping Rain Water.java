42. 接雨水 Trapping Rain Water


给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6


一列一列的算, 
一个是从左更新 一个是从右更新, 相向双指针

一个下标(位置) 能盛下的雨水量 = 它左右两边柱子的最大高度(left_max, right_max)的较小值 - 它的高度     



 I calculated the stored water at each index a and b in my code. 
 At the start of every loop, 
    I update the current maximum height from left side (that is from A[0,1...a]) 
    and the maximum height from right side(from A[b,b+1...n-1])

    if(leftmax < rightmax) then, at least (leftmax-A[a]) water can definitely be stored 
        no matter what happens between [a,b] 
        since we know there is a barrier at the rightside(rightmax>leftmax). 

        On the other side, we cannot store more water than (leftmax-A[a]) at index a 
        since the barrier at left is of height leftmax. 

        So, we know the water that can be stored at index a is exactly (leftmax-A[a]). 

    The same logic applies to the case when (leftmax>rightmax).

At each loop we can make a and b one step closer




class Solution {
    public int trap(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int ans = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, nums[left]);
            rightMax = Math.max(rightMax, nums[right]);
            
            if (leftMax < rightMax) {
                ans += leftMax - nums[left];
                left++;
            } else {
                ans += rightMax - nums[right];
                right--;
            }
        }
        return ans;
    }
}


O(n) O(1)









