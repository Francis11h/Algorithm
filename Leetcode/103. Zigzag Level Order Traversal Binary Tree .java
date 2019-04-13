103. Binary Tree Zigzag Level Order Traversal

层序遍历, 一行从左往右输出, 下一行从右往左, 不断交替。

input:
    3
   / \
  9  20
    /  \
   15   7
output:
[
  [3],
  [20,9],
  [15,7]
]

方法一 : 用 queue 层序遍历 非递归

ArrayList 每次在队首插入 函数: 
	ArrayList.add(int index, E elemen)
		在 index 处插入 elemen, 原本index处的元素及其之后的元素 向右移一个
		e.g. 	tmp.add(0, cur.val)

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOdd = true;
        while (!queue.isEmpty()) {
        	List<Integer> level = new ArrayList<>();
        	int size = queue.size();							//一定要在 for循环外记录 size, 不能进了 for 在for的第二个参数算queue.size()，因为queue在变
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = queue.poll();
        		if (isOdd) {
        			level.add(cur.val);							// 加的是 value
        		} else {
        			level.add(0, cur.val);						//每次加入队首。ArrayList.add(int index, E elemen)
        		}
				if (cur.left != null)
    				queue.offer(cur.left);
    			if (cur.right != null)
    				queue.offer(cur.right);
        	}
        	isOdd = isOdd ? false : true;							// isOdd是 true 变为 false 是false 变为true
        	ans.add(level);
        }
        return ans;
    }
}


方法二 递归

