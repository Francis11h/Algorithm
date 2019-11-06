package amazonOA1;

import java.util.Arrays;

// 等于 和 不等于 这个逻辑搞混
// 改两处
// 如果 arr[i] == arr[i - 1] 该位就输出0 不同输出1
public class Signal {
    public static int[] manchester(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];
        boolean result;
        res[0] = (arr[0] != 0) ? 1 : 0;         // 0 那句不能直接 res[0] = 0 要 res[0] = (arr[0] != 0) ? 1:0
        for (int i = 1; i < len; i++) {
            result = (arr[i] != arr[i - 1]);    //后面 for 循环里判断是要把 arr == arr[i-1]改成 arr != arr[i-1]
            res[i] = result ? 1 : 0;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {0, 1, 0, 0, 1, 1, 1, 0};
        System.out.println(Arrays.toString(manchester(arr)));
    }
}
