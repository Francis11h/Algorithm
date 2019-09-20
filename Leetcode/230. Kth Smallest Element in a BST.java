230. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.


Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1


Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3



//intuitively do inorder traversal and stop at the kth
class Solution {
	public int kthSmallest(TreeNode root, int k) {
		if (root == null) return -1;
		Stack<TreeNode> stack = new Stack<>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}

		while (!stack.isEmpty() && k > 0) {
			TreeNode cur = stack.pop();
			k--;
			if (k == 0) return cur.val;
			if (cur.right != null) {
				TreeNode node = cur.right;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}
		return -1;
	}
}
O(h + k)  h 是树的高度



// inorder recursive maintain a global count 
It makes the solution stateful which is less robust in practice since it is more error prone and not thread safe.


class Solution {
	public static int count = 0;
	public static int ans = 0;
	public int kthSmallest(TreeNode root, int k) {
		count = k;
		inorder(root);
		return ans;
	}

	private void inorder(TreeNode root) {
		if (root == null) return;
		inorder(root.left);
		count--;
		if (count == 0) {
			ans = root.val;
			return;
		}
		inorder(root.right);
	}
}

O(N)




Follow Up :
What if the BST is modified (insert/delete operations) often 
and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?


增删多次 
That is a design question,to implement a structure which contains a 
BST inside and optimises the following operations :
	Insert

	Delete

	Find kth smallest

same logic as for LRU cache design, and combine an indexing structure 
(we could keep BST here) with a double linked list.


树下面 链一个 双向 LinkedList



二叉搜索树的作用可看作是提供了一个排好序的列表
在二叉搜索树中搜索就像是在这个有序列表搜索某个元素一样







