Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1  '5'
           / \   \
          5   5   5

Output: 4 (3 leaves + root '5')



错误代码 

反思 为什么能写出这种代码？？？

还是没有理解递归

[5,1,3,1,1,1] 这个cornercase 会导致 NullPointerException

class Solution {
    int totalCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        isUnivalueTree(root);
        return totalCount;
    }
    
    private boolean isUnivalueTree(TreeNode root) {
        if (root == null) return true;
        //这里就是冗余 因为下一层recurison 会传进来 所以不用单独写
        if (root.left == null && root.right == null)  {
            totalCount++;
            return true;
        }
        // 这里也是 冗余 因为 isUnivalueTree 这个函数 当一边为空的时候 传回的也是 true 所以不用再分别讨论
        if (root.left == null && isUnivalueTree(root.right) && root.val == root.right.val) {
            totalCount++;
            return true;
        } else if (root.right == null && isUnivalueTree(root.left) && root.val == root.left.val) {
            totalCount++;
            return true;
            //按理说 直接写这里 然后在这里判断下 左右为null 的 NPE 即可
        } else if (isUnivalueTree(root.right) && isUnivalueTree(root.left) && root.val == root.right.val && root.val == root.left.val) {
            totalCount++;
            return true;
        } else {
            return false;
        }
    }
}


但是 这种也不对
这种的话 结果是2 应该是 4 
就相当于漏掉了右边的

class Solution {
    int totalCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        isUnivalueTree(root);
        return totalCount;
    }
    
    private boolean isUnivalueTree(TreeNode root) {
        if (root == null) return true;
        // 这么写是不对的 
        // if (A && B) 如果A是false B就不走了 所以 必须得单列出来 以保证左右都走
        if (isUnivalueTree(root.left) && isUnivalueTree(root.right) && 
            (root.left == null || root.val == root.left.val) && 
            (root.right == null || root.val == root.right.val)) {
            totalCount++;
            return true;
        } 
        
        return false;
    }
}




正确代码改进

class Solution {
    int totalCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        isUnivalueTree(root);
        return totalCount;
    }
    
    private boolean isUnivalueTree(TreeNode root) {
        // end of recurison
        if (root == null) return true;
        // get information from its children 
        boolean isLeft = isUnivalueTree(root.left);
        boolean isRight = isUnivalueTree(root.right);
        
        if (isLeft && isRight && (root.left == null || root.val == root.left.val) && (root.right == null || root.val == root.right.val)) {
            totalCount++;
            return true;
        }
        return false;
    }
}
















