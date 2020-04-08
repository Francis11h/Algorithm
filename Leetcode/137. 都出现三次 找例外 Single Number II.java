
137. 都出现三次 找例外 Single Number II

除了一个数之外 都出现三次 找到这个 数

Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99




还是 位运算

位运算符：NOT( ~ ), AND( & ) 和 XOR( ^ )

XOR ^

	该运算符用于检测出现"奇数次"的位：1、3、5 等。

	0 与任何数 XOR 结果为该数。
	0 ^ x=x

	两个相同的数 XOR 结果为 0。
	x ^ x=0

	以此类推，只有某个位置的数字出现奇数次时，该位的掩码才不为 0。


AND 和 NOT

	为了区分出现一次的数字和出现三次的数字，使用两个位掩码：seen_once 和 seen_twice。

思路是：

	仅当 seen_twice 未变时，改变 seen_once。

	仅当 seen_once 未变时，改变seen_twice。

位掩码 seen_once 仅保留出现一次的数字，不保留出现三次的数字。


举个例子 
		x = 2 				000010


1st		






只有一个数 出现一次 其余数字出现多次 的 通解


本题 n = 3
统计所有数字中每个位中1出现的总数，那么对于某个位，1出现的次数一定是3的倍数+1或0，那么对这个数%3得到的结果就是目的数字在该位上的值

class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
            }
            ans ^= (sum % 3) << i;
        }
        return ans;
    }
}
