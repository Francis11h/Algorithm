743. Network Delay Time

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), 
                where u is the source node, 
                v is the target node, 
                and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K.

How long will it take for All nodes to receive the signal? 

If it is impossible, return -1.

Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2


Hints:
We visit each node at some time, 
and if that time is better than the fastest time we‘ve reached this node, 
we travel along outgoing edges in sorted order. 
Alternatively, we could use Dijkstra‘s algorithm.



Dijkstra

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        //build graph   
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new LinkedList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        // build minHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        minHeap.offer(new int[] {K, 0});
        // build result map
        Map<Integer, Integer> dist = new HashMap<>();
        // do the dijkstra
        while (!minHeap.isEmpty()) {
            int[] now = minHeap.poll();
            int node = now[0], d = now[1];          // the node the from K to reach, the time that from K to node
            if (!dist.containsKey(node)) {
                dist.put(node, d);
                if (graph.containsKey(node)) {
                    for (int[] edge : graph.get(node)) {
                        int nei = edge[0], d2 = edge[1];
                        if (!dist.containsKey(nei)) {               // add to reduce some redundant calculation, but without is fine
                            minHeap.offer(new int[] {nei, d + d2});
                        }
                    }
                }
            }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int d : dist.values()) {
            ans = Math.max(ans, d);
        }
        return ans;
    }
}











