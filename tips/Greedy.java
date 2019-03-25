每一步选择中, 都采取在当前状态(本阶段)下最好的选择, 从而希望导致结果是最好的解法。

贪心算法 在有 最优子结构的问题中尤为有效。
(最优子结构：局部最优解 能决定 全局最优解 => 问题能够分成子问题来解决, 且子问题的最优解能递推到最终问题的最优解)

与dp 不同的是 greedy 在于 它对每个子问题的解决方案 都做出选择, 不能回退。
dp 则会保存以前的运算结果， 并根据以前的结果对当前进行选择，有回退功能。


动态规划两大性质:   最优子结构 + 重叠子问题

贪心算法两大性质:   最优子结构 + 贪心选择



最经典 greedy 活动选择 activity select。
activities a1, a2, ... ,an
each with s starting time: s1, s2, ... ,sn
each with a finishing time: f1, f2, ...,fn

participate as many as possible
if fi < sj or fj < si you can do both ai and aj

Sij = set of activities between ai and aj, if i >= j Sij = null;

subproblems: c[i, j] : the largest # of act we can do from the set Sij

two dummy act : a0 and an+1 : f0 = 0, sn+1 = infinite
so c[0, n+1] is our answer


dp formula
c[i, j] =   0                                           i = j - 1
            max(c[i, k] + c[k, j] + 1) (i < k < j)      i < j - 1
O(n^3)


greedy choice

you can make it before any subproblems
and once you do that, you only have one subproblem left

每次选择 最早结束的活动

activitySelection(n)
    sort acts by finish time
    #first select the first act to finish
    k = 1
    A = {a1}
    # then look remaining acts in order of finish time
    for m : 2 => n
        #判断下能选吗
        if sm >= fk (要选的开始 >= 上一个已选的结束,那么可选)
            A = A U {am}
            k = m
    return A

T: O(nlogn) + O(n) 
S: O(n)





算法导论例子 完整代码

public class Test {
    private static int greedySelector(int[] s, int[] f, boolean[] a) {
        int n = s.length - 1;
        a[1] = true;
        int j = 1;
        int count = 1;
        for (int i = 2; i <= n; i++) {
            if (s[i] >= f[j]) {
                a[i] = true;
                j = i;
                count++;
            } else {
                a[i] = false;
            }

        }
        return count;

    }

    public static void main(String[] args) {
        int[] s = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
        int[] f = { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        boolean[] a = new boolean[s.length];

        int result = greedySelector(s, f, a);
        System.out.println("Result is: " + result);
        for (int i = 1; i <= s.length - 1; i++) {
            if (a[i]) {
                System.out.println("第" + i + "活动被选中，其开始时间为：" + s[i] + "，结束时间为：" + f[i]);
            }
        }

    }
}




