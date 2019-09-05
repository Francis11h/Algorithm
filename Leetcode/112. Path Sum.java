112. Path Sum


     '5'
     / \
   '4'  8
   /   / \
 '11' 13  4
 /  \      \
7   '2'     1

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


// root-to-leaf path
// one point is that the path must end at leaf 

// what info that we need to get from our children?
// dfs() return whether one root is satisfy
// outdoor of dfs is the node is a leaf and equal to modified sum


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        // if the node is leaf and can satisfy the sum now, we found! 
        if (root.left == null && root.right == null && root.val == sum)    return true;
        // find through our left & right child and sum should minus node.val
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

T:O(N) we visit each node exactly once
S : worst O(N) tree is a list;  normal O(logN)






iterations  Using a stack to implement DFS
			for not to modify the original tree use another stack to store the current target value


class Solution {
	public boolean hasPathSum(TreeNode root, int sum) {
		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> sums = new Stack<>();
		stack.push(root);
		sums.push(sum);

		while (!stack.isEmpty() && root != null) {
			TreeNode node = stack.pop();
			int value = sums.pop();
			if (node.left == null && node.right == null && node.val == value) return true;
			if (node.left != null) {
				stack.push(node.left);
				sums.push(value - node.val);
			}
			if (node.right != null) {
				stack.push(node.right);
				sums.push(value - node.val);
			}
		}
		return false;
	}
}








