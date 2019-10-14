52. N-Queens II

改为求个数了 更简单
就不需要 board[][] 了 直接 抽想着求即可

class Solution {
    
    int ans = 0;
    Set<Integer> sum = new HashSet<>();
    Set<Integer> diff = new HashSet<>();
    Set<Integer> col = new HashSet<>();
    
    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        search(0, n);
        return ans;
    }
    
    private void search(int level, int n) { //每个level带表第几行
        if (level == n) {
            ans++;
            return;
        }
        
        // try to put queen at level i
        // 每个i代表 一列（每一行中能走的状态，就是8列！！）
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !diff.contains(level - i) && !sum.contains(level + i)) {
                col.add(i);
                diff.add(level - i); //主对角线方向 行-列 相同
                sum.add(level + i); //副对角线 行+列 相同
                search(level + 1, n);
                col.remove(i);
                diff.remove(level - i);
                sum.remove(level + i);
            }
        }

    }
}