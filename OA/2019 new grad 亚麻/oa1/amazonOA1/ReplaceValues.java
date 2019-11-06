package amazonOA1;

import java.util.Arrays;

// 数组越界 最后的 len 不能取    因为是 0-based 所以index只能取到 arr.length - 1
public class ReplaceValues {
    //若 array 长度为奇数全换为 1 偶数换为 0
    public static int[] replaceValue(int[] arr) {
        int i, j, len = arr.length;
        if (len % 2 == 0) {
            for (i = 0; i < len; i++) {         //这里改
                arr[i] = 0;
            }
        } else {
            for (i = 0; i < len; i++) {
                arr[i] = 1;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {20, 20, 10, 40, 50};
        System.out.println(Arrays.toString(replaceValue(arr)));
    }
}
