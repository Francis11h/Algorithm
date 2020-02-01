505. The Maze II

490 the Maze + 记录最小步数



主要是 点 不一定可以只经过一次 可能第二次用的路程更短, 
所以仅仅用 boolean[][] visited 来判断是不够的 
判断走过 跟distance 需要联合起来 所以要用 int[][] visited来记录目前到该点的步数 然后与新来的比较
所以 也就不用新建类来记录step了，，，




class Solution {
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
 
        int[][] visited = new int[maze.length][maze[0].length];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();  
            int x = cur[0], y = cur[1];
            
            int[] dirs = new int[] {0, 1, 0, -1, 0};
            for (int k = 0; k < 4; k++) {
                int nx = x , ny = y;
                int step = 0;
                
                while (isValid(nx + dirs[k], ny + dirs[k + 1], maze)) {
                    nx += dirs[k];
                    ny += dirs[k + 1];
                    step++;
                }
                
                
                if (visited[nx][ny] > visited[x][y] + step ) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = visited[x][y] + step;     
                }
            } 
        }
        return visited[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : visited[destination[0]][destination[1]];
    }
    
    private boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1;
    }
}











官方标答


public class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
         int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
        Queue < int[] > queue = new LinkedList < > ();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.add(new int[] {x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
}