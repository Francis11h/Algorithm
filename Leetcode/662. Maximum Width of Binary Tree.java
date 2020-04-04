662. Maximum Width of Binary Tree

Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.




Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).






就是 每个点 再存一个 index 即可

也可以不用新建类 建个map 也行

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int dist = 0, left = 0, right = 0;
            
            for (int i = 0; i < size; i++) {
                Node now = queue.poll();
                TreeNode cur = now.node;
                int pos = now.pos;
                if (cur.left != null) {
                    queue.offer(new Node(cur.left, 2 * pos));
                }
                if (cur.right != null) {
                    queue.offer(new Node(cur.right, 2 * pos + 1));
                }
                if (i == 0) left = now.pos;
                if (i == size - 1) right = now.pos;
            }
            maxWidth = Math.max(maxWidth, right - left + 1);
        }
        
        return maxWidth;
    }
}

class Node {
    int pos;
    TreeNode node;
    public Node(TreeNode node, int pos) {
        this.pos = pos;
        this.node = node;
    }
}






Java Solution with explaination
Obviously, I would rather use a BFS to solve this problem because if we do BFS as a level layer which means in each for loop, we visit every node in the same layer and determine the largest distance in this layer and compare the largest layer with return value.

It first came to me that we could just found our path to the left and to the right bottom of this tree, but this idea is absolutely wrong. Becuase sometimes, it's not always the bottom layer who has the largest distance. So we have to go through every node and rule out every possible situation.

Now we do BFS, but instead we put Node in our queue, we need to store a specific number to remember the position of this node among this layer.