450. Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.


Search for a node to remove.
If the node is found, delete the node.


root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
被删除节点的右子树中最小的点替换

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].
被删除节点的左子树中最大的点替换

    5
   / \
  2   6
   \   \
    4   7



用左边子树最大的节点替换
//deleteNode() return the root node reference of the modified BST
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val == key) {
            //if the key node is a leaf, delete directly
            if (root.left == null && root.right == null) return null;
            // if the key node just has one child, return this child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // if the key has two children, we use the Max node of its left Subtree to replace the key node and delete this Max node in its left Subtree
            //first find the Max node 
            int leftMax = findMax(root.left);
            //just replace the value is fine
            root.val = leftMax;
            // delete the leftMax node in the left subtree of the root by the same method 
            root.left = deleteNode(root.left, leftMax);         //这里比较关键 用同样的函数删左边的
        }
        return root;
    }
    //Max in a BST is the rightmost node of the BST
    private int findMax(TreeNode root) {
       if (root.right == null) {
           return root.val;
       }
        return findMax(root.right);
    }
}




还有一个小点 可以用 
assert key == root.val
如果不等于 抛个异常



或者 用 右边子树最小的节点替换

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            int rightMin = findMin(root.right);
            root.val = rightMin;
            // delete the rightMin node in the right subtree of the root by the same method 
            root.right = deleteNode(root.right, rightMin);         
        }
        return root;
    }
    //Min in a BST is the leftmost node of the BST
    private int findMin(TreeNode root) {
       if (root.left == null) {
           return root.val;
       }
        return findMin(root.left);
    }
}