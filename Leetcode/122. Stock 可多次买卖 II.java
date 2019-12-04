122. Best Time to Buy and Sell Stock II

可多次买卖

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
但是 每次买之前要先把手头的卖了 即 只能同时持有一支股票
  
  
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.


Greedy solution

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null) return 0;
        int maxProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        return maxProfit;
    }
}



就 只要 有上升的 折线 就加到最后的maxProfit里面


The key point is we need to consider Every peak Immediately following a valley to maximize the profit. 
In case we skip one of the peaks 如果我们跳过其中的一个peek, we will end up losing 最终会损失 the profit over one of the transactions leading to an overall lesser profit.






Solution2 dp解法1

dp[i] 表示 第i天结束 最大利润
dp[i] = Math.max(dp[i - 1], prices[i] - (prices[j] - dp[j - 1]));
    1. if we dont trade, then the profit is same as previous day "dp[i-1]"
    2. if we bought stock j-th day, where j=[0...i-1], then sell the stock on i-th day,
        then the profit is ("prices[i] - prices[j] + dp[j - 1]").


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];          

        for (int i = 1; i < prices.length; i++) {
            int min = prices[0];
            for (int j = 1; j <= i; j++) {                      //所有i之前的天 都可能买, 计算最小的买入价格, 同时减去之前 白嫖赚的
                min = Math.min(min, prices[j] - dp[j - 1]);
            }
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }

        return dp[prices.length - 1];
    }
}


        [7,1,5, 3, 6, 4]
min      7 1 1 -1 -1 -1
dp[i]    0 0 4  4  7  7 

Solution2 dp解法2 优化 因为 min运算 一直在重复


class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int[] dp = new int[prices.length];          

        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 每次 新来一个 price 都计算出 买入价 - 之前几个trans 白嫖的利润
            min = Math.min(min, prices[i] - dp[i - 1]);
            dp[i] = Math.max(dp[i - 1], prices[i] - min);
        }

        return dp[prices.length - 1];
    }
}












Follow Up: 可以同时持有多支股票 但是一天只能进行一次买or卖

[1,1,5,9]   按 只能同时持有一支股票, maxProfit = 8
            按 可以同时持有多支股票, maxProfit = 12


怎么做 可以 这样子 
    ❌
    先找到 单次买卖profit最大的 记录 (即 121)
    然后 "数组中删去这两个元素", 再找 
    直到 数组中 再也找不到利润

    [1,1,5,9,3,20] test case 失败
    ... 感觉是 np问题

    


