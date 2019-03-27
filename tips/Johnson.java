Floyd-warshall

















Johnson

在 O(V^2*logV + VE) 的时间内 找到所有节点对儿之间的最短路径
用了 斐波那契堆最小优先队列

运行中 要 使用 Dijkstra 和 bellman-ford 作为自己的子程序

他运用的技术叫 重新赋予权重
Re-Weighting Edges
	1. eliminates the negative ones
	2. doesn’t change any of the shortest paths

Add a new source vertex, S. With edge-weight of 0 to every other vertex.
新增一个源顶点 ，并使其与所有顶点连通，新边赋权值为 0

接下来重新计算新增顶点到其它顶点的最短路径，利用单源最短路径算法，图中存在负权重节点，使用bellman ford算法
计算新增节点到其它节点的最短路径 h[]，然后使用如下公式对所有边的权值进行 "re-weight"

w(u, v) = w(u, v) + (h[u] - h[v]).

现在除新增结点外，其它结点的相关边权重值都已经为正数了，可以将新增结点删除，对其它结点使用Dijkstra 算法了。

Johnson()
	Add a new vertex, s, with 0-weight edges to every other vertex.
	Bellman-Ford from s, computing distance h(v) Complain if there’s a negative-weight cycle.
	remove s and its edges (if you want) 
	Re-weight all edges
		w( i, j ) += h( i ) – h( j )
	For each vertex v
		Run Dijkstra’s algorithm with source v
