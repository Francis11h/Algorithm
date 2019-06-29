402. Remove K Digits

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.


Input: num = "10200", k = 1
Output: "200"

Input: num = "10", k = 2
Output: "0"



num = 1234567,  k = 3               前面的位上已经都不比后面的大了 移除后面的
output = 1234

num = 1234167,  k = 3               中间有小的位, 改位的值必然要前移
output = 1167

规律 : 应该最先移除 靠前的位上的数字, 如果后面 有比前面小的, 前面的必移除
目标 : 保证小的数尽可能地往前移


If the previous character in stk is larger than the current one,
Then removing it will get a smaller number

class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) return "0";
        if (k >= num.length()) return "0";

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < num.length()) {
            //前面的比后面的大, 则应移除, 本题的关键点
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            //结果 放入stack
            stack.push(num.charAt(i));
            i++;
        }
        //corner case 要是单增 或者 不降, 则前面不会pop, 直接去掉最后几位大的即可
        while (k > 0) {
            stack.pop();
            k--;
        }

        //reconstruct from stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        //remove all the 0 at head
        while (sb.length() > 1 && sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}






