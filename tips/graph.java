图
BFS

最短路径 determine shortest distance
连通分量	connected component



BFS : Prim最小生成树  Dijkstra单源最短路径

算法需要发现 所有距离 源节点s为k的所有节点之后，才会发现 距离 源节点s为k+1 的其他节点

黑black	: finished exploring
灰gray	: discovered but not yet explored
白white	: never seen this vertex before (have not been disvocered)


BFS(s) {
	set all vertices to white, with distance and predecessor undefined.			linear time in V

	set source vertex : s to gray, with a distance of 0

	enqueue(s)
	while (queue is not empty) {												linear time in V
		u = dequeue();
		for all edges u -> j													total to E
			if j is white(not discovered)
				color j to gray
				set j.dist 	to 	u.dist + 1
				make u the predecessor of j
				enqueue(j);
		color u to black;
	}
}

初始化为白色之后，不会给任何节点涂上白色
每个节点 最多入队一次（所以也就对应最多出队一次）				O(V)
算法只在一个节点 出队 的时候 才对该节点的邻接链表扫描, 所有邻接链表的长度之和（边）为E 	O(E)
So BFS O(V + E)

