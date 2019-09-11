366. Find Leaves of Binary Tree
Given a binary tree, collect a tree‘s nodes as if you were doing this: 
Collect and remove all leaves, repeat until the tree is empty.

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]


bottom-up recurison

class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) return ans;
        depth(root);
        return ans;
    }
    
    private int depth(TreeNode root) {
        if (root == null) return -1;
        int height = Math.max(depth(root.left), depth(root.right)) + 1;
        //第一次搜这一层时 新建 list
        if (height == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(height).add(root.val);
        return height;
    }
}