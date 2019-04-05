保留6位小数
题目描述
一堆不同袜子, 用一个长为 L 的数组S来描述
每个数字表示这堆袜子中某一只的颜色, 要是 两者之差的绝对值 <= d,
则可以组成一对儿。

早起 随机拿出两双, 问 可以接受作为一对儿概率。
2 <= L <= 50, 1 <= S[i] <= 50, 0 <= d <= 49



输入是     S = {31, 18, 19, 1, 25}
          d = 10
输出。   0.400000





import java.text.DecimalFormat;
import java.util.*;
public class Main {
    private static String probability(int[] nums, int d) {
        DecimalFormat df = new DecimalFormat("0.000000");

        double count = 0, total = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                total++;
                if (Math.abs(nums[i] - nums[j]) <= d)
                    count++;
            }
        }
        return df.format(count / total);
        //return String.format("%.6f",(count / total));
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] str = s.substring(1, s.length() - 1).replaceAll(" ", "").split(",");
            int[] nums = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                nums[i] = Integer.valueOf(str[i]);
            }
            int d = sc.nextInt();
            System.out.println(probability(nums, d));
        }
    }
}

