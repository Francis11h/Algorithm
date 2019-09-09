144. Binary Tree Preorder Traversal

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        ans.add(root.val);
        helper(root.left, ans);
        helper(root.right, ans);
    }
}


// 首先存入当前节点值，然后先将右儿子压入栈中，再将左儿子压入栈中。对栈中元素遍历访问
// 先右后左 先入后出
// 一个节点只考虑它的直系孩子 从右往左入栈

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        
        return ans;
    }
}


