528. Random Pick with Weight

Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.


就是 数组中的数字代表权重 按权重输出 下标

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]




就求和 然后做 算是 brute force 但也是 O(n)

class Solution {
    int sum = 0;
    int[] ww;
    public Solution(int[] w) {
        ww = w;
        for (int num : w) {
            sum += num;
        }
    }
    
    public int pickIndex() {
        Random rand = new Random();
        int ran = rand.nextInt(sum) + 1;    // 【1. sum】
        int temp = 0;
        for (int i = 0; i < ww.length; i++) {
            temp += ww[i];
            if (ran <= temp) return i;
        }
        return 0;
    }
}




后面会有 binary search 的解法


Use accumulated freq array to get idx.
w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
then get random val random.nextInt(14)+1, idx is in range [1,14]

idx in [1,2] return 0
idx in [3,7] return 1
idx in [8,10] return 2
idx in [11,14] return 3

then become LeetCode 35. Search Insert Position
Time: O(n) to init, O(logn) for one pick
Space: O(n)

class Solution {

    Random random;
    int[] wSums;
    
    public Solution(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }
    
    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position 
        while(left < right){
            int mid = left + (right-left)/2;
            if(wSums[mid] == idx)
                return mid;
            else if(wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}