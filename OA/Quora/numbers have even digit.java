5.	Find how many numbers have even digit in a list.

Ex.Input: A = [12, 3, 5, 3456]
Output: 2

Ex.Input: A = [12, 32, 65, 3456]
Output: 4


public class Test {

    public static void main(String[] args) {
        int[] A = new int[]{12, 32, 65, 3456};
        System.out.println(helper(A));
    }

    public static int helper(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            while (num != 0) {
                int digit = num % 10;
                num /= 10;

                if ((digit & 1) == 0) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
