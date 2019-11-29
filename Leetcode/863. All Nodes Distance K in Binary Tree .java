863. All Nodes Distance K in Binary Tree

We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.







解法1  
不建图 最tricky 但是最diao

class Solution {
    Map<TreeNode, Integer> map = new HashMap<>();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        List<Integer> ans = new ArrayList<>();
        find(root, target);
        dfs(root, K, map.get(root), ans);
        return ans;
    }
    
    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    // Then we can easily use dfs to travel the whole tree. 
    // Every time when we meet a treenode which has already stored in map, 
    // use the stored value in hashmap instead of the value from its parent node.
    private void dfs(TreeNode root, int K, int dist, List<Integer> ans) {
        if (root == null) return;
        if (map.containsKey(root)) dist = map.get(root);
        if (dist == K) ans.add(root.val);
        dfs(root.left, K, dist + 1, ans);
        dfs(root.right, K, dist + 1, ans);
    }
}



解法2 直接建图

The key problem is to solve how to deal with nodes which are not descendents of target node. 
For descendents, it is trivial dfs.
My idea is to treat the tree more like a graph meaning if there is a way to go up the path from the target then we can know the distances of those nodes that are not descendents of target, correct?
So why not build a parent-child map only for that path (path from root to target)? 
Once we have it, the problem just needs a dfs on "graph" and collect nodes at distance k.



class Solution {
    Map<TreeNode, TreeNode> p = null;
    List<Integer> list = new ArrayList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildParents(root, target, new HashMap<>());
        dfs(target, null, 0, k);
        return list;
    }
    private void dfs(TreeNode curr, TreeNode parent, int d, int k){
        if(curr == null) return;
        if(d == k){
            list.add(curr.val);
            return;
        }
        if(curr.left != parent)
            dfs(curr.left, curr, d+1, k);
        if(curr.right != parent)
            dfs(curr.right, curr, d+1, k);
        dfs(p.getOrDefault(curr, null), curr, d+1, k);
    }
    private boolean buildParents(TreeNode curr, TreeNode t, Map<TreeNode, TreeNode> tmp){
        if(curr == null) return false;
        if(curr == t){
            p = new HashMap<>(tmp);
            return true;
        }
        boolean ret = false;
        if(curr.left != null){
            tmp.put(curr.left, curr);
            ret = buildParents(curr.left, t, tmp);
            tmp.remove(curr.left);
        }
        if(!ret && curr.right != null){
            tmp.put(curr.right, curr);
            ret = buildParents(curr.right, t, tmp);
            tmp.remove(curr.right);
        }
        return ret;
    }
}




Method 3: use HashMap
//1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.
class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap();
    //here can also use Map<TreeNode, TreeNode> to only store the child - parent mapping, since parent-child mapping is inherent in the tree structure
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
         List<Integer> res = new ArrayList<Integer> ();
        if (root == null || K < 0) return res;

        buildMap(root, null); 

        if (!map.containsKey(target)) return res;
        Set<TreeNode> visited = new HashSet<TreeNode>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                for (TreeNode next : map.get(node)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
            K--;
        }
        return res;
    }
    
    private void buildMap(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<TreeNode>());
            if (parent != null)  { 
                map.get(node).add(parent); 
                map.get(parent).add(node); 
            }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }    
}






2019.11.29 
    从一棵树建无向图 递归用 node,parent node 每次两个节点 加边进hashMap
    从图中某一点 按size层bfs 找距离为K的点

//1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.

class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        // build a graph
        buildgraph(root, null);
        // corner case
        if (!map.containsKey(target)) return ans;
        // starting bfs at target node
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        
        while (!queue.isEmpty() && K > 0) {
            int size = queue.size();
            K--;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                for (TreeNode nei : map.get(cur)) {
                    if (!visited.contains(nei)) {
                        queue.offer(nei);
                        visited.add(nei);
                    }
                }
            }
        }
        if (K > 0) return ans;
        while (!queue.isEmpty()) {
            ans.add(queue.poll().val);
        }
        return ans;
    }
    
    // parameters  1. current node 2.its parent node, cause we want to build an undirected graph
    // so we need to add parent-child edge twice 
    private void buildgraph(TreeNode cur, TreeNode parent) {
        if (cur == null) return;
        if (!map.containsKey(cur)) {
            map.put(cur, new ArrayList<>());
            if (parent != null) {   // cause root node doesnt hace a parent, corner case
                map.get(cur).add(parent);
                map.get(parent).add(cur);
            }
            // recursive
            buildgraph(cur.left, cur);
            buildgraph(cur.right, cur);
        }
    }
}