256. Paint House


dp[i][c] means the min cost of painting houses 0...i AND paint the house i with color c. And the answer is then the min of dp[n-1][c] where c = red, green, blue.

the initialization:
dp[0][c] is just the cost of each color.

the recursion:
if house i is painted with color c, then the new total cost dp[i][c] should be dp[i-1][C != c] + cost[i][C == c].


    
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int[][] dp = new int[costs.length][3];
        
        for (int c = 0; c < 3; c++) dp[0][c] = costs[0][c];
        
        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        int len = costs.length;
        return Math.min(Math.min(dp[len - 1][0], dp[len - 1][1]), dp[len - 1][2]);
    }
}