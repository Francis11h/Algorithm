34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order
find the starting and ending position of a given target value.

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]




本题要求 O(logn) ------> binary search

与基本二分不一样的是, 会有重复的值
怎么确定 哪个是开始 哪个是结束?

nums[mid] == target 的时候 怎么取舍？ 

或许我们没有办法一次二分就找出整个范围----->不如做两次, 第一次找开始, 第二次找结束

[5, 7, 7, 8, 8, 10]

l       m        r

[7, 8, 8, 10]
l   m     r



class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int[] ans = new int[2];
        //找开始
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else if (nums[mid] >= target) {
                right = mid;
            }
        }
        if (nums[left] == target) ans[0] = left;
        else if (nums[right] == target) ans[0] = right;
        else return new int[]{-1, -1};
        //找结束
        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (nums[right] == target) ans[1] = right;
        else if (nums[left] == target) ans[1] = left;
        else ans[1] = ans[0];
          
        return ans;
    }
}


















