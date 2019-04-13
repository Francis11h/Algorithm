104. Maximum Depth of Binary Tree

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

T: O(N)
S: worst : O(N)  avg: O(logN)


非递归:
 层序遍历 bfs 每一层level++