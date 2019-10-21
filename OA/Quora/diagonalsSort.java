diagonalsSort
    int fun(int[][] a), 把一个 matrix 按斜线顺序重排

       [8, 4, 1]       [4, 4, 1]        [4, 1, 1]
       [4, 4, 1]  -->  [4, 8, 1]  -->   [4, 8, 4]
       [4, 8, 9]       [4, 8, 9]        [4, 8, 9]

所有的 对角线 都要排一遍

把一个n*n matrix的每一条斜线上 (与主对角线平行的对角线有 2n-1 个) 的值从小到大重新排序
暴力取出每一斜线的值sort之后再放回去就行了。




import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[][] nums = new int[][] {{8, 4, 1},
                                    {4, 4, 1},
                                    {4, 8, 9}};
        diagonalsSort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.println(nums[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static void diagonalsSort(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int d = 0; d < n; d++) {
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n - d; i++) {       // 这里就是 一些矩阵里的数学计算
                list.add(matrix[i][i + d]);
            }

            Collections.sort(list);

            for (int i = 0; i < n - d; i++) {
                matrix[i][i + d] = list.get(i);
            }

            list.clear();
            if (d != 0) {

                for (int i = 0; i < n - d; i++) {
                    list.add(matrix[i + d][i]);
                }

                Collections.sort(list);

                for (int i = 0; i < n - d; i++) {
                    matrix[i + d][i] = list.get(i);
                }
            }
        }
    }

}


