1319. 连电脑 Number of Operations to Make Network Connected

https://leetcode.com/problems/number-of-operations-to-make-network-connected/

There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network 
where connections[i] = [a, b] represents a connection between computers a and b. 

Any computer can reach any other computer directly or indirectly through the network.


Given an initial computer network connections. 
You can extract certain cables between two directly connected computers, 
and place them between any pair of disconnected computers to make them directly connected. 

Return the minimum number of times you need to do this in order to make "all the computers connected". 
If it is not possible, return -1. 



Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2




Constraints:

1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
There are no repeated connections.
No two computers are connected by more than one cable.





Hint 1
As long as there are at least (n - 1) connections, there is definitely a way to connect all computers.
Hint 2
Use DFS to determine the number of isolated computer clusters.




就很真实 几个 孤岛 （连通分量） 连几次 就完事儿了
不用管具体怎么连
The key idea is building graph and count number of connected group.

class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections == null || connections.length < n - 1) return -1;
        int part = 0;
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
        	graph[i] = new LinkedList<>();
        }
        for (int[] c : connections) {
        	graph[c[0]].add(c[1]);
        	graph[c[1]].add(c[0]);
        }

        for (int i = 0; i < n; i++) {
        	if (!visited[i]) {
        		part++;
        		dfs(i, graph, visited);
        	}
        }

        return part - 1;
    }

    private void dfs(int u, List<Integer>[] graph, boolean[] visited) {
    	if (visited[u]) return;
    	visited[u] = true;
    	for (int v : graph[u]) {
    		if (!visited[v]) {
    			dfs(v, graph, visited);
    		}
    	}
    }
}

Time: O(n+m), m is the length of connections
Space: O(n)




bfs



class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections == null || connections.length < n - 1) return -1;
        int components = 0;
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
        	graph[i] = new LinkedList<>();
        }
        for (int[] c : connections) {
        	graph[c[0]].add(c[1]);
        	graph[c[1]].add(c[0]);
        }

        for (int i = 0; i < n; i++) {
        	if (!visited[i]) {
        		components++;
        		bfs(i, graph, visited);
        	}
        }
        return components - 1;
    }

    private void bfs(int source, List<Integer>[] graph, boolean[] visited) {
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(source);
        visited[source] = true;
        
    	while (!queue.isEmpty()) {
    		int u = queue.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
    	}
    }
}

Time: O(n+m), m is the length of connections
Space: O(n)








union find


class Solution {
	int[] father = null;

	private int find(int i) {
		if (father[i] == i) return father[i];
		father[i] = find(father[i]);
		return father[i];
	}

    public int makeConnected(int n, int[][] connections) {
        if (connections == null || connections.length < n - 1) return -1;
        father = new int[n];
        for (int i = 0; i < n; i++) {
        	father[i] = i;
        }

        int components = n;
        for (int[] c : connections) {
        	int rootA = find(c[0]), rootB = find(c[1]);
			if (rootA != rootB) {
				father[rootA] = rootB;  //father[rootA] 不是 rootA
				components--;	//合并了 就减一个
			}
        }
        
        return components - 1;
    }
}

Time: O(n+mlogn), m is the length of connections
Space: O(n)
