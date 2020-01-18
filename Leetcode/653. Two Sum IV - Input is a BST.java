653. Two Sum IV - Input is a BST


Given a Binary Search Tree and a target number, 
return true if there exist two elements in the BST such that 
	their sum is equal to the given target.


Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True




Solution 1:
// dfs + Set
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, Set<Integer> set, int k){
    	if (root == null) return false;
    	if (set.contains(k - root.val)) return true;
    	set.add(root.val);
    	return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}






Solution 2:

 we make use of the fact that the given tree is a Binary Search Tree. Now, we know that the inorder traversal of a BST gives the nodes in ascending order
 Thus, we do the inorder traversal of the given tree and put the results in a list which contains the nodes sorted in ascending order

Note that we need not increase the larger number or reduce the smaller number in any case. 
This happens because, in case, a number larger than the current list[r] is needed to form the required sum k, 
the right pointer could not have been reduced in the first place. 
The similar argument holds true for not reducing the smaller number as well.




class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        
        int l = 0, r = nums.size() - 1;
        
        while (l < r) {
            if (nums.get(l) + nums.get(r) == k) return true;
            else if (nums.get(l) + nums.get(r) < k) l++;
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

T:O(n) S:O(n)




