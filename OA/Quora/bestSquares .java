bestSquares 

给一个Matrix和一个Int k，在这个matrix上找到所有 k x k大小的submatrix 并计算这个submatrix里面所有值的和
如果这个submatrix的和是最大的话则将该submatrix里面所有distinct的value想加
如果存在和相同的submatrix
则将这个submatrix里面的值同样加到 distinct value 上最后返回所有 distinct value 的和


举个栗子：
matrix  [[1,2,3],   k = 2  submatrix有[[1,2],   和 [[2,3],   
         [4,2,2]]                      [4,2]]       [2,2]]

两个submatrix的和都为最大值9, 所以最后能找到的 distinct value 有[1,2,3,4] 输出的结果为10


Best Squares
mat = [[1,2,3,4,5], 
       [0,1,10,2,0],
       [1,3,5,7,6],
       [0,1,4,2,3]]






import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 2, 2}};
        System.out.println(bestSquares(matrix, 2));
    }

    public static int bestSquares(int[][] matrix, int k) {
        int ans = 0;

        int m = matrix.length, n = matrix[0].length;
        if (k > Math.min(m, n)) return 0;

        Map<int[], Integer> map = new HashMap<>();
        int maxSum = Integer.MIN_VALUE;

        for (int x = 0; x <= m - k; x++) {
            for (int y = 0; y <= n - k; y++) {
                // start point (x, y)
                int sum = 0;
                for (int i = x; i < x + k; i++) {
                    for (int j = y; j < y + k; j++ ) {
                        sum += matrix[i][j];
                    }
                }
                int[] start = new int[] {x, y};
                map.put(start, sum);
                maxSum = Math.max(maxSum, sum);
            }
        }

        Set<Integer> set = new HashSet<>();

        for (int[] key : map.keySet()) {
            if (map.get(key) == maxSum) {
                int x = key[0], y = key[1];
                for (int i = x; i < x + k; i++) {
                    for (int j = y; j < y + k; j++ ) {
                        set.add(matrix[i][j]);
                    }
                }
            }
        }

        for (Integer val : set) {
            ans += val;
        }

        return ans;
    }

}







