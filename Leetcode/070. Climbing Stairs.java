70. Climbing Stairs

It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

stair 0 1 2 3 4 
ways  0 1 2 3 5

1. dp 解法

base case : 第0级 0种way / 1级 1种/ 2级 2种/ 后面 递推 即可

dp[i] 到第i级台阶 有多少种方法

dp[i] = 0   i <= 0
        1   i == 1
        2   i == 2
        dp[i - 1] + dp[i - 2]   i > 2

class Solution {
    public int climbStairs(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}



2. 记忆化 

class Solution {
    int[] cache;                        //1.全局变量
    public int climbStairs(int n) {
        if (n <= 0)
            return 0;
        cache = new int[n + 1];
        for (int i = 0; i <= n; i++) {      // 2. 初始化
            cache[i] = -1;                  //      -1 表示还没有被算过
        }
        f(n);
        return cache[n];
    }

    void f(int i) {             //辅助函数, 因为主函数里 要给 cache 提供 n这个参数, 所以建数组在主函数里，这样子需要个副函数 来递归了
        if (cache[i] != -1)     //3.！！！！最最重要的 递归出口 通过这步避免重复计算
            return;
        if (i == 0 || i == 1) {     //也是停止条件
            cache[i] = 1;
            return;
        } 
        f(i - 1);
        f(i - 2);
        cache[i] = cache[i - 1] + cache[i - 2];
    } 
}

2.2 记忆化 另一种写法

class Solution {
    int[] cache;                         
    public int climbStairs(int n) {
        if (n <= 0)
            return 0;
        cache = new int[n + 1];
        return f(n);
    }

    int f(int i) {   
        if (i == 0 || i == 1) {
            return cache[i] = 1;
        } 
        if (cache[i] != 0)      
            return cache[i];
        return cache[i] = f(i - 1) + f(i - 2);
    } 
}



3. 最优解:

fibonacci 递推 省空间

base case 
    if n <= 0, then the number of ways should be zero.
    if n == 1, then there is only way to climb the stair.
    if n == 2, then there are two ways to climb the stairs. 
               One solution is one step by another; the other one is two steps at one time.


class Solution {                        
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int a = 1, b = 2;
        while (n-- > 2) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    } 
}

