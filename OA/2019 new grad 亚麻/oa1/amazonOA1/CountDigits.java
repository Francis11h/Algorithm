package amazonOA1;

public class CountDigits {
    public static int helper(int num) {
        int count = 0;
        int temp = num;                 //num 改变了 要记录下
        while (num != 0) {
            num = num / 10;
            count++;
        }
        return temp % count;
    }

    public static void main(String[] args) {
        System.out.println(helper(122));
    }
}
