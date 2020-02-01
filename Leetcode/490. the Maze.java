490. The Maze

There is a ball in a maze with empty spaces and walls. 
The ball can go through empty spaces by rolling up, down, left or right, 
	but it wont stop rolling "until hitting a wall" 只有撞墙才能停(即换方向). 

When the ball stops, it could choose the next direction.

Given the ball‘s start position, the destination and the maze, 
	determine whether the ball could "stop停住" at the destination.

The maze is represented by a binary 2D array. 
1 means the wall and 0 means the empty space. 


You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.




Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze wont exceed 100.





关键的关键 while 循环 不断添加, 合法才加到 nx ny上 直到撞墙, 此时的 nx ny 就是 撞墙前的那个位置, 即是可以换方向的位置
visited 来记录已经走过 防止重复




class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // search we can use BFS, only hit the wall the ball will stop, so in each direction, 
        // we need to keep move until the point is invalid
        // also we need a visited array to avoid duplicate
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();  
            int x = cur[0], y = cur[1];
            // this is the most important step in this problem 
            // cause the ball wont stop until it hit the wall 
            // So we need to record Last position before hitting the wall
            // then we need to add first, then check, if valid, we add, if invalid we just break the while loop and now the nx and ny is the last position before hitting the wall and we can choose another direction at here
            int[] dirs = new int[] {0, 1, 0, -1, 0};
            for (int k = 0; k < 4; k++) {
                int nx = x , ny = y;
                // 这里的 while 太关键了。。。。本题和200 # of island 区别 就是这里要一直走到墙的前一个
                while (isValid(nx + dirs[k], ny + dirs[k + 1], maze)) {
                    nx += dirs[k];
                    ny += dirs[k + 1];
                }
                if (nx == destination[0] && ny == destination[1]) return true;
                // only if we didnot visit, we add it to our queue
                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;     //then mark it as visited to avoid duplicate in futrue
                }
            } 
        }
        return false;
    }
    
    private boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1;
    }
}




O(mn)	 m and n refers to the number of rows and coloumns of the maze.
O(mn)
