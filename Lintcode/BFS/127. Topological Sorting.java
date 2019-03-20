拓扑排序 Topological Sorting
三步走

1 要知道每个点的入度为多少
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
        // 第一部分
        //需要知道一个点 对应的 入度 是多少. 建 哈希存储
        //Map 里 存的是入度
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        
        for (DirectedGraphNode node : graph) { //取出图中所有点
            for(DirectedGraphNode nei : node.neighbors) { //取每一个点指向的其他点
                // node -> neighbors , 有一个node指向该点，该点neighbors 入度加一
                if (map.containsKey(nei)) {
                    map.put(nei, map.get(nei) + 1);
                } else { //hash里没有直接存 1 ,因为 该点已经是经由别的点指向得出的
                    map.put(nei, 1);
                }
            }
        }
        
        //第二部分 加入天然的大哥，即入度为0的
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) { //这里是 入度为0的点
                queue.offer(node);  //入队
            }
        }
        
        // 第三部分 BFS 找
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll(); // 出队
            result.add(node); //大哥加入 result
            
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1); //所有该点指向的点的 入度 - 1；
                
                if (map.get(n) == 0) { //可能为0的点，只有当前变化（入度减1的）的点 
                    queue.offer(n);
                }
            }
        }
        return result;
    }
}