188. Best Time to Buy and Sell Stock IV

Design an algorithm to find the maximum profit. You may complete at most k transactions.
k次 买卖
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
只能同时持有一支股票



Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.


Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.




Memory Limit Exceeded when K = 100000000

class Solution {
    public int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length == 0) return 0;

        int trans = K;
        int[][] dp = new int[trans + 1][prices.length];

        for (int k = 1; k <= trans; k++) {
        	int min = prices[0];
        	for (int i = 1; i < prices.length; i++) {
        		min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
        		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
        	}
        }

        return dp[trans][prices.length - 1];
    }
}



所以可以考虑 当 K>= prices.length/2 即相当于不限制交易次数 即有gap就可以累加 = 122. Best Time to Buy and Sell Stock II 	 只要 有上升的 折线 就加到最后的maxProfit里面






class Solution {
    public int maxProfit(int K,int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        //corner case ---> reduce calculation
        if (K >= prices.length / 2) return buyAndSellWithoutLimit(prices);

        int trans = K;
        int[][] dp = new int[trans + 1][prices.length];

        for (int k = 1; k <= trans; k++) {
        	int min = prices[0];
        	for (int i = 1; i < prices.length; i++) {
        		min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
        		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
        	}
        }

        return dp[trans][prices.length - 1];
    }

    private int buyAndSellWithoutLimit(int[] prices) {		//122. Best Time to Buy and Sell Stock II 	 只要 有上升的 折线 就加到最后的maxProfit里面
    	int profit = 0;
    	for (int i = 1; i < prices.length; i++) {
    		if (prices[i] > prices[i - 1]) {
    			profit += prices[i] - prices[i - 1];
    		}
    	}
    	return profit;
    }
}

