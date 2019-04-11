236. LCA of BT, 节点可能不在树上。


/*树节点的定义
public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}*/

public class Solution {

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        if (!isExist(root, A) || !isExist(root, B)) {
            return null;
        }
        return LCA(root, A, B);
    }

    public boolean isExist(TreeNode root, TreeNode A) {
        if (root == null) {
            return false;
        }
        if (root == A) {
            return true;
        }
        boolean left = isExist(root.left, A);
        boolean right = isExist(root.right, A);

        if (left || right) {
            return true;
        }
        return false;
    }

    public TreeNode LCA(TreeNode root, TreeNode A, TreeNode B) {
        //两个递归出口，没搜到 返回null
        if (root == null) {
            return null;
        }
        //搜到其中之一，即root为其中一个，就返回该root
        if (root == A || root == B) {
            return root;
        }

        //divide 如果该root没有搜到，递归往下搜索
        TreeNode left = LCA(root.left, A, B);
        TreeNode right = LCA(root.right, A, B);

        //conquer 如果一root 其左右子树的返回值都不为空，则证明两边分别找到了不同的目标结点，则此root就是他们的LCA
        if (left != null && right != null) {
            return root;
        }
        //如果一边为空，则证明两点都在root的一边，返回该边即可
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        //两边都空，则没有
        return null;
    }
}
