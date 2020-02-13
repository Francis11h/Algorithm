150. Evaluate Reverse Polish Notation 逆波兰表达式求值

逆波兰 不含 括号



Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6


(a+b)*c的逆波兰式为 ab+c*

1）a入栈（0位置）
2）b入栈（1位置）
3）遇到运算符“+”，将a和b出栈，执行a+b的操作，得到结果d=a+b，再将d入栈（0位置）
4）c入栈（1位置）
5）遇到运算符“*”，将d和c出栈，执行d*c的操作，得到结果e，再将e入栈（0位置）
经过以上运算，计算机就可以得到(a+b)*c的运算结果e了。





逆波兰是对计算机友好地表达式 对于普通的表达式 可以先转换 or 也可直接用栈

普通表达式的题
224.  Basic Calculator
    (, ), +, -, non-negative integers

227.  Basic Calculator II
    +, -, *, /, non-negative integers

772. Basic Calculator III
    (, ), +, -, *, /, non-negative integers



普通表达式转逆波兰
https://www.lintcode.com/problem/convert-expression-to-reverse-polish-notation/description




class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int substractor = stack.pop();
                int minuend = stack.pop();
                stack.push(minuend - substractor);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int divisor = stack.pop();
                int dividend = stack.pop();
                stack.push(dividend / divisor);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}




2020.2.12

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
        for (String token : tokens) {
            if (!set.contains(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }
            int b = stack.pop();
            int a = stack.pop();

            if (token.equals("+")) {
                stack.push(a + b);
            } else if (token.equals("-")){
                stack.push(a - b);
            } else if (token.equals("*")){
                stack.push(a * b);
            } else if (token.equals("/")){
                stack.push(a / b);
            }
        }
        return stack.pop();
    }
}