拓扑排序 Topological Sorting
三步走

1 要知道每个点的入度为多少  入度:indegree
    Map<节点, 入度>
2 从 DAG中选出入度为0的点, 入队
    入度为0的点 : 不在 Map中的点
3 删除该点, 及从该点出发指向其他点的边, 再把新的 入度为0的点加入队列
    新的点 : Map中 value为0的点




拓扑排序 是针对 有向无环图的 = Directed Acyclic Graph (DAG)

有向图(节点) 定义 :
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors; //每个节点用一个ArrayList来存它指向的节点
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}




public class Solution {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode nei : node.neighbors) {
                if (map.containsKey(nei)) {
                    map.put(nei, map.get(nei) + 1);
                } else {
                    map.put(nei, 1);
                }
            }
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                queue.offer(node);
            }
        }
        
        while (!queue.isEmpty()) {
            DirectedGraphNode tmp = queue.poll();
            result.add(tmp);
            
            for (DirectedGraphNode nei : tmp.neighbors) {
                map.put(nei, map.get(nei) - 1);
                if (map.get(nei) == 0) {
                    queue.offer(nei);
                }
            } 
        }
        return result;
    }
}
