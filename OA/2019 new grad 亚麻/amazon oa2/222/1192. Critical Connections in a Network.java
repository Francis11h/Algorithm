1192. Critical Connections in a Network

There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

如果 这条路径删去 有的server 就变得不可达了


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.



hzr 最初步 想法 找所有入度 为1的点 把该点连的线 拿出来即可

错误❌

反面例子 6 [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]]
即 两个连通块儿 然后 分别由一个 connection 连接, 连接点的入度 不一定为1, 此反例为3

所以感觉 仅仅拿 入度 的 个数 和 奇偶 并不能准确知道  需要转换思路

--->

感觉有点像 union find

---->
想一下暴力的解决办法 :
	去掉一个点 / 边 , 在对图进行 dfs遍历 如果连通分量增加 那么该顶点就是 割点/边

	在具体的代码实现中，并不需要真正删除该顶点和删除依附于该顶点所有边。
	对于割点，我们只需要在DFS前，将该顶点对应是否已访问的标记置为ture，然后从其它顶点为根进行DFS即可。
	对于割边，我们只需要禁止从这条边进行DFS后，如果联通分量增加了，那么这条边就是割边。

---> hint : Use Tarjan‘s algorithm.

判断一个顶点是不是割点除了从定义, 还可以从DFS（深度优先遍历）的角度出发

假设DFS中我们从 顶点U访问到了顶点V u->v（此时顶点V还未被访问过）, 那么我们称顶点U为顶点V的父顶点 parent，V为U的孩子顶点 child。
在顶点U之前被访问过的顶点，我们就称之为U的祖先顶点 Ancestor node。

显然如果 顶点U的所有孩子顶点 可以 不通过父顶点U 而访问到U的祖先顶点
那么说明此时去掉顶点U不影响图的连通性, U就不是割点

相反,如果顶点U至少存在一个孩子顶点, 必须通过父顶点U 才能访问到 U的祖先顶点
那么去掉顶点U后, 顶点U的祖先顶点和孩子顶点就不连通了, 说明U是一个割点。

root 要单独判断


具体实现Tarjan算法上，我们需要在DFS（深度优先遍历）中，额外定义三个数组

dfn[]	下标表示 ---> 顶点的编号. 数组中值----> 该顶点在dfs中的遍历顺序(时间戳)
		每访问到 一个未访问过的顶点 访问顺序的值就增加1, 子顶点的dfn值一定比父顶点的dfn值大(但比一定恰好大1 比如父节点有2个及以上分枝)
		每访问一个节点后 它的dfn值就定下来了 不会再改变

low[]	下标表示 ---> 顶点的编号. 数组中值----> 该顶点不通过父顶点 能访问到的 祖先顶点中 最小的顺序值
		每个顶点最初的 low值和dfn值应该一样, 在dfs中我们根据情况不断更新low值
		假设 由 u->v 我们从 v "回溯" 到u 时 如果 dfn[v] < low[u] 那么 low[u] = dfn[v]
		(所有的 更新low 都是在回溯的时候更新 即 此路不通 需要往回倒dfs的时候更新)
		如果顶点u还有其他分支 每个分支回溯时都进行上述操作 
		那么 low[u] 就表示了 u 不通过 u的父节点 所能访问到的 最早的祖先节点

parent[] 下标表示 ---> 顶点的编号. 数组中值----> 该顶点的父顶点的编号 
		 它主要用于更新low值的时候排除父节点 (可以用其他方法代替)


然后 做完dfs后 三个数组值都确定了 就该判断了

割点 :
	判断顶点u是否为割点 用u顶点的dfn值 : dfn[u]
	和它所有孩子的 low值比较 
	如果存在至少一个 孩子 满足 low[v] >= dfn[u] 就说明该孩子 必须通过u 才可能访问u的祖先顶点  则u就是一个割点
	 


割边(桥) : 如果 low[v] > dfn[u] 则说明 v-u是桥
			没有等号 即说明 v不通过u 连u都到不了 更别说u的祖先了 即u-v是桥
			如果 low[v] <= dfn[u] 的话说明 不通过 u-v 这个边 v还是能到u or u的祖先 则u-v这条边就不重要 不是桥
















