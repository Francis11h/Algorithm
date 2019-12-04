309. Best Time to Buy and Sell Stock with Cooldown

不限次 但是每次卖了第二天不能买
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

1.You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
2.After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)



Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]




dp[i] 表示 第i天结束 最大利润
dp[i] = Math.max(dp[i - 1], prices[i] - prices[j] + dp[j - 2]));	//没有冷却的话是 + dp[j - 1]代表回到j前一天的利润 因为是在 j买的, 有了冷却 就再往前走一天即可
	1. if we dont trade, then the profit is same as previous day "dp[i-1]"
	2. if we bought stock j-th day, where j=[0...i-1], then sell the stock on i-th day,
		then the profit is ("prices[i] - (prices[j] - dp[j - 2]))").


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];			

    	for (int i = 1; i < prices.length; i++) {
    		int min = prices[0];
    		for (int j = 1; j <= i; j++) {						//所有i之前的天 都可能买, 计算最小的买入价格, 同时减去之前 白嫖赚的
    			int tmp = j < 2 ? 0 : dp[j - 2];				// corner case 
    			min = Math.min(min, prices[j] - tmp);
    		}
    		dp[i] = Math.max(dp[i - 1], prices[i] - min);
    	}

        return dp[prices.length - 1];
    }
}




优化 dp 不用每次都算 min 来一个新的算一个


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];	

        int min = prices[0];
    	for (int i = 1; i < prices.length; i++) {
    		int tmp = i < 2 ? 0 : dp[i - 2];				// corner case 
    		min = Math.min(min, prices[i] - tmp);
    		dp[i] = Math.max(dp[i - 1], prices[i] - min);
    	}

        return dp[prices.length - 1];
    }
}


基本同 122 dp解法

