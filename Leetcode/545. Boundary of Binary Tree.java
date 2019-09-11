545. Boundary of Binary Tree

给定一个二叉树，从根开始返回其逆时针方向的边界值。边界包括左边界、叶和右边界，没有重复的节点。

左边界的定义为从根到最左边节点的路径。右边界的定义为从根到最右边的节点的路径。

如果根没有左子树或右子树，那么根本身就是最左边或者最右边的节点。注意，这个定义只适用于二叉树的根节点，而不适用于任何子树。

而当根有左子树时，最左边的节点是：你以有先访问左子节点的方式遍历二叉树时，第一个碰到的叶子节点。最右边的节点的定义类似，方向相反。






最简单直接的思路就是进行三次DFS, 分别把左边界, 叶子, 右边界放到答案序列中.

左边界: 从根的左子节点开始, 优先向左, 没有左子节点就向右, 直到叶子节点, 沿路的所有节点放入答案序列
叶子节点: 遍历整棵树, 为了保证逆时针顺序, 需要先访问左子节点, 碰到叶子就放入答案序列
右边界: 与左边界类似, 只不过将节点放入答案序列的时机要延后 -- 在递归结束时放入


// just do three time dfs to identify the left-bound leaves and right-bound
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return ans;
        ans.add(root.val);
        if (root.left == null && root.right == null) return ans;    //corner case [1]
        Left(root.left);
        leaves(root);
        Right(root.right);
        return ans;
    }
    //find left bound
    private void Left(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        ans.add(root.val);
        if (root.left != null) 
            Left(root.left);
        else 
            Left(root.right);
       
    }
    //find leaves
    private void leaves(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return;
        }
        leaves(root.left);  //先左后右 逆时针
        leaves(root.right);
    }
    
    //find right bound
    private void Right(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        // ans.add(root.val); 要保证逆时针 所以后加入 先recurison
        if (root.right != null) 
            Right(root.right);
        else 
            Right(root.left);
        ans.add(root.val);  
    }
    
}




Complexity:
O(n) leftBoundary(root.left);
O(n) leaves(root.left);
O(n) leaves(root.right);
O(n) rightBoundary(root.right);

Total: O(n)




