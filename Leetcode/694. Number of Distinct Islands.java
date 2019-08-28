694. Number of Distinct Islands

给定一个由0和1组成的非空的二维网格，一个岛屿是指四个方向（包括横向和纵向）都相连的一组1（1表示陆地）。
你可以假设网格的四个边缘都被水包围。
找出所有不同的岛屿的个数。
如果一个岛屿与另一个岛屿形状相同（不考虑旋转和翻折），我们认为这两个岛屿是相同的    
即

11
1	

 1					
11

被认为是相同的



我们可以通过BFS/DFS得到每一个岛屿, 然后把每一个岛屿的形状放到 set 里, 最后 set 的大小就是答案.

关键就是 这么表示 一个岛屿的形状

有两个基本思路

1.记录一个岛屿所有点相对于左上角的点的相对位置.
2.记录一个岛屿的bfs/dfs轨迹


方法1 也有多种实现方法, 比如一个岛屿形状可以用set记录, 也可以将所有点的相对坐标排序后转换成字符串.

方法2 需要注意一个细节: 不能仅仅储存下来dfs/bfs移动的方向, 因为涉及到回溯等问题, 可以加上一定的间隔符, 或者除方向之外额外记录一个位置信息.





import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    static String temp;
    static int cx = 0, cy = 0;
    static Set<String> set = new HashSet<>();

    public static int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    temp = "";							//每次 开始一个新的岛屿的时候 记录该点的位置 然后 dfs中记录走过的路与该点的相对位置
                    cx = i;
                    cy = j;
                    dfs(grid, i, j, visited);
                    set.add(temp);
                }
            }
        }
        return set.size();
    }

    private static void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1 || visited[x][y])
            return;
        //下[1,0] 上[-1,0] 右[0,1] 左[0,-1]
        temp += (x - cx) + " " + (y - cy) + " ";
        visited[x][y] = true;
        dfs(grid, x + 1, y, visited);   // x 是行号 下一行同一列 就是 向下走1
        dfs(grid, x - 1, y, visited);
        dfs(grid, x, y + 1, visited);
        dfs(grid, x, y - 1, visited);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                        {1,1,0,0,0},
                        {1,0,0,0,0},
                        {0,0,0,0,1},
                        {0,0,0,1,1}};
        System.out.println(numDistinctIslands(grid));
    }
}




// example 1
11000
11000
00011
00011
// temp1 = temp2 = "0 0 1 0 1 1 0 1 "


// example 2
11011
10000
00001
11011
// temp1 = "0 0 1 0 0 1 "
// temp2 = "0 0 0 1 "
// temp3 = "0 0 1 0 1 -1 "
// temp4 = "0 0 0 1 "





