26. Remove Duplicates from Sorted Array

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int l = 0, r = 0;
        
        while (r < nums.length) {
            if (nums[r] != nums[l]) {
                nums[l + 1] = nums[r];
                l++;
            }
            r++;
        }
        return l + 1;
    }
}



// [0, 1, 1, 1, 2, 3]
//     l
//              r

        
//  l record now non-duplicate val
//  r find the next non-duplicate val with nums[l] 算法：

基本思路就是右指针向右走，找到跟左指针值不一样的元素，然后跟左指针加一的元素进行swap。
这种双指针同向移动的题目，一定要把左右指针干什么事情定义好。比如这个题目，右指针就是找下一个不同的元素，左指针就是记录当前不同元素。

