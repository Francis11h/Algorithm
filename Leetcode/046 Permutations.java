46. Permutations
[1, 2, 3] 无重复 数组的全排列

class Solution {
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> ans = new ArrayList<>();
    	dfs(0, nums, new boolean[nums.length], new ArrayList<>(), ans);
    	return ans;
    }

    private void dfs(int level, int[] nums, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
    	if (level == nums.length) {
    		ans.add(new ArrayList<Integer>(cur));
    		return;
        }
    	for (int i = 0; i < nums.length; i++) {
    		if (!visited[i]) {
    			visited[i] = true;
    			cur.add(nums[i]);
    			dfs(level + 1, nums, visited, cur, ans);
    			cur.remove(cur.size() - 1);
    			visited[i] = false;
    		}
    	}
    }
}