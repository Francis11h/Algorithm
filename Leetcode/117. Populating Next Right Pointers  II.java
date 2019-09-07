117. Populating Next Right Pointers in Each Node II

Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL


少了 这两个条件 

1. perfect binary tree where all leaves are on the same level, 
2. every parent has two children

同时 还要求 S : O(1)


if don‘t care about space time complexity we can just use level order traversal from right to left
and  initialize 'next' to null at each level begin
the same as '116. Populating Next Right Pointers in Each Node'.

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	Node next = null;
        	for (int i = 0; i < size; i++) {
        		Node node = queue.poll();
        		node.next = next;
        		next = node;
        		if (node.right != null) {
        			queue.offer(node.right);
        		}
        		if (node.left != null) {
        			queue.offer(node.left);
        		}
        	}
        }
        return root;
    }
}



care about the space complexity

solution for 116 (complete binary tree & guarantee every parent has two children)

class Solution {
    public Node connect(Node root) {
        Node level_start = root;
        while (level_start != null) {
            Node cur = level_start;
            while (cur != null) {
            	//这里我们不能这么写了 因为 cur.right 可能不存在
                if (cur.left != null) {
                	cur.left.next = cur.right;
                }
                //这行是 116 的 key 右边 什么时候连 
                // 117 因为没有了 完全二叉树 和 满的 条件 这里也不能这么写 因为 cur.next.left 也可能是 null
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


本题的 最优化解法


use Level_start to indicate the head of each level
use Prev to indicate the prev node of the next layer, means what node we should modify the next pointer
use Cur to indicate the current node of current level level 

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0);
        Node prev = dummy;
        Node level_start = root;
        while (level_start != null) {           //if the head of the traverse layer is not null, then traverse that layer
            Node cur = level_start;
            while (cur != null) {
                //left child
                if (cur.left != null) {
                    prev.next = cur.left;
                    prev = prev.next;
                }
                //right child
                if (cur.right != null) {
                    prev.next = cur.right;
                    prev = prev.next;
                }
                //move to next node
                cur = cur.next;
            }
            // move to next level
            level_start = dummy.next;
            dummy.next = null;
            prev = dummy;

        }
        return root;
    }
}


dummy的指向 由 每一层最开始的 prev.next 改变 因为最开始 prev == dummy





最优化解法 减少一层while循环

class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
    }
}




dfs 麻烦的解法
https://github.com/awangdev/LintCode/blob/master/Java/Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II.java