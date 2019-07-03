993. 判断两个节点是不是兄弟 Cousins in Binary Tree

Cousins ： same depth but different parent


最naive的想法 : 
    需要知道的信息 depth of  x & y + parent of x & y

=====> 转化 不需要知道具体的层数, 必须满足在同一层这个条件, 
        如果在同一层 (可以对俩点各写个判断是不是在这层) ---- 父亲相同 ----不满足
        同时 要是他俩是同一个父亲, 则代表该父亲一定有两个孩子


use BFS, space beats 100%

class Solution {
    public boolean isCousins(TreeNode root, int A, int B) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isAexist = false;
            boolean isBexist = false;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.val == A) isAexist = true;
                if (temp.val == B) isBexist = true;
                if (temp.left != null && temp.right != null) {
                    if (temp.left.val == A && temp.right.val == B) return false;
                    if (temp.left.val == B && temp.right.val == A) return false;
                }
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            if (isAexist && isBexist) return true;
        }
        return false;
    }
}




Easy to think of, check the depth and whether two nodes are sibling.




class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return findDepth(root,x,1) == findDepth(root,y,1) && !isSibling(root,x,y); 
    }
    
    private boolean isSibling(TreeNode node, int x, int y) {
        if(node == null) return false;
        
        boolean check = false;
        if(node.left != null && node.right != null){
            check = (node.left.val == x && node.right.val == y) ||
                    (node.left.val == y && node.right.val == x);
        }
        return check || isSibling(node.left, x, y) || isSibling(node.right, x, y);
    }
    
    private int findDepth(TreeNode node, int val, int height) {
        if(node == null) return 0;
        if(node.val == val) return height;
        
        return findDepth(node.left, val, height + 1) | 
               findDepth(node.right, val, height + 1);
    }
}



In binary number, every digit do OR operation. 
In this case, u can get the exact height in only one return value, others are all zero, so ...0 | 0 | x | 0 | .... = x that is the height u want. 



