Rotate matrix among Diagnals

	rotate with k
对角线方向旋转矩阵中的元素k次，其中1<= k <= 4
对角线上的元素不转

Example：
[[1, 2, 3],
[4, 5, 6],
[7, 8, 9]]
-->
[[1, 4, 3],
[8, 5, 2],
[7, 6, 9]]

[[1,2,3,4,5],
[6,7,8,9,10],
[11,12,13,14,15],
[16,17,18,19,20],
[21,22,23,24,25]]
-->
[[1,16,11,6,5],
[22,7,12,9,2],
[23,18,13,8,3],
[24,17,14,19,4],
[21,10,15,20,25]]

48 rotate image 变形 check是不是diagnol




import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 2, 3},
                                {4, 5, 6},
                                {7, 8, 9}};

        int[][] B = new int[][]{{1,2,3,4,5},
                                {6,7,8,9,10},
                                {11,12,13,14,15},
                                {16,17,18,19,20},
                                {21,22,23,24,25}};

        int k = 1;
        System.out.println(Arrays.deepToString(helper(k, B)));

    }

    public static int[][] helper(int k, int[][] nums) {
        int n = nums.length;
        while (k > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (!isDiagonal(n, i, j)) {
                        int temp = nums[i][j];
                        nums[i][j] = nums[j][i];
                        nums[j][i] = temp;
                    }
                }
            }

            for (int j = 0; j < n / 2; j++) {
                for (int i = 0; i < n; i++) {
                    if (!isDiagonal(n, i, j)) {
                        int temp = nums[i][j];
                        nums[i][j] = nums[i][n - 1 - j];
                        nums[i][n - 1 - j] = temp;
                    }
                }
            }
            k--;
        }
        return nums;
    }

    private static boolean isDiagonal(int n, int x, int y) {
        return x == y || x + y == n - 1;
    }

}

