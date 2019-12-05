236. LCA of a Binary Tree

普通二叉树的 LCA, 即 没有BST 中节点值的关系了
=> 与 BST 中 LCA 不同点 : 需要明确找到两个节点

Input:  For the following binary tree:
    4
   / \
  3   7
     / \
    2   6

  LCA(3, 2) = `4`

  LCA(2, 6) = `7`

  LCA(2, 4) = `4`

Note :
	All of the nodes‘ values will be unique.
	p and q are different and both values will exist in the binary tree.



找到A和B 第一次 在 同一棵子树中的 子树根节点 即是LCA

LCA(root, p, q) 函数的意义

在以root为根的整个树中搜索pq的LCA
如果root就是p或q 返回root  如果root既不是p也不是q 我们先检查左边 然后检查右边
如果在左边找到了 p/q 停止这边 并返回该node
继续检查下一边 下一边 有另一个 返回root, 没有 返回在左边找到的node




// There is an assumption that p and q nodes exist and are part of the tree.

// Let’s start from the beginning, from the root node.
// If root of the tree is p or q then line

// if(root == p || root == q) return root;

// returns real root which is LCA since it’s doesn’t matter where second (p or q) is – root is LCA anyway.

// Let’s say root node is not p or q then it checks left branch first

// TreeNode left = lowestCommonAncestor(root.left, p, q);

// And then right branch:

// TreeNode right = lowestCommonAncestor(root.right, p, q);

// If it found p or q on the left it stops and returns that node and doesn’t check the rest of the left sub tree.
// Why? Because if second node (p or q) is somewhere below in the current left sub tree – then current node is LCA and it already has it. How does it know whether second node is below in the current sub tree or not? It goes to check a right branch.
// If right branch doesn’t have second node then it’s somewhere below in the left sub tree where we found first p or q and it’s below the node we already found so the node we found on the left is LCA.
// If second node is on the right branch then LCA is a node for which both lines

// TreeNode left = lowestCommonAncestor(root.left, p, q); and

// TreeNode right = lowestCommonAncestor(root.right, p, q);

// return != null left and right

// It checks this here:
// return left != null && right != null ? root





BST 中用范围确定
	普通 BT 中 需要明确找到两个节点

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        //两个递归出口，没搜到 返回null
        if (root == null) {
            return null;
        }
        //搜到其中之一，即root为其中一个，就返回该root
        if (root == A || root == B) {
            return root;
        }

        //divide 如果该root没有搜到，递归往下搜索
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        //conquer 如果一root 其左右子树的返回值都不为空，则证明两边分别找到了不同的目标结点，则此root就是他们的LCA
        if (left != null && right != null) {
            return root;
        }
        //如果一边为空，则证明两点都在root的一边，返回该边即可(必是LCA 因为也是从底向上一步步回溯的)
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        //两边都空，则没有
        return null;
        //4 合一 代码更优雅
        //return left != null && right != null ? root : left == null ? right : left; 
    }
}

T:O(N)
S:O(N)


Iterative using parent pointers



Java iterative solution with 1 stack
The idea is when finding p or q the first time current stack must contain LCA. 
So when stack size is decreased the pop node could be the LCA if another node (p or q) is found under it.

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null|| root == p || root == q) return root;

        TreeNode n = root;
        TreeNode res = null;
        int size = -1;
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (n!=null || !s.empty()) {
            while (n!=null) {
                s.push(n);
                if (n == p || n == q) {
                    if (res == null) { //find p or q the first time
                        res = n;
                        size = s.size();
                    } else return res; //find both p and q
                }
                n = n.left;
            }
            n = s.pop();
            if (s.size() < size) {
                size = s.size();
                res = n;
            }
            n = n.right;
        }
        return res;
    }
}








如果 节点可能不在树上 先判断在不在树上

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (!isExist(root, p) || !isExist(root, q)) return null;
        return LCA(root, p, q);
    }
    
    private boolean isExist(TreeNode root, TreeNode p) {
        if (root == null) return false;
        if (root.val == p.val) return true;
        if (isExist(root.left, p) || isExist(root.right, p)) return true;
        return false;
    }
    
    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;
        if (left == null) return right;
        if (right == null) return left;
        return null;
    }
}





2019.12.5 重新写 一遍过 


class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        // the root is neither p nor q, recursively call the function on root's children
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left == null && right == null) return null;
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }
}

// how can we search the tree, what parameters should we pass in, what should we return
// use dfs; input root, p, q; return the LCA node of the p q in this tree whose root is root
