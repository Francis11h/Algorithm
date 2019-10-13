40. Combination Sum II

Given a set of candidate numbers (may have duplicates) and a target number,
Find all unique combinations in candidates where the candidate numbers sums to the target.

Each number in candidates may only be used once in the combination.
每个候选数字仅可以使用1次

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<>(), res);
        return res;
    }

    //level 的意义 当前走到的层数 表示的是 结果中的第几个数 
    public void dfs(int level, int target, int[] candidates, List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = level; i < candidates.length; i++) {
            // i > level 处理corner case [1,1,1] target = 2
            // 在当前深度 如果有重复 需要跳过 
            // i > level 表示的是 i = level 之后 所有的更深层dfs 都已经结束 回溯了 然后在这个for中 我们没有取 candidates[level]
            // 然后 如果 后面的元素 == 前面的元素的话 我们就不可以再计算一次了 因为 当前深度 我已经用过该元素了
            if (i > level && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > target) return;
            cur.add(candidates[i]);
            //因为不能重复使用 所以这里 i 加 1 表示选下一个数字
            dfs(i + 1, target - candidates[i], candidates, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}






