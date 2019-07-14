007. Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

Input: -123
Output: -321

Input: 120
Output: 21

Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. 
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.



class Solution {
    public int reverse(int num) {
        int ans = 0;
        while (num != 0) {              //因为正负均可 所以不能写 num > 0  要写 num != 0
            int digit = num % 10;       // Java -1 % 10 = -1
            num /= 10;
            
            if (ans < Integer.MIN_VALUE / 10) return 0;   //
            if (ans > Integer.MAX_VALUE / 10) return 0;     //if (num < Integer.MIN_VALUE / 10) return 0; 这么写不行 test case : 1534236469 要反转  反转之后的数不能越界 所以要用ans判断 ： 乘10  不能越界
            
            ans = ans * 10 + digit;
        }
        return ans;
    }
}



O(logn)
O(1)