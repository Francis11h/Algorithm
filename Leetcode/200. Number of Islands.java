

dfs beats 100% 

visited[][] 可以去掉 但是 这样子 就改变了 原矩阵


只有走到'1' 且该位置之前不能通过其他位置到达 才计数 然后通过该位置 把其他能走的1全标记了

class Solution {
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {      // 这是本题最关键的 何时记数？ 只有走到'1' 且该位置之前没有通过其他位置到达过
                    dfs(grid, i, j, visited);
                    ans++;
                }
            }
        }
        return ans;
    }
    
    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != '1') 
            return;      
        visited[x][y] = true;
        dfs(grid, x + 1, y, visited);
        dfs(grid, x - 1, y, visited);
        dfs(grid, x, y + 1, visited);
        dfs(grid, x, y - 1, visited);
    }
}


    dfs中 往下循环 可以这么写

    int[] dirs = new int[] {1, 0, -1, 0, 1};
    for (int k = 0; k < 4; k++) {
      int nx = x + dirs[k], ny = y + dirs[k + 1];
      dfs(grid, nx, ny, visited);
    }








本题的两个关键点：
bfs() 相当于一个染色的过程, 每次染一个 connected component，所以 每次 if（点是白色）一定要改 visited 为 true
主函数中 调用bfs时， 起点必须为白色， 所以也要加 grid[i][j] == '1' && !visited[i][j]


Time complexity : 
O(MN) where M is the number of rows and N is the number of columns.
每个点最多走一次


Space O(MN)  visited[][] 大小

public class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length, n = grid[0].length, ans = 0;
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          ans++;
          bfs(grid, i, j, visited);
        }
      }
    }
    return ans;
  }
  
  private void bfs(char[][] grid, int x, int y, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;

    Queue<int[]> queue = new LinkedList<>();
    int[] dirs = new int[] {1, 0, -1, 0, 1};
    queue.offer(new int[]{x, y});
    
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      for (int k = 0; k < 4; k++) {
        x = cur[0] + dirs[k];
        y = cur[1] + dirs[k + 1];
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != '1') continue;
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
      }
    }
  }
}



