133. Clone Graph


就是 很简单的 BFS + HashMap HashMap key 是原来的node value是新node



Given a reference of a node in a connected undirected graph, 
return a deep copy (clone) of the graph. 
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors





/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Node newNode = new Node(node.val, new ArrayList<>());

        Map<Node, Node> map = new HashMap(); 
        map.put(node, newNode);
        
        Queue<Node> queue = new LinkedList<>();
                        //new ArrayDeque(); // origin nodes
        queue.add(node);
        // do BFS and every traversal, use a hashMap to map the old node to the new node
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nei : cur.neighbors) {
                if (!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.val, new ArrayList<>()));
                    queue.offer(nei);
                }
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        return newNode;
    }
}




