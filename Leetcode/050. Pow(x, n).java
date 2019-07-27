50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (x ^ n).

Input: 2.00000, 10
Output: 1024.00000

Input: 2.10000, 3
Output: 9.26100

Input: 2.00000, -2
Output: 0.25000

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]






注意 算出来的数可能很大 要用 long
Java double 类型也是 8 字节



Solution 1 : just simulate the process, multiply x for n times;

官方版本
if n < 0, we replace x, n with 1/x, -n to make sure n >= 0;

class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        Long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++) {
            ans *= x; 
        }
        return ans;
    }
}

T:O(n) (we multiply x for n times)
S:O(1)



题目给的hint 是 Binary Search


Assume 我们有 x ^ n 要求 x ^ 2n; 很明显我们不用再次 对x成n次, 仅需 (x ^ n) ^ 2;
所以这就是 二分思想的一种表现
但是 这个会和 n 的奇偶性 有关系, 因为我们现在只知道n 相当于 从 n 找 n / 2, n / 4


假如我们现在知道了 a^5，那么要求 a^10，只需要用 a^5 * a^5 即可。

那么如何求 a^5 呢？这个和求 a^10 是一个道理。

不断的套用上面的套路，递归，直至求到 a^1 或者是 a^0 为止。

通过递归,不断地把这个问题分解为一半儿, T 就会降低到 O(log N)   N >= 0


但是 需要注意的是 parity 奇偶性
a^5 = a^2 * a^2 *a 因此如果指数n是奇数odd的话，那么 a^n = a^(n/2) * a^(n/2) * a 。




Solution 2 : Fast Power Algorithm Recursive

我自己的清晰版本
class Solution {
    public double myPow(double x, int n) {
        double ans = pow(x, Math.abs(n));       //n还有可能是负数
        return n < 0 ? 1 / ans : ans;
    }

    public double pow(double x, int n) {
        //递归出口
        if (n == 0) return 1;
        if (n == 1) return x;
        //下一层 分治
        double temp = pow(x, n / 2);
        //merge 合并
        temp *= temp;
        //判 是不是 odd (每轮合并都要判断)
        if (n % 2 != 0) temp *= x;          //这里改成 (n % 2 == 1) 的话, test case (2.00000, -2147483648)过不了
                                            //因为 这个 Math.abs(-2147483648) = -2147483648, 本来是 偶数,  但是 %2 = -1
                                            //因为 -1 % 2 = -1. 所以逻辑错误了 要是改成(n % 2 == 1 || n % 2 == -1) 即可。 或者单独判断下-2147483648 这个越界的数

        return temp;
    }
}

T:O(logn)
S:O(logn)    每一次计算，我们需要存储 上一次的结果。 我们需要计算 O(logn) 次，所以空间复杂度为 O(logn) 。




Approach 3: Fast Power Algorithm Iterative

我们现在要求 a^n。 那么如果n是偶数，那么 a^n = a^(n/2) * a^(n/2) 。 如果n是奇数，那么 a^n = a^(n/2) * a^(n/2) * a 。
接下来，如果 n/2 是偶数，那么 a^(n/2) = a^(n/4) * a^(n/4) 。 如果 n/2 是奇数，那么 a^(n/2) = a^(n/4) * a^(n/4) * a^2 。



相当于 只有在 absN 是 奇数的时候才乘 

class Solution {
    public double myPow(double x, int n) {
        
        double ans = 1;
        long absN = Math.abs((long)n);
        while(absN > 0)
        {
            if((absN & 1) == 1)         // absN is odd
                ans *= x;
            absN >>= 1;                 // absN 除以2
            x *= x;
        }
        return n < 0 ? 1 / ans : ans;
    }
}


相当于 只有在 absN 是 奇数的时候才乘 这句话 就是 整道题的关键  就是 看一个 数 二分几次能到 1, 因为任何一个数 不断二分结果最终归为1
其实 本来是 想相当于 只记录在1 的时候 x是原本x的多少倍就可以 但是 由于我们做除法的时候 会舍去 (5/2 = 2 ...1)  那么 这个1 就被省掉了
这使得我们倒推(因为 我们的计算顺序是 从 x开始 倍增多少次 每次乘自己) 的时候得不到想要的结果 所以 每次 absN为奇数的时候 要在乘一下 倒推时到当前位置 x的值 这样保证不会丢掉那个余数对应的值
  



myPow(2, 2)     absN = 2  --->  1
                  x  = 2  --->  4                                       4

myPow(2, 3)     absN = 3  --->  1
                  x  = 2  --->  2^2 = 4                         2 * 4 = 8


mypow(2, 4)     absN = 4  --->  2        --->  1
                  x  = 2  --->  2^2 = 4  --->  4^2=16                   16


mypow(2, 5)     absN = 5  --->  2        --->  1
                  x  = 2  --->  2^2 = 4  --->  4^2=16          2 * 16 = 32


mypow(2, 10)     absN =10 --->  5  --->  2  --->  1
                  x  = 2  --->  4  --->  16 --->  256       4 * 256 = 1024


迭代的官方版本
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
};






中国
https://leetcode.wang/leetCode-50-Pow.html





