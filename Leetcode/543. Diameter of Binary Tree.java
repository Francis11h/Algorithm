543. Diameter of Binary Tree

The diameter of a binary tree is 
the length of the longest path between Any two nodes in a tree. 
This path may or may Not pass through the root.

          1
         / \
        2   3
       / \     
      4   5  

      return 3 , which is the length of the path [4,2,1,3] or [5,2,1,3].


对每一个节点，先dfs求其深度 => 需要dfs其左右两孩子节点的深度
而 过这一点的 最长路径长 = 其左子树的最大深度+ 其右子树的最大深度
=>  维护一个全局变量 diameter 每次dfs 判断是否需要修改其即可



// convert to calculate the depth of the leaves 
// cause the longest path must be one leaf to another leaf
// so can we just calculate the deepest leaf on each side of root? no
// but we need to calculate every node! careful, every! and modify the global val of diameter
// the path is the depth of the left subtree plus the depth of the right subtree


class Solution {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }
    
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}

time complexity : O(N) we visted every node once.
space complexity : the size of our implicit call stack during our dfs
