47. Permutations II

可能有重复数组的全排列
Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

先给数组排序就必不可少了

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


Input:[3, 3, 0, 3]
Output:
[  
  [0,3,3,3],
  [3,0,3,3],
  [3,3,0,3],
  [3,3,3,0]
]


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);									//为了之后去重
        dfs(0, nums, new boolean[nums.length], new ArrayList<>(), ans);
        return ans;
    }
    private void dfs(int level, int[] nums, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (level == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
            	//关键
                if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
                visited[i] = true;
                cur.add(nums[i]);
                dfs(level + 1, nums, visited, cur, ans);
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
    }
}



关键
if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
