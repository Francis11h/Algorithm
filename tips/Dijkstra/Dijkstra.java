

//没有判断 是否有负数权重

import java.util.*;
public class Dijkstra {

    private static int[] dijkstra(Graph graph, int source, int[] answer) {
        int VertexNum = graph.vertexNum;

        //记录每个点是否走过
        boolean[] visited = new boolean[VertexNum];

        PriorityQueue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.key - o2.key;
            }
        });

        answer[source] = 0;

        minHeap.add(new Point(answer[source], source));

        while (!minHeap.isEmpty()) {
            Point current = minHeap.poll();
            int vertex = current.value;

            if (!visited[vertex]) {
                visited[vertex] = true;

                List<Edge> list = graph.adjacencylist.get(vertex);

                for (Edge edge : list) {

                    int destination = edge.destination;

                    if (!visited[destination]) {
                        if (answer[vertex] != Integer.MAX_VALUE) {
                            int newDist = answer[vertex] + edge.weight;
                            int currentDist = answer[destination];
                            if (newDist < currentDist) {
                                minHeap.offer(new Point(newDist, destination));
                                answer[destination] = newDist;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < VertexNum; i++) {
            System.out.printf("%d shortest dist to %d is %s\n", source, i, answer[i]);
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
            graph.printG();

            //做 Dijkstra
            int[] answer = new int[V];
            for (int i =0; i < V; i++) {
                answer[i] = Integer.MAX_VALUE;
            }

            dijkstra(graph, 0, answer);
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