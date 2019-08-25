280. Wiggle Sort

Given an unsorted array nums, 
reorder it In-Place such that nums[0] <= nums[1] >= nums[2] <= nums[3]...


最开始的笨方法

直接sort 之后从第二位开始 每两位交换

最笨
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        Arrays.sort(nums);
        if (nums.length % 2 == 1) {
            for (int i = 1; i < nums.length - 1; i += 2) {
                swap(nums, i, i + 1);
            }
        } else {
            for (int i = 1; i < nums.length - 2; i += 2) {  //但其实这里是没有必要的 就是 奇偶情况是一样的 因为 i每次 +2 i只能取到奇数index
                swap(nums, i, i + 1);
            }
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


// [123456]
// [132546]

// [12345]
// [13254]

不用分奇偶 稍微优化 但还是 nlogn
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}




one-pass 方法


Intuitively, we should be able to reorder it in one-pass. 
As we iterate through the array, we compare the current element to its next element 
and if the order is incorrect, we swap them.


public void wiggleSort(int[] nums) {
    boolean less = true;
    for (int i = 0; i < nums.length - 1; i++) {
        if (less) {
            if (nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
            }
        } else {
            if (nums[i] < nums[i + 1]) {
                swap(nums, i, i + 1);
            }
        }
        less = !less;
    }
}

We could shorten the code further by compacting the condition to a single line. 
Also observe the boolean value of less actually depends on whether the index is even or odd.


public void wiggleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
        if (((i % 2 == 0) && nums[i] > nums[i + 1])
                || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
            swap(nums, i, i + 1);
        }
    }
}




In the worst case we swap at most n/2 times. 
An example input is [2,1,3,1,4,1].



