120. Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).









DP 问题！！

This problem is quite well-formed in my opinion. 
The triangle has a tree-like structure, which would lead people to think about traversal algorithms such as DFS. 
However, if you look closely, you would notice that the adjacent nodes always share a 'branch'. 
In other word, there are "overlapping subproblems".

Also, suppose x and y are 'children' of k. 
Once minimum paths from x and y to the bottom are known, the minimum path starting from k can be decided in O(1), 
that is "optimal substructure"

Therefore, "dynamic programming" would be the best solution to this problem in terms of time complexity.


'Bottom-up' DP, on the other hand, is very straightforward: 
we start from the nodes on the bottom row; 
    the min pathsums for these nodes are the values of the nodes themselves. 

From there, the min pathsum at the ith node on the kth row would be 
    the lesser of the pathsums of its two children plus the value of itself, i.e.:
        minpath[k][i] = min(minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];

Or even better, since the row minpath[k+1] would be useless after minpath[k] is computed, 
    we can simply set minpath as a 1D array, and iteratively update itself:
        For the kth level:
            minpath[i] = min(minpath[i], minpath[i+1]) + triangle[k][i]; 









[

[2],

[3,4],

[6,5,7],

[4,1,8,3]

]




自底向上的  bottom to top
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(triangle.size() - 1).size();

        int[][] dp = new int[row][col];

        int i = 0;
        for (Integer val : triangle.get(row - 1)) {     //last row, min = themselves
            dp[row - 1][i++] = val;
        }

        for (int r = row - 2, m = 0; r >= 0; r--, m++) {
            for (int c = 0; c < col - 1 - m; c++) {
                dp[r][c] = Math.min(dp[r + 1][c], dp[r + 1][c + 1]) + triangle.get(r).get(c);
            }
        }
        return dp[0][0];
    }
}







更加优化 不用二维数组 直接用 1D array 每行找一个最小的即可---> 在优化 直接递推 每次改triangle数组即可  从下往上推



class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();

        for (int r = row - 2; r >= 0; r--) {
            for (int c = 0; c <= r; c++) {
                int self = triangle.get(r).get(c);
                int now = self + Math.min(triangle.get(r + 1).get(c), triangle.get(r + 1).get(c + 1));
                triangle.get(r).set(c, now);
            }
        }
        return triangle.get(0).get(0);
    }
}





用 1D array  但是 很tricky 就是 用行来存了列。。。不太推荐这种


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;
        // M[i] represents the min total from bottom to current layer
        

        int row = triangle.size();
        int col = triangle.get(row - 1).size();

        int[] M = new int[row];
        // copy the last row in triangle to M (这里很 tricky 用行来存了列)
        for (int i = 0; i < col; i++) {
            M[i] = triangle.get(row - 1).get(i);
        }

        // induction rule
        // M[i] = min(M[i], M[i + 1]) + curVal
        for (int r = row - 2; r >= 0; r--) {
            for (int c = 0; c < triangle.get(r).size(); c++) {
                // 这里 也很 tricky 每次更新 一整行的 所有列 所以应该是 M[c] 不是 M[r] 
                M[c] = Math.min(M[c], M[c + 1]) + triangle.get(r).get(c);
            }
        }
        return M[0];
    }
}



自顶向下        top to bottom
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;
        // M[i] represents the min total from current layer to bottom
        
        int row = triangle.size();
        int col = triangle.get(row - 1).size();

        int[] M = new int[row];
        M[0] = triangle.get(0).get(0);

        // induction 
        // M[j] = min(M[j - 1], M[j]) + curVal
        for (int i = 1; i < row; i++) {
            // layer order traversal
            List<Integer> cur = triangle.get(i);
            // 这里的顺序 特别关键 因为 M[j] 要用到 M[j - 1] 所以 要从后往前更新 这样子 前面的 就不会被破坏
            for (int j = cur.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    M[0] = M[0] + cur.get(j);
                } else if (j == cur.size() - 1) {
                    M[j] = M[j - 1] + cur.get(j);
                } else {
                    M[j] = Math.min(M[j - 1], M[j]) + cur.get(j);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < col; i++) {
            ans = Math.min(ans, M[i]);
        }
        return ans;
    }
}


