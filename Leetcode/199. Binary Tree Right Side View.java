199. Binary Tree Right Side View


// intuitively we should traverse all levels of the tree 
// and output the last node of each level
// what strategy can we use to traverse? and how to identify which node is the last of one level?
// bfs / dfs 
// bfs : record the queue.size()    //从右边看树， 即取层序遍历最后一个
// dfs : level 

// bfs
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        //corner case 
        if (root == null)  return ans;
        //begin bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //record the size of one level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                //identify whether the node is the last element of the level
                if (i == size - 1) {        // if (i == 0) we can get the left side view of the tree
                    ans.add(node.val);
                }
            }
        }
        return ans;
    }
}


//dfs postorder

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        //corner case 
        if (root == null)  return ans;
        dfs(root, 0, ans);
        return ans;
    }
    // use parameter level in dfs to identify
    private void dfs(TreeNode root, int level, List<Integer> ans) {
        // ourdoor of dfs
        if (root == null)   return;
        // level == ans.size() to identify a new layer
        if (level == ans.size()) {
            ans.add(root.val);
        }
        //add right child first ---> postorder
        dfs(root.right, level + 1, ans);
        dfs(root.left, level + 1, ans);         // if reverse the visit order, that is first LEFT and then RIGHT, it will return the left view of the tree.
    }
}

O(n)
O(n)



//102. Binary Tree Level Order Traversal.java
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


