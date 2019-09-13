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
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        if (word == null || word.length() == 0) return false;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, 0, i, j, visited, word)) return true;
            }
        }
        return false;
    }
    // what param dfs need?  1.board 2.x 3.y 4.visited 5. index of the string 6. string
    // what dfs return? whether the char at board[x][y] == word.charAt(index)
    //      if index == word.length() ----> the word can be constructed from the board
    private boolean dfs(char[][] board, int index, int x, int y, boolean[][] visited, String word) {
        int m = board.length, n = board[0].length;
        //found
        if (index == word.length()) return true;
        // not qualified
        if (x < 0 || x >= m || y < 0 || y <= n || visited[x][y] || board[x][y] != word.charAt(index)) return false;
        visited[x][y] = true;
        if (dfs(board, index + 1, x + 1, y, visited, word) || 
            dfs(board, index + 1, x - 1, y, visited, word) || 
            dfs(board, index + 1, x, y + 1, visited, word) || 
            dfs(board, index + 1, x, y - 1, visited, word) )
        return true;
        visited[x][y] = false;
        return false;
    }
}



DFS 还是要先写出口 然后 涉及方向的话 4个 排列 简单明了


time is O(M * N * 4^L) 
where M*N is the size of the board and 
we have 4^L for each cell because of the recursion.
    if the average length is 5 , we will have 44444 total ways we could form a word on the board 
            (note we ignore the boundaries of the board in those kind of calculations)

S O(MN+L)





