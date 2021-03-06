98. Validate Binary Search Tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


经典的错误解法 只考虑了 root和它的直系children的大小关系 没考虑root也得比左边的孙子大 比右边的孙子小
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.val <= root.left.val) return false;
        if (root.right != null && root.val >= root.right.val) return false;
        return isValidBST(root.left) && isValidBST(root.right);
    }
}


所以我们 还需要知道 的信息 是 每一个node的取值范围 这就要求我们每次dfs都传进来 并且下次dfs要修改

Solution 1 : Recurison

class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValid(TreeNode root, long lower, long upper) {
        //outdoor
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;
        //modify lower and upper
        return isValid(root.left, lower, root.val) && isValid(root.right, root.val, upper);
    }
}

use Long to handle the corner case of [2147483647]




use inorder traversal properties where 
previous element in output would always be lesser than the current output.


Solution 2 : Iteration : inorder traversal

//first add all left children to the stack
//pop and check (use a prev node to record tcur node's prev and cur.val must greater than prev.val)
//and if the cur node has cur.right push cur.right to stack
//and the most important is to push all of the left children of the cur.right to stack

class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        TreeNode prev = null;

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (prev != null && cur.val <= prev.val) return false;
            //modify the prev
            prev = cur;
            if (cur.right != null) {
                TreeNode node = cur.right;
                //push all of the left children of the cur.right to stack
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return true;
    }
}
















