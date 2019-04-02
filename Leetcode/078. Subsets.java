078. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).
数组 无重复, 求所有 可能子集

Note: The solution set must not contain duplicate subsets.


Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

方法一: 连续搜索
		每层level, 处理一个元素(对应在数组中是nums[level])
		可以 取该元素, 或者 不取该元素
		两种方法 分别 开叉(分支) : 两个 子dfs。
		然后 取第n层的结果

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(0, nums, cur, ans);
        return ans;
    }
    
    private void dfs(int level, int[] nums, List<Integer> cur, List<List<Integer>> ans) {
        if (level == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        cur.add(nums[level]);						//取 nums[level] 元素
        dfs(level + 1, nums, cur, ans);				// dfs 下一层
        cur.remove(cur.size() - 1);  				//不取, 回溯 nums[level] 元素
        dfs(level + 1, nums, cur, ans);				// dfs 下一层
    }
}


DFS的时间复杂度分析  :
	解的个数 * 得到每个解所花的平均时间 
	解的个数:2^n
	得到每个解所花的时间:n  
	时间复杂度为:O(n * 2 ^ n) 

两种方法 Time 一样


方法二: 跳跃搜索
	如果现在取了一个元素, 那么 下一层会从它的下一个元素开始处理
	[1, 2, 3]	当前加 [], 后面只可能加 1 or 2 or 3
				当前加  1, 后面只可能加 2 or 3
				当前加  2, 后面只可能加 3

				因为每次 dfs 都要往 ans 放值, 所以要保证每次 取后的情况 之前都没有重复

	index 的含义 : 开始start 取的位置, 最开始是0, 后面每次递归调用dfs时 都会把 i + 1 赋值给 index, 
					表示从当前位置的后一个开始取, i 表示 当前位置。
	每一层能取几个值，和 index 有关, for (index -> nums.length - 1) 里面有几个, 能取几个	


public class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int index, int[] nums, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        
        //if(index >= nums.length) return; 出口 但是dfs在for循环内，for循环结束了，就出口
        
        for (int i = index; i < nums.length; ++i) {
            cur.add(nums[i]);
            //选了 1， 后面只能选23，所以从i + 1
            dfs(i + 1, nums, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}

