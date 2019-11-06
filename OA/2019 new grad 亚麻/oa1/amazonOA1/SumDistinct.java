package amazonOA1;

import java.util.Arrays;

public class SumDistinct {

    public static int helper(int size, int[] inputArray) {
        int sum = inputArray[0];
        Arrays.sort(inputArray);
        int point = inputArray[0];
        for (int i = 1; i < size; i++) {
            if (point != inputArray[i]) {
                sum += inputArray[i];
                point = inputArray[i];
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] inputArray = new int[]{1, 1, 3,2,5, 8, 2};
        System.out.println(helper(inputArray.length, inputArray));
    }
}

