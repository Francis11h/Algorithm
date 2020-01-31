51. N-Queens

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.




2020.1.31
根本 就是 用行row 来遍历 同时记录 列col 主对角线 diff 副对角线 sum 不能有重复 用set来表示这三个


class Solution {
    char[][] board;
    Set<Integer> col = new HashSet<>();
    Set<Integer> sum = new HashSet<>();
    Set<Integer> diff = new HashSet<>();
    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return ans;
        //build the chessboard
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        //search
        dfs(0, n);
        return ans;
    }

    private void dfs(int row, int n) {
        if (row == n) {
            List<String> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                cur.add(String.valueOf(board[i]));
            }
            ans.add(new ArrayList<>(cur));
            return;
        }

        //each column
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !sum.contains(i + row) && !diff.contains(i - row)) {
                board[row][i] = 'Q';
                col.add(i);
                sum.add(i + row);
                diff.add(i - row);

                dfs(row + 1, n);
                // backtracking
                board[row][i] = '.';
                col.remove(i);
                sum.remove(i + row);
                diff.remove(i - row);
            }
        }
    }
}

























class Solution {
    
    List<List<String>> results = new ArrayList<>();
    Set<Integer> sum = new HashSet<>();
    Set<Integer> diff = new HashSet<>();
    Set<Integer> col = new HashSet<>();
    char[][] borad = null;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return results;
        borad = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                borad[i][j] = '.';
            }
        }

        search(0, n);
        return results;
    }
    
    private void search(int level, int n) { //每个level带表第几行
        if (level == n) {
            List<String> now = new ArrayList<>();

            //把char数组的每一行都分别变为String，用 String.valueOf()
            for (int i = 0; i < n; i++) {
                now.add(String.valueOf(borad[i]));
            }
            results.add(now);
            return;
        }
        
        // try to put queen at level i
        // 每个i代表 一列（每一行中能走的状态，就是8列！！）
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !diff.contains(level - i) && !sum.contains(level + i)) {
                col.add(i);
                diff.add(level - i); //主对角线方向 行-列 相同
                sum.add(level + i); //副对角线 行+列 相同
                borad[level][i] = 'Q';
                
                search(level + 1, n);

                col.remove(i);
                diff.remove(level - i);
                sum.remove(level + i);
                borad[level][i] = '.';
            }
        }

    }
}