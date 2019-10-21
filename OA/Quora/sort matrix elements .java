sort matrix elements 


输入一个二维矩阵，可能有重复数字。首先按照数字出现的频率排序，然后在二维矩阵中diagonal 排序

Sol: 先用hashmap统计频率，然后pq进行排序，然后按对角线输出（自己在对角线这里卡了20min，大家记得提前想好）


sort elements in a matrix then re-organize the elements 


sort: 1. by frequency of elements 2. by value of the element



[ [16,15,13,10],
  [14,12,9,6],
  [11,8,5,3],
  [7,4,2,1]]
 

就是给一个矩阵
先把里面的元素按给的规则（先看出现频率，如果频率一致比较数字大小）整体排序
然后从小到大按照规则（自矩阵右下角按对角线往左上角排，看原贴例子）填进矩阵




import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[][] matrix = {{3, 3, 1}, {2, 2, 2}, {1, 1, 1}};
        int[][] matrix2 = {{3, 3, 3}, {2, 2, 2}, {1, 1, 1}};
        int[][] matrix3 = {{4, 4, 4, 4}, {3, 3, 3, 3}, {2, 2, 2, 2}, {1, 1, 1, 1}};
        int[][] matrix4 = {{4, 4, 4, 4}, {4, 3, 3, 3}, {2, 2, 2, 2}, {1, 1, 1, 1}};
        sortMatrix(matrix);
        sortMatrix(matrix2);
        sortMatrix(matrix3);
        sortMatrix(matrix4);

        int m = matrix4.length, n = matrix4[0].length;
        for (int i = 0; i < m; i++) {
            String str = "";
            for (int j = 0; j < n; j++) {
                str += matrix4[i][j];
            }
            System.out.println(str);
        }
    }

    //square matrix
    private static int[][] sortMatrix(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = matrix[i][j];
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }

            }
        });


        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] now = new int[] {entry.getKey(), entry.getValue()};
            minHeap.offer(now);
        }

        List<Integer> sequence = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            int[] now = minHeap.poll();
            int count = now[1];
            while (count > 0) {
                sequence.add(now[0]);
                count--;
            }
        }

        int sum = 2 * n - 2;
        int index = 0;
        //bottom + secondary diagonal
        int k = n - 1;
        while (sum >= n - 1) {

            for (int i = n - 1; i >= k ; i--) {
                matrix[i][sum - i] = sequence.get(index);
                index++;
            }
            k--;
            sum--;
        }

        //upper part
        k = Math.max(n - 2, 0);
        while (sum >= 0) {
            for (int i = k; i >= 0; i--) {
                matrix[i][sum - i] = sequence.get(index);
                index++;
            }
            k--;
            sum--;
        }

        return matrix;
    }
}





