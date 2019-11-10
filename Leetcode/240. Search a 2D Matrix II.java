240. Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix.

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]


//brute force O(mn)
// just search ignore the property of sorted
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }
}



//O(m + n)
// search from top right, each round we can reduce one col or one row
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        
        int row = 0, col = n - 1;
        while (row != m && col != -1) {	// -1才是取不到的 注意下
        	if (matrix[row][col] == target) return true;
        	else if (matrix[row][col] < target) row++;
        	else col--;
        }

        return false;
    }
}
