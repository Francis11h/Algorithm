import java.util.*;

public class johnsons {

     public static int[] bellmanFord(Graph graph, int source) {

        int vertexNum = graph.adjacencylist.size();
        boolean hasNLoop = false;

        // Initialize the distance to all nodes to be infinity
        // except for the start node which is zero.
        int[] distanceToSource = new int[vertexNum];
        Arrays.fill(distanceToSource, Integer.MAX_VALUE);
        distanceToSource[source] = 0;

        // For each vertex, apply relaxation for all the edges  O(V-1 * E)
        for (int i = 0; i < vertexNum - 1; i++) {
            for (List<Edge> edges : graph.adjacencylist) {
                for (Edge edge : edges) {
                    if (distanceToSource[edge.source] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (distanceToSource[edge.source] + edge.weight < distanceToSource[edge.destination]) {
                        distanceToSource[edge.destination] = distanceToSource[edge.source] + edge.weight;
                    }
                }
            }
        }
        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        for (int i = 0; i < vertexNum - 1 && !hasNLoop; i++)
            for(List<Edge> edges : graph.adjacencylist)
                for (Edge edge : edges)
                    if (distanceToSource[edge.source] + edge.weight < distanceToSource[edge.destination])
                        hasNLoop = true;

        if (hasNLoop) {
            //System.out.printf("There is Negative edge weight cycle\n");
            return null;
        }
        // Return the array containing the shortest distance to every node
//        for (int i = 0; i < vertexNum; i++) {
//            System.out.printf("The cost to get from node %d to %d is %d\n", source, i, distanceToSource[i]);
//        }
        return distanceToSource;

    }


    private static int[][] dijkstra(Graph graph, int source, int[][] answer, int[] h) {
        int VertexNum = graph.vertexNum;

        //记录每个点是否走过
        boolean[] visited = new boolean[VertexNum];

        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.key - o2.key;
            }
        });

        answer[source][source] = 0;

        minHeap.add(new Point(answer[source][source], source));


        while (!minHeap.isEmpty()) {
            Point current = minHeap.poll();
            int vertex = current.value;

            if (!visited[vertex]) {
                visited[vertex] = true;

                List<Edge> list = graph.adjacencylist.get(vertex);

                for (Edge edge : list) {

                    int destination = edge.destination;

                    if (!visited[destination]) {
                        if (answer[source][vertex] != Integer.MAX_VALUE) {
                            int newDist = answer[source][vertex] + edge.weight;
                            int currentDist = answer[source][destination];
                            if (newDist < currentDist) {
                                minHeap.offer(new Point(newDist, destination));
                                answer[source][destination] = newDist;
                            }
                        }
                    }
                }
            }
        }

        for (int j = 0; j < VertexNum; j++) {
            if (answer[source][j] != Integer.MAX_VALUE) {
                answer[source][j] += h[j] - h[source];
            }
        }

        return answer;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            Graph graph = new Graph(V);

            for (int i = 0; i < E; i++) {
                int source = sc.nextInt(), destination = sc.nextInt(), weight = sc.nextInt();
                graph.addEdge(source, destination, weight);
            }
            //graph.printG();

            //加一行，表示新加一个节点
            graph.adjacencylist.add(new LinkedList<>());
            for (int i = 0; i < V; i++) {
                graph.addEdge(V, i, 0);
            }
            //graph.printG();

            //bellmanFord(graph, V);

            int[] bellmanDistance = bellmanFord(graph, V);

            if (bellmanDistance == null) {
                System.out.println("Negative edge weight cycle");
                return;
            }
            //把新加的点去掉
            graph.adjacencylist.remove(graph.adjacencylist.size() - 1);
            //reweight
            for(List<Edge> edges : graph.adjacencylist) {
                for (Edge edge : edges) {
                    edge.weight += bellmanDistance[edge.source] - bellmanDistance[edge.destination];
                }
            }
            //graph.printG();
            //做 Dijkstra

            int[][] answer = new int[V][V];
            for (int i =0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    answer[i][j] = Integer.MAX_VALUE;
                }
            }

//            for (int i = 0; i < V; i++) {
//                for (int j = 0; j < V; j++) {
//                    System.out.printf(" answer[%d][%d] = %d", i, j, answer[i][j]);
//                }
//            }

            //dijkstra(graph, 0, answer, bellmanDistance);

            for (int i = 0; i < V; i++) {
                dijkstra(graph, i, answer, bellmanDistance);
            }

//            for (int i = 0; i < V; i++) {
//                for (int j = 0; j < V; j++) {
//                    System.out.printf(" answer[%d][%d] = %d", i, j, answer[i][j]);
//                }
//            }

            int k = sc.nextInt();
            for (int i = 0; i < k; i++) {
                int from = sc.nextInt(), to = sc.nextInt();
                if (answer[from][to] != Integer.MAX_VALUE) {
                    System.out.printf("%d -> %d = %d\n", from, to, answer[from][to]);
                } else {
                    System.out.printf("%d -> %d = x\n", from, to);
                }
            }
        }
    }
}




class Edge {
    int source;
    int destination;
    int weight;
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {

    int vertexNum;
    List<List<Edge>> adjacencylist;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        adjacencylist = new LinkedList<>();

    //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertexNum; i++) {
            adjacencylist.add(new LinkedList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist.get(source).add(edge); //for directed graph  ; add at first by using addFirst
    }

    public void printG() {
        for (int i = 0; i < adjacencylist.size(); i++) {
            List<Edge> list = adjacencylist.get(i);
            for (int j = 0;j < list.size(); j++) {
                System.out.println("vertex-" + i + " is connected to " +
                        list.get(j).destination + " with weight " +  list.get(j).weight);
            }
        }
    }
}

class Point {
    int key, value;
    Point(int key, int value) {
        this.key = key;
        this.value = value;
    }
    public int getKey() {
        return key;
    }
    public int getValue() {
        return value;
    }
}