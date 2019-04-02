
77. Combinations
组合, n个 无重复数 中 选k个 求所有组合                

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

方法一 : 连续搜索

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(0, k, n, cur, ans);
        return ans;
    }
    
    private void dfs(int level, int k, int n, List<Integer> cur, List<List<Integer>> ans) {
        if (level == n) {
            if (cur.size() == k) {
               ans.add(new ArrayList<>(cur));
            }
            return;
        }
        
        cur.add(level + 1);                         //取 nums[level] 元素
        dfs(level + 1, k, n, cur, ans);             // dfs 下一层
        cur.remove(cur.size() - 1);                 //不取, 回溯 nums[level] 元素
        dfs(level + 1, k, n, cur, ans);             // dfs 下一层
    }
}


方法二 : 跳跃搜索

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, n, k, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int index, int n, int k, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < n; ++i) {
            cur.add(i + 1);
            //选了 1， 后面只能选23，所以从i + 1
            dfs(i + 1, n, k, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}


n个 无重复数 中 选k个 求所有排列

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0) return null;
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        dfs(nums, k, new boolean[n], new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int[] nums, int k, boolean[] done, List<Integer> tmp, List<List<Integer>> ans) {
        if (tmp.size() == k) {
            ans.add(new ArrayList<>(tmp));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!done[i]) {
                tmp.add(nums[i]);
                done[i] = true;
                dfs(nums, k, done, tmp, ans);
                done[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    } 
}



思路很简单，所有的组合就是一个以[1,2,...k] 开始，以[n-k,n-k+1,...n]
结束的组合序列，以n=4，k=2为例，第一个就是[1,2],最后一个就是[3,4]
那么中间的组合就是以[1,2]为基础，每次给最后一个数+1，比如[1,2]的下一个就是[1,3]。
当最后一个数大于n时，则进行"进位",也就是给倒数第二个数加一，
从右往左数第i（以0开始）个位置上的数最大值是n-i，如果超过最大值，就将前一个数加一

public static List<List<Integer>> combine(int n, int k) {
    LinkedList<List<Integer>> list = new LinkedList<>();
    if(k>n) {
        return list;
    }
    // 组合缓存，使用数组表示的组合
    Integer[] cache = new Integer[k];
    // 初始化组合为1...k
    for(int i=0; i<k; i++) {
        cache[i]=i+1;
    }
    // 条件也可以设置为initCombine[0]<=n-k。在循环内判断可以少执行一次对cache的赋值
    while(true) {
        // 根据cache生成组合加入最终列表
        ArrayList<Integer> combine = new ArrayList<>(k);
        combine.addAll(Arrays.asList(cache));
        list.add(combine);
        // 从右向左判断，如果数值超过本位置的最大可取值，则继续向左
        int i = 0;
        while(i<k && cache[k-1-i]+1>(n-i)) {
            i++;
        }
        // 当i=k时，表示下一个组合的第一个数字已经超过最大值，说明组合已经遍历完了。
        if(i==k) {
            break;
        }
        cache[k-1-i]++;
        // 对于加一后的数组，数值都是前一位数值加一
        for(int j=k-i; j<k; j++) {
            cache[j]=cache[j-1]+1;
        }
    }
    return list;
}
