package amazonOA1;

public class DistinctElementCount {
    public static int dist(int size, int[] elements) {
        int[] counted = new int[size];
        int count, flag;
        counted[0] = elements[0];
        count = 1;

        for (int i = 0; i < size; i++) {
            flag = 0;
            for (int j = 0; j < count; j++) {
                if (elements[i] == counted[j]) {
                    flag = 1;
                }
            }
            if (flag != 1) {            //这里改下 改成不等于
                count++;
                counted[count - 1] = elements[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 3, 3, 5};
        System.out.println(dist(6, arr));
    }
}























