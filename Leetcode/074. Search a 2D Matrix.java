74. Search a 2D Matrix

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row - 1;
        while (left + 1 < right) {
        	int mid = left + (right - left) / 2;
        	if (matrix[mid][0] == target)
        		return true;
        	if (matrix[mid][0] < target)
        		left = mid;
        	else 
        		right = mid;
        }
        if (matrix[right][0] <= target)
        	row = right;
        else if (matrix[left][0] <= target)
        	row = left;
        else 
        	return false;

        left = 0;
        right = col - 1;
        while (left + 1 < right) {
        	int mid = left + (right - left) / 2;
        	if (matrix[row][mid] == target)
        		return true;
        	if (matrix[row][mid] < target)
        		left = mid;
        	else 
        		right = mid;
        }
        if (matrix[row][left] == target || matrix[row][right] == target)
        	return true;
        return false;
    }
}