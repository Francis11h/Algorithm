39. Combination Sum

Given a set of candidate numbers (without duplicates) and a target number,
Find all unique combinations in candidates where the candidate numbers sums to the target.

The same repeated number may be chosen from candidates unlimited number of times.
每个候选数字可以重复使用无数次

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    //level 的意义 是取candidates数组中的下标为level的数
    public void dfs(int level, int target, int[] candidates, List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = level; i < candidates.length; i++) {
            if (candidates[i] > target) return;
            cur.add(candidates[i]);
            //因为可以重复使用 所以这里 i 不加
            dfs(i, target - candidates[i], candidates, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}









