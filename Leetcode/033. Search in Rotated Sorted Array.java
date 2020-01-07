33. Search in Rotated Sorted Array

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

O(logn)


nums = [4,5,6,7,0,1,2]


1.determine which part the Mid is in
	就是先判断A[mid]与A[left]的大小，要是A[mid]大，就保证在xxxxoooo的x里面，
    * 因为 left 到 mid 是单增
    * 而mid到right可能单减或先增后减
    所以 先比 nums[left] < nums[mid], 要是A[mid]大，就保证在xxxxoooo的x里面.


2.determine what range Target is in
	这时判断 target >= A[left] && target < A[mid],
	既保证了每次缩小范围时，右边界缩小，即每次把mid值赋给right；
    *否则，左边界缩小。




最基本的 二分 是比 nums[mid] 和 target 然后决定, 把 mid 赋值给 left / right 哪个


但是本题直接比 nums[mid] 和 target 并不能确定target是在哪一边


[2, 3, 4, 5, 0, 1]

一般情况下 num[mid] > target, 右边的不要了 right = mid。

比如 nums[mid] = 4 > target = 1 要是直接不要右边的 数组变为 ---> [2, 3, 4] 就丢了


因为我们不确定 target 是 在  xxxxxMidxxxooooo 中的哪里。 
如果 target 在 xxxx里 (xxTxxMidxxxooooo) 则需要保留左边的
如果 target 在 oooo里 (xxxxxMidxxxooToo) 则需要保留右边的

所以相当于多了情况---> 分4种了

如果mid在右边递增的序列里 : xxxxxooMidooooo

如果 target 在 oooo里 (xxxxxooMidooToo) 则需要保留右边的
如果 target 在 xxxx里 (xxTxxooMidooooo) 则需要保留左边的







nums[mid]直接等于target                        return mid

nums[mid]在左半边的递增区域 xxxxxx

        a. target 在 left 和 mid 之间          把 mid 赋值给 right  

        b. 不在之间                            把 mid 赋值给 left 

nums[mid]在右半边的递增区域 oooooo

        a. target 在 mid 和 right 之间         把 mid 赋值给 left 

        b. 不在之间                            把 mid 赋值给 right 




class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[mid] > nums[left]) {                               // mid 在 xxxx 里面    xxxxxMidxxxooooooo
                if (nums[mid] > target && target >= nums[left])         // xxxTxMidxxxooooooo   就 target 卡在 left 与 mid 之间  递增
                    right = mid;
                else                                                    // xxxxxMidxxxooToooo 或者 xxxxxMidxTxooooooo  
                    left = mid;                         
            } else {                                                    // mid 在 oooo 里面    xxxxxoooooMidoooo
                if (nums[mid] < target && target <= nums[right])        // xxxxxoooMidoooTo
                    left = mid;
                else                                                    // xxxxxoToMidooooo   或者 xxTxxoooMidooooo
                    right = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}








2019 11.23 再写。。。 写出了 两个笨b做法 我吐了。。。



class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            

            if (nums[mid] > nums[left]) {
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid;
                } else {
                    if (nums[left] == target) return left;          // 这里 可写可不写。。 等号带着也行。。。。
                    else if (nums[left] > target) left = mid; 
                    else right = mid;
                }
            } else {
                if (nums[mid] == target) {                      // 这里就是 代码的冗余
                    return mid;
                } else if (nums[mid] > target) {
                    right = mid;
                } else {
                    if (nums[left] == target) return left;  
                    else if (nums[left] > target) left = mid; 
                    else right = mid;
                }
            }
            
        }

        if (nums[left] == target)  return left;
        if (nums[right] == target) return right;
        
        return -1;
    }
}








class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) return mid;
            
            if (nums[mid] > nums[left]) {
                if (target > nums[mid]) {
                    left = mid;
                } else {
                    if (target >= nums[left]) right = mid;
                    else left = mid;                                    // 这里也是冗余 这里可以和上面合并
                }
            } else {
                if (target < nums[mid]) {
                    right = mid;
                } else {
                    if (target <= nums[right]) left = mid;
                    else right = mid;
                }
            }
        }

        if (nums[left] == target)  return left;
        if (nums[right] == target) return right;
        
        return -1;
    }
}


精简版本 



class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) return mid;

            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) right = mid;
                else left = mid;
            } else {
                if (target <= nums[right] && target > nums[mid]) left = mid;
                else right = mid;
            }
        }
        
        if (nums[left] == target)  return left;
        if (nums[right] == target) return right;
        
        return -1;
    }
}







2020.1.5 写的版本 有错误

千万不要 直接先比 nums[mid] > target 因为 我 mid 在那边 都不确定 直接先比 nums[mid] 与 target 毫无意义。。。
妈的 浪费一小时搞这东西 






2020.1.6 

+ - 1 版本的 binary search

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {   // 2,3,4,5,6,7,0,1      =等号是 这个 test case "[3,1] 1"
                if (nums[mid] > target && target >= nums[left]) right = mid - 1;
                else left = mid + 1;
            } else {                        //  6,7,0,1,2,3,4,5
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }
}


