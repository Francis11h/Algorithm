287. 找重复的数字 Find the Duplicate Number

Input: [1,3,4,2,2]
Output: 2

Input: [3,1,3,4,2]
Output: 3

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.


要求 原数组不能改
    不能用额外的空间
    只有一个元素是重复的


不符合要求的算法:

朴素的思想, 排序Sorting
解法一:

class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return -1;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }
}

T:O(nlogn)
S:O(1)
但是 改变了原数组, 要是不改变原数组, S则会是 O(n).



朴素的思想, 用Set去重
解法二 :

class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return num;
            set.add(num);
        }
        return -1;
    }
}

T: O(n)
S: O(n)


解法三
binary search


public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;
    while (low <= high) {
        int mid = (int) (low + (high - low) * 0.5);
        int cnt = 0;
        for (int a : nums) {
            if (a <= mid) ++cnt;
        }
        if (cnt <= mid) low = mid + 1;
        else high = mid - 1;
    }
    return low;
}



符合题目要求的解法

快慢指针

假设数组中没有重复，那我们可以做到这么一点，
就是将数组的下标和1到n每一个数一对一的映射起来。
比如数组是213,则映射关系为 0->2, 1->1, 2->3


假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。
如果我们从下标为0出发，根据这个函数计算出一个值，以这个值为新的下标，再用这个函数计算，以此类推，直到下标超界。
实际上可以产生一个类似链表一样的序列。比如在这个例子中有两个下标的序列，0->2->3。


但如果有重复的话，这中间就会产生多对一的映射，比如数组 [2131] ,则映射关系为 0->2, {1，3}->1, 2->3。
这样，我们推演的序列就一定会有环路了，这里下标的序列是 0->2->3->1->1->1->1->...，而环的起点就是重复的数。

所以该题实际上就是找环路起点的题。
过程是这样的：我们先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次，直到两个下标再次相同。
这时候 保持慢下标位置不变，再用一个新的下标从0开始，这两个下标都继续每轮映射一次，当这两个下标相遇时，就是环的起点，也就是重复的数。



      0  1  2  3  4
     [1, 3, 4, 2, 2]            1->3->2->4->2->4->2->4
1.    S
         F

2.       S
            F

3.              S     
                    
            F

4.          S
            F
-------------------------->  set F = nums[0]   
5.          S
     F

6.              S
        F
7.          S
                F


class Solution {
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}





