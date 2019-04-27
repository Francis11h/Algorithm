import java.util.*;

public class Bellman {

    public static int[] bellmanFord(Graph graph, int source) {
        int vertexNum = graph.vertexNum;
        boolean hasNLoop = false;
        // Initialize the distance to all nodes to be infinity
        // except for the start node which is zero.
        int[] distanceToSource = new int[vertexNum];
        Arrays.fill(distanceToSource, Integer.MAX_VALUE);
        distanceToSource[source] = 0;

        // For each vertex, apply relaxation for all the edges  O(V-1 * E)
        for (int i = 0; i < vertexNum - 1; i++) {
            for (LinkedList<Edge> edges : graph.adjacencylist) {
                for (Edge edge : edges) {
                    if (distanceToSource[edge.source] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (distanceToSource[edge.source] + edge.weight < distanceToSource[edge.destination]) {
//                        System.out.println(distanceToSource[edge.source]);
                        distanceToSource[edge.destination] = distanceToSource[edge.source] + edge.weight;
                    }
                }
            }
        }
        // Run algorithm a second time to detect which nodes are part
        // of a negative cycle. A negative cycle has occurred if we
        // can find a better path beyond the optimal solution.
        for (int i = 0; i < vertexNum - 1 && !hasNLoop; i++)
            for(LinkedList<Edge> edges : graph.adjacencylist)
                for (Edge edge : edges)
                    if (distanceToSource[edge.source] + edge.weight < distanceToSource[edge.destination])
                        hasNLoop = true;

        if (hasNLoop) {
            System.out.printf("Negative loop\n");
            return null;
        }
        // Return the array containing the shortest distance to every node
        for (int i = 0; i < vertexNum; i++) {
            System.out.printf("The cost to get from node %d to %d is %d\n", source, i, distanceToSource[i]);
        }
        return distanceToSource;

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
            graph.printG();

            int[] bellmanDistance = bellmanFord(graph, 0);

            if (bellmanDistance == null) {
                System.out.println("hasNLoop");
                return;
            }
//
//            //reweight
//            for(LinkedList<Edge> edges : graph.adjacencylist) {
//                for (Edge edge : edges) {
//                    edge.weight += bellmanDistance[edge.source] - bellmanDistance[edge.destination];
//                }
//            }
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
    LinkedList<Edge>[] adjacencylist;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        adjacencylist = new LinkedList[vertexNum];

        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertexNum; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge); //for directed graph  ; add at first by using addFirst
    }

    public void printG() {
        for (int i = 0; i < vertexNum; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0;j < list.size(); j++) {
                System.out.println("vertex-" + i + " is connected to " +
                        list.get(j).destination + " with weight " +  list.get(j).weight);
            }
        }
    }
}
