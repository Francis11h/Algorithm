15. 3Sum

Given an array nums of n integers, 
are there elements a, b, c in nums 
such that a + b + c = 0? 
Find all Unique triplets in the array which gives the sum of zero.


重中之重 结果集合不能包含重复的元素

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]


		[-4, -1, -1, 0, 1, 2]
		 i1
		     i2
		         l         r
		      		  l  r
		      		 i3  
		      		     i4 
		      		     	i5	 

想着用HashMap，先确定第一个数，然后后面两数加起来等于nums[i]，但这样会出现重复，无法去除类似于[0, 0, 0, 0]这种情况

改进 用 two pointers, 确定第一个数后,要判断是否与之前确定的数一样, 这就要求整个数组是有序的 所以要先排序.
					 该数确定之后, 也是不走回头路的, 该数 下一个 设为 left, 数组最大值 设为 right
					 如果 nums[left] + nums[right] < target 我们 扩大一点 往右边动一下 即 left++  再试 
					 如果                          > 			缩小一点 往左边动一下  right--	
					 							  == 		加入ans 
					 							  怎么建List<List<>> ? new ArrayList<>(Arrays.asList(几个数));
					    找到相等 两个同时动下 left++ right--
					 	再用两个指针可排除后面出现的重复情况, 出现第一次等于0后,两个指针l,r 都要一直跳到不重复的数为止.

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
        	if (i > 0 && nums[i] == nums[i - 1]) continue;			//这就处理了这种 corner case [0, 0, 0, 0]  
        	int left = i + 1, right = nums.length - 1, target = 0 - nums[i];
        	while (left < right) {
        		
        		if (nums[left] + nums[right] == target) {
					ans.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));        			//这里面也可能会有重复 比如 [0, 0, 0, 0, 0] 第一次 l,r match 了 后面l or r 必须得有一个不一样 否则会重复
        			// [0, 1, 1, -1, -1]这种一样的 corner case 所以左右都要走到和之前不一样的数字
        			while (left < right && nums[left] == nums[left + 1]) left++;
        			while (left < right && nums[right] == nums[right - 1]) right--;
        			//正常的match都该向里面缩 这是必须的 上面的两个while是去重
        			left++;
        			right--;
        		} else if (nums[left] + nums[right] < target) {
        			left++;
        		} else {
        			right--;
        		}
        	}
        }
        return ans;
    }
}



// [-1, 0, 1, 2, -1, -4]   target = 0
 
// no duplicates;
// [
//     [-1, 0, 1],
//     [-1, -1, 2]
// ]

// first, => Arrays.sort();    [-4, -1, -1, 0, 1, 2]

// choose one element as the new target,
// which means that a = b + c, and just iterate a  and bc

// //find a
// for (0 -> nums.length - 2) third last ele
// if (i > 0 && nums[i] == nums[i - 1]) continue;

// int l = i + 1, r = nums.length - 1;
// //find apporiate b and c
// while (l < r) {
//     if ( == ) {
//         ans.add();
//         l++;
//         r--;
//         while( nums[l] == nums[l - 1]) l++;
//         while (nums[r] == nums[r + 1]) r--;
//     } else if ( < ) {
//         l++;
//     } else {
//         r--;
//     }
// }

// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         List<List<Integer>> ans = new ArrayList<>();
//         Arrays.sort(nums);
//         for (int i = 0; i < nums.length - 2; i++) {
//             if (i > 0 && nums[i] == nums[i - 1])
//                 continue;
//             int target = -nums[i];
//             int left = i + 1, right = nums.length - 1;

//             while (left < right) {
//                 if (nums[left] + nums[right] == target) {
//                     ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                     left++;
//                     right--;
//                     while (left < right && nums[left] == nums[left - 1]) left++;
//                     while (left < right && nums[right] == nums[right + 1]) right--;
//                 }
//                 if (nums[left] + nums[right] < target)
//                     left++;
//                 if (nums[left] + nums[right] > target)
//                     right--;
//             }
//         }
//         return ans;
//     }
// }


























