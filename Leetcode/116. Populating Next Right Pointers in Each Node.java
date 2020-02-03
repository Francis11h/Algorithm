116. Populating Next Right Pointers in Each Node

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/


如果没有限定 空间复杂度 那么我们还可以像   '102 level order traversal' 一样   
但是 我们每层可以从右到左遍历 因为一层最右边的是最特殊的 其他的都一样
（从左到右也能做, 右到左 记得是 next But 左到右 记得是 prev）

// bfs level order traversal from right to left
class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //每一层的最右边 的 next 应该指向 null, initialize at each level
            Node next = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                node.next = next;
                next = node;
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
            }
        }
        return root;
    }
}
O(N) O(N)



2020.2.3
解法2 从左往右正常的顺序加 就是多一个prev记录即可
class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = new Node(0);
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i != 0) prev.next = cur;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                prev = cur;
            }
        }
        
        return root;
    }
}





// use only constant space ----> dfs queue is unacceptable 
// recurisve approach is fine, implicit stack space does not count as extra space
// ----> dfs
// every parent has two children
// perfect binary tree ----> all leaves are on the same bottom level
// 


we cannot use dfs without true list to denote the level!
class Solution {
    public Node connect(Node root) {
        //corner case 
        if (root == null) reutrn null;
        dfs(root, 0);
    }
    //input should contians root + level
    private void dfs(Node root, int level) {
        //outdoor of dfs
        if (root == null) return;
        // key : how can we identify a level without a true list to denote a level?
        // answer : we can't identify a level without a true list!!! so this solution can't work
        if (level )
        dfs(rot.right, level + 1);
        dfs(root.left, level + 1);
    }
}


but we can use dfs without the concept of level

----> 下边的解法 就和 层数 这个概念 就无关了
就完全是 考虑 怎么连, 什么时候连？
    ==> 因为题目规定了 每个parent要是有child 一定是有两个
        所以 只要有左孩子 --> 把左孩子的next指向右孩子即可
        ==>
        右孩子 什么时候指向null?? 这个是本题的关键
        只要 parent.next 不为空 即 我们右边有节点 那么我们的右孩子一定可以指向 parent.next.left 
            因为题目规定了 所有的leaves在同一层


class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = root.next == null ? null : root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

但是 recurison 还是用了 递归栈 S严格意义上来说还不是 O(1)的 


所以 还是得用 非递归的办法
但是 还是要考虑 ‘层level’ 这个概念 如何判定每层开始or结束？ 
再建一个 node 来表示 每层开始 ----> level_start 然后 level_start = level_start.left;来更新

class Solution {
    public Node connect(Node root) {
        Node level_start = root;
        while (level_start != null) {
            Node cur = level_start;
            while (cur != null) {
                if (cur.left != null) {
                cur.left.next = cur.right;
                }
                //这行是 key 同上 右边 什么时候连 
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            level_start = level_start.left;
        }
        return root;
    }
}







