407. Trapping Rain Water II

from the borders, pick the shortest cell visited and check its neighbors:
if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
add all its neighbors to the queue.


Just like 1-D two pointer approach, we need to find some boundary. 
    Because all boundary cells cannot hold any water for sure, we use them as the initial boundary naturally.

Then which bar to start? 
    Find the min bar (lets call it A) on the boundary (heap is a natural choice), then do 1 BFS (4 directions).

    Why BFS? 
    Because we are sure that the amount of water that A‘s neighbors can hold is only determined by A now for the same reason in 1D two-pointer approach.


How to update the heap during BFS
Step 1. Remove the min bar A from the heap
Step 2. If A’s neighbor B‘s height is higher, it cannot hold any water. Add it to the heap
Step 3. If B’s height is lower, it can hold water and the amount of water should be height_A - height_B. 
    Here comes the tricky part, we still add B‘s coordinate into the heap, 
    BUT change its height to A’s height because A is the max value along this path 
    (for this reason we cannot just use heightMap and need a class/array to store it‘s coordinates and UPDATED height). 

    And we can think of B as a replacement of A now and never worry about A again. 
    Therefore a new boundary is formed and we can repeat this process again.



洋葱理论 基本上 外围算了 往里面走


class Solution {
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(new Comparator<Cell>(){
            public int compare (Cell a, Cell b) {
                return a.height - b.height;
            }
        });

        int m = heights.length, n = heights[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            minHeap.offer(new Cell(i, 0, heights[i][0]));
            minHeap.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }
        
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            minHeap.offer(new Cell(0, i, heights[0][i]));
            minHeap.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        int water = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        while (!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();
            for (int k = 0; k < 4; k++) {
                int nx = cell.x + dirs[k], ny = cell.y + dirs[k + 1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                water += Math.max(0, cell.height - heights[nx][ny]);
                minHeap.offer(new Cell(nx, ny, Math.max(cell.height, heights[nx][ny])));
            }
        }
        return water;
    }
}

class Cell {
    public int x, y, height;
    public Cell(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}