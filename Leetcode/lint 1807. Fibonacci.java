1807. Fibonacci easy
Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of "i-1" th number and "i-2" th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...



Example 1:
    Input:  1
    Output: 0
    
    Explanation: 
    return the first number in  Fibonacci sequence .

Example 2:
    Input:  2
    Output: 1
    
    Explanation: 
    return the second number in  Fibonacci sequence .



--------------------------------------------------------------------------------------------------------------
dp 来算 fibonacci, 避免重复计算

Fibonacci的iteration(bottom-up)

class Solution {
    public int fibonacci(int n) {
        if (n <= 0) return -1;
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;

        if (n == 1) return 0;
        if (n == 2) return 1;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
O(n)
O(n)
--------------------------------------------------------------------------------------------------------------
recursion 

class Solution {
    public int fibonacci(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        if (n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

O(2^n)
O(n)

--------------------------------------------------------------------------------------------------------------

recursion优化 
top-down

class Solution {
    int[] cache = new int[31]; // assume n <= 30

    public int fibonacci(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (cache[n] != 0) {
            return cache[n];
        }
        return cache[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
}

O(n) O(n)

--------------------------------------------------------------------------------------------------------------

iterative 按定义加

class Solution {
    public int fibonacci(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        int a = 0, b = 1;
        while (n > 2) {
            int sum = a + b;
            a = b;
            b = sum;
            n--;
        }
        return b;
    }
}

O(n)
O(1)
