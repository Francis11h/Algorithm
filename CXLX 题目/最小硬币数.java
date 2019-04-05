
给你 m, n, n个数 分别代表一种币值

   求 可以完全表示 1 - m 的 最小硬币数量。
   无解输出 -1.

input :   
	20 4   (m = 20, n = 4)
	1
	2
	5
	10
output => 5


如果没有1就无解。只要有1就一定有解，因为每种硬币是无限多的

注意到大面值硬币是凑不出小面值的，但如果我们要硬币尽量少，大面值的又不能太少。
所以按面值从小到大的顺序讨论，尽量用已经选的硬币凑成大面值硬币的面值-1，如果不能恰好凑成就多用一个。




import java.util.Arrays;

class Main {
    public static void main(String[] args) {


        int m = 20, n = 4;
        int[] arr = new int[]{0, 1, 2, 5, 10};

        Arrays.sort(arr);
        if (arr[1] != 1) System.out.println(-1);

        int sum = 0, ans = 0;
        while (true) {
            if (sum >= m) {
                System.out.println(ans);
                return;
            }
            for (int i = n; i >= 1; i--) {  //从大到小
                if (arr[i] <= sum + 1) {
                    sum += arr[i];
                    ans++;
                    System.out.println(ans);
                    break;
                }
            }
        }
    }
}

