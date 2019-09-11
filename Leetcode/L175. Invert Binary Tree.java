L175. Invert Binary Tree
翻转一颗二叉树

Input: {1,3,#}
Output: {1,#,3}

	  1    1
	 /  =>  \
	3        3


中间变量temp保存一下 直接换root的左右孩子 再递归换下面的

public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        if (root == null) return;    
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    }
}






非递归 栈模拟



class Solution {
    public void invertBinaryTree(TreeNode root) {
        if (root == null) return;  
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }
}