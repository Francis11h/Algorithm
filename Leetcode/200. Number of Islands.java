class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {  //是 ‘1’ 且没走过，才用从这里开始bfs
                    bfs(grid, visited, i, j, m, n);
                    ans++;
                }
            }
        }
        return ans;
    }
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {-1, 1, 0, 0};
    
    private void bfs(char[][] grid, boolean[][] visited, int x, int y, int m, int n) {
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        qx.offer(x);
        qy.offer(y);
        visited[x][y] = true;
        
        while (!qx.isEmpty()) {
            int sx = qx.poll(), sy = qy.poll();
            for (int k = 0; k < 4; k++) {
                int nx = sx + dx[k], ny = sy + dy[k];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1' && !visited[nx][ny]) {
                    qx.offer(nx);
                    qy.offer(ny);
                    visited[nx][ny] = true;     //最重要的地方，把这个已经入队的点 标记为已经走过
                }
            }
        }
    }
}


本题的两个关键点：
bfs() 相当于一个染色的过程, 每次染一个 connected component，所以 每次 if（点是白色）一定要改 visited 为 true
主函数中 调用bfs时， 起点必须为白色， 所以也要加 grid[i][j] == '1' && !visited[i][j]






Time complexity : 
O(MN) where 
M is the number of rows and 
N is the number of columns.
每个点最多走一次


Space O(MN)  visited[][] 大小