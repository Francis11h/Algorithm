428. Serialize and Deserialize N-ary Tree

基本思路 同 297. Serialize and Deserialize Binary Tree 

但是 最重要的 一个 关键 不同 就是 不知道 N 是 多少！！！！

---->  这怎么办 ？？？ 不需要关心 把每一层大小  记录下来 
        同时 就不用 放 占位符 "#" 来表示 null 了 因为 size 已经知道了 就 可以 重构了



/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serial(root, sb);
        return sb.toString();
    }
    
    private void serial(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        } else {
            sb.append(root.val + ",");
            sb.append(root.children.size() + ",");
            for (Node child : root.children) {
                serial(child, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(",");
        List<String> nodeList = new LinkedList<>(Arrays.asList(nodes));
        return deserial(nodeList);
    }
    
    private Node deserial(List<String> nodes) {
        
        Node node = new Node(Integer.parseInt(nodes.get(0)), new ArrayList<>());
        nodes.remove(0);
        int size = Integer.parseInt(nodes.get(0));
        nodes.remove(0);
        for (int i  = 0; i < size; i++) {
            node.children.add(deserial(nodes));
        }
        return node;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

