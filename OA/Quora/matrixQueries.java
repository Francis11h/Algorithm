
matrixQueries

matrix里面每一个vector<int>的形式必定是[l,r,target]，固定只有3个数。
然后要求统计array里 index从l 到 r这个区间出现了多少次target这个数。 比如:

array = [1,1,2,3,2]
matrix = [[1,2,1], 
          [2,4,2], 
          [0,3,1]]
output : 5
因为在 matrix[0], array的index 1到2区间出现1 一次
      matrix[1], array的index 2到4区间出现2 两次
      matrix[2], array的index 0到3区间出现1 两次


注意TLE
这个题如果直接暴力解O(n*n)会有两个test case过不了

用hashmap<​​​​​​​​​​​​​​​​​​​int, vector<pair<int,int>>>

key是target
value是index区间
这样走一遍array
每次确定一下当前index在不在区间里就行了




import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 2, 3, 2};
        int[][] matrix = new int[][] {
                {1, 2, 1},
                {2, 4, 2},
                {0, 3, 1}
        };
        System.out.println(helper(nums, matrix));
    }

    private static int helper(int[] nums, int[][] matrix) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] tuple : matrix) {
            int num = tuple[2];
            if (!map.containsKey(num)) {
                map.put(num, new ArrayList<>());
            }
            map.get(num).add(new int[] {tuple[0], tuple[1]});
        }
        int ans = 0;
        for (int key : map.keySet()) {
            List<int[]> temp = map.get(key);
            for (int[] cur : temp) {
                for (int i = cur[0]; i <= cur[1]; i++) {
                    if (nums[i] == key) ans++;
                }
            }
        }
        return ans;
    }
}

确实 比 O(n*n) 要优秀 因为 后面 搜的区间少了




（O(mn), m = len(query), n = len(arr)）
前辈说的k到(from, to)的hashmap我不太明白怎么写
但是可以用最朴素的DP迅速解决这道题
  1）扫描一遍query得到所有待查询的值做成set
  2）维护一个dict，其中给每个set中的元素创建一个长度为len(arr)的DP cache list，初始值全是0
  3）扫描arr，每次loop（设第idx次loop）都有dict[k][idx] = dict[k][idx-1]，k为dict中每一个元素
    然后额外的，当前arr[idx]的值p要额外+1：dict[p][idx] += 1。
    这样就给每个元素建立了从1到每个pos的区间个数和的DP cache。
  设query为(from, to, k), 则最终输出dict[k][to] - dict[k][from-1]之和即可
  时间复杂度降为O(m'n)，m'为所有待查询值的种类。由于可能有很多同一个k值重复查询，使得'm'<<m，因此得到了一定的优化




