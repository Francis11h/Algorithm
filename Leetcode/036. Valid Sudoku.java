36. Valid Sudoku


建一个 boolean[] visited = new boolean[9]; 表示 1-9 用没用过
然后 按行 按列 按小矩阵 依次判断, 每次判断完 初始化 Arrays.fill(visited, false);


class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];
        //row 
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(visited, board[i][j])) return false;
            }
        }


        //col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!isValid(visited, board[j][i])) return false;
            }
        }

        //sub matrix
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    if (!isValid(visited, board[i + k / 3][j + k % 3])) return false;
                }
            }
        }
        return true;
    }


    public boolean isValid(boolean[] visited, char digit) {
        if (digit == '.') return true;

        int num = digit - '0';

        if (num < 1 || num > 9 || visited[num - 1]) return false;

        visited[num - 1] = true;
        return true;
    }
}
















