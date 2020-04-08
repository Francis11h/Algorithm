268. 缺失数字
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。


输入: [3,0,1]
输出: 2

输入: [9,6,4,2,3,5,7,0,1]
输出: 8




将数组中的所有数插入到一个集合中，这样每次查询操作的时间复杂度都是 O(1) 的

class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int n = nums.length + 1;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}
O(n) O(n)




位运算
由于异或运算（XOR）满足结合律，并且对一个数进行两次完全相同的异或运算会得到原来的数，因此我们可以通过异或运算找到缺失的数字。

因此我们可以先得到 [0..n]的异或值，再将结果对数组中的每一个数进行一次异或运算。
未缺失的数在 [0..n]和 数组中 "各出现一次 =  两次" ，因此异或后得到 0。
而缺失的数字只在 [0..n]中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。


class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i <= n; i++) {
        	ans ^= i;
        }
        
        for (int i = 0; i < n; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}

 

我们可以用 高斯求和公式 求出 [0..n] 的和，减去数组中所有数的和，就得到了缺失的数字。
O(n) O(1)
