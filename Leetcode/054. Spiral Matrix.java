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
We go boundary by boundary and move inwards向内. 
That is the essential operation. 
First row, last column, last row, first column and 
then we move "inwards" by 1 and then repeat. 
That‘s all, that is all the simulation that we need.







https://leetcode-cn.com/problems/spiral-matrix/solution/luo-xuan-ju-zhen-by-leetcode/

层数 划分 
数字代表层数

[[1, 1, 1, 1, 1, 1],        
 [1, 2, 2, 2, 2, 1],
 [1, 2, 3, 3, 2, 1],
 [1, 2, 2, 2, 2, 1],
 [1,'1''1''1''1',1]]


拨洋葱 一层一层 左上角(r1,c1) 右下角(r2,c2)
top: (r1, c1 -> c2)   
right: (c2, r1 + 1 -> r2)
bottom: (r2, c2 - 1 -> c1 + 1)       下面的 四个黄色'1'
left: (c1, r2 -> r1 + 1)






class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
         List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return ans;
        
        //  拨洋葱 一层一层 左上角(r1,c1) 右下角(r2,c2)
        // top: (r1, c1->c2)
        // right: (c2, r1 + 1 -> r2)
        // bottom: (r2, c2 -1 -> c1 + 1)
        // left: (c1, r2 -> r1 + 1)
        int r1 = 0, c1 = 0;
        int r2 = matrix.length - 1, c2 = matrix[0].length - 1;
        
        // last point r1 == r2 && c1 == c2 ---> then end loop
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {   
                // remove duplicate for test case: [1,2,3] only one row 
                // for only one col [[1], [2], [3]] one col
                for (int c = c2 - 1; c >= c1 + 1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r >= r1 + 1; r--) ans.add(matrix[r][c1]);
            }
            // update boundary
            r1++;
            c1++;
            r2--;
            c2--;
        }
        return ans;
    }
}





