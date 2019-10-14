60. Permutation Sequence

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Example 1:

Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"




基本是 数学题。。。

康拓展开, 计算排列与字典序排名的映射关系.


class Solution {
    public String getPermutation(int n, int k) {
        
        boolean[] visited = new boolean[n];
        // 求 总共有多少种可能 n! 种排列
        int factorial = 1;
        int x = 1;
        while (x <= n) {
            factorial *= x;
            x++;
        }
        
        // 求 k是 第几个 从 1-n 如果能整除就是第n个 不能就是余数
        if (k % factorial == 0){
            k = factorial;
        } else {
            k %= factorial;
        }
        
        StringBuilder ans = new StringBuilder();
        //每一位计算 该位的数字 共n位
        while (n > 0) {
        	// 少一位的 排列的个数 即 最高位一位 对应多少种排列 即 n! / n 每次除以n即可
            factorial /= n;
            // 需要的最高位 的个数 有余数的话要加一位
            int now = 0;
            if (k % factorial != 0) {
                now = k / factorial + 1;
            } else {
                now = k / factorial;
            }

            int count = 0;
            int digit = 0;
            //每次便利我的 visited数组 从中找出第几个没用过的数字
            for (int i = 0; i < x - 1; i++) {
                if (!visited[i]) count++;
                if (count == now) {		//当找够了 位数
                    visited[i] = true;
                    digit = i + 1;
                    break;
                }
            }
            ans.append(digit);
            k -= (now - 1) * factorial;		// now - 1 是因为 之前加了 这里减去	
            n--;
        }
        
        return ans.toString();
    }
}


O