1218. Longest Arithmetic Subsequence of Given Difference

最长定差子序列


Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.



Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].
Example 2:

Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.
Example 3:

Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].

这道题思路比较简单，跟经典问题最长递增（减）子序列有点相似，而这道题称为最长等差子序列, 也就是说是固定公差的递增（减），相对还更简单一点。

可以用dp[i]来记录以数字i为结尾的最长等差子序列的长度，那么它应该只有两种情况：

dp[i] = 1 // 表示在 i 之前没有出现等差子序列
dp[i] = dp[i - difference] + 1 // 表示在 i 之前出现了等差子序列，长度为 dp[i - difference], 而 i 也是满足这个等差序列的，所以等差序列的长度在此基础上加 1 就可以了
考虑元素值会出现负数，所以用数组存放是不行的，那么可以用一个 map来维护以 i 结尾的最长等差序列的长度，所以也就不难得出如下代码：

PS: 经评论提醒，可以为下标加一个偏置，解决出现负值的情况，这是很OK，因为这道题arr[i]、difference的数据范围已经给的很明确了，而且比较小。







class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 1;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num - difference, 0) + 1);
            ans = Math.max(ans, map.get(num));
        }
        return ans;
    }
}