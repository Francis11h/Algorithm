图 基本 

graph representation:

adjacency matrix
adjacency list
edge list	(用于MST kruskal)

 
					adjacency matrix		adjacency list
inspect all edges			O(V^2)					O(E)
Find all edges incident on i 		O(V)					O(k)
Is there an edge from i to j		O(1)					O(logk)



临街表建图 实例 List[], 之后 每个index 都要new出来 list 对象

	List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] pre : prerequisites) {
            int from = pre[1], to = pre[0];
            // add edge for our directed graph
            // there are no duplicate edges in the input prerequisites.
            graph[from].add(to);
            indegree[to]++;
        }
