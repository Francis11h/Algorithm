348. Design Tic-Tac-Toe

Design a Tic-tac-toe game that is played between two players on a n x n grid.
三连棋游戏(两人轮流在印有九格方盘上划 “+” 或 “O” 字, 谁先把三个同一记号排成横线、直线、斜线, 即是胜者)

You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.


hint:
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.





维护 所有行的和 所有列的和 两个对角线的和 
然后 每次 player1 +1, player2 - 1 
如果 有达到 正负bound 则代表 该 行/列/对角线 全部是这个人下的 游戏结束

class TicTacToe {

	int[] vertical;
	int[] horizontal;
	int[] diagonal;
	int bound;

    public TicTacToe(int n) {
        vertical = new int[n];
        horizontal = new int[n];
        diagonal = new int[2];
        bound = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int val = player == 1 ? 1 : -1;
        vertical[row] = vertical[row] + val;
        horizontal[col] = horizontal[col] + val;
        if (row == col) {
        	diagonal[0] = diagonal[0] + val;
        }
        if (row + col == bound - 1) {
        	diagonal[1] = diagonal[1] + val;
        }

        if (vertical[row] == bound || horizontal[col] == bound || diagonal[0] == bound || diagonal[1] == bound) {
        	return 1;
    	}

    	if (vertical[row] == -bound || horizontal[col] == -bound || diagonal[0] == -bound || diagonal[1] == -bound) {
        	return 2;
    	}
    	return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */





















