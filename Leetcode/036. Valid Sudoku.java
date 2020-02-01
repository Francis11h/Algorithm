36. Valid Sudoku


建一个 boolean[] visited = new boolean[9]; 表示 1-9 用没用过
然后 按行 按列 按小矩阵 依次判断, 每次判断完 初始化 Arrays.fill(visited, false);

小矩阵 判断 有点意思。 i,j 每次 加三 k 除三模三

class Solution {
    // use an array indicates 1-9, check every row, col, block 
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];
        //check every row
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(board[i][j], visited)) return false;
            }
        }
        //check every col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(board[j][i], visited)) return false;
            }
        }
        // check every block
        // each block start we need to     +3
        // use k to move in each block   row k / 3, col k % 3
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    if (!isValid(board[i + k / 3][j + k % 3], visited)) return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char digit, boolean[] visited) {
        if (digit == '.') return true;
        int num = digit - '0';
        if (num < 1 || num > 9 || visited[num - 1]) return false;
        visited[num - 1] = true;
        return true;
    }
}















