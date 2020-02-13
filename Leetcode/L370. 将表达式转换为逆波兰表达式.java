370. 将表达式转换为逆波兰表达式

输入: ["3", "-", "4", "+", "5"]
输出: ["3", "4", "-", "5", "+"]
解释: 3 - 4 + 5 = -1 + 5 = 4
    3 4 - 5 + = -1 5 + = 4


输入: ["(", "5", "-", "6", ")", "*", "7"]
输出: ["5","6","-","7","*"]
解释: (5 - 6) * 7 = -1 * 7 = -7
    5 6 - 7 * = -1 7 * = -7

将一个普通的中序表达式转换为逆波兰表达式的一般算法是

一个栈 存运算符 operatorStack		负责比较 priority(先用一个 Map 存下) 

一个栈 负责存答案 numStack

1. 取出的是 操作数 operand, 直接入num栈
2. 取出的是 操作符 operator, 则将该 符号与 operatorStack	栈顶元素比较 
	如果该 运算符 大于 栈顶运算符的优先级 则直接将该运算符存入operatorStack栈
	否则 将operatorStack栈顶运算符 弹出 送入 numStack中 直到 operatorStack栈顶运算符 低于(不包含等于) 该运算符优先级
	最后将该运算符 送入 operatorStack	
3. 取出的是 左括号, 则直接送入 operatorStack 栈顶
4. 取出的是 右括号, 则将距离 operatorStack 栈栈顶最近的"("之间的运算符
	,逐个出栈 依次送入 numStack栈, 此时抛弃"(".

最后 再把 numStack 处理下



class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public List<String> convertToRPN(String[] expressions) {
        if (expressions == null || expressions.length == 0) return null;
        Map<String, Integer> priority = new HashMap<>();
        priority.put("*", 2);
        priority.put("/", 2);
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("(", 0);
        priority.put(")", 0);

        Stack<String> numStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String expression : expressions) {
        	if (!priority.containsKey(expression)) {
        		numStack.push(expression);
        	} else {
        		if (expression.equals("(")) {
        			operatorStack.push(expression);
        		} else if (expression.equals(")")) {
        			while(!operatorStack.peek().equals("(")) {
        				numStack.push(operatorStack.pop());
        			}
        			operatorStack.pop();
        		} else {
        			while (!operatorStack.isEmpty() && 
        					priority.get(operatorStack.peek()) >= priority.get(expression)) {
        				numStack.push(operatorStack.pop());
        			}
        			operatorStack.push(expression);
        		}
        	}
        }
        while (!operatorStack.isEmpty()) {
            numStack.push(operatorStack.pop());
        }
        
        List<String> result = new ArrayList<>();
        while (!numStack.isEmpty()) {
            result.add(0, numStack.pop());
        }
        return result;
        
    }
}













2020.2.12


借助 栈 我们可以实现中缀表达式到后缀表达式(即逆波兰表达式, RPN)的转换.

从左到右遍历中缀表达式:

    如果碰到 数字, 直接追加到 RPN 末尾.
    如果碰到 左括号, 入栈
    如果碰到 右括号, 弹栈, 并将弹出的元素依次追加到 RPN 末尾, 直至左括号弹出(左括号不追加至PN)
    如果碰到 运算符, 弹栈直至栈顶元素优先级 小于 当前运算符, 所有弹出的元素依次追加到 RPN 末尾, 最后再将该运算符入栈
    
    出于方便, 我们设定所有元素的优先级: */ 最高, +- 次之, 然后是数字, 最后是括号.

(把括号设为最低是因为, 碰到运算符弹栈时, 遇到括号也要停止, 所以可以直接设为最低)

最后, 如果栈还有剩余, 弹栈, 依次追加到 RPN 末尾, 然后我们就得到了正确结果 RPN.




class Solution {
    public List<String> convertToRPN(String[] expressions) {
        if (expressions == null || expressions.length == 0) return null;
        List<String> RPN = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (String str : expressions) {
            if (str.equals("(")) {
                stack.push(str);
            } else if (str.equals(")")) {
                while (!stack.peek().equals("(")) {
                    RPN.add(stack.pop());
                }
                stack.pop();
            } else if (Character.isDigit(str.charAt(0))) {
                RPN.add(str);
            } else {
                int priority = getPriority(str);
                while (!stack.isEmpty() && getPriority(stack.peek()) >= priority) {
                    RPN.add(stack.pop());
                }
                stack.push(str);
            }
        }
        while (!stack.isEmpty()) RPN.add(stack.pop());
        return RPN;
    }

    private int getPriority(String str) {
        if (str.equals("*") || str.equals("/")) return 3;
        if (str.equals("+") || str.equals("-")) return 2;
        if (str.equals(")")) return 1;
        return 0;
    }
}













