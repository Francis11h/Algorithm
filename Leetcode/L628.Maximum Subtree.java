给你一棵二叉树，找二叉树中的一棵子树，他的所有节点之和最大
返回这棵子树的根节点


Input:
{1,-5,2,0,3,-4,-5}
Output:3

The tree is look like this:
     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
The sum of subtree 3 (only one node) is the maximum. So we return 3.




bottom-up recurison
what we need? ------> the node and the sum of the subtree ---> global variable
what parent need from children ? the sum of the subtree ---> dfs return the sum of the subtree

class Solution {

	TreeNode node = null;
	int max = Integer.MIN_VALUE;

    /**
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public TreeNode findSubtree(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        return node;
    }
    // return the sum of the subtree whose root is root
    private int dfs(TreeNode root) {
    	if (root == null) return 0;
    	int sum = dfs(root.left) + dfs(root.right) + root.val;
    	// 不用再把 left right 分别列出来 因为 返回的是 int 就 怎么都会走的
    	// int left = dfs(root.left);
    	// int right = dfs(root.right);
    	// int sum = left + right + root.val;
    	if (node == null || max < sum) {	// node == null for corner case {-2147483648}
    		max = sum;
    		node = root;
    	}
    	return sum;
    }
}




