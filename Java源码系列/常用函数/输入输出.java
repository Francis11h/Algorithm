java 输入

Scanner 类种 方法
next()  取得一个字符串
hasNext()   是否还有输入
nexInt()    将取得的字符串转换成int类型的函数
nextFloat()     将取得的字符串转换为float型
nextBoolean()   将取得的字符串转换为boolean型

Scanner取得输入的依据是空格符，包括 空格键，Tab键，Enter键，按下其中任意一键时，Scanner就会返回下一个输入。
所以 当输入的内容中包括空格时，显然，用Scanner就得不到完整的字符串

// import java.util.Scanner;

// public class Test {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         while (sc.hasNext()) {
//             int m = sc.nextInt();
//             String str = sc.next();
//             for (int i = 0; i < m; i++) {
//                 System.out.println("输出");
//                 System.out.println(sc.nextInt());
//             }
//             System.out.println("666");
//             System.out.println(str);
//         }
//     }
// }



Math

Math.abs();

Math.log(value) / Math.log(base);
求 log2X
Math.log(x) / Math.log(2);