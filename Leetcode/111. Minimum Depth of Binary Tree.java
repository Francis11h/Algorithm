111. Minimum Depth of Binary Tree

The minimum depth is the number of nodes 
along the shortest path from the root node down to the nearest leaf node.

从 根节点 到 叶子结点 的最短路程

    3
   / \
  9  20
    /  \
   15   7

   =========> 2


    3
     \
     20
    /  \
   15   7

   =========> 3


class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}


class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        
        // leaf node
        if(root.left == null && root.right == null) return 1;
        
        // leaf nodes are in right subtree
        if(root.left == null) return minDepth(root.right) + 1;
        
        // leaf nodes are in left subtree
        if(root.right == null) return minDepth(root.left) + 1;
 
        // left/right subtrees both contains leaf nodes
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left, right) + 1;
    }
}
