package amazonOA1;

public class EvenOddPattern {
    public static void helper(int num) {
        int i, print = 0;
        if ((num % 2) == 0) {
            print = 0;
            for (i = 0; i < num; i++) {                 //for 的大括号缺失
                System.out.print(print + " ");
                print += 2;
            }

        } else {
            print = 1;
            for (i = 0; i < num; i++) {
                System.out.print(print + " ");
                print += 2;
            }

        }
    }

    public static void main(String[] args) {
        helper(3);
    }
}
