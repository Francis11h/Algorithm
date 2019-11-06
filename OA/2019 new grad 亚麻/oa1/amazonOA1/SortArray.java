package amazonOA1;

import java.util.Arrays;


public class SortArray {
    // 排序 升降顺序 搞反
    // selection sort 每次选最大的 放在首位
    public static int[] sort(int[] arr) {
        int i, max, location, j, temp, len = arr.length;
        for (i = 0; i < len; i++) {
            max = arr[i];
            location = i;
            for (j = i; j < len; j++) {
                if (max < arr[j]) {         //这里 之前 > 改成 <
                    max = arr[j];
                    location = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[location];
            arr[location] = temp;
        }
        return arr;
    }

    //bubble sort 向前冒泡
    public static int[] sort2(int[] arr) {
        int len = arr.length;
        int small, pos, i, j, temp;

        for (i = 0; i <= len - 1; i++) {
            for (j = i; j < len; j++) {
                temp = 0;
                if (arr[i] < arr[j]) {          //这里之前 > 改 <
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {20, 20, 10, 40, 50};
        System.out.println(Arrays.toString(sort2(arr)));
    }
}
