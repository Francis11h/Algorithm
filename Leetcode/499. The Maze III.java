499. The Maze III

505. The Maze II
    490 the Maze + 记录最小步数



Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"

Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".





比 505  Maze II 多得是 要找最短的路径 并且打印路径 lul
所以用一个类来 存点 同时改类中有方法 可以比较两个dist相同的点的 字母序
 

较烂的写法。。。
class Solution {
    class Point {
        int x, y, dist;
        String path;    
        public Point(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
        public boolean lessThan(Point p) {
            if (this.dist < p.dist) return true;
            else if (this.dist == p.dist) return this.path.compareTo(p.path) < 0;   //compareTo a < b 的话返回的是0
            
            return false;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // still need a visited array to avoid duplicate
        // 505 用 int[][] visited 代替 490 boolean[][] visited
        // 499 本题 用 Point[][] cost 来存 相当于 又多存了个path(存在 Point里)
        int m = maze.length, n = maze[0].length;
        Point[][] cost = new Point[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cost[r][c] = new Point(r, c, Integer.MAX_VALUE, "");
            }
        }

        int[] dirs = new int[]{0, 1, 0, -1, 0};
        //(0, 1) means right move  (1, 0) down (0, -1) left (-1,0)up
        String[] dirStr = new String[]{"r", "d", "l", "u"};

        Queue<Point> queue = new LinkedList<>();

        queue.offer(new Point(ball[0], ball[1], 0, ""));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cost[cur.x][cur.y].lessThan(cur)) {
                continue;
            }
            cost[cur.x][cur.y] = cur;
            if (cur.x == hole[0] && cur.y == hole[1]) {
                continue;
            }
            
            for (int k = 0; k < 4; k++) {
                int x = cur.x, y = cur.y, dist = cur.dist;
                
                while (isValid(x, y, maze, hole)) {
                    x += dirs[k];
                    y += dirs[k + 1];
                    dist++;
                }
                if (x != hole[0] || y != hole[1]) {
                    x -= dirs[k];
                    y -= dirs[k + 1];
                    dist--;
                }
                   
                queue.offer(new Point(x, y, dist, cur.path + dirStr[k]));
            }
        }
        return cost[hole[0]][hole[1]].dist == Integer.MAX_VALUE ? "impossible" : cost[hole[0]][hole[1]].path;
    }

    private boolean isValid(int x, int y, int[][] maze, int[] hole) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0 && (x != hole[0] || y != hole[1]);
    }
}







很烂的写法 极其不推荐。。。
class Solution {
    class Point {
        int x, y, dist;
        String path;    
        public Point(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
        public boolean lessThan(Point p) {
            if (this.dist < p.dist) return true;
            else if (this.dist == p.dist) return this.path.compareTo(p.path) < 0;   //compareTo a < b 的话返回的是0
            
            return false;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // still need a visited array to avoid duplicate
        // 505 用 int[][] visited 代替 490 boolean[][] visited
        // 499 本题 用 Point[][] cost 来存 相当于 又多存了个path(存在 Point里)
        int m = maze.length, n = maze[0].length;
        Point[][] cost = new Point[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cost[r][c] = new Point(r, c, Integer.MAX_VALUE, "");
            }
        }

        int[] dirs = new int[]{0, 1, 0, -1, 0};
        //(0, 1) means right move  (1, 0) down (0, -1) left (-1,0)up
        String[] dirStr = new String[]{"r", "d", "l", "u"};

        Queue<Point> queue = new LinkedList<>();
        cost[ball[0]][ball[1]].dist = 0;
        queue.add(cost[ball[0]][ball[1]]);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                //先初始化成 cur.x cur.y的 然后再加, 主要是确定方向 把path先加上
                Point np = new Point(cur.x, cur.y, cur.dist, cur.path + dirStr[k]);
                while (isValid(np.x + dirs[k], np.y + dirs[k + 1], maze, hole)) {
                    np.x += dirs[k];
                    np.y += dirs[k + 1];
                    np.dist++;
                    //这里的 if 写进了 while里面 每次 都更新 就不符合逻辑。。。
                    if (np.lessThan(cost[np.x][np.y])) {
                        // cost[np.x][np.y] = np;
                        cost[np.x][np.y].dist = np.dist;
                        cost[np.x][np.y].path = np.path;
                        queue.offer(np);
                    }
                // 新的 np.x np.y 如果之前没到过 or 之前用的步数长 用np更新cost里的之前存的point 否则丢弃新的point np  
                }

                
            }
        }
        return cost[hole[0]][hole[1]].dist == Integer.MAX_VALUE ? "impossible" : cost[hole[0]][hole[1]].path;
    }

    private boolean isValid(int x, int y, int[][] maze, int[] hole) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}


















更加优雅的写法。。
张三疯还是牛逼。。。。

https://www.jiuzhang.com/solution/the-maze-iii/#tag-other


class Point {
    public int x, y, l;
    String path;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.l = Integer.MAX_VALUE;
        this.path = "";
    }
    
    public Point(int x, int y, int l, String path) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.path = path;
    }
    
    public boolean isLess(Point p) {
        if (this.l < p.l || (this.l == p.l && this.path.compareTo(p.path) < 0)) {
            return true;
        }
        return false;
    }
}


public class Solution {
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
        int m = maze.length, n = maze[0].length;
        
        Point[][] res = new Point[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = new Point(i, j);
            }
        }
        
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(ball[0], ball[1], 0, ""));
        
        int[] dirs = {-1, 0, 1, 0, -1};
        String[] dirChars = {"u", "r", "d", "l"};
        
        while (!que.isEmpty()) {
            Point cur = que.poll();
            if (res[cur.x][cur.y].isLess(cur)) {
                continue;
            }
            res[cur.x][cur.y] = cur;
            if (cur.x == hole[0] && cur.y == hole[1]) {
                continue;
            }
            
            for (int k = 0; k < 4; ++k) {
                int x = cur.x, y = cur.y, l = cur.l;
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0 && (x != hole[0] || y != hole[1])) {
                    x += dirs[k];
                    y += dirs[k + 1];
                    l++;
                }
                if (x != hole[0] || y != hole[1]) {
                    x -= dirs[k];
                    y -= dirs[k + 1];
                    l--;
                }
                
                que.offer(new Point(x, y, l, cur.path + dirChars[k]));
            }
        }
        
        return res[hole[0]][hole[1]].l == Integer.MAX_VALUE ? "impossible" : res[hole[0]][hole[1]].path;
    }
}







