2019.12.2  代码重构 重新写


dfs solution 一遍过 beast 100%

// first we can use seach, which can start at every cell, and mark visited
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        // auxiliary array to avoid repeated traversal
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        //  we can start searching at every point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {     // 这是本题最关键的 何时记数？ 只有走到'1' 且该位置之前没有通过其他位置到达过
                    ans++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return ans;
    }
    // parameters that we need:1.grid 2. coordinate x 3. coordinate y 4. visited 
    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // not qualified
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != '1')
            return;
        visited[x][y] = true;
        dfs(grid, x + 1, y, visited);
        dfs(grid, x - 1, y, visited);
        dfs(grid, x, y + 1, visited);
        dfs(grid, x, y - 1, visited);
    }
}

Time complexity: 
T:O(MN) where M is the number of rows and N is the number of columns.每个点最多走一次
S:O(MN)  visited[][] 大小


    dfs中 往下循环 可以这么写

    int[] dirs = new int[]{1, 0, -1, 0, 1};
    for (int k = 0; k < 4; k++) {
        int nx = x + dirs[k], ny = y + dirs[k + 1];
        dfs(grid, nx, ny, visited);
    }









bfs solution

本题的两个关键点:

1.  bfs() 相当于一个染色的过程, 每次染一个 "connected component",
    所以 每次 if（点是白色）一定要改 visited 为 true

2.  主函数中 调用bfs时,起点必须为白色,所以也要加 grid[i][j] == '1' && !visited[i][j]


bfs 建queue 永远是 "在 bfs 里面建" 不是 主函数, 即 一次bfs一个queue
同时 bfs 还需要"方向数组"



// first we can use seach, which can start at every cell, and mark visited
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        // auxiliary array to avoid repeated traversal
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        //  we can start searching at every point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    ans++;
                    bfs(grid, i, j, visited);
                }
            }
        }
        return ans;
    }
    // parameters that we need:1.grid 2. coordinate x 3. coordinate y 4. visited 
    private void bfs(char[][] grid, int x, int y, boolean[][] visited) {
        //when we do bfs, we will initialize a queue first 
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        // directions array
        int[] dirs = new int[] {1, 0, -1, 0, 1};
        
        while (!queue.isEmpty()) {
            int m = grid.length, n = grid[0].length;
            int[] cur = queue.poll();
            // we can only search (do dfs) on 4 firections
            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dirs[k], ny = cur[1] + dirs[k + 1];
                // not qualified, attention, what we check is the next position, nx/ny not x/y
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny] || grid[nx][ny] != '1')
                    continue;
                visited[nx][ny] = true;
                queue.offer(new int[] {nx, ny});
            }
        }
    }
}





union find solution



class Solution {
    
    private int[] father = null;
    
    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    private void union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
            res--;      //每一次 union 就少一个 独立的岛 就减去1
        }
    }
    
    int res = 0;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length, n = grid[0].length;
        father = new int[m * n];
        
        //先把 所有 '1' 先拿出来 并且 构建father数组 初始father节点都是其本身
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    father[i * n + j] = i * n + j;
                }
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dirs[k], ny = j + dirs[k + 1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                            union(i * n + j, nx * n + ny);
                        }
                    }
                }
            }
        }
        
        return res;
    }
}

