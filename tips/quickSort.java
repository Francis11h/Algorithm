quickSort

Input: [3, 2, 1, 4, 5], 
Output: [1, 2, 3, 4, 5].

快排的最基本实现

class Solution {
	public void sortIntegers2(int[] nums) {
		if (nums == null) return;
		helper(nums, 0, nums.length - 1);
	}

	private void helper(int[] nums, int left, int right) {
		if (left >= right) {						//递归结束条件， 即A数组只有1个或0个元素 不用排,结束递归
			return;
		}
		int pivot = nums[left + (right - left) / 2];	
		int i = left, j = right;
		while (i <= j) {
			while (i <= j && nums[i] < pivot) {      // 左边找一个比pivot大的或等的, 这里左边找比pivot小的跳过, 所以没有等号
				i++;
			}
			while (i <= j && nums[j] > pivot) {		// 右边找比pivot小的或等的
				j--;
			}

			if (i <= j) {							//此时 i还不大于j 就互换两元素 再ij 分别移动
				int temp = nums[i];
				nums[i++] = nums[j];
				nums[j--] = temp;
			}
		}
		helper(nums, left, j);						//[left, ..., j]
		helper(nums, i, right);						//[i, ..., right]
	}
}

O(nlogn)
	如果排序n个关键字，其递归树的深度就为log2n+1,即仅需递归log2n次
	数组有n个元素，因为要递归运算，算出支点pivot的位置，然后递归调用左半部分和有半部分，
	这个时候理解上是若第一层的话就是 n/2，n/2，若是第二层就是n/4,n/4,n/4,n/4 这四部分，
	即n个元素理解上是一共有几层 2^x=n，x(层数)=logn，
	然后每层都是n的复杂度，那么平均就是O(nlogn)的时间复杂度。
	最坏情况 : (n-1)*(n-2)*....*1，这个复杂度肯定就是O(n^2)

就空间复杂度来说，主要是递归造成的栈空间的使用，
	最好情况，递归树的深度为log2n，其空间复杂度也就为O(logn)，
	最坏情况，需要进行n‐1递归调用，其空间复杂度为O(n)，
	平均情况，空间复杂度也为O(logn)