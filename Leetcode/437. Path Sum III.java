437. Path Sum III

任意位置开始 任意位置结束 path
分析 按开始位置来count

pathSum() 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，和为目标值的路径总数。
findPath() 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，能凑出几个 '以该节点为路径开头'，和为目标值的路径总数。

class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        //以root为根的 树 中pathSum = target 的个数 =
        //从root开头的 pathSum = target的个数 + 
        //以root.left  为根的 subtree 中pathSum = target 的个数 + 
        //以root.right 为根的 subtree 中pathSum = target 的个数
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int findPath(TreeNode root, int sum) {   //从 root点开始的路径，和为目标的个数
        int res = 0;
        if (root == null) return res;
        if (sum == root.val) res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);

        return res;
    }
}



明白每个函数能做的事，并相信他们能够完成。

写递归的技巧是：明白一个函数的作用并相信它能完成这个任务，
				千万不要跳进这个函数里面企图探究更多细节，否则就会陷入无穷的细节无法自拔。
				你就算浑身是铁，能压几个栈？

按照前面说的技巧，先来定义清楚每个递归函数应该做的事：
pathSum 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，和为目标值的路径总数。
count 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，能凑出几个以该节点为路径开头，和为目标值的路径总数。
class Solution {
	/* 有了以上铺垫，详细注释一下代码 */
	int pathSum(TreeNode root, int sum) {
	    if (root == null) return 0;
	    int pathImLeading = count(root, sum); // 自己为开头的路径数
	    int leftPathSum = pathSum(root.left, sum); // 左边路径总数（相信他能算出来）
	    int rightPathSum = pathSum(root.right, sum); // 右边路径总数（相信他能算出来）
	    return leftPathSum + rightPathSum + pathImLeading;
	}
	int count(TreeNode node, int sum) {
	    if (node == null) return 0;
	    // 我自己能不能独当一面，作为一条单独的路径呢？
	    int isMe = (node.val == sum) ? 1 : 0;
	    // 左边的小老弟，你那边能凑几个 sum - node.val 呀？
	    int leftBrother = count(node.left, sum - node.val); 
	    // 右边的小老弟，你那边能凑几个 sum - node.val 呀？
	    int rightBrother = count(node.right, sum - node.val);
	    return  isMe + leftBrother + rightBrother; // 我这能凑这么多个
	}
}














