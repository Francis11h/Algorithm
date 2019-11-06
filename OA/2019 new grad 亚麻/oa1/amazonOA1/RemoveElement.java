package amazonOA1;

import java.util.Arrays;

public class RemoveElement {

    public static int[] helper(int[] arr, int index) {
        int i, j, len = arr.length;
        if (index < len) {
            for (i = index; i < len - 1; i++) {
                arr[i] = arr[i + 1];        //之前这里是 i++ 因该改为 i+1 就是 不能改变i本身的值
            }
            int[] rarr = new int[len - 1];
            for (i = 0; i < len - 1; i++) {
                rarr[i] = arr[i];
            }
            return rarr;
        } else {
            return arr;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(helper(arr, 2)));
    }
}
