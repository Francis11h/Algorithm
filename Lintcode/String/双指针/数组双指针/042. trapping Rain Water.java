42. 接雨水 Trapping Rain Water


给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6


一个位置一个位置算, 
一个下标(位置) 能盛下的雨水量 = 它左右两边柱子的最大高度(left_max, right_max)的最小值 - 它的高度     

所以就变成了 如何找所有位置的左右两边柱子的最大高度 left_max, right_max

但是 一个是从左更新 一个是从右更新
所以用到双指针, 然后 用 if (height[left] <= height[right]) 判断左边还是右边
                再用 一个 if (height[left] < left_max) 或者 if (height[right] < right_max) 来判断是不是可以盛水



class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0, right = height.length - 1;
        int left_max = 0;
        int right_max = 0;          
        int ans = 0;
        while (left < right) {              
            if (height[left] <= height[right]) {         //左边比右边小,  当前处理的就是左边这个下标
                                                        //那么要是更新两边柱子的最大高度(left_max, right_max)的最小值, 就是会更新在左边
                if (height[left] < left_max)    ans += left_max - height[left];
                else left_max = height[left];
                left++;
            } else {
                if (height[right] < right_max)  ans += right_max - height[right];
                else right_max = height[right];
                right--;
            }
        }
        return ans;
    }
}

O(n) O(1)


// https://leetcode-cn.com/problems/trapping-rain-water/solution/zuo-you-liang-bian-de-zui-da-zhi-by-powcai/






