package amazonOA1;

import java.util.Arrays;

// bug 原因 len变动不清晰 in-place 变换 两个指针走的不准确

public class ReverseArray {

    public static int[] reverseArray1(int[] arr) {
        int i, temp, originalLen = arr.length;
        int len = originalLen;
        for (i = 0; i < originalLen / 2; i++) {
            temp = arr[len - 1 - i];                //或者 arr[len - 1]都改成arr[len-i-1], 并且去掉len+=1
            arr[len - 1 - i] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static int[] reverseArray2(int arr[]) {
        int i, temp, originalLen = arr.length;
        int len = originalLen;
        for (i = 0; i < originalLen / 2; i++) {
            temp = arr[len - 1];
            arr[len - 1] = arr[i];
            arr[i] = temp;
            len -= 1;                   //这里改动
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {20, 30, 10, 40, 50};
        System.out.println(Arrays.toString(reverseArray2(arr)));
    }

}
