121. Best Time to Buy and Sell Stock

you were only permitted to complete at most one transaction
总共只能买卖一次
you cannot sell a stock before you buy one.





Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


因为只能买卖一次 所以我们只要找 max(price[j] - price[i]) for every i and j that j > i


1. bture force 枚举所有可能

class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    int tmp = prices[j] - prices[i];
                    if (ans < tmp) {
                        ans = tmp;
                    }
                }
            }
        }
        return ans;
    }
}

O(n ^ 2) loop runs n(n-1)/2
O(1)


2. 仅一遍遍历 找到 peek 和 valley 即可

贪心: 我们想要在最低的价格买入，然后在最高的价格卖出. 但是 最高的价格必须在最低价之后，所以 这个最高是相对的。


最朴素证明：
找 valley 即 在其之后的 peek, 并 维护一个maxProfit
若之后有更低的 valley, 更新 valley 再找 新valley 之后的peek 再计算 profit 看会不会 是maxProfit

就遍历一遍prices[], 若prices[i] < minPrice(valley), 则把改值赋值给minPrice,
                   否则计算 profit即可.



class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = Integer.MAX_VALUE;
        
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) 
                minPrice = prices[i];
            else if (prices[i] - minPrice > ans) 
                ans = prices[i] - minPrice;
        }
        return ans;
    }
}

//维护一个 每个数组元素对应的当前 最低买入价
//维护一个 最大利润 

We want to buy at the smallest price current seen, 
We keep track of the minimum price we want to buy at through the variable minBuy 
(Any other choice for buying will result in a smaller profit so they donot need to be checked) 

At every step I keep greedily picking the maximum profit and keep track of the maximum Profit,
which is either a profit previously seen or the current price subtracted from the minimum price we bought at (selling on this day).

