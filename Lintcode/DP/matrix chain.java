import java.util.Scanner;

public class dp {

    private static long dpStrategy(long[] nums, int n) {
        long[][] dp = new long[n][n];
        //dp[i][j] means minimum cost of multiply A i -> j
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int l = 2; l < n; l++) {  // l is chain length
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                if(j == n) continue;
                dp[i][j] = Long.MAX_VALUE;
                // possible k's position
                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            long[] nums = new long[n + 1];
            for (int i = 0; i < n + 1; i++) {
                nums[i] = sc.nextLong();
            }
            System.out.println(dpStrategy(nums, nums.length));
        }
    }
}




dp[i, j] means that optimal cost of multiplying the chain Ai to Aj.
When i is greater than j the value in corresponding dp[i, j] is meaningless.
When i is equal to j, the corresponding value in dp means the cost of multiply a matrix itself, which is also 0.

Then we need to compute the cost of multiplying the chains of length is 2.
Then length is 3 
... 
finally to n.       for(l = 2; l < n; l++).

For every length, we need a for loop to determine the start position of the chain : for(i = 1; i < n − l + 1; i++).
As we know the length, we can induce the end of chain: int j = i + l - 1;
And for each start position, we need to calculate the minimum cost of the multiplying, which means to find a k position on the chain starting from the
position i, with the length l : for(k = i; k < j; k++).


The formula to calculate cost is :
cost =dp[i][k]+dp[k+1][j]+nums[i−1]*nums[k]*nums[j];

Finally we return the value of dp[1][n − 1].


The time complexity is O(n ^ 3), cause there are O(n^2) cells and each cell cost at most O(n). 
The space complexity is O(n ^ 2).