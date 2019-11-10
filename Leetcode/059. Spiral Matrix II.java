59. Spiral Matrix II

Given a positive integer n, 
generate a square matrix filled with elements from 1 to n^2 in spiral螺旋 order.

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]



class Solution {
    public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        
        int[][] matrix = new int[n][n];
        
        int num = 1;
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
    
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
          for (int j = colBegin; j <= colEnd; j++) {
            matrix[rowBegin][j] = num++;
          }
          rowBegin++;

          for (int i = rowBegin; i <= rowEnd; i++) {
            matrix[i][colEnd] = num++;
          }
          colEnd--;

          if (rowBegin <= rowEnd){
            for (int j = colEnd; j >= colBegin; j--) {
              matrix[rowEnd][j] = num++;
            }
            rowEnd--;
          }

          if (colBegin <= colEnd) {
            for (int i = rowEnd; i >= rowBegin; i--) {
              matrix[i][colBegin] = num++;
            }
            colBegin++;
          }
        }
        return matrix;
    }
}