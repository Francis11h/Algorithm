509. Fibonacci Number

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.

Given N, calculate F(N).

dp 
记忆化搜索
的最基本认识

//dp down-to-top
class Solution {
    public int fib(int n) {
        //加这个 是为了防止 若 n=0, new int[1];  这个数组只有一个元素
        //那么此时dp[1] 就会越界
        if (n == 0) {   
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
Time complexity: O(n)
Space complexity: O(n)

//记忆化搜索  top-down
class Solution {
    //全局变量，存结果
    int[] cache = new int[31];  // n <= 30 

    public int fib(int n) {
        if (n <= 1)
            return n;
        if (cache[n] != 0)
            return cache[n];
        return cache[n] = fib(n - 1) + fib(n - 2); 
        //是下面式子的简写
        // int val = fib(n - 1) + fib(n - 2);
        // cache[n] = val;
        // return val;
    }
}

Time complexity: O(n)
Space complexity: O(n)

//recursive
class Solution 
{
    public int fib(int N)
    {
        if(N <= 1)
            return N;
        else
            return fib(N - 1) + fib(N - 2);
    }
}

Time complexity: O(2 ^ n)	//每个问题要先算他的前两个, 满二叉树
Space complexity: O(n)


// iterative
class Solution {
	public int fib(int n) {
		if (n <= 1)
			return n;
		int a = 0, b = 1;

		while (n-- > 1) {
			int sum = a + b;
			a = b;
			b = sum;
		}
		return b;
	}
}


Time complexity: O(n)
Space complexity: O(1)
