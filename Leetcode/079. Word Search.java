79. 单词搜索

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
同一个单元格内的字母不允许被重复使用。


board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.




DFS 标准写法

class Solution {
     public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && backtrack(i, j, 0, word, visited, board)) return true;
            }
        }
        return false;

    }
  
    //判断index位是否和board[i][j] 相等
    private boolean backtrack(int i, int j, int idx, String word, boolean[][] visited, char[][] board) {
        //先写 dfs 递归出口
        if (idx == word.length()) return true;
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(idx) || visited[i][j])
            return false;
        //标记走过，以便进入下一状态
        visited[i][j] = true;
        //4个方向递归进入下一状态
        if (backtrack(i + 1, j, idx + 1, word, visited, board) 
            || backtrack(i - 1, j, idx + 1, word, visited, board) 
            || backtrack(i, j + 1, idx + 1, word, visited, board) 
            || backtrack(i, j - 1, idx + 1, word, visited, board)) 
            return true;
        //回溯回上一状态
        visited[i][j] = false;
                                                      
        return false;
    }
}





dfs 模仿bfs的写法  以后不要这么写！！ 
DFS 还是要先写出口 然后 涉及方向的话 4个 排列 简单明了


class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;       // 对这个 [["a","a"]] "aaa"  test case
                if (dfs(board, 0, word, i, j, visited)) return true;
                visited[i][j] = false;      // 对这个 [["a","a"]] "aaa"  test case
            }
        }
        return false;
    }
                        
    
    private boolean dfs(char[][] board, int index, String word, int x, int y, boolean[][] visited) {
        int m = board.length, n = board[0].length;
        
        int[] dirs = new int[] {1, 0, -1, 0, 1};
        char ch = board[x][y];
        if (ch != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        
        for (int i = 0; i < 4; i++) {
            int adjx = x + dirs[i], adjy = y + dirs[i + 1];
            if (adjx < 0 || adjx >= m || adjy < 0 || adjy >= n || visited[adjx][adjy]) continue;
            visited[adjx][adjy] = true;
            if (dfs(board, index + 1, word, adjx, adjy, visited)) {
                return true;
            }
            visited[adjx][adjy] = false;
        }
        
        return false;
    }
}






