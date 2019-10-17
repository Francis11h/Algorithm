48. Rotate Image

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

---  in-place



Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]


Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

肯定是 涉及数学计算 和 坐标变换的

所有的旋转 都是 翻转合成的

All rotations are composite reflections 
(in fact, all transformations are composite reflections); 

目标 [i, j] ----> [j, n - 1 - i]

过程 [i, j] ----> [j, i] -----> [j, n - 1 - i]

		沿主对角线翻转    沿中轴翻转


class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        if (matrix.length != matrix[0].length) return;

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {		// i + 1 只用一半 否则相当于 转过来又转回去了
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[j][i];
        		matrix[j][i] = temp;
           	}
        }

        for (int j = 0; j < n / 2; j++) { 
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][j];
        	    matrix[i][j] = matrix[i][n - 1 - j];
        	    matrix[i][n - 1 - j] = temp;
            }
        }
         // for i in range(n):
         //    matrix[i].reverse()
    }
}


O(N^2)
O(1)




