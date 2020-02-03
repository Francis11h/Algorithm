156. Binary Tree Upside Down

Given a binary tree where all the right nodes 
    are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
    flip it upside down and turn it into a tree 
        where the original right nodes turned into left leaf nodes. Return the new root.


题意是说每个结点的右子树要么为空, 要么一定有一个左子树孩子和一个右子树孩子, 因此树的形状是"左偏"的
所以我们要将最左边的子树作为最终的新根结点, 然后递归的将其父结点作为其右孩子,并且父结点的右孩子作为其左孩子. 
一个非常重要的地方是每次一定要将父结点的左右孩子都置为空, 因为父结点设置成其左孩子的右孩子之后成了叶子结点, 需要将其指针断掉.


Example:

Input: [1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

Output: return the root of the binary tree [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  





The transform of the base three-node case is like below:

                         Root                   L
                         /  \                  /  \
                        L    R                R   Root





    When we turn a simple tree upside down: 
            Root                Left
            /  \                /  \
         Left  Right        Right  Root
    So recursively go to the most left child. Since that will be our new root. 
    And we populate the that child all the way up. 
    So it‘s visiting root -> root.left -> root.left.left -> root.left.left.left....
    And when its at current root, we make root.left.left = right; root.left.right = root. (We are basically assign left and right child to root.left - new root). Then delete root.left and root.right

       1
      / \   root=2 now            root=1 now
     2   3    ---->      4   1   ------------>   4
    / \                 / \ / \                 / \
   4   5               5   2   3               5   2
        (deleted 2‘s original 4 and 5)            / \
                                                 3   1  (deleted 1’s original 2 and 3)





class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null ||(root.left == null && root.right == null))
            return root;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;
        return newRoot;
    }
}








