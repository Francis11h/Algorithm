1120. Maximum Average Subtree

Input：
{1,-5,11,1,2,4,-2}
Output：11

The tree is look like this:
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
The average of subtree of 11 is 4.3333, is the maximun.




// maxAverage  ---> class variable

// dfs needs to return both of 
// 1. the sum of the subtree
// 2. the # of nodes in the subtree
// -----> we can use int[2] or a new class to record that

class Solution {
    double maxAverage = Double.MIN_VALUE;
    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return maxAverage;
    }
    
    //return the sum of the subtree whosr root is root 
    // and the # of nodes in the subtree
    // put the two num into an array 
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[] {0, 0};
        int[] ans = new int[2];
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        ans[0] = left[0] + right[0] + root.val;
        ans[1] = left[1] + right[1] + 1;
        maxAverage = Math.max(maxAverage, (double)ans[0] / (double)ans[1]);
        return ans;
    }
}





L597. Subtree with Maximum Average

要求返回的是 该subtree的 root 不再仅仅是求 maxAverage了
这时 就需要每次在 int[2] 的 基础上 再加上一个 TreeNode 所以 用一个类来存

class Solution {
	class Result {
		int sum;
		int size;
		TreeNode node;
		Result(int sum, int size, TreeNode node) {
			this.size = size;
			this.sum = sum;
			this.node = node;
		}
	}

    Result maxAvg = null;

    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) return null;
        dfs(root);
        return maxAvg.node;
    }
    
    //return the sum of the subtree whosr root is root 
    // and the # of nodes in the subtree
    // and the root of the subtree
    // combine three into a Result class
    private Result dfs(TreeNode root) {
        if (root == null) return new Result(0, 0,  null);
        
        Result left = dfs(root.left);
        Result right = dfs(root.right);
        Result ans = new Result(left.sum + right.sum + root.val, left.size + right.size + 1, root);

        if (maxAvg == null || maxAvg.sum * ans.size < ans.sum * maxAvg.size) {
        	maxAvg = ans;
        }
        return ans;
    }
}


