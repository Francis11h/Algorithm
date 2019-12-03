123. Best Time to Buy and Sell Stock III

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.


Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.





前面两道题 greedy可以做, 到了 现在 只能DP了
 并且后面 只能买一卖一的 带冷却的 都可以用dp来做


Goal: find the max profit at day "n-1" with "at most k" transactions.

Here we have two keys: day and transaction.




dp[k][i] = maxProfit for at most k transactions on i-th day.

dp[k][i] = Math.max(dp[k][i - 1], prices[i] - prices[j] + dp[k - 1][j - 1],j=[0...i-1])

	1. if we dont trade, then the profit is same as previous day dp[k][i-1]

	2. if we bought stock j-th day, where j=[0...i-1], then sell the stock on i-th day,
		then the profit is prices[i] - prices[j] + dp[k - 1][j - 1].



解法1 完全按照 dp转移方程翻译

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int trans = 2;
        int[][] dp = new int[trans + 1][prices.length];

        for (int k = 1; k <= trans; k++) {
        	for (int i = 1; i < prices.length; i++) {
        		int min = prices[0];
        		for (int j = 1; j <= i; j++) {						//所有i之前的天 都可能买, 计算最小的买入价格, 同时减去之前 白嫖赚的
        			min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
        		}
        		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
        	}
        }
        return dp[trans][prices.length - 1];
    }
}

T:O(kn^2) 
S:O(kn)



优化 计算 min 的时候 一直在重复计算, 因为一直从 0--i, 
In the above code, min is repeated calculated. It can be easily improved as:

"Solution 2:"

Idea is simple : Keep track of the minimum value till previous day and 
check how much maximum profit can be obtained for current day.

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int trans = 2;
        int[][] dp = new int[trans + 1][prices.length];

        for (int k = 1; k <= trans; k++) {
        	int min = prices[0];
        	for (int i = 1; i < prices.length; i++) {
        		// 每次 新来一个 price 都计算出最买入价 - 之前几个trans 白嫖的利润
        		min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
        		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
        	}
        }
        return dp[trans][prices.length - 1];
    }
}

T:O(kn) 
S:O(kn)





[1,4,2,7,6,8]

prices		1	4	2	7	6	8
	day		0	1	2	3	4	5
k = 1		0	3	3	6	6	7
k = 2		0	3	3	8	8	9






















//Version 3
//swap the two 'for' loops,save min for each transaction.
class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n==0) return 0;
        int[][] dp=new int[3][n];
        int[] min=new int[3];
        Arrays.fill(min,prices[0]);
        for (int i=1;i<n;i++){
          for (int k=1;k<=2;k++){
                min[k]= Math.min(min[k], prices[i] - dp[k-1][i-1]);
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min[k]);
            }
        }
        return dp[2][n-1];
    }
}

//Version 4: compact i
class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n==0) return 0;
        int[] dp=new int[3];
        int[] min=new int[3];
        Arrays.fill(min,prices[0]);
        for (int i=1;i<n;i++){
            for (int k=1;k<=2;k++){
                min[k]= Math.min(min[k], prices[i] - dp[k-1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[2];
    }
}

//Version 5

终极简化版 面试不推荐。。
On every day, we buy the share with the price as low as we can, and sell the share with price as high as we can. 
For the second transaction, we "integrate the profit of first transaction into the cost of the second buy", 
then the profit of the second sell will be the total profit of two transactions.

class Solution {
    public int maxProfit(int[] prices)  {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }
}





https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution















