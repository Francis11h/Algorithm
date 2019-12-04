714. Best Time to Buy and Sell Stock with Transaction Fee


就是 一次 交易加了个 fee 本质没变 还是 dp


dp[i] 表示 第i天结束 最大利润
dp[i] = Math.max(dp[i - 1], prices[i] - prices[j] + dp[j - 1] - fee));	
	1. if we dont trade, then the profit is same as previous day "dp[i-1]"
	2. if we bought stock j-th day, where j=[0...i-1], then sell the stock on i-th day,

		then the profit is ("prices[i] - (prices[j] - dp[j - 1] + fee))").


class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];			

        for (int i = 1; i < prices.length; i++) {
            int min = prices[0] + fee;
            for (int j = 1; j <= i; j++) {						//所有i之前的天 都可能买, 计算最小的买入价格, 同时减去之前 白嫖赚的
                min = Math.min(min, prices[j] - dp[j - 1] + fee);
            }
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }

        return dp[prices.length - 1];
    }
}



优化 dp 不用每次都算 min 来一个新的算一个

class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];	

        int min = prices[0] + fee;
    	for (int i = 1; i < prices.length; i++) {
    		min = Math.min(min, prices[i] - dp[i - 1] + fee);
    		dp[i] = Math.max(dp[i - 1], prices[i] - min);
    	}

        return dp[prices.length - 1];
    }
}



还是同 122 dp解法