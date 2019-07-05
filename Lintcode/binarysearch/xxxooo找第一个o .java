153. Find Minimum in Rotated Sorted Array

Input: [3,4,5,1,2] 
Output: 1


xxxxoooo 找第一个o
l  m   r
xoooo
l m r
xoo
lmr
xo
lr



[3,4,5,1,2]
l    m   r

[5, 1, 2]
l   m  r



还是 xxxxoooo 基本二分


class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] > nums[right]) {
                if (nums[mid] > nums[left]) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                return nums[left];
            }
        }
        if (nums[left] < nums[right]) return nums[left];
        else return nums[right];
    }
}

但是上面这么写 必须得是两段 单增 
要是只有一段的话 就不成立了 所以需要 更多的条件判断 
[1, 2, 3]
 l  m  r




