542. 01 Matrix
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
    input:
        0 0 0
        0 1 0
        1 1 1
    output:
        0 0 0
        0 1 0
        1 2 1



BFS
开始初始化，遍历整个matrix，对每个非0，距离设为无穷，把每个0入队。
要是 现在 点cell 的值 不比 它的父亲+1 大， 不用管了就 (现在 cell 值为0)

如果 比其 父+1 大，代表其没走过 那么 把 父+1 赋值给他，让他入队 往后再搜
越往后遍历到的 值越大的是

本题 较新的地方是 队列中 存的是个数组，代表位置坐标了，这样子不用建两个队列了
如果 不查的话 是 continue 也要注意下



class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[] dirs = {1, 0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = cell[0] + dirs[i];
                int col = cell[1] + dirs[i + 1];
                if (row < 0 || row >= m || col < 0 || col >= n ||
                    matrix[row][col] <= matrix[cell[0]][cell[1]] + 1) continue;
                queue.offer(new int[] {row, col});
                matrix[row][col] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }
}