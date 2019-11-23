75. Sort Colors

Given an array with n objects colored red, white or blue, 
sort them "in-place" so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]



A rather straight forward solution is a "two-pass" algorithm using counting sort.
First, iterate the array counting number of 0‘s, 1’s, and 2‘s, then overwrite array with the counts.


class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int a = 0, b = 0, c = 0;
        for (int num : nums) {
            switch (num) {
                case 0 : 
                    a++;
                    break;
                case 1 :
                    b++;
                    break;
                case 2 :
                    c++;
                    break;
            }
        }

        // for (int num : nums) {       // 不写 switch 这么写 也可以
        //     if (num == 0) a++;
        //     else if (num == 1) b++;
        //     else if (num == 2) c++;
        // }

        // for (int num : nums) {       ❌错误 这样子 for(int num : nums) 修改不了nums 数组中的值
        //     if (a > 0) {
        //         num = 0;
        //         a--;
        //     } else if (b > 0) {
        //         num = 1;
        //         b--;
        //     } else {
        //         num = 2;
        //     }
        // }
        for (int i = 0; i < nums.length; i++) {
            if (a > 0) {
                nums[i] = 0;
                a--;
            } else if (b > 0) {
                nums[i] = 1;
                b--;
            } else {
                nums[i] = 2;
            }
        }
    }
}







Follow Up:
Could you come up with a "one-pass" algorithm using only constant space?

一般面试 都要求 这种做法

----> one-pass ----> two pointers



"quicksort 3-way partition"
+------+---------+------------+-------+
|  <p  |  =p     |  unseen .  |   > p |
+------+---------+------------+-------+
        ↑         ↑          ↑
        l         i          h 



基于快排的 三分做法

low:    1st elem == pivot
i:      1st unseen elem
high:   last unseen elem


class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int low = 0, high = nums.length - 1, i = 0;
        while (i <= high) {
            if (nums[i] == 0) {                 //  nums[i] < pivot
                swap(nums, i, low);
                i++;
                low++;
            } else if (nums[i] == 2) {          // nums[i] > pivot
                swap(nums, i, high);
                high--;                         // 注意 这里没有i的变化 因为 i和 high 都是 unseen的
            } else {                            // nums[i] == pivot
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}







[0  1   1   2   0]
 l              h
 i           


[0  1   1   2   0]
    l           h
    i  


[0  1   1   2   0]
    l           h
        i 


[0  1   1   2   0]
    l           h
            i 


[0  1   1   0   2]
    l       h
            i 

[0  0   1   1   2]
    l       h
            i 

[0  0   1   1   2]
        l   h
                i       -- i > high end