class Solution {
    static int time = 1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // initialize the graph with nodes
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        //build graph
        for (List<Integer> edge : connections) {
            int a = edge.get(0), b = edge.get(1);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // build auxiliary array
        int[] dfn = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dfn, -1);   //means this vertex has not been discovered
        Arrays.fill(parent, -1);   //means not update its parent
        //answer
        // Set<Integer> V = new HashSet<>();
        List<List<Integer>> E = new ArrayList<>();
        // do dfs
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                dfs(graph, dfn, low, parent, i, E);
            }
        }
        return E;
    }
    
    private void dfs(Map<Integer, Set<Integer>> graph, int[] dfn, int[] low, int[] parent, int cur, List<List<Integer>> E) {
        // int children = 0;   // handle the root node 
        dfn[cur] = low[cur] = time++;
        // time++;
        // all of its neighbours
        for (int nei : graph.get(cur)) {
            if (dfn[nei] == -1) {       //if nei has not been discovered, do dfs
                // children++;
                parent[nei] = cur;
                dfs(graph, dfn, low, parent, nei, E);
                low[cur] = Math.min(low[cur], low[nei]);
                // if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= dfn[cur]))
                //     V.add(cur);
                //cur is not the root
                if (parent[cur] != -1 && low[nei] > dfn[cur])
                    E.add(Arrays.asList(cur, nei));
                // corner case : cur is root 
                if (parent[cur] == -1 && low[nei] > dfn[0]) 
                    E.add(Arrays.asList(cur, nei));
                
            } else if (nei != parent[cur]) {    //backtracking and update low[] indicates that the lowest node it can reach without going through its parent node
                low[cur] = Math.min(low[cur], dfn[nei]);
            }
        }
    }
}



通过的版本 用数组代替 hashMap来建图
用hashMap会TLE 因为 hashmap in java se 8 use red-black tree implementation. 
					Even with a array-base hashmap, if initial size is not specified, Rehashing also add more than constant time complexity. 


class Solution {
    static int time = 1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // initialize the graph with nodes
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        //build graph
        for (List<Integer> edge : connections) {
            int a = edge.get(0), b = edge.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }
        // build auxiliary array
        int[] dfn = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dfn, -1);   //means this vertex has not been discovered
        Arrays.fill(parent, -1);   //means not update its parent
        //answer
        // Set<Integer> V = new HashSet<>();
        List<List<Integer>> E = new ArrayList<>();
        // do dfs
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                dfs(graph, dfn, low, parent, i, E);
            }
        }
        return E;
    }
    
    private void dfs(List<Integer>[] graph, int[] dfn, int[] low, int[] parent, int cur, List<List<Integer>> E) {
        // int children = 0;   // handle the root node 
        dfn[cur] = low[cur] = time++;
        // time++;
        // all of its neighbours
        for (int nei : graph[cur]) {
            if (dfn[nei] == -1) {       //if nei has not been discovered, do dfs
                // children++;
                parent[nei] = cur;
                dfs(graph, dfn, low, parent, nei, E);
                low[cur] = Math.min(low[cur], low[nei]);
                // if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= dfn[cur]))
                //     V.add(cur);
                // corner case : cur is root + cur is not the root
                if ((parent[cur] == -1 && low[nei] > dfn[0]) || (parent[cur] != -1 && low[nei] > dfn[cur]))
                    E.add(Arrays.asList(cur, nei));
                
            } else if (nei != parent[cur]) {    //backtracking and update low[] indicates that the lowest node it can reach without going through its parent node
                low[cur] = Math.min(low[cur], dfn[nei]);
            }
        }
    }
}


// critical connections 要注意 
// 1. index是从1开始的 2. 我把PairInt加进result list里的时候 确保first小于second (不用sort result

// node和edge数量可能<= 0 ，会出现runtime error.
// 然后return时候PairInt的 first必须小于second. 不需要check是否和原来输入的edge的first second顺序一样。

https://leetcode.com/problems/critical-connections-in-a-network/discuss/382632/Java-implementation-of-Tarjan-Algorithm-with-explanation


https://www.cnblogs.com/nullzx/p/7968110.html


https://leetcode.com/discuss/interview-question/417213/



