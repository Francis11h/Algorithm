import java.util.*;

//start with a vertex, and grow the tree by greedily adding in low-cost edges.

//将权重作为邻接链表中的每个节点对象的属性

//Prim()
//        Set all vertex costs and predecessors to undefined
//        Q = queue of all vertices, ordered by cost.    			一个关于Vertex的最小堆
//
//        choose some root vertex r
//        r.cost = 0
//
//        while Q is non-empty							O( V ) iterations
//        u = remove lowest-cost vertex from Q 				O(log V) for each remove.
//        add u to the tree via its predecessor
//        for each u -> j edge
//        If j is in Q and weight[u][j] < j.cost
//        make u the predecessor of j
//        j.cost = weight[u][j]

public class prim {

    private static int[] prim(Graph graph, int branchingFactor) {
        int[] ans = new int[2];

        int numOfTree = 0;
        int totalWeight = 0;

        int VertexNum = graph.vertexNum;
        boolean[] visited = new boolean[VertexNum];

        for (int i = 0; i < VertexNum; i++) {
            if (visited[i]) {
                continue;
            }
            numOfTree++;
            Heap minHeap = new Heap(branchingFactor);
            Vertex current = graph.listArray.get(i);

            int index = i;
            int tmpMin = 0;

            while (current != null && !visited[index]) {
                visited[index] = true;
                totalWeight += tmpMin;

                while (current != null) {
                    if (!visited[current.vDes]) {
                        minHeap.insertValue(new Point(current.weight, current.vDes));
                    }
                    current = current.next;
                }
                while (!minHeap.isEmpty() && visited[minHeap.getFirstValue()]) {
                    minHeap.removeMin();
                }

                if (!minHeap.isEmpty()) {
                    index = minHeap.getFirstValue();
                    current = graph.listArray.get(index);
                    tmpMin = minHeap.getFirstKey();
                }
            }
        }
        ans[0] = numOfTree;
        ans[1] = totalWeight;
        return ans;


    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            int branchingFactor = 2;
            while (branchingFactor * V < E) {
                branchingFactor = branchingFactor << 1;
            }

            Graph graph = new Graph(V);
            for (int i = 0; i < E; i++) {
                int v = sc.nextInt(), d = sc.nextInt(), w = sc.nextInt();
                graph.addEdge(v, d, w);
                graph.addEdge(d, v, w);
            }
            //graph.printG();

            int[] ans = prim(graph, branchingFactor);
            System.out.println(branchingFactor + " " +  ans[0] + " " + ans[1]);
        }
    }
}



class Heap {
    private static ArrayList<Point> A = new ArrayList<>();
    private static int x;             //power of 2 : bitShifting

    public Heap(int x) {
        this.x = x;
    }
    public  void insertValue(Point p) {
        A.add(p);
        int k = A.size() - 1;
        if (k == 0) {
            return;
        }
        while (k > 0) {                                   // shiftup
            int i = ((k + (1 << x) - 1) >> x) - 1;       //find k's parent
            if (A.get(k).key < A.get(i).key) {
                Point tmp = A.get(i);
                A.set(i, A.get(k));
                A.set(k, tmp);
            } else {
                break;
            }
            k = i;                                      // point to its parent node, then shiftup
        }
    }

    public void removeMin() {                   //remove the top element on the heap(min element)
        if (A.size() == 0) {
            System.out.println("heap is empty");
            return;
        }

        if (A.size() == 1) {
            A.remove(0);
        } else {
            A.set(0, A.get(A.size() - 1));              //copy the last element to the top
            A.remove(A.size() - 1);
            boolean needHeapify = true;
            int i = 0;                                  //the index of the element need to be shiftdown

            while (needHeapify) {                       //i has child   i <= (A.size() >> x) - 1
                int minChildIndex = 1;                  // minChildIndex
                needHeapify = false;
                // traverse the child element of current element and find the min child
                for (int k = 2; k <= (1 << x); k++) {
                    int j = (i << x) + k;               // j : currentChildIndex
                    if (j >= A.size()) break;
                    if (A.get((i << x) + minChildIndex).key > A.get(j).key) {
                        minChildIndex = k;
                    }
                }

                if (((i << x) + 1) >= A.size()) {
                    break;
                }

                int currentMinChildIndex = (i << x) + minChildIndex;

                if (A.get(currentMinChildIndex).key < A.get(i).key) {
                    // swap current element with child element
                    needHeapify = true;
                    Point temp = A.get(i);
                    A.set(i, A.get(currentMinChildIndex));
                    A.set(currentMinChildIndex, temp);
                }
                i = currentMinChildIndex;
            }
        }

    }


    public int getFirstKey() {
        return A.get(0).key;
    }

    public int getFirstValue() {
        return A.get(0).value;
    }

    public boolean isEmpty() {
        return A.isEmpty();
    }
}

// key = cost
// value = index of vertex

class Point {
    int key, value;
    Point(int key, int value) {
        this.key = key;
        this.value = value;
    }
}



class Graph {
    int vertexNum;
    List<Vertex> listArray;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
        listArray = new ArrayList<>(vertexNum);
        for (int i = 0; i < vertexNum; i++) {
            listArray.add(null);
        }
    }

    public void addEdge(int v, int d, int w) {
        Vertex newVertex = new Vertex(d, w);
        newVertex.next = listArray.get(v);
        listArray.set(v, newVertex);
    }
    /*
    public void printG() {
        for(int i = 0; i < vertexNum; i++) {
            System.out.print("v: " + i + "-> ");
            Vertex curr = listArray.get(i);
            while(curr != null) {
                System.out.println("(" + curr.vDes + ", " + curr.weight + ")");
                curr = curr.next;
            }
        }
    }*/
}

class Vertex{
    int vDes;
    int weight;
    Vertex next;
    Vertex(int vDes, int weight){
        this.vDes = vDes;
        this.weight = weight;
        next = null;
    }
}
