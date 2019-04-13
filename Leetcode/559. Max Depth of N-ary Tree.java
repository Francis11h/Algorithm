559. Max Depth of N-ary Tree

N叉树最大深度

遍历每个节点所有孩子，找出其孩子的最大深度 再 +1

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};



class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int maxChildrenHeight = 0;
        for (Node child : root.children) {
            maxChildrenHeight = Math.max(maxChildrenHeight, maxDepth(child));
        }
        return maxChildrenHeight + 1;
    }
}