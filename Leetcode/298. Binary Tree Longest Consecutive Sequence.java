298. Binary Tree Longest Consecutive Sequence


Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).



Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.


----------------------------------------------------------------------------------------

top-down dfs

A top down approach is similar to an "in-order traversal". 
We use a variable "length" to store the current consecutive path length and pass it down the tree. 
As we traverse, we compare the current node with its parent node to determine if it is consecutive. 
If not, we reset the length.

class Solution {
	int maxLength = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, null, 0);
        return maxLength;
    }

    private void dfs(TreeNode root, TreeNode parent, int length) {
    	if (root == null) return;
    	length = (parent != null && root.val == parent.val + 1) ? length + 1 : 1;
    	maxLength = Math.max(maxLength, length);
    	dfs(root.left, root, length);
    	dfs(root.right, root, length);
    }
}


Time complexity : 
O(n). The time complexity is the same as an in-order traversal of a binary tree with n nodes.

Space complexity : 
O(n). The extra space comes from implicit stack space due to recursion. For a skewed binary tree, the recursion could go up to n levels deep.






Approach #2 (Bottom Up Depth-first Search) [Accepted]

Algorithm

The bottom-up approach is similar to a "post-order traversal". We return the consecutive path length starting at current node to its parent. Then its parent can examine if its node value can be included in this consecutive path.


private int maxLength = 0;
public int longestConsecutive(TreeNode root) {
    dfs(root);
    return maxLength;
}

private int dfs(TreeNode p) {
    if (p == null) return 0;
    int L = dfs(p.left) + 1;
    int R = dfs(p.right) + 1;
    if (p.left != null && p.val + 1 != p.left.val) {
        L = 1;
    }
    if (p.right != null && p.val + 1 != p.right.val) {
        R = 1;
    }
    int length = Math.max(L, R);
    maxLength = Math.max(maxLength, length);
    return length;
}

