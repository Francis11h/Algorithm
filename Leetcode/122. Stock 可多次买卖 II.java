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




