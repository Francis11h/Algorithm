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



2019.11.25 重新写 一遍过

class Solution {
    public boolean exist(char[][] board, String word) {
        //corner case
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        if (word == null || word.length() == 0) return true;
        int m = board.length, n = board[0].length;
        // auxiliary array to avoid repeated traversals / avoid duplication
        boolean[][] visited = new boolean[m][n];
        // every point in board can be our start point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, visited, 0, word)) return true;
            }
        }
        return false;
    }
    
    // parameters that our dfs need: 1.board[][] 2.x 3.y 4.visited[][] 5.index 6.word
    // as for 5.index: indicates which level our dfs at + the corresponding index of the char in word
    // 这个 index 即代表了是dfs的第几层 同时也就是走到了 word中的 哪个下标
    // index == word.length() 就代表走完了 即找到了 因为若不是全部match 前面的步骤就直接return了.
    private boolean dfs(char[][] board, int x, int y, boolean[][] visited, int index, String word) {
        // found
        if (index == word.length()) return true;
        // out of bounds or repeated traversal
        int m = board.length, n = board[0].length;
        if (x >= m || x < 0 || y >= n || y < 0 || visited[x][y]) return false;
        // not match
        if (board[x][y] != word.charAt(index)) return false;
        // match then mark visited and do next level dfs on 4 dirextions
        visited[x][y] = true;
        if (dfs(board, x + 1, y, visited, index + 1, word) || 
            dfs(board, x - 1, y, visited, index + 1, word) || 
            dfs(board, x, y + 1, visited, index + 1, word) || 
            dfs(board, x, y - 1, visited, index + 1, word))
            return true;
        // backtracking 
        visited[x][y] = false;
        //not found
        return false;
    }
}



T: O(M*N * 4 ^ wordLength)  worst case
    actually    O(M*N * 4 * 3 ^ (wordLength - 1)) 
        Assume the maximum length of word is "L"
            initially we would have at most "4" directions to explore
            Assume each direction is valid (i.e. worst case), 
            during the following exploration, 
            we have at most "3" neighbor cells ("excluding the cell where we come from") to explore.


            each of the cells in the board contains the letter "a", and the word dictionary contains a single word ['aaaa']

worst case
board:      [[a,a,a,a],         word :['aaaa']
             [a,a,a,a],
             [a,a,a,a],
             [a,a,a,a]]

S: O(MN + L)










为了 让 dfs return void 而改的一种做法。。。有点捞

class Solution {
    boolean[] isFound = new boolean[1];     //这里必须传 reference 如果是基本数据类型 会传copy 即dfs不能修改这个值
    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 || board[0].length == 0) return false;
        if (word == null || word.length() == 0) return true;
        int m = board.length, n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isFound[0]) {
                    dfs(board, i, j, visited, 0, word, isFound);
                }
            }
        }
        return isFound[0];
    }
    

    private void dfs(char[][] board, int x, int y, boolean[][] visited, int index, String word, boolean[] isFound) {

        if (index == word.length()) {
            isFound[0] = true;
            return;
        }

        int m = board.length, n = board[0].length;
        if (x >= m || x < 0 || y >= n || y < 0 || visited[x][y]) return;

        if (board[x][y] != word.charAt(index)) return;

        visited[x][y] = true;
            
        if (!isFound[0]) {     // 加上这个 要提前结束 算是 low版的 prune
            dfs(board, x + 1, y, visited, index + 1, word, isFound); 
        }
         if (!isFound[0]) {
            dfs(board, x - 1, y, visited, index + 1, word, isFound); 
        }
         if (!isFound[0]) {
             dfs(board, x, y + 1, visited, index + 1, word, isFound);
        }
         if (!isFound[0]) {
            dfs(board, x, y - 1, visited, index + 1, word, isFound);
        }

        // 最开始这么写 但是 这种过不了 这个 test case
        //  [["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"],["a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","b"]]
        //  "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        // 
        // 
        // dfs(board, x + 1, y, visited, index + 1, word, isFound); 
        // dfs(board, x - 1, y, visited, index + 1, word, isFound); 
        // dfs(board, x, y + 1, visited, index + 1, word, isFound);
        // dfs(board, x, y - 1, visited, index + 1, word, isFound);

        // backtracking 
        visited[x][y] = false;
    }
}

