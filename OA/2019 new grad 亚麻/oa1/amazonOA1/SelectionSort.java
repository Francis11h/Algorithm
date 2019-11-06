package amazonOA1;

import java.util.Arrays;

public class SelectionSort {
    public static int[] sort(int[] arr) {
        int x, y, n = arr.length;
        for (x = 0; x < n; x++) {
            int index_or_min = x;
            for (y = x; y < n; y++) {
                if (arr[index_or_min] > arr[y]) {           //最开始 这个错误是 arr[x] 因为没有搞清楚 第二层循环里面才是要找的最小数 外层x是最后要放的位置
                    index_or_min = y;
                }
            }
            int temp = arr[x];
            arr[x] = arr[index_or_min];
            arr[index_or_min] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {20, 20, 10, 40, 50};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
