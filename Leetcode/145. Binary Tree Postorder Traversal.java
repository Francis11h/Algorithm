145. Binary Tree Postorder Traversal

永远都是 左孩子先然后右孩子 先中后序 指的是 root 的出现顺序    

    2  
   / \
  1   3

先序 preorder  213
中序 inorder   123
后序 postorder 132


class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        helper(root.left, ans);
        helper(root.right, ans);
        ans.add(root.val);
    }
}

后序遍历很tricky
相当于是 
    2  
   / \
  1   3
先输出 231 然后 倒过来


class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(0, cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return ans;
    }
}




590. N-ary Tree Postorder Traversal

