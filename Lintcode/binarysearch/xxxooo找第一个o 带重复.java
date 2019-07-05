154. Find Minimum in Rotated Sorted Array II

The array may contain duplicates.


Input: [1,3,5]
Output: 1

Input: [2,2,2,0,1]
Output: 0



xxxxooooo

[2,2,2,0,1]         [2, 0, 1]
 l   m   r


[3, 1, 3]
 l  m  r

[10,1,10,10,10]   [10, 1, 10]
 l     m     r      l  m  r

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] >= nums[right]) {
                if (nums[mid] > nums[left]) {
                    left = mid;
                } else if (nums[mid] < nums[left]) {
                    right = mid;
                } else {
                    left++;                     //反正nums[left]不是最小的了（如果是后面也有重复的），所以可以跳过
                }
            } else {
                return nums[left];
            }
        }
        if (nums[left] < nums[right]) return nums[left];
        else return nums[right];
    }
}






// version 1: just for loop is enough
public class Solution {
    public int findMin(int[] num) {
        //  这道题目在面试中不会让写完整的程序
        //  只需要知道最坏情况下 [1,1,1....,1] 里有一个0
        //  这种情况使得时间复杂度必须是 O(n)
        //  因此写一个for循环就好了。
        //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
        //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min)
                min = num[i];
        }
        return min;
    }
}