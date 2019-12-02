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
        // LinkedList 保证每次往 index = 0 的时候插都是 O(1)
        List<Integer> ans = new LinkedList<>(); 
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




2019.12.2 
真正意义上的 postorder Traversal, 用 stack.peek();

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>(); 
        if (root == null) return ans;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Set<TreeNode> set = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (cur.left != null && !set.contains(cur.left)) {
                stack.push(cur.left);
            } else if (cur.right != null && !set.contains(cur.right)) {
                stack.push(cur.right);
            } else {
                stack.pop();
                ans.add(cur.val);
                set.add(cur);
            }
        }
        return ans;
    }
}