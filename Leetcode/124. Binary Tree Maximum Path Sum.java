124. Binary Tree Maximum Path Sum

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.
路径可以不经过整棵树的根节点, 可以从任意位置开始结束, 最小为一个node

Input: [1,2,3]

       1
      / \
     2   3

Output: 6


Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42


想法: 肯定是 遍历了。
    遍历什么, 维护什么 子函数返回什么

    遍历每个节点  维护一个maxPathSum      返回的是 每个点开始 直下的路径的和的最大值
    为什么返回这个 因为要求经过某点的 路径的最大值, 需要知道 从该点 两个孩子开始的 直下的路径的最大值, 再和 0 比
        取比0大的边(可能左右都要, 可能只要一边, 可能都不要) 再加上 root.val  = now, 就是 经过这个点的 路径的最大值
        再拿 now 和 全局变量 pathSum 比 取大的

    dfs 返回的是 每个点开始 直下的路径的最大值 只能取 左右大的一边 要是两边都小于0 就都不取。
    

class Solution {
    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        int now = Math.max(left, 0) + root.val + Math.max(right, 0);
        maxPathSum = Math.max(maxPathSum, now);
        
        return Math.max(Math.max(left, 0), Math.max(right, 0)) + root.val;
    }
}


不用全局变量, 传一个数组进去



class Solution {
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        int[] maxPath = new int[] {Integer.MIN_VALUE};
        dfs(root, maxPath);
        return maxPath[0];
    }

    private int dfs(TreeNode root, int[] maxPath) {

        int left = root.left != null ? Math.max(dfs(root.left, maxPath), 0) : 0;
        int right = root.right != null ? Math.max(dfs(root.right, maxPath), 0) : 0;

        int now = left + root.val + right;
        maxPath[0] = Math.max(maxPath[0], now);
        
        return Math.max(left, right) + root.val;
    }
}















