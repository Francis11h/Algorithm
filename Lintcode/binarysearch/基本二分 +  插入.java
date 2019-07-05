35. Search Insert Position

还是基本二分
就是 如果找到返回下标 找不到返回应该插入的位置

Input: [1,3,5,6], 5
Output: 2

Input: [1,3,5,6], 2
Output: 1


还是基本二分, 就是最后找不到的时候判断3种插入位置

    [left, ,right]
left  | right | right + 1

class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target <= nums[left]) return left;
        if (target > nums[left] && target <= nums[right]) return right;
        if (target > nums[right]) return right + 1;
        return 0;
    }
}






