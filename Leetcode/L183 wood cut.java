L183 wood cut


1.枚举 切出来的木头的长度即最终答案
2.计算 一共能切出来多少块儿木头 
    while 
        >= k left = mid,
        else right = mid,
3.算出来的值有序吗？ 假设长度114能切成7段，那么长度113一定能切出>=7段
 


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