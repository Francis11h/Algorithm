128. Longest Consecutive Sequence
最长连续元素序列

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.


Solution 1: set 

全部都放进 hashSet 再从里面删除 应用的是   set.remove() 返回的是 boolean, 这个布尔太关键了, 就每次可以判断+/-1 的元素在不在set中

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int maxLength = 0;
        for (int num : nums) {
            int len = 1;
            if (set.contains(num)) {
                set.remove(num);
                int temp = num - 1;
                while (set.remove(temp)) {
                    len++;
                    temp--;
                }
                temp = num + 1;
                while (set.remove(temp)) {
                    len++;
                    temp++;
                }
            }
            maxLength = Math.max(maxLength, len);
        }
        return maxLength;
    }
}

O(n)
"set.remove() 返回的是 boolean"



Solution 2: 更朴素的想法 排序即可

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;      
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {     // remove depuliacte
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}


O(nlogn)



