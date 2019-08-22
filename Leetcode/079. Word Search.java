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
    //main function
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(0, i, j, board, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //dfs function
    //这里的 index 表示 start 即 从该位开始 是否可以在matrix里找到
    //如果这里不用 index 用 String str (与 word search2 一样的话 会超时TLE)

    public boolean dfs(int index, int x, int y, char[][] board, String word, boolean[][] visited) {
        if (index == word.length()) return true;
        //出口 要写在 board[x][y] != word.charAt(index)之前 否则 会越界
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) return false;
        if (board[x][y] != word.charAt(index)) return false;
        
        visited[x][y] = true;
        //言下之意是 如果后面的都满足 那么加上开始的这一位 肯定也满足 
        if (dfs(index + 1, x + 1, y, board, word, visited) ||
            dfs(index + 1, x - 1, y, board, word, visited) || 
            dfs(index + 1, x, y + 1, board, word, visited) ||
            dfs(index + 1, x, y - 1, board, word, visited)) {
            return true;
        }
        visited[x][y] = false;
        
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






