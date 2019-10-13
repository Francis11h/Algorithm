排列组合总结


组合 

39. Combination Sum
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

Input: candidates = [2,3,6,7], target = 7,

[2, 2, 3]
[7]

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int[] candidates, int target, int level,List<Integer> cur, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = level; i < candidates.length; i++) {
            if (target < candidates[i]) return;
            cur.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}

40. Combination Sum II
Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.
[1, 1, 1, 2, 2] target = 4,

[1, 1, 2]
[2, 2]

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int level, int[] candidates, int target, List<Integer> cur, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = level; i < candidates.length; i++) {
            if (target < candidates[i]) return;
            // i > level 处理corner case [1,1,1] target = 2
            // 在当前深度 如果有重复 需要跳过 
            // i > level 表示的是 i = level 之后 所有的深层dfs 都已经结束 回溯了 然后在这个for中 我们没有取 candidates[level]
            // 然后 如果 后面的元素 == 前面的元素的话 我们就不可以再计算一次了 因为 当前深度 我已经用过该元素了
            if (i > level && candidates[i] == candidates[i - 1]) continue;
            cur.add(candidates[i]);
            dfs(i + 1, candidates, target - candidates[i], cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}




78. Subsets
Given a set of distinct integers, nums, return all possible subsets (the power set).

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

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int[] nums, int level, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        
        for (int i = level; i < nums.length; i++) {
            cur.add(nums[i]);
            dfs(nums, i + 1, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}



90. Subsets II

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int[] nums, int level, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        
        for (int i = level; i < nums.length; i++) {
            // 同理 [1, 2, 2]  第一个 [1, 2(1)] 可以取 然后 [1, 2(1), 2(2)] 然后回溯 
            // 然后 这个 [1, 2(2)] 这个就是 重复的 
            // 在相同的深度(level = 2 即 结果的第二个元素) 不能用相同的  所以跳过
            if (i > level && nums[i] == nums[i - 1]) continue;
            cur.add(nums[i]);
            dfs(nums, i + 1, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}



77. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, k, n, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int index, int k, int n, List<Integer> cur, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = index; i <= n + 1 - k; i++) {
            cur.add(i);
            dfs(i + 1, k - 1, n, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}



216. Combination Sum III
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used 

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int index, int target, int k, List<Integer> cur, List<List<Integer>>ans) {
        //if (cur.size() > k) return;
        if (cur.size() == k && target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = index; i < 10; i++) {
            cur.add(i);
            dfs(i + 1, target - i, k, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}






46. Permutations
Given a collection of distinct integers, return all possible permutations.
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(0, nums, visited, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int level, int[] nums, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
        if (level == nums.length) {
            ans.add(new ArrayList<>(cur));
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


47. Permutations II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
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
                // 也是 前面的元素 如果没选 则是 回溯后的  然后 我再选相同的 就会导致重复
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