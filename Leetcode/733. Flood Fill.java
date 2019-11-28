733. Flood Fill

To perform a "flood fill", consider the starting pixel,
plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, 
plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), 
and so on. 

Replace the color of all of the aforementioned pixels with the newColor.





Input: 
    image = [[1,1,1],
             [1,1,0],
             [1,0,1]]

    sr = 1, sc = 1, newColor = 2

Output: [[2,2,2],
         [2,2,0],
         [2,0,1]]

Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.




标准 4方向 dfs 


class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) return image;
        int m = image.length, n = image[0].length;
        boolean[][] visited = new boolean[m][n];
        int old = image[sr][sc];
        dfs(image, visited, sr, sc, old, newColor);
        return image;
    }
    
    private void dfs(int[][] image, boolean[][] visited, int x, int y, int old, int newColor) {

        int m = image.length, n = image[0].length;
        // out of bounds or repeated traversal
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) return;
        // not same color as old color
        if (image[x][y] != old) return;
        image[x][y] = newColor;
        visited[x][y] = true;

        dfs(image, visited, x + 1, y, old, newColor);
        dfs(image, visited, x - 1, y, old, newColor);
        dfs(image, visited, x, y + 1, old, newColor);
        dfs(image, visited, x, y - 1, old, newColor);
        
    }
}

T:O(mn) go each cell at most once
S:O(mn)