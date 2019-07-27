29. Divide Two Integers

要求不使用乘法、除法和 mod 运算符。
如果溢出(超出32位有符号整型表示范围)，返回 2147483647 
整数除法应截断为零。

Both dividend and divisor will be 32-bit signed integers.



基本思路是利用减法, 看看被除数可以减去多少次除数.

使用倍增的思想优化, 可以将减法的次数优化到对数时间复杂度.

我们将除数左移一位(或者让它加上自己), 即得到了二倍的除数, 这时一次减法相当于减去了2个除数. 不断倍增, 时间效率很优秀.

与此同时还需要一个 变量shift 记录此时的除数是最初的除数的多少倍, 每次减法后都加到 结果result 上即可.




Note :
首先是 两个整数 Integer 相除 就不可能出现除出来的数在 比原来的数大 (正负号的不考虑在此范围内)
而且 题目又明确规定 是两个 int 所以范围肯定在 [-2147483648, 2147483647] 之间

那么 唯一可能越界的情况就是 -2147483648 / -1 就这一种情况


方法一 先转换为long 然后都变成正数

class Solution {
    public int divide(int dividend, int divisor) {
        //1. 除数为0
        if (divisor == 0) {
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        //2. 被除数为0
        if (dividend == 0) {
            return 0;
        }
        //3. 越界 -2147483648 / -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        //由于 我们不想正负情况 一起判断 所以 就是 先 都把这两个数 变成正数 再 做减法 
        //但是 要记录下 最终的结果是不是 正数
        boolean isNegative = (dividend < 0 && divisor > 0) ||
                (dividend > 0 && divisor < 0);

        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        //至此 我们已经 处理了所有的 边界情况 并且 得到了两个正数
        //所以 在此就可以使用 倍增法
        // 1. 最外层 while 表示 只要 被除数 大于等于 除数 这个就还得 继续运算 因为 至少 结果会 + 1
        // 2. 每次 初始化 一个 shift 然后 再用个while 移动shift 直到 被除数 < 除数
        // 3. 被除数 减去 不大于被除数的 移位 shift - 1位的 除数
        // 4. 结果 加上 1 << (shift - 1) 再次进入第一步
        int result = 0;
        while (a >= b) {
            int shift = 0;
            // 这里 a 最大是 2147483648 但是 由于是 long 我们的 b << shift 可以超过 所以没关系
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative? -result: result;
    }
}

T: O(logN) :  最坏的情况 如果 除数divisor 是 1, 如果一次一次的减 除数 那么 要减去 N 次
            但是 我们每次都翻倍了 所以总共减了 log(N) 次
S: O(1)




优化 我们上面 用到了 long 我们就在想 什么时候 可以不用long 用long完完全全 是因为-2147483648 
因为负数比正数多一个所以无法使用 int 因为我们之前是 记录了 isNegative 然后 把所有负数转化为正数
但是 我们现在 反过来  把所有 正数 都变为 负数 然后 之前的 减法变加法 加法变减法 是不是 就解决了问题？

其实 有很多坑 就是 最主要是是 找 离 a 最近的 b 是通过 b的倍增 就会有 越界的问题 所以要限制死 两个边界 即 b << shift 必须是负数 同时 shift 不能到31 


方法二 不用转换成long 然后 用负数
class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isNegative = (dividend < 0 && divisor > 0) ||
                (dividend > 0 && divisor < 0);

        int a = dividend < 0 ? dividend : - dividend;
        int b = divisor < 0 ? divisor : - divisor;

        //至此 我们 得到了两个负数
        //我们仍然 使用 倍增法
        // 1. 最外层 while 表示 只要 被除数 小于等于 除数 这个就还得 继续运算 因为 至少 结果会 - 1
        // 2. 每次 初始化 一个 shift 然后 再用个while 移动shift 直到 被除数 > 除数
                这里面 由于不使用 long 可能会导致越界 所以要 加上两个条件
                1. (b << shift) 可能会越界  a = -2147483647, b = -3, 
                                        -3<<29 = -1610612736 -3<<30 = 1073741824 这里 就相当于是越过了 0 这个界限 
                                        导致 后面shift 会多算一位 所以 还是要 加上 (b << shift < 0) 的这个判断条件

                2. shift 最多只可能移位 31 因为 a = -2147483648, b = -1  
                                这种情况 如果b移动31 位, 但是 该情况下 (-1<<31 = a), (-1<<31 = -1) 就会永远循环了, 
                                所以要限制 shift < 31 

                    同时 a <= (b << shift) 的等号不能少 要不然 a = -1, b = -1, shift = 0, 
                                    b << (shift - 1) ----> -1 << -1 = -2147483648 就乱了
                    其实就是 限制了 (shift - 1) 的范围 在 [0, 30] 因为 1 << 31 也会越界

        // 3. 被除数 减去 不大于被除数的 移位 shift - 1位的 除数
        // 4. 结果 减去 1 << (shift - 1) 再次进入第一步
        int result = 0;
        while (a <= b) {
            int shift = 0;
            // 如果 a = -2147483648 的时候 就永远 是最小的 因为它是 int的最小值 这就越界了
            // 所以 
            while (a <= (b << shift) && (b << shift) < 0 && shift < 31) {
                shift++;
            }
            a -= b << (shift - 1);
            result -= 1 << (shift - 1);
        }
        return isNegative? result: -result;
    }
}



总结下？？ 整数的范围 判断 很多坑 必须要自己踩下。。。






