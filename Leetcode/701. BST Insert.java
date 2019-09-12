701. Insert into a Binary Search Tree


Return the root node of the BST after the insertion. 
It is guaranteed that the new value does not exist in the original BST.

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5

         4
       /   \
      2     7
     / \   /
    1   3 5


// The insert() return to the root node of the BST
// where to insert? -----> one nodes's left or right child
//encounter what condition we can insert? ---> when the root is null we insert --->
          // which means the position has no node and then we build a new node with the val
    
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        //insert at left subtree
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}


//iterate use two auxiliary nodes  
// one means the father node where we can insert
// one means where is the propriate null position

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode father = null;
        //when cur == null we insert at one of the father node's child
        TreeNode cur = root;
        while (cur != null) {
            father = cur;
            if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (father.val > val) {
            father.left = new TreeNode(val);
        } else {
            father.right = new TreeNode(val);
        }
        return root;
    }
}