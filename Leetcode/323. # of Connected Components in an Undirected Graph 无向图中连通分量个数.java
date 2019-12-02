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

 O(edge + node)


union find 不用建图 遍历 edgelist and union everytime

class Solution {
    private int[] father;
    private int find(int x) {
        if (father[x] != x) {
            father[x] = find(father[x]);
        }
        return father[x];
    }
    private void union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA != rootB) {
            father[rootB] = rootA;
        }
    }
    public int countComponents(int n, int[][] edges) {
        father = new int[n];
        for (int i = 0; i < n; i++) father[i] = i;

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            union(a, b);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (father[i] == i) ans++;
        }
        return ans;
    }
}

Initially, there are n points which means there are n disconnected groups.
Every edge will connect two points. If these two points already connected, then we won't reduce the disconnected groups. Otherwise disconnected groups will reduce by 1.
Then we could think of union find to solve this problem. And time complexity is "O(n + m log n)", space complexity is O(n).
m is the edges.length, and n is how many points.


    优化之 解决尾递归
    private int find(int x) {
        if (father[x] != x) {
            father[x] = find(father[x]);
        }
        return father[x];
    }
    优化后
    private int find(int x) {
        while (father[x] != x) {
            father[x] = father[father[x]];
            x = father[x];
        }
        return x;
    }




follow up: 找岛屿的数量， 岛屿的定义是不与任何其它的node连接

visited改成 map 记录 岛屿大小





I have the similar questions when I learn Union-Find algorithm since lots of problems seem faster if using DFS/BFS over union-find.

DFSvsUF:http://stackoverflow.com/questions/28398101/union-find-or-dfs-which-one-is-better-to-find-connected-component

Union-Find or DFS: which one is better to find connected component?
2.For time complexity, please check Runtime analysis: DFS vs. Union-Find https://cs.stackexchange.com/questions/47596/dfs-vs-union-find-for-computing-connected-components-of-a-static-graph

I quote the conclusions:
a. "For static graph, DFS; Dynamic graph, Union-Find"
b. "That said, union-find is helpful only if edges and vertices are never deleted."
c. "Union-find has an extra multiplicative factor α(V), which makes it slower than DFS asymptotically (and probably practically as well)."