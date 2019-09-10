101. Symmetric Tree

is Symmetric
    1
   / \
  2   2
 / \ / \
3  4 4  3

is not 
    1
   / \
  2   2
   \   \
   3    3


怎么判断他是对称的？
关于root对称 可是 怎么量化？？ 


感觉recurison直接传一个root 很难实现 因为对称就要找两个root比	-----> 直接比对称的两个node 这是本题的关键

它俩是不是关于中轴线对称 
如果这两个点是一个点 那必然对称（root）
如果两个点都为null 也对称 （递归的出口）
如果两个点 一个为null 另一个不为 必然不对称
如果这两个点 值相等 并且 A的左孩子 与B的右孩子 A的右孩子与B的左孩子 关于 中心线对称 则 A B 关于轴对称



class Solution {
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }
    //whether node A and B is symmetrical to the root node of the whole tree
    private boolean dfs(TreeNode A, TreeNode B) {
        if (A == null && B == null) return true;
        if (A == null || B == null) return false;
        return (A.val == B.val) && dfs(A.left, B.right) && dfs(A.right, B.left);
        
    }
}

O(N)
O(N)


stack or queue will also be fine

class Solution {
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return true;
        stack.push(root.left);
        stack.push(root.right);
        
        while (!stack.isEmpty()) {
            TreeNode A = stack.pop(), B = stack.pop();
            if (A == null && B == null) continue;
            if (A == null || B == null || A.val != B.val) return false;
            stack.push(A.left);
            stack.push(B.right);
            stack.push(A.right);
            stack.push(B.left);
        }
        return true;
    }
}













