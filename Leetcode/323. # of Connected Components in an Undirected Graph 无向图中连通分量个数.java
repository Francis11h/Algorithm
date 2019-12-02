323. # of Connected Components in an Undirected Graph 无向图中连通分量个数

连通分量

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2

Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1








bfs&dfs 都不能直接用 edge list 都需要建图 adjacency list + boolean[] visited 来遍历

dfs
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans++;
                dfs(graph, i, visited);
            }
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int i, boolean[] visited) {
        visited[i] = true;
        for (int nei : graph[i]) {
            if (!visited[nei]) {
                dfs(graph, nei, visited);
            }
        }
    }
}

bfs

class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        int ans = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans++;
                bfs(graph, i, visited);
            }
        }
        return ans;
    }

    private void bfs(List<Integer>[] graph, int i, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int nei : graph[now]) {
                if (!visited[nei]) {
                    queue.offer(nei);
                    visited[nei] = true;
                }
            }
        }
    }
}


union find








follow up: 找岛屿的数量， 岛屿的定义是不与任何其它的node连接

visited改成 map 记录 岛屿大小