227. Basic Calculator II

表达式 带 + - * /  空格  不带小括号

Input: "3+2*2"
Output: 7

Input: " 3+5 / 2 "
Output: 5





Solution1 通俗算法 即翻译整个流程 用stack实现操作反转 

栈 实现 
第一个数 入栈 
遇到 + 下一个数 num 入栈 
遇到 - 下一个数 -num 入栈 
遇到 * / 先将上一个数出栈 与当前数进行运算后 再将结果入栈

class Solution {
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();

        int number = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                number = number * 10 + ch - '0';
            }
            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                switch(sign) {
                    case '+' :
                        stack.push(number);
                        break;
                    case '-' :
                        stack.push(-number);
                        break;
                    case '*' :
                        stack.push(stack.pop() * number);
                        break;
                    case '/' :
                        stack.push(stack.pop() / number);
                        break;
                }
                sign = ch;
                number = 0;
            }
        }
        int result = 0;
        for (int num : stack) {
            result += num;
        }
        return result;
    }

}










2019.12.5
Calculator 标准解法     prev, num, sum, prevOp
核心思想是 "calculate delay"

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Queue<Character> q = new LinkedList<>();
        //preprocessing remove whitespaces
        for (char c : s.toCharArray()) {
            if (c != ' ')
                q.offer(c);
        }
        q.offer(' ');
        return helper(q);
    }
    private int helper(Queue<Character> q) {
        int prev = 0, num = 0, sum = 0;
        char prevOp = '+';
        while (!q.isEmpty()) {
            char c = q.poll();
            // num may have many consecutive digtis
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {    
                //when c is a operator, do calculation according to prevOp (previous!!!!)
                //+-
                //if the previous operator is +, merge(add) the prev to sum, then assign num to prev
                //if the previous operator is -, merge(add) the prev to sum, then assign -num to prev
                //*/ 
                //do not modify the sum cause "3+2*2" when we first meet * wo can't deal with the prev, which is 2
                //if the previous operator is *, modify the prev by multiplying the prev by num
                //if the previous operator is /, modify the prev by dividing the prev by num
                //''
                //placeholder, calculate the remain previous operator, then break;
                //and then update the prevOp
                switch (prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }
                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }
}




// "3+2*2" ---> "0+3+2*2 "
q       prev    num     sum     prevOp
begin   0       0       0       '+'
3       0       3       0       '+'
+       3       0       0       '+' //this '+' is updated to a new '+'
2       3       2       0       '+'
*       2       0       3       '*'
2       2       2       3       '*'
' '     4       0       3       ' '
return sum + prev = 3 + 4 = 7

    
    



















