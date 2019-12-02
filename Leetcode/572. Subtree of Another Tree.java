572. Subtree of Another Tree

Given two non-empty binary trees s and t, 
check whether tree t has exactly the same Structure and Node-Values with a subtree of s. 

A subtree of s is a tree consists of a node in s and all of this node‘s descendants. 
The tree s could also be considered as a subtree of itself.

s:
     3
    / \
   4   5
  / \
 1   2
t:
   4 
  / \
 1   2
Return true
---------------
Given tree s:
     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.




class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        this.val = x;
    }
}




class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (t == null) return true;

        boolean isLeft = isSubtree(s.left, t);
        boolean isRight = isSubtree(s.right, t);

        return isSameTree(s, t) || isLeft || isRight;
    }

    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}





Time complexity : O(m*n). In worst case(skewed tree) traverse function takes O(m*n)time.

Space complexity : O(n). The depth of the recursion tree can go upto n. n refers to the number of nodes in s.



实际应用？？
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=574764&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26orderby%3Ddateline%26sortid%3D311%26orderby%3Ddateline
