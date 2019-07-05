81. Search in Rotated Sorted Array II

You are given a target value to search. If found in the array return true, otherwise return false.

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


This is a follow up problem to Search in Rotated Sorted Array, 
where nums may contain duplicates.

旋转数组可能有重复



xxxxxxxxMxxooToooo
xxxxTxxxMxxooooooo
xxxxoTooMooooooooo
xxxxooooMoooTooooo


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid])  right = mid;
                else left = mid;
            } else if (nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right])  left = mid;
                else right = mid;
            } else {
                left++;                 //nums[mid] == nums[left] 时 无法判断 那就 把left再移一位, 再判断 (我觉解决不了你,那我就跳过你 反正你不是target)
            }
        }
        if (nums[left] == target || nums[right] == target) return true;
        return false;
    }
}



[1, 1, 3, 1]      T = 3
 l  m     r


[1, 3, 1, 1, 1]
l      m     r

问题出来了 我们现在无法根据 nums[mid] 与 nums[left] 大小关系 确定 M 在 xxxxxoooo的哪边了 因为有重复的(相等关系导致混乱) 不是单增的
         所以相当于再单独判断 nums[mid] 与 nums[left] 相等的情况即可 这种情况其实很简单 left++ 即可




其实没有什么屌用。。。

最坏情况下 [1,1,2,1... 1] 里有一个2即可 这种情况下 无法用2分啊, 就直接遍历一遍吧 O(n)

public class Solution {
    // 这个问题在面试中不会让实现完整程序
    // 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
    // 在这种情况下是无法使用二分法的，复杂度是O(n)
    // 因此写个for循环最坏也是O(n)，那就写个for循环就好了
    //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
    //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i ++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;
    }
}


