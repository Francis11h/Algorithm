037. Sudoku Solver

根据给出的不完全数独 得到一个解
假设可以得到一个唯一解
You may assume that the given Sudoku puzzle will have a single unique solution.



int[][] board = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9},
};


 // 1- 9的数字， 数组存下标是从0开始 0-9 是10个，所以后面要定为10
// 前面一个代表坐标 位置，后面一个代表数
static boolean[][] row = new boolean[9][9 + 1];
static boolean[][] col = new boolean[9][9 + 1];
// 前面两个代表坐标 位置，后面一个代表数
static boolean[][][] block = new boolean[3][3][9 + 1];

意义是

比如 row[0][5] = True, 就是 第0行已经用过5这个数字了
比如 col[1][3] = True, 就是 第1列已经用过3这个数字了
上面是 9 x 10
下面是 3 x 3 x 10
    block[0][0][5] = T 表示 第(0,0)个小格子 里 用过了5
    block[0][1][9] = T 表示 第(0,1)个小格子 里 用过了9
    block[2][0][6] = T 表示 第(2,0)个小格子 里 用过了9

数字 0 版本

public class Solution {
    
    // 1- 9的数字， 数组存下标是从0开始 0-9 是10个，所以后面要定为10
    // 前面一个代表坐标 位置，后面一个代表数
    boolean[][] row = new boolean[9][9 + 1];
    boolean[][] col = new boolean[9][9 + 1];
    // 前面两个代表坐标 位置，后面一个代表数
    boolean[][][] block = new boolean[3][3][9 + 1];
    boolean ans = false;
    
    public void solveSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int item = board[i][j];
                //有值(board中出现过的数)的对应的地方初始化为true 。
                    //          每三个一循环    小方块中的 第几行，第几列
                row[i][item] = col[j][item] = block[i / 3][j / 3][item] = true;
            }
        }
        dfs(board, 0, 0);
    }
    
    //(i,j)即将要填的位置的下表
    private void dfs(int[][] board, int i, int j) {
        //能走到9 代表填满了
        if (i == 9) {
            ans = true;
            return;
        }        
        //j一直往右走，可能越界
        if (j == 9) {
            dfs(board, i + 1, 0); //直接往下走一层，
            return; //返回
        }
        //如果这里开始前就已经被填了
        if (board[i][j] != 0) {
            dfs(board, i, j + 1);
            return;
        } 
        
        for (int item = 1; item <= 9; item++) {
            
            // 判断  是否合法,这里就是一种兼剪枝了
            if (!row[i][item] && !col[j][item] && !block[i / 3][j / 3][item]) {
                row[i][item] = col[j][item] = block[i / 3][j / 3][item] = true;
                board[i][j] = item; //填进去，改变该位置的值
                dfs(board, i, j + 1);//行row不动，列col动j
            //从这里出来，要是找到答案，return
                if (ans) { //ans = true 就是代表填满了，已经是一个正确答案了
                    return;
                }
            board[i][j] = 0; //回溯为0(初始值)
            //回溯为没走过
            row[i][item] = col[j][item] = block[i / 3][j / 3][item] = false;
            }
                
        }   
    }
}






字符 '.' 的版本


class Solution {

    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][][] block = new boolean[3][3][9];
    boolean done = false;

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    int item = ch - '1';
                    row[i][item] = col[j][item] = block[i / 3][j / 3][item] = true;
                }
            }
        }
        dfs(board, 0, 0);
    }

    private void dfs(char[][] board, int i, int j) {
        if (i == 9) {
            done = true;
            return;
        }

        if (j == 9) {
            dfs(board, i + 1, 0);
            return;
        }

        if (board[i][j] != '.') {
            dfs(board, i, j + 1);
            return;
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            int item = ch - '1';
            if (!row[i][item] && !col[j][item] && !block[i / 3][j / 3][item]) {
                row[i][item] = col[j][item] = block[i / 3][j / 3][item] = true;
                board[i][j] = ch;
                dfs(board, i, j + 1);
                if (done) {
                    return;
                }
                board[i][j] = '.';
                row[i][item] = col[j][item] = block[i / 3][j / 3][item] = false;
            }
        }
    }
}















