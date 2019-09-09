94. Binary Tree Inorder Traversal

//recurison
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
    private void helper(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        helper(root.left, ans);
        ans.add(root.val);
        helper(root.right, ans);
    }
}

T:O(N)
S:O(logN)


//iteration
// using stack

class Solution {
    //iterate 用 inorder traversal 的 定义一步步的来做
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {      		//用一个while先把最左边一条全部压入栈
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {      	//开始 弹栈
            TreeNode cur = stack.pop();     //记录弹出的节点
            res.add(cur.val);
            if (cur.right != null) {        //如果该弹出的节点，没有右孩子，继续 弹栈（弹它的父亲）即可
                TreeNode node = cur.right;  //如果有，则要把它右孩子当作一个新的root
                while (node != null) {      //并且把其右孩子为根的树的最左边一条 全部入栈
                    stack.push(node);
                    node = node.left;
                }
            }
        }         
        return res;
    }
}



更优雅的写法

class Solution {
	//iterate 直接变换 root赋值，不用写if判断了
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
	//按照左根右的次序遍历二叉树，搜索左子树，存入当前点，搜索右子树。
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}


