845. Length of Longest Mountain in Array

最长的山的长度

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.



            7
2       4       3       5
    1               2






class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) return 0;
        int ans = 0;
        // 至少有3个 我们 先找 peak 再延展 stretch
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                int left = i - 1;
                while (left > 0 && A[left] > A[left - 1]) {
                    left--;
                }
                
                int right = i + 1;
                while (right < A.length - 1 && A[right] > A[right + 1]) {
                    right++;
                }
                
                ans = Math.max(ans, right - left + 1);
            }
        }
        return ans;
    }
}

遍历两遍数组

T : O(N + N)   
S : O(1)