136. 一个数组中只出现一次的那个数字 Single Number
找到一个数组中只出现一次的那个数字（其他都出现两次），如果有两个出现一次的数字呢

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4


solution HashMap


class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if(map.get(key) == 1) {
                return key;
            }
        }  
        return 0;
    }
}


O(n) O(n)
--------------------------------------------------------------------------------

亦或 XOR 运算
如果两个相应位 相同，则结果为0，不同为1


0^0=0,0^1=1 0异或任何数＝任何数

1^0=1,1^1=0 1异或任何数－任何数取反

10100001^
00010001=
10110000




If we take XOR of zero and some bit, it will return that bit
a⊕0=a
If we take XOR of two same bits, it will return 0
a⊕a=0

----->	"a⊕b⊕a=(a⊕a)⊕b=0⊕b=b"

So we can XOR all bits together to find the unique number.



java中的 异或运算符:   ^



class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        } 
        return a;
    }
}

O(n) O(1)





