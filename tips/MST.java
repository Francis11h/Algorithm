Minimum-Weight Spanning Trees

Given a weighted, undirected graph
	choose a subset of the edges
	That keeps the graph connected – with minimum total weight.

Number of edges will be V – 1, if the graph is connected


Prim’s algorithm : 
	start with a vertex, and grow the tree by greedily adding in low-cost edges.

Kruskal’s algorithm : 
	gradually add low-cost edges as long as they’re not redundant.




Prim()
  	Set all vertex costs and predecessors to undefined
   	Q = queue of all vertices, ordered by cost.    			一个关于Vertex的最小堆

	choose some root vertex r
	r.cost = 0

	while Q is non-empty									O( V ) iterations
	    u = remove lowest-cost vertex from Q 				O(log V) for each remove.
      	add u to the tree via its predecessor
		for each u -> j edge
		If j is in Q and weight[u][j] < j.cost
		    make u the predecessor of j
		    j.cost = weight[u][j]


T: O( V ) + O( V log V ) + O( E ) + O( E log V )


O(VlogV+ElogV)=O(ElogV)

fibnoacci heap
O( E + V log V )




Kruskal()
	Make a disjoint-set element for each vertex
	Sort the edges by weight 							O( E log E )

	For each of the sorted edges u <--> j 				O( E ) iteraEon
		if find(u) != find(j)							Amortized O( α( V ) )
		union( u, j )
		add u <-->edge to the tree

O( V ) + O( E log E ) + O( E α( V ) )
simplified: O( E log E )
logE ≤ V^2
So O(ElogE)=O(ElogV2 )=O(ElogV) (Same as prim’s without the Fibonacci heap refinement)

