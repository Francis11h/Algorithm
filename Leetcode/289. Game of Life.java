289. Game of Life

生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。
每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

live with 2-3 live ===> maintain live
如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；	
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；

die with 3 live  ===> die to live
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；



你可以使用原地算法解决本题吗？
请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。


本题中，我们使用二维数组来表示面板。
原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？




Hzr 解法


only need to find the position needs to change the state
live : 1 
die : 0

live ---> die   (live neighbor < 2 || live neighbor > 3)
die ---> live   (live neighbor == 3 )

in-place ? change twice  
要保证 改变了状态之后 仍然可以判断它的上一状态 是什么 这里用了 是否比0 大 作分界线
即使 要从 die 变 live 我们也先把该点设为 -1 这样子该点还是 <= 0 就相当于保留了原状态
    ====>  go live : -1   (still <= 0)
           go die : 2   (still > 0)
    
               
class Solution {

    int ToDie = 2, ReBorn = -1;

    //main function do twice transform
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        //first round change original state to intermediate state
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                changeState(i, j, board);
            }
        }
        
        //from intermediate state to final state
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == ReBorn) board[i][j] = 1;
                if (board[i][j] == ToDie) board[i][j] = 0;
            }
        }
    }
    
    //fisrt round
    public void changeState(int i, int j, int[][] board) {
        //get state of the position
        if (isLive(i, j, board)) {
            //live ----> die intermediate
            if (numberOfLive(i, j, board) < 2 || numberOfLive(i, j, board) > 3) {
                board[i][j] = ToDie;
            }
        } else {
            //die ----> live intermediate
            if (numberOfLive(i, j, board) == 3) {
                board[i][j] = ReBorn;
            }
        }
        
    }
    
    //count # of live neighbor
    public int numberOfLive(int x, int y, int[][] board) {
        int count = 0;
        int[] dirx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
        int[] diry = new int[]{1, -1, 0, 0, 1, -1, 1, -1};
        
        for (int k = 0; k < 8; k++) {
            count += isLive(x + dirx[k], y + diry[k], board) ? 1 : 0;
        }
        return count;
    }
    
    //determine neighbor of specific position isValid and live or die
    public boolean isLive(int x, int y, int[][] board) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] <= 0)
            return false;
        return true;
    }
}









