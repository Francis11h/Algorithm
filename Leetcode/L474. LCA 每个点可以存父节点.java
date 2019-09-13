L474 LCA 每个点可以存父节点


给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。

两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。

每个节点除了左右儿子指针以外，还包含一个父亲指针parent，指向自己的父亲。



做法---->
从两个点向上回溯至 root
然后记录path
然后 从path上往下 找第一个不一样的点 最后一个相同的点就是LCA






/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */


public class Solution {

    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        if (root == null) {
            return null;
        }
        List<ParentTreeNode> pathA = getPath(A);
        List<ParentTreeNode> pathB = getPath(B);
        
        int indexA = pathA.size() - 1;
        int indexB = pathB.size() - 1;
        
        ParentTreeNode LCA = null;
        while (indexA >= 0 && indexB >= 0) {
            if (pathA.get(indexA) != pathB.get(indexB)) {
                break;
            }
            LCA = pathA.get(indexA);
            indexA--;
            indexB--;
        }
        return LCA;
    }
    
    public List<ParentTreeNode> getPath(ParentTreeNode A) {
        List<ParentTreeNode> path = new ArrayList<>();
        while (A != null) {
            path.add(A);
            A = A.parent;
        }
        return path;
    }
}

// 将两个目标点，都移动到root 然后shiftdown
// 找到第一个不一样的点上面一个就是LCA。

// 需要记录path，用两个ArrayList即可