import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'findMinDistance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER w
     *  2. INTEGER h
     *  3. INTEGER n
     */

    private static int[][] grid;

    public static int findMinDistance(int w, int h, int n) {
        grid = new int[w][h];
        for (int i = 0; i < w; i++) 
            for (int j = 0; j < h; j++)
                grid[i][j] = -1;
        return dfs(0, n, w, h, 0, 0);
    }
    private static int dfs(int count, int n, int w, int h, int row, int col) {
        if (count == n)
            return bfs(w, h);
        int r = row, c = col;
        if (col >= h) {
            r += 1;
            c = col % h;
        }
        int minDistance = Integer.MAX_VALUE;

        for (int i = r; i < w; i++) {
            for (int j = c; j < h; j++) {
                grid[i][j] = 0;
                int tmp = dfs(count + 1, n, w, h, i, j + 1);
                minDistance = Math.min(minDistance, tmp);
                //backtracking
                grid[i][j] = -1;
            }
        }
        return minDistance;
    }

    private static int bfs(int w, int h) {
        int[][] dist = new int[w][h];
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++) 
                dist[i][j] = grid[i][j];
                
        int maxDistance = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (dist[i][j] == 0)
                    queue.add(new int[] {i, j});
            }
        }
        // begin searh from the building at 4 dirs
        int[] dirs = new int[] {1, 0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            maxDistance = Math.max(maxDistance, dist[cell[0]][cell[1]]);

            for (int k = 0; k < 4; k++) {
                int nx = cell[0] + dirs[k];
                int ny = cell[1] + dirs[k + 1];
                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if (dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[cell[0]][cell[1]] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return maxDistance;
    }
}

public class Solution {