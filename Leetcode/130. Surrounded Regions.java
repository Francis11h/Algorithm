130. Surrounded Regions

Given a 2D board containing 'X' and 'O' (the letter O), 
capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

input
X X X X
X O O X
X X O X
X O X X
output
X X X X
X X X X
X X X X
X O X X



从边界的'O'开始遍历，只访问'O'，先暂时都设置为'T'或其他字符
遍历结束后，将剩下的'O'替换为'X'，再将'T'还原即可


class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0])
                bfs(i, 0, m, n, board, visited);
            if (board[i][n - 1] == 'O' && !visited[i][n - 1])
                bfs(i, n - 1, m, n, board, visited);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j])
                bfs(0, j, m, n, board, visited);
            if (board[m - 1][j] == 'O' && !visited[m - 1][j])
                bfs(m - 1, j, m, n, board, visited);
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'T')
                    board[i][j] = 'O';
            }
        }
    }
    int[] dirs = new int[] {0, 1, 0, -1, 0};

    public void bfs(int i, int j, int m, int n, char[][] board, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        board[i][j] = 'T';
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || board[nx][ny] != 'O' || visited[nx][ny])
                    continue;
                board[nx][ny] = 'T';
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}


还有一种比较笨的方法

在记录每个点是否访问过的前提下，依次从每个'O'开始bfs，并且只访问未访问过的'O'
如果一个'O'可以放访问到边界，那么不做任何操作
否则将这个'O'可访问到的所有'O'变为'X'