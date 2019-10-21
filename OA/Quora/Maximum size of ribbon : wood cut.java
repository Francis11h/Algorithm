7.  Maximum size of ribbon
                      带子

Given a list representing the length of ribbon, and the target number "k" parts of ribbon.
we want to cut ribbon into k parts with the same size, at the same time we want the maximum size.
Ex.
Input: A = [1, 2, 3, 4, 9], k = 5
Output: 3

Explanation:
if size = 1, then we have 19 parts
if seize = 2, then we have 8 parts
if size = 3, then we have 5 parts
if size = 4, then we have 3 parts, which is not enough.


Sol.
Use binary search to find the size of the ribbon to reach the time limit.



public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null) return 0;
        int left = 1, right = 0;
        
        for (int item : L) {
            right = Math.max(right, item);
        }
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (count(L, mid) >= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (count(L, right) >= k) return right;
        if (count(L, left) >= k) return left;
        return 0;
    }
    
    private int count(int[] L, int len) {
        int ans = 0;
        for (int item : L) {
            ans += item / len;
        }
        return ans;
    }
}