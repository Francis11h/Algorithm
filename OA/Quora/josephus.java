
假设有n个人在玩类似于左轮枪转圈的游戏。
每个人有个编号
从一开始
每一轮当前玩家中第k个被淘汰
直到只剩下一名玩家。


典型的josephus问题 不过变化为输出list而不是最后存活的index



直接用 list 模拟过程

每次 更新 start 和 nums 长度 即可



import java.util.*;

public class Test {

    public static void main(String[] args) {
        System.out.println(joseohus(5, 3));
    }

    public static List<Integer> joseohus(int n, int k) {
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int start = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            start = (start + k - 1) % nums.size();
            ans.add(nums.remove(start));

        }

        return ans;
    }

}













