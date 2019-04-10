33. Search in Rotated Sorted Array

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

O(logn)


nums = [4,5,6,7,0,1,2]


1.determine which part the Mid is in
	就是先判断A[mid]与A[left]的大小，要是A[mid]大，就保证在xxxxoooo的x里面，
    * 因为 left 到 mid 是单增
    * 而mid到right可能单减或先增后减
    所以 先比 nums[left] < nums[mid], 要是A[mid]大，就保证在xxxxoooo的x里面.


2.determine what range Target is in
	这时判断 target >= A[left] && target < A[mid],
	既保证了每次缩小范围时，右边界缩小，即每次把mid值赋给right；
    *否则，左边界缩小。

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[left] < nums[mid]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid;
                else 
                    left = mid;
            } else {
                if (target > nums[mid] && target <= nums[right]) 
                    left = mid;
                else 
                    right = mid;
            }
        }
        if (nums[left] == target)
            return left;
        if (nums[right] == target)
            return right;
        return -1;
    }
}