 Critical Routers

AWS wants to increase the reliability of their network by upgrading crucial data center routers.
Each data center has a single router that is connected to every other data center through a direct link or some other data center.

To increase the fault tolerance of the network, AWS wants to identify routers which would result in a disconnected network if they went down and replace then with upgraded versions.

Write an algorithm to identify all such routers.


Input:

The input to the function/method consists of three arguments:

	numRouters, an integer representing the number of routers in the data center.
	numLinks, an integer representing the number of links between the pair of routers.
	Links, the list of pair of integers - A, B representing a link between the routers A and B. The network will be connected.

Return a list of integers representing the routers with need to be connected to the network all the time.


Input:

numRouters = 7
numLinks = 7
Links = [[0,1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3,4]]

Output:

[2, 3, 5]





the outputs are edges (same as 1192) rather than vertices. 
For each output edge, smaller vertex before the larger














import java.util.*;

public class CriticalConnections {

    static int time = 1;
    public static List<List<Integer>> criticalConnections(List<List<Integer>> connections, int n11, int n) {
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

        Set<Integer> V = new HashSet<>();

        List<List<Integer>> E = new ArrayList<>();
        // do dfs
        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                dfs(graph, dfn, low, parent, i, V, E);
            }
        }
        System.out.println("V is " + V.toString());
        return E;
    }

    private static void dfs(List<Integer>[] graph, int[] dfn, int[] low, int[] parent, int cur, Set<Integer> V, List<List<Integer>> E) {
         int children = 0;   // handle the root node
        dfn[cur] = low[cur] = time++;
        // time++;
        // all of its neighbours
        for (int nei : graph[cur]) {
            if (dfn[nei] == -1) {       //if nei has not been discovered, do dfs
                 children++;
                parent[nei] = cur;
                dfs(graph, dfn, low, parent, nei,V, E);
                low[cur] = Math.min(low[cur], low[nei]);
                 if ((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= dfn[cur]))
                     V.add(cur);
                // corner case : cur is root + cur is not the root
                if ((parent[cur] == -1 && low[nei] > dfn[0]) || (parent[cur] != -1 && low[nei] > dfn[cur]))
                    E.add(Arrays.asList(cur, nei));

            } else if (nei != parent[cur]) {    //backtracking and update low[] indicates that the lowest node it can reach without going through its parent node
                low[cur] = Math.min(low[cur], dfn[nei]);
            }
        }
    }
    public static List<List<Integer>> change(int[][] lists)  {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] list : lists) {
            List<Integer> temp = new ArrayList<>();
            for (int a : list) {
                temp.add(a);
            }
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }

    public static void main(String[] args) {
        int numRouters1 = 5;
        int numLinks1 = 6;
        int[][] links1 = {{0, 1}, {1, 2}, {0, 2}, {2, 3}, {2, 4}, {3, 4}};
        System.out.println(criticalConnections(change(links1), numLinks1, numRouters1).toString());

        int numRouters2 = 5;
        int numLinks2 = 5;
        int[][] links2 = {{0, 1}, {1, 2}, {0, 2}, {0, 3}, {3, 4}};
        System.out.println(criticalConnections(change(links2), numLinks2, numRouters2));

        int numRouters3 = 4;
        int numLinks3 = 3;
        int[][] links3 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println(criticalConnections(change(links3), numLinks3, numRouters3));

        int numRouters4 = 7;
        int numLinks4 = 7;
        int[][] links4 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
        System.out.println(criticalConnections(change(links4), numLinks4, numRouters4));

        int numRouters5 = 4;
        int numLinks5 = 4;
        int[][] links5 = {{0, 1}, {0, 2}, {0, 3}};
        System.out.println(criticalConnections(change(links5), numLinks5, numRouters5));

        int numRouters6 = 2;
        int numLinks6 =  4;
        int[][] links6 = {{1, 0}};
        System.out.println(criticalConnections(change(links6), numLinks6, numRouters6).toString());
    }
}

