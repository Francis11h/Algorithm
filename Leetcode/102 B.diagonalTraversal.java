https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/


diagonal-traversal-of-binary-tree

    1
   / \
  2   3
 /\   /\
4  5 6  7
        /\
       8  9  

输出就应该是[[1, 3, 7, 9], [2, 5, 6, 8], [4]]

1. root开始 所有右孩子入队
2. pop 出队后 "如果有"左孩子 再遍历该左孩子节点 的右孩子入队
3. 一层 记一下size

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

queue
[1, 3, 7 ,9]

class Solution {
    public List<List<Integer>> diagonalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();

        while (root != null) {
            queue.offer(root);
            root = root.right;
        }

        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                    cur = cur.left;
                    while (cur.right != null) {     //这个 while 要放在 if里面   对应这个 [1,2,3,4,5,null,7,null,null,null,null,8,9]
                        queue.offer(cur.right);
                        cur = cur.right;
                    }  
                }
            }
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
}








class Solution {
    public List<Integer> diagonalTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        
        while (root != null) {
            queue.offer(root);
            root = root.right;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                ans.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                    cur = cur.left;
                    while (cur.right != null) {
                        queue.offer(cur.right);
                        cur = cur.right;
                    } 
                }  
            }
        }
        return ans;
    }
}













