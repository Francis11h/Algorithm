286. Walls and Gates

    -1  wall or obstacle
    0   a gate
    INF empty room

Fill each empty room with the distance to its nearest gate.
If it is impossible to reach a gate, it should be filled with INF.

input:
    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF
output:
    3  -1   0   1
    2   2   1  -1
    1  -1   2  -1
    0  -1   3   4  


brute force
     we just implement a breadth-first search from each empty room to its nearest gate.

Time complexity : O(m^2n^2） 
    For each point in the m×n size grid, the gate could be at most m×n steps away.
Space complexity : O(mn) (queue size at most mn)



相当于从所有的Gates同时开始BFS（直接入队好几个点） 只写一个函数 维护这个队列

class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0)
            return;
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});     //最开始就把所有的起点（Gates）放入 queue
                }
            }
        }

        int[] dirs = {0, 1, 0, -1, 0};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || rooms[nx][ny] != Integer.MAX_VALUE)
                    continue;
                rooms[nx][ny] = rooms[x][y] + 1;
                queue.add(new int[]{nx, ny});
                
            }
        }
    }
}













