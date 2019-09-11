111. Minimum Depth of Binary Tree

The minimum depth is the number of nodes 
along the shortest path from the Root node down to the Nearest Leaf node.

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

错误解法
这么写 不符合本题题意 因为 要求 root-to-leaf 
corner case : 
    3
   / 
  9 
这个 minDepth 应该是 2 不是 1, 因为 3 右边 无leaf 所以 不能算 root-to-leaf

错误解法
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}


正确解法
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left == 0 || right == 0 ? left + right + 1 : Math.min(left, right) + 1;
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
