L375. Clone Binary Tree

For the given binary tree, return a deep copy of it.


class Solution {
	public TreeNode cloneTree(TreeNode root) {
		if (root == null) return null;
		TreeNode newRoot = new TreeNode(root.val);
		newRoot.left = cloneTree(root.left);
		newRoot.right = cloneTree(root.right);
		return newRoot;
	}
}




