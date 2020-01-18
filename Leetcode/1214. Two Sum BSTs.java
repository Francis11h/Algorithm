1214. Two Sum BSTs


Given two binary search trees, return True 
	if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.




基本同 653. Two Sum IV - Input is a BST




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> A = new ArrayList<>();
        inorder(root1, A);
        List<Integer> B = new ArrayList<>();
        inorder(root2, B);
        
        int l = 0, r = B.size() - 1;
        
        while (l < A.size() && r >= 0) {
            if (A.get(l) + B.get(r) == target) return true;
            else if (A.get(l) + B.get(r) < target) l++;
            else r--;
        }
        return false;
        
    }
    
    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
}