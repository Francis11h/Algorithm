110. Balanced Binary Tree

验证二叉树 是否平衡

定义:
	左子树平衡 + 右子树平衡 + |左右子树height差| <= 1

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(height(root.left) - height(root.right)) <= 1);
    }
    
    private int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}


方法二 （并不直观）
	基于最大深度
	维护全局变量 boolean isValid 
	从下往上 不断计算子节点的最大深度
	可以 稍微早一点发现 不是 true

public class Solution {
	private boolean isValid = true;

	public boolean isBalanced(TreeNode root) {
	    maxDepth(root);
	    return isValid;
	}

	public int maxDepth(TreeNode root) {
	    if (root == null)
	        return 0;
	    int l = maxDepth(root.left);
	    int r = maxDepth(root.right);
	    if (Math.abs(l - r) > 1)
	        isValid = false;
	    return 1 + Math.max(l, r);
	}
}