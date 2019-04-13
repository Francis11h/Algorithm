求 N！末尾第一个 非0数字

6！= 720
=> 2

N <= 100,0000

import java.util.*

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Integer n = sc.nextInt();
            System.out.println(factorial(n));
        }
    }

    public static int factorial(Integer n) {
        int count = 1;
        int k1;
        int k2 = 0;

        for (int i = 5; i <= n; i++) {
            int m = i;
            while (m % 5 == 0) {
                m /= 5;
                k2++;
            }
        }
        k1 = k2;

        for (int i = 1; i <= n; i++) {
            int m = i;
            while (m % 2 == 0 && k1 != 0) {
                m /= 2;
                k1--;
            }
            while (m % 5 == 0 && k2 != 0) {
                m /= 5;
                k2--;
            }
            m %= 10;
            count = (count * m) % 10;
        }

        return count;
    }
}

//import java.math.BigInteger;
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        Integer n = sc.nextInt();
//
//        Integer t=1;
//
//        BigInteger b1 = new BigInteger(t.toString());
//        for(Integer i = 2;i<=n;i++) {
//            b1 = b1.multiply(new BigInteger(i.toString()));
//        }
//        String str = b1.toString();
//        for(int i = str.length()-1;i >= 0;i--) {
//            if(str.charAt(i) != '0') {
//                System.out.println(str.charAt(i));
//                break;
//            }
//        }
//    }
//}