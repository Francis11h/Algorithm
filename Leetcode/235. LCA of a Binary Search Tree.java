235. LCA of a Binary Search Tree
	最近 lowest 

The lowest common ancestor is defined between two nodes p and q as the lowest node in T 
	that has both p and q as descendants 
	(where we allow a node to be a descendant of itself).

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2

Note:
All of the node‘s values will be unique.	每个节点的值都不同
p and q are different and both values will exist in the BST.	p q两个节点都存在于该BST中。


关键是 在一个 BST 中 寻找 LCA
要充分利用 BST 的特点 : 	root.left.val < root.val < root.right.val
						（inorder traverse increasing）



//recurison
class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root.val < p.val && root.val < q.val) {						//pq都在root 右边
			return lowestCommonAncestor(root.right, p, q);
		} else if (root.val > p.val && root.val > q.val) {				//pq都在root 左边
			return lowestCommonAncestor(root.left, p, q);
		} else {														//pq 在root 两边, 那么 root 就是LCA of pq
			return root;
		}
	}
}

T: O(N) N is the # if nodes in BST (worst case we traverse all nodes in BST)
S: O(N) maximum amount of space utilized by the recurison stack would be N 
		since the height of a skewed BST could br N. (斜二叉树,全在一边, 是一个链)


//iterative

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Value of p
        int pVal = p.val;

        // Value of q;
        int qVal = q.val;

        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {

            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
    }
}

T: O(N)
Space Complexity : O(1)





