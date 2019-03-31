标准	binary search

For a given sorted array (ascending order) and a target number, 
find the first index of this number in O(log n) time complexity.

Input: [1, 2, 3, 3, 4, 5, 10], target = 3
Output: 2
Explanation: the first index of 3 is 2


几个关键点:
1. left + 1 < right, 保证每次循环区间都缩小
2. mid = left + (right - left) / 2, 防止相加越界
3. while 循环结束的状态 数组只有两个值 [left, right], 分别比较即可

class Solution {
	public int binarySearch(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		int left = 0, right = nums.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target)
				left = mid;
			else 
				right = mid;
		}	

		if (nums[left] == target)
			return left;
		if (nums[right] == target)
			return right;
		return -1;
	}
}