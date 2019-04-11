236. LCA of a Binary Tree

普通二叉树的 LCA, 即 没有BST 中节点值的关系了
=> 与 BST 中 LCA 不同点 : 需要明确找到两个节点

Input:  For the following binary tree:
    4
   / \
  3   7
     / \
    2   6

  LCA(3, 2) = `4`

  LCA(2, 6) = `7`

  LCA(2, 4) = `4`

Note :
	All of the nodes‘ values will be unique.
	p and q are different and both values will exist in the binary tree.



BST 中用范围确定
	普通 BT 中 需要明确找到两个节点

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
    //两个递归出口，没搜到 返回null
        if (root == null) {
            return null;
        }
        //搜到其中之一，即root为其中一个，就返回该root
        if (root == A || root == B) {
            return root;
        }

        //divide 如果该root没有搜到，递归往下搜索
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        //conquer 如果一root 其左右子树的返回值都不为空，则证明两边分别找到了不同的目标结点，则此root就是他们的LCA
        if (left != null && right != null) {
            return root;
        }
        //如果一边为空，则证明两点都在root的一边，返回该边即可(必是LCA 因为也是从底向上一步步回溯的)
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        //两边都空，则没有
        return null;
    }
}

T:O(N)
S:O(N)


Iterative using parent pointers



Java iterative solution with 1 stack
The idea is when finding p or q the first time current stack must contain LCA. 
So when stack size is decreased the pop node could be the LCA if another node (p or q) is found under it.

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null|| root == p || root == q) return root;

        TreeNode n = root;
        TreeNode res = null;
        int size = -1;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (n!=null || !s.empty()) {
            while (n!=null) {
                s.push(n);
                if (n == p || n == q) {
                    if (res == null) { //find p or q the first time
                        res = n;
                        size = s.size();
                    } else return res; //find both p and q
                }
                n = n.left;
            }
            n = s.pop();
            if (s.size() < size) {
                size = s.size();
                res = n;
            }
            n = n.right;
        }
        return res;
    }
}
