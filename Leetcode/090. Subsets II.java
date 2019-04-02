090. Subsets II

集合数据 本身可能有重复

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


方法一 连续搜索的 去重 :

先排序的基础上

taken 的含义 : 当前数字 前面的数字 有没有被取到, 如果取到, 则当前数字可取。
                
    若 前面的数字 和 当前数字相同, 且前面的数字没有被取, 那么当前的数字不能取。(因为是回溯回来的)

分两叉 : 不取当前数字, 那么不用判断, 直接dfs(level + 1,...)
        取当前数字, 那么 只有当 1. 该数字与其前面数字不同 or 2. 其前面的数字已经被取过 时 
            再cur.add(),再dfs(level + 1,...), 再回溯

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0, true, nums, cur, ans);
        return ans;
    }
    
    private void dfs(int level, boolean taken, int[] nums, List<Integer> cur, List<List<Integer>> ans) {
        if (level == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        
        if (taken || nums[level] != nums[level - 1]) {
            cur.add(nums[level]);           //取 nums[level] 元素
            dfs(level + 1, true, nums, cur, ans);       // dfs 下一层
            cur.remove(cur.size() - 1);         //不取, 回溯 nums[level] 元素  
        }
        
        dfs(level + 1, false, nums, cur, ans);       // dfs 下一层
    }
}




方法二 跳跃搜索的去重:

也是先排序的基础上
    key 就这一句话:
    if (i > index && nums[i] == nums[i - 1]) continue;

    只有当 i > index 的时候 才计算重复
    那么 i 何时大于 index ？
        =>  for(int i = index; i < nums.length; i++) 走到下一个的时候
            => for 中 i 走到下一个的时候  代表什么？
                => 代表 当前情况 下标开始的点(index)的 所有情况已经考虑完了且回溯了(因为 for 走了两轮了, 所以走过一次回溯了)
                   那么 此时 要是 下一个元素和这个重复, 此时是不是就会有重复的情况!
    e.g. 
    [1, 2, 2] => ans = [[], [1], [1, 2],[1, 2, 2‘], [1, 2‘]] 
                        此时 index = 1, i 由 1 -> 2, 显而易见的重复了。




class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int index, int[] nums, List<Integer> cur, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(cur));
        
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            cur.add(nums[i]);
            dfs(i + 1, nums, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}




