import java.util.Arrays;

public class CostOfTheTree {
    public static int mctFromLeafValues(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        int[][] maxVal = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++)
                    if (max < arr[k]) max = arr[k];
                maxVal[i][j] = max;
            }
        }
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                dp[i][j] = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++)
            dp[i][i] = 0;

//        for (int i = 1; i < len; i++) { // 长度
//            for (int j = 0; j < len - i; j++) { // 起始点
//                for (int k = j; k < j + i; k++) { // 中间点
//                    dp[j][j+i] = Math.min(dp[j][j+i], dp[j][k] + dp[k+1][j+i] + maxVal[j][k] * maxVal[k+1][j+i]);
//                }
//            }
//        }

        for (int k = 1; k < len; k++) {             // 长度
            for (int i = 0; i < len - k; i++) {     // 起始点
                for (int j = i; j < i + k; j++) {   // 所有可能的划分
                    dp[i][i + k] = Math.min(dp[i][i + k],
                            dp[i][j] + dp[j + 1][i + k] + maxVal[i][j] * maxVal[j + 1][i + k]);
                }
            }
        }

        System.out.println(Arrays.deepToString(maxVal));
        System.out.println(Arrays.deepToString(dp));

        return dp[0][len-1];
    }

    public static void main(String[] args) {
        mctFromLeafValues(new int[]{6,2,4});
    }
}
