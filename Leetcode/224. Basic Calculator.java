224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .


Input: " 2-1 + 2 "
Output: 3

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Input: " 1 - (-3) "
Output: 4



224.  Basic Calculator
    (, ), +, -, non-negative integers

227.  Basic Calculator II
    +, -, *, /, non-negative integers

772. Basic Calculator III
    (, ), +, -, *, /, non-negative integers







第一种解法 用一个栈 
碰到'(' 存之前的 sum 和 sign (+1 / -1)


Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
Only 5 possible input we need to pay attention:

digit: 	it should be one digit from the current number
'+': 	number is over, we can add the previous number and start a new number
'-': 	same as above
'(': 	push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
')':	pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.

Finally if there is only one number, from the above solution, we haven‘t add the number to the result, so we do a check see if the number is zero.

number表示当前的操作数, sign表示当前的操作数应该被加还是被减, result表示结果



a.碰到数字	则追加到number尾端
b.碰到"+"	说明上一个数字已经完全被计算至number, 这时应该把number * sign加到result中, 然后把sign置为1 (因为当前碰到了加号)
c.碰到'-',   同上, 不同的在于最后要把sign置为 -1,
d.碰到'(', 	说明这时要优先出右边的表达式, 需要将result和sign压入栈中(注意, 此时的sign表示的是这个括号内的表达式应该被result加上还是减去), 然后初始化result和sign, 准备计算括号内的表达式
e.碰到')', 	说明一个括号内的表达式被计算完了, 此时需要从栈中取出该括号之前的sign和result, 与当前的result相加运算 (注意, 是原来的result + sign * 当前result)

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int number = 0, sign = 1, result = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                number = number * 10 + ch - '0';
            } else if (ch == '+') {
                result += number * sign;
                sign = 1;
                number = 0;
            } else if (ch == '-') {
                result += number * sign;
                sign = -1;
                number = 0;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;           //case "1-(5)" 
                //number = 0;		同时 左括号前必定有 +/- 号 或者在开头 那会儿 number都是0 所以不用在 number = 0 当然加上肯定不会错
            } else if (ch == ')') {
                int prevSign = stack.pop();
                int prevRes = stack.pop();
                result += number * sign;
                result = prevRes + result * prevSign;
                number = 0;
            }
        }
        
        return number == 0 ? result : result + number * sign;
    }
}

--------------------------------------------------------------------------------------------------------------------------------------------




第二种解法 双栈法

Ideas


1.Compute the parenthesis level of each operator.

2.Use a stack to maintain the execution order of operators
	a. if top operator < current, push
	b. if top operator > current, pop
3.Use a separate stack to maintain operands
	a. when we scan an operand, push
	b. when we pop an operator, pop two operands and push the result

如果题目输入字符串是逆波兰表达式，那这个题就很简单了。直接采用一个栈即可，遇到操作符就计算，遇到右括号，就将里面的串计算完成后，再和左括号成对的退出。但是这道题 ，它不是逆波兰表达式，它是一个用户友好的表达式。 这样我们就需要用别的解法了。

一个很直观的思维就是， 把操作符和数字分开存到两个栈中，需要计算的时候，从数字栈中弹出两个数字，从操作数栈中弹出一个元素，计算完成后再放入数字栈中。



public static int calculate(String s) {
    if (s == null || s.length() == 0) {
        return -1;
    }
    // 操作数栈，遇到+,-和(, 就入栈
    Stack<Character> operatorStack = new Stack<>();

    // 数字栈，遇到数字放入
    Stack<Integer> digitStack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        // 为空格直接跳过
        if (c == ' ') {
            continue;
        }
        // 如果是正括号或者加减号，则直接入栈
        if (c == '(' || c == '+' || c == '-') {
            operatorStack.push(c);
        } else if (c == ')') {
            // 开始弹出并计算
            operatorStack.pop();
            // 触发连续计算。 如果操作符栈顶有+或-，则弹出计算，计算完成后，放到digitStack中
            while (operatorStack.size() > 0 && isOperator(operatorStack.peek())) {
                int b = digitStack.pop();
                int a = digitStack.pop();
                digitStack.push(calc(a, b, operatorStack.pop()));
            }
        } else {
            // 数字，求最长连续数字串
            int digitTotal = 0;
            int j = i;
            for (; j < s.length(); j++) {
                char tmpChar = s.charAt(j);
                if (isDigit(tmpChar)) {
                    digitTotal = 10 * digitTotal + (tmpChar - '0');
                } else {
                    break;
                }
            }
            // 判断是否要计算一波。如果操作数栈为空或者栈顶不为+或-，则直接数字入栈
            if (operatorStack.isEmpty() || !isOperator(operatorStack.peek())) {
                digitStack.push(digitTotal);
            }
            // 如果操作符栈顶有+或-，则弹出计算，计算完成后，放到digitStack中
            while (operatorStack.size() > 0 && isOperator(operatorStack.peek())) {
                Character cc = operatorStack.pop();
                Integer d = digitStack.pop();
                digitStack.push(calc(d, digitTotal, cc));
            }
            i = j - 1;
        }
    }
    // 返回数字栈顶元素，即为结果
    return digitStack.pop();
}

private static int calc(int a, int b, char op) {
    if (op == '+') {
        return a + b;
    } else {
        return a - b;
    }
}

private static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
}
private static boolean isOperator(char c) {
    return c == '+' || c == '-';
}




--------------------------------------------------------------------------------------------------------------------------------------------



2019.12.5 / 2020.2.11


Calculator 通用解法
       prev, num, sum, prevOp
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
        // add place holder otherwise the last operator will be omission
        // 把 num的值 转化给 prev 把prev加到sum 因为最后return的是 prev+sum 和 num无关了
        q.offer(' ');
        return helper(q);
    }
    
    private int helper(Queue<Character> q) {
        int prev = 0, num = 0, sum = 0;
        char prevOp = '+';
        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = helper(q);
            } else {
                switch(prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                }
                if (c == ')') break;
                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }
}




0 + 3 - 2 ''

prev   num    sum   prevOp
0       0      0     '+'        begin
0       3      0     '+'        first       ch == '3'
3       0      0     '-'        second      ch == '-'
3       2      0     '-'        third       ch == '2'
-2      0      3     ' '        fourth      ch == ' ' (placeholder)

return sum + prev = 3 + (-2) = 1






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



// "3-(1-2)" ---> "0+3-(1-2) "
// q       prev    num     sum     prevOp
// begin   0       0       0       '+'
// 3       0       3       0       '+'
// -       3       0       0       '-' 
// "0+3- -1 "
// -1      3       -1      0       '-'
// ' '     1       0       3       ' ' 
// return sum + prev = 3 + 1 = 4

// call stack
// "0+1-2) "
// q       prev    num     sum     prevOp
// begin   0       0       0       '+'
// 1       0       1       0       '+'
// -       1       0       0       '-' 
// 2       1       2       0       '-'
// )       -2      0       1
//return sum+prev = -1 

