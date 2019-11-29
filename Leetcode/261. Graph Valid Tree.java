261. Graph Valid Tree

判断一个图 是否是树

Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.




a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”









解法 1
    什么时候图是合法的树？1. N 个顶点正好 N-1 条边 2.全部连通
    即 建图 + 某一点开始 bfs, 最后判断 visited vertex set 是不是有n个点即可


class Solution {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    public boolean validTree(int n, int[][] edges) {
        // must have n-1 edges
        if (n == 0 || edges.length != n - 1) return false;
        buildGraph(n, edges);
        // do bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nei : map.get(cur)) {
                if (!visited.contains(nei)) {
                    queue.offer(nei);
                    visited.add(nei);
                }
            }
        }

        return visited.size() == n;     //  must have n-1 edges
    }

    private void buildGraph(int n, int[][] edges) {
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
    }
}


判断一个图是否有环 https://www.cnblogs.com/TenosDoIt/p/3644225.html









