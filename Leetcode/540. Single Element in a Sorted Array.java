540. Single Element in a Sorted Array

其他元素 出现两次 找只出现一次的元素

Input: [1,1,2,3,3,4,4,8,8]
Output: 2




Your solution should run in O(log n) time and O(1) space.


限制了 时间空间复杂度 

时间 ----> 二分 binary search ----> 怎么用 二分？ 
空间 ---> 不可用 map

sorted -----> 如果 一个数和它某一边相等 然后 就可以 除去含这两个数的 且总数 是偶数的那一边 
             除去的内容在下边几个case中有说明


先把 nums[mid] 和两边比 
如果 和两边都不一样  nums[mid] 就是答案
如果 和左边一样
    case 1
    mid 为 odd   就代表 0->mid 内 含有偶数个值 然后 nums[mid]==nums[mid-1] 所以 这些值是两两配对儿的 所以答案在右边 
                  所以移动边界为 left = mid + 1;
    case 2
    mid 为 even  就代表 mid-1->nums.length-1 内 含有偶数个值 然后 nums[mid]==nums[mid-1] 所以 这些值是两两配对儿的 所以答案在左边 
                  所以移动边界为 right = mid - 2;

如果 和右边一样
    case 3
    mid 为 odd   就代表 mid->nums.length-1 内 含有偶数个值 然后 nums[mid]==nums[mid+1] 所以 这些值是两两配对儿的 所以答案在左边 
                  所以移动边界为 right = mid - 1;
    case 2
    mid 为 even  就代表 0->mid+1 内 含有偶数个值 然后 nums[mid]==nums[mid+1] 所以这些值是两两配对儿的 所以答案在右边
                  所以移动边界为 left = mid + 2;



[1, 1, 2, 2, 3, 3, 4]    case 1                 除去[1, 1, 2, 2]
mid = 3 odd
            [3, 3, 4]

[1,1,2,3,3,4,4,8,8]      case 2                 除去[3,3,4,4,8,8]
mid = 4
[1,1,2]

[1, 1, 2, 3, 3, 4, 4]    case 3                 除去[3, 3, 4, 4]
mid = 3
[1, 1, 2]  

[1,1,2,2,3,3,4,4,8]      case 4                 除去[1,1,2,2,3,3]
mid = 4  even        
            [4,4,8]      


class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left < right) {                  //这里不能有等号 否则会越界限 left == right时 区间里就只有一个值了 即为结果
            int mid = left + (right - left) / 2;
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) 
                return nums[mid];
            else if (nums[mid] == nums[mid - 1]) {
                if (mid % 2 == 1) {
                    left = mid + 1;                 //case 1
                } else {
                    right = mid - 2;                //case 2
                }
            } else {
                if (mid % 2 == 1) {
                    right = mid - 1;                //case 3
                } else {
                    left = mid + 2;                 //case 4
                }
            }       
        }
        return nums[left];
    }
}








