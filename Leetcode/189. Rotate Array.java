189. Rotate Array
rotate the array to the right by k steps, where k is non-negative.

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]




// use another array to stroe the result and then copy it to original array
// O(n)
// O(n)
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }
}



Using Cyclic Replacements [Accepted]


We can directly place every number of the array at its required correct position. 
But if we do that, we will destroy the original element.
Thus, we need to store the number being replaced in a Temp variable. 

Then, we can place the replaced number(temp) at its correct position and so on,n times, 


when we hit the original number‘s index again, we start the same process with the number following it.

当数组长度正好能被k整除时，一次while循环是不够的，因为有些位置无论如何也无法到达，比如下面这个例子：

[1,2,3,4,5,6], k = 2

这个循环一直在位置 0,2,4 这几个位置转移，而无法到达其他位置

所以想到的解决办法是 外层再套一个循环 记移动过的数字个数 要==nums.length 才可以结束
如果 cur == start 的话 就要从 start下一个开始
when we hit the original number‘s index again, we start the same process with the number following it.






自己写的 错误代码

只用 temp 存 要被替换的位置的值 但是temp会丢失 因为没有用 temp 赋值


public class RotateArray {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3 ,4 ,5, 6};
        rotate(A, 2);
    }
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int cur = start;
            int next = (cur + k) % nums.length;
            int temp = nums[next];
            nums[next] = nums[cur];
            cur = next;
            count++;
            while (cur != start) {
                next = (cur + k) % nums.length;     //next position
                temp = nums[next];
                nums[next] = nums[cur];
                cur = next;
                count++;
            }
        }
    }
}
[1,2,3,4,5,6], k = 2
结果会是这样子 因为3（temp）丢失了 因为 旧的temp还没赋值时就被新的覆盖
[1,2,1,4,1,6] 



用 prev 来保存 temp 因为 temp要记下一个放的位置


public class RotateArray {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3 ,4 ,5, 6};
        rotate(A, 2);
    }
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int cur = start;
            int prev = nums[start];
            int next = (cur + k) % nums.length;
            int temp = nums[next];
            nums[next] = prev;
            prev = temp;
            cur = next;
            count++;
            while (cur != start) {
                next = (cur + k) % nums.length;     //next position
                temp = nums[next];
                nums[next] = prev;
                prev = temp;
                cur = next;
                count++;
            }
        }
    }
}

do while 使代码更简洁

// do……while至少执行一次，这是它和while的区别。
// 用while替代do……while的方式：
// 在while循环前，将循环体内的代码复制一份到前面，强行执行一次




class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int cur = i;
            int prev = nums[i];
            do {
                int next = (cur + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                cur = next;
                count++;
            } while (i != cur);
        }
    }
}






// [1,2,3,4,5,6,7]  k = 3           
// 0 ----> 3
// 3 ----> 6
// 6 ----> 2
// 2 ----> 5
// 5 ----> 1
// 1 ----> 4
// 4 ----> 0
// [5,6,7,1,2,3,4]



[1,2,3,4,5,6], k = 2

[5,2,1,4,3,6]

[5,6,1,2,3,4]






最优的办法 借鉴 rotate list
分块儿

Example：nums = [1,2,3,4,5,6,7] k = 3
Step1:划分成[1,2,3,4], [5,6,7]
Step2:分别reverse，[4,3,2,1], [7,6,5]
Step3:合并reverse，[5,6,7,1,2,3,4]


public class Solution {
    
    public int[] rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
        return nums;
    }
    
    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }
}










