863. All Nodes Distance K in Binary Tree

We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.





2019.11.29  2020.2.9 目前最推荐的写法--------
    从一棵树建无向图 递归用 node,parent node 每次两个节点 加边 进hashMap (两个点分别当起点 加 最后去重即可)
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













--------------------------------------------------------------------------------------------------------------------------------------------------------


其他解法


解法1  
不建图 最tricky 但是最diao
存 从 root - target 这一条路径中 node到target的距离 于map
再从root 直接dfs 传dist 
    如果节点不在map里 就直接dist+1 如果在 dist更新为 map里的值

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-with(without)-hashmap-including-explanation

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



解法2 直接建图 但是 比最推荐 厉害的是 不再存全部的图了 只需要知道某一节点的单个父亲即可  

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




Method 3: 建完全图
//1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.

class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        dfs(root, null);
        return bfs(target, K);
    }
    
    // first dfs to build undirected graph,  node---parent one edge, map put twice
    // so parameter for dfs we need the node, and its parent
    private void dfs(TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new LinkedList<>());
            if (parent != null) {
                map.get(node).add(parent);
                map.get(parent).add(node);
            }
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
    // search begin at target, extend outward for K level
    private List<Integer> bfs(TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        
        while (!queue.isEmpty() && k > 0) {
            int size = queue.size();
            k--;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                for (TreeNode nei : map.get(cur)) {
                    if (!visited.contains(nei)) {
                        queue.add(nei);
                        visited.add(nei);
                    }
                }
            }
        }
        if (k > 0) return ans;
        while (!queue.isEmpty()) {
            ans.add(queue.poll().val);
        }
        return ans;
    }
}




Method 4 只建parent map 标答
Intuition
	If we know the parent of every node x, we know all nodes that are distance 1 from x. 
	We can then perform a breadth first search from the target node to find the answer.
Algorithm
	We first do a depth first search where we annotate every node with information about it‘s parent.
	After, we do a breadth first search to find all nodes a distance K from the target


class Solution {
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}




