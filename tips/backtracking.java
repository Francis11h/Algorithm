
排列型 dfs 的模板

void dfs(int level, boolean[] visited, nums, cur, ans,...) { // level表示现在处理排列第几个数
    
    if (level == K) {                   // cur.size() == k
        收集或打印结果; 
        return; 
    } 

    for (int i = 0; i < n; ++i) {       // 枚举第level层选择的元素i 
        if (!done[i] && 满足一定条件) {   // e.g. 去重
            done[i] = true;             //记录第i个元素已被选择            
            cur.add(nums[i]);        //记录下第level层选择的元素i
            dfs(level + 1, .....);             //进入下一层递归
            done[i] = false;            //记录第i个元素已被释放
        }
    }
} 






举个 带重复数字 全排列permutation的例子

private void dfs(int level, int[] nums, boolean[] visited, List<Integer> cur, List<List<Integer>> ans) {
    if (level == nums.length) {
        ans.add(new ArrayList<>(cur));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (!visited[i]) {
            //要在 sort基础上 才可以这么判断去重
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
            visited[i] = true;
            cur.add(nums[i]);
            dfs(level + 1, nums, visited, cur, ans);
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }
}




