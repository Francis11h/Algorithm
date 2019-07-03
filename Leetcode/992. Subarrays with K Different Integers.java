992. Subarrays with K Different Integers

Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good 
if the number of different integers in that subarray is exactly K.

子数组要连续, 并且恰好有K个不同的字符, 但是每个字符可以多次出现,同时子数组也可以重复

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: 
                [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].



之前做过的题（159） 字符只能有两种 ： 也是 外层while动右指针, 再加个变量计现在有几个字符, 2个及以上字符就动左指针。
    
                                具体如何计数： 数组中某个值由 0--->1, dictCount++; 由 1--->0, dictCount--.



本题 :  也是 基本思路一样 就是用HashMap记录下每个数字出现的次数

但是本题要求 正好是两个 + 要所有组合的排列 所以和159不太一样 要变一下








TLE 的解法 :

The Idea is to iterate the right pointer until the size of the map is equal to K  
               and then clear the map and move the left pointer and set right pointer to the left pointer.

就是 先动右指针 直到map的size正好等于K, 这会儿继续动右指针 每动一下加一次 直到头,

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if (A == null || A.length == 0 || K <= 0) return 0;
        int ans = 0, left = 0, right = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right <  A.length) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);

            if (map.size() > K) {
                map.clear();
                left++;
                right = left;
                continue;
            }

            if (map.size() == K) {
                ans++;
                right++;
                if (right == A.length) {
                    map.clear();
                    left++;
                    right = left;
                }
                continue;
            }
            right++;
        }
        return ans;
    }
}

T:O(n^2)
S:O(K)


TLE 解法2 用set 稍微优化
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        int N = A.length;
        if (N == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        int start = 0, end = 0;
        int res = 0;
        while (end < N) {
            //add next element if it's not already in the set
            int endEl = A[end];
            if (!set.contains(endEl))
                set.add(endEl);
            
            int size = set.size();
            //if we over the K - need to reset set and increase start pointer
            if (size > K) {
                set.clear();
                start++;
                end = start;
            } else if (size == K) {
                //if we reach K unique elements - increment res by 1 
                res++;
                end++;
                if (end == N) {
                    set.clear();
                    start++;
                    end = start;
                }
            } else
                //default - increment end pointer
                end++;
        }
        return res;
    }
}





正解

但是本题要求 正好是两个 + 要所有组合的排列 所以和159不太一样 要变一下
正好是K个, 之前是至多是K个, 那我们就是 用 至多是 K 个 - 至多是 K-1 个 不就是正好恰好是K个么！！！



class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return  subarraysWithAtMostKDistinct(A, K) - subarraysWithAtMostKDistinct(A, K - 1);
    }

    public int subarraysWithAtMostKDistinct(int[] A, int K) {
        if (A == null || A.length == 0 || K <= 0) return 0;
        int ans = 0, left = 0, right = 0, distCount = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (right <  A.length) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            if (map.get(A[right]) == 1) distCount++;

            while (distCount > K) {
                map.put(A[left], map.get(A[left]) - 1);
                if (map.get(A[left]) == 0) distCount--;
                left++;
            }
            ans += right - left + 1;
            right++;
        }
        return ans;
    }
}


ans += right - left + 1;
这里的解释

lets say current sub-array=[1,2,3]
when a element is added lets say 4
added sub-arrays are:
4
3,4
2,3,4
1,2,3,4
4 <- one is added because of this new one length sub-array

T:O(N)


