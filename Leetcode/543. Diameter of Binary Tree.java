543. Diameter of Binary Tree

The diameter of a binary tree is 
the length of the longest path between Any two nodes in a tree. 
This path may or may Not pass through the root.

          1
         / \
        2   3
       / \     
      4   5  

      return 3 , which is the length of the path [4,2,1,3] or [5,2,1,3].


对每一个节点，先dfs求其深度 => 需要dfs其左右两孩子节点的深度
而 过这一点的 最长路径长 = 其左子树的最大深度+ 其右子树的最大深度
=>  维护一个全局变量 diameter 每次dfs 判断是否需要修改其即可


The longest path that passes a given node as the ROOT node is T = left_height+right_height. 
So you just calculate T for all nodes and output the max T.

// convert to calculate the depth of the leaves 
// cause the longest path must be one leaf to another leaf
// so can we just calculate the deepest leaf on each side of root? no
// but we need to calculate every node! careful, every! and modify the global val of diameter
// the path is the depth of the left subtree plus the depth of the right subtree


class Solution {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return diameter;
    }
    
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}

time complexity : O(N) we visted every node once.
space complexity : the size of our implicit call stack during our dfs









Solution 2: Iterative Method

The idea is to use PostOrder traversal which to 
                    ensure the availability of the node 
                              "until its left and right subtrees are processed".

For this reason, we use peek() method to keep the node on the stack 
                        until its left and right subtrees gets processed.

Then for each node, find the maximum-depth of the left and right subtrees. 
                  Using this maximum-depth, we update diameter if required.



class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null ) return 0;
        
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        int diameter = 0;
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            
            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            } else {
                stack.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);
                // record node's depth
                map.put(node, Math.max(leftDepth, rightDepth) + 1);
                // update the diameter
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }
        return diameter;
    }
}



Time complexity : O(n) - since every node in the tree is visited
Space complexity : O(n) - space required for the HashMap and Stack