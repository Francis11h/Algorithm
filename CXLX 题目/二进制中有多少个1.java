二进制中有多少个1
把一个32-bit整型转成二进制，其中包含多少个1，比如5的二进制表达是101，其中包含2个1

5的二进制是101，其中包含2个1

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            System.out.println(test(num));
        }
    }

    public static int test(int num) {
        int ans = 0;
        while (num != 0) {
            if ((num & 0x1) == 1) ans++;
            num = num >> 1;
        }
        return ans;
    }
}


