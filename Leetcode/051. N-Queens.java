51. N-Queens

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.


class Solution {
    
    List<List<String>> results = new ArrayList<>();
    Set<Integer> sum = new HashSet<>();
    Set<Integer> diff = new HashSet<>();
    Set<Integer> col = new HashSet<>();
    char[][] borad = null;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return results;
        borad = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                borad[i][j] = '.';
            }
        }

        search(0, n);
        return results;
    }
    
    private void search(int level, int n) { //每个level带表第几行
        if (level == n) {
            List<String> now = new ArrayList<>();

            //把char数组的每一行都分别变为String，用 String.valueOf()
            for (int i = 0; i < n; i++) {
                now.add(String.valueOf(borad[i]));
            }
            results.add(now);
            return;
        }
        
        // try to put queen at level i
        // 每个i代表 一列（每一行中能走的状态，就是8列！！）
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !diff.contains(level - i) && !sum.contains(level + i)) {
                col.add(i);
                diff.add(level - i); //主对角线方向 行-列 相同
                sum.add(level + i); //副对角线 行+列 相同
                borad[level][i] = 'Q';
                
                search(level + 1, n);

                col.remove(i);
                diff.remove(level - i);
                sum.remove(level + i);
                borad[level][i] = '.';
            }
        }

    }
}