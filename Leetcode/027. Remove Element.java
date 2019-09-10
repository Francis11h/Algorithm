27. Remove Element

Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Given nums = [3,2,2,3], val = 3,
Your function should return length = 2, 

Given nums = [0,1,2,2,3,0,4,2], val = 2,
should return length = 5

class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[j] == val) {
                j--;
                continue;
            }
            if (nums[i] == val) {
                nums[i] = nums[j];
                nums[j] = val;
                i++;
                j--;
            } else {
                i++;
            }   
        }
        return i;
    }
}

//in-place 我们只需要把所有等于element的元素放到最后
//本质上是对array进行划分 类似 quick sort 
//左边全是 非val 右边全是val  双指针 用element作为pivot 



