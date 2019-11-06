package amazonOA1;

public class MatrixSum {
    public static int helper(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j, sum = 0;
        while (i < m) {
            j = 0;
            while (j < n) {
                sum += matrix[i][j++];
            }
            i++;            //移动到这里
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 1, 2},
                {3, 3, 5}};

        System.out.println(helper(matrix));
    }
}
