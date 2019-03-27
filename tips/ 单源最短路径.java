单源最短路径

use δ(x, y) to represent the shortest path length from vertex x to vertex y

Shortest paths don’t need to loop.
They can’t be more than V-1 edges. (Otherwise, they’d contain a cycle).

For single-source, the shortest path can be represented via predecessor edges.
	Each vertex (other than the source) can have a predecessor.

Bellman-Ford Algorithm

Expensive, but suitable for graphs with negative edge weights。
		–  Just no cycles with a negative total edge weight.		

	1.Every vertex, v, has a distance estimate =>  v.distance 最开始设为 infinite;	s.distance = 0.

	2.A basic operation of Bellman-Ford is to relax an edge.
		If there’s an x->y edge, And the distance at x plus the cost of that edge is less than the distance at y.
		Update the distance at y, and set the predecessor of y to x.

	3.Relax all the edges again, nothing will change
		At most V-1 rounds relax



Bellman-Ford()
   	Set all predecessors and distance estimates to undefined			O(V)
   	Set source distance to zero
   	# Relax everything enough times to find all shortest paths.
   	for V – 1 iterations												O(V) * O(E)
		for each edge i -> j 
			relax edge i -> j
	# See if there are any edges that could still relax 
	for each edge i -> Just												O(E)
      	if j.distance > i.distance + weight[ i ][ j ]
        	return false
return true

T: O(VE)






但是对 有向无环图 DAG: Shortest Paths in a DAG
	我们可以先确定 relax的方向(谁先谁后) 拓扑排序
	然后一次循环即可

	relax the edges out of each vertex,	In the order of the topological sort.

DAG-Shortest-path()
	topologically sort the vertices										O(V + E)
   	Set all predecessors and distance estimates to undefined
   	Set source distance to zero
   	# Relax all the edges in the order of the topological sort.
   	for each vertex i in topological sort order.						V次循环，总共E条边			
		for each edge i -> j 											O(E)
			relax edge i -> j

O(V+E)






Dijkstra

Works with graphs containing cycles。 图可以有环, 但不能有边的权重是负值。

1.start at the source vertex, at distance 0,
2.Maintains a min priority queue of all vertice
	Extracts the min node from the priority queue
	Relaxes all of its outgoing edges.
3.Repeat

Dijkstra()
	Set all vertex costs and predecessors to undefined.	O( V )
	Q = queue of all vertices, ordered by distance 		
	For source vertex s
	s.distance = 0										O( 1 ) + O( log V )

	while Q is non-empty								O( V ) itera@ons
	    u = remove lowest-distance vertex from Q. 		O( log V )
		for each u -> j edge 							Total of O( E ) itera@ons			
			relax edge u -> j 							O( log V )


T : O(VlogV+ElogV)
Typically O( E log V ) Fibonacci heap








