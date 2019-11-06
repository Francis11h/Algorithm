package amazonOA1;//打印 2 4 6 8。。。 个 "1"

public class PrintPattern {
    public static void printPattern(int n) {
        int i, j, print = 1;

        for (i = 1; i <= n; i++) {                  //给 for loop 加括号 这里需要注意下 就是 可能会漏加括号 就导致后面的换行符只打印一次
            for (j = 1; j <= 2 * i; j++) {
                System.out.print((print));
            }
            System.out.println();
        }
    }

    // 第二种情况 输出 a ab  abc abcd cout(ch++)
    public static void printPattern2(int row) {
        for (int i = 0; i < row; i++) {
            char ch = 'a';
            char print = ch;
            for (int j = 0; j <= i; j++) {
                System.out.print((print++));        // 之前这里是 ch++ 变为 print++ 就好了
            }
            System.out.println(" ");
        }
    }
    public static void main(String[] args) {
        printPattern(3);
        printPattern2(4);
    }
}


