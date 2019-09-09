80. Remove Duplicates from Sorted Array II

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 3) return nums.length;
        int i = 2, j = 2;
        
        while (j < nums.length) {
            if (nums[j] == nums[i - 2]) {
                j++;
            } else {        //means find the new non-duplicate value
                nums[i++] = nums[j++];
            }
        }
        return i;
    }
}




r find a new non-duplicate value 

nums[r] 比的话是 跟 nums[l-2] 比 因为只能要两个 且数组有序 sorted


// sorted 

// r find a new non-duplicate value

// [1,1,1,1,2,2,2,3]
//      l
//          r
// [1,1,2,1,2,2,2,3]     
//        l
//            r
// [1,1,2,2,2,2,2,3]     
//          l
//              r
// [1,1,2,2,2,2,2,3]     
//          l
//                r
// [1,1,2,2,3,2,2,3]     
//            l
//                  r
