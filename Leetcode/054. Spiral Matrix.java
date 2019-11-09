54. Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), 
return all elements of the matrix in spiral order.



Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


hint 1:
Well for some problems, the best way really is to come up with some algorithms for simulation.
Basically, you need to simulate what the problem asks us to do.

hint 2:
We go boundary by boundary and move inwards. 
That is the essential operation. 
First row, last column, last row, first column and 
then we move "inwards" by 1 and then repeat. 
That‘s all, that is all the simulation that we need.

hint 3:
Think about when you want to switch the progress on one of the indexes. 
If you progress on
i
out of
[i, j]
, you‘d be shifting in the same column. 

Similarly, by changing values for
j
, you’d be shifting in the same row. 

Also, keep track of the end of a boundary so that you can move inwards and then keep repeating. 
It‘s always best to run the simulation on edge cases like a single column or a single row to see if anything breaks or not.



不完全正确的代码 

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
        	for (int j = colBegin; j <= colEnd; j++) {
        		ans.add(matrix[rowBegin][j]);
        	}
        	rowBegin++;

        	for (int i = rowBegin; i <= rowEnd; i++) {
        		ans.add(matrix[i][colEnd]);
        	}
        	colEnd--;

        	for (int j = colEnd; j >= colBegin; j--) {
        		ans.add(matrix[rowEnd][j]);
        	}
        	rowEnd--;

        	for (int i = rowEnd; i >= rowBegin; i--) {
        		ans.add(matrix[i][colBegin]);
        	}
        	colBegin++;
        }
        return ans;
    }
}




Input
[[1,2,3,4],
[5,6,7,8],
[9,10,11,12]]
Output
[1,2,3,4,8,12,11,10,9,5,6,7,6]
Expected
[1,2,3,4,8,12,11,10,9,5,6,7]


Input
[[3],[2]]
Output
[3,2,2]
Expected
[3,2]

上面写的 有bug 即重复

改了 还是 不完全对 
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
        	for (int j = colBegin; j <= colEnd; j++) {
        		ans.add(matrix[rowBegin][j]);
        	}
        	rowBegin++;

        	for (int i = rowBegin; i <= rowEnd; i++) {
        		ans.add(matrix[i][colEnd]);
        	}
        	colEnd--;

        	if (rowBegin < rowEnd){
	        	for (int j = colEnd; j >= colBegin; j--) {
	        		ans.add(matrix[rowEnd][j]);
	        	}
	        	rowEnd--;
	        }

	        if (colBegin < colEnd) {
	        	for (int i = rowEnd; i >= rowBegin; i--) {
	        		ans.add(matrix[i][colBegin]);
	        	}
	        	colBegin++;
	        }
        }
        return ans;
    }
}

Input:
[[2,5,8],[4,0,-1]]
Output:
[2,5,8,-1,4,0]
Expected:
[2,5,8,-1,0,4]



完全正确的 加上等号。。。

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;

        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
        	for (int j = colBegin; j <= colEnd; j++) {
        		ans.add(matrix[rowBegin][j]);
        	}
        	rowBegin++;

        	for (int i = rowBegin; i <= rowEnd; i++) {
        		ans.add(matrix[i][colEnd]);
        	}
        	colEnd--;

        	if (rowBegin <= rowEnd){
	        	for (int j = colEnd; j >= colBegin; j--) {
	        		ans.add(matrix[rowEnd][j]);
	        	}
	        	rowEnd--;
	        }

	        if (colBegin <= colEnd) {
	        	for (int i = rowEnd; i >= rowBegin; i--) {
	        		ans.add(matrix[i][colBegin]);
	        	}
	        	colBegin++;
	        }
        }
        return ans;
    }
}
