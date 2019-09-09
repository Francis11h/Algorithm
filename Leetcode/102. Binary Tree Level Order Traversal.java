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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> path = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                path.add(cur.val);

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ans.add(path);
        }
        return ans;
    }
}




递归的解法 
本来 如果要是 listlist的话 要用一个 path 不断变 回溯（排列组合问题）
这里 
if (level == ans.size()) {
    ans.add(new ArrayList<>());
}
ans.get(level).add(root.val);
很奇妙 看的很湿.............. 就是 每次到新的一层建一个当层的list 然后每次 先通过level get 这个list然后再往里加

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        dfs(root, 0, ans);
        return ans;
    }
    private void dfs(TreeNode root, int level, List<List<Integer>> ans) {
        if (root == null) return;
        if (level == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        dfs(root.left, level + 1, ans);
        dfs(root.right, level + 1, ans);
    }
}

















199. Binary Tree Right Side View
//从右边看树， 即取层序遍历最后一个
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
                if (i == size - 1)      // if (i == 0) 从左边看
                     ans.add(tmp.val);
            }
            
        }
        return ans;
    }
}