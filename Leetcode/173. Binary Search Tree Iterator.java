173. Binary Search Tree Iterator


class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        helper(root);
    }
    //每次把该节点左边的全入栈
    private void helper(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        helper(node.right);
        return node.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}




解法2 保证不会出现 next()一定合法

//栈中保存其子路径 中所有靠左的节点，因为取next只会取右儿子的最左节点
public class BSTIterator {
    
    private Stack<TreeNode> stack;
    TreeNode next = null;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        next = root;
    }

    public boolean hasNext() {
        if (next != null) { //意味着当前节点有右儿子
            TreeNode cur = next;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; //把 cur节点左边线上的所有节点入栈
            }
            next = null;
        }
        return !stack.isEmpty();  //为空，就返回false，表示无候选节点
    }

   
    public TreeNode next() {
        if (!hasNext()) {
            return null;
        }
        TreeNode cur = stack.pop();
        next = cur.right;  // next 指向前节点的右儿子  可能为空
        return cur;
    }
}

