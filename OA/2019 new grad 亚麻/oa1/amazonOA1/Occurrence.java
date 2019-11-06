package amazonOA1;

public class Occurrence {

    // 错误原因 TLE 死循环 while条件里的变量 i 没有随着每次循环而改变 所以 死循环
    public static int countOccurrence(int[] arr, int value) {
        int i = 0, count = 0, len = arr.length;
        while (i < len) {
            if (arr[i] == value) {
                count += 1;
            }
            i++;        //这里加上 i++
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {20, 20, 10, 40, 50};
        System.out.println(countOccurrence(arr, 20));
    }
}
