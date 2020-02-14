20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Stack data structure 完美贴合
出来的时候判断 false 比true要快捷的多 因为一个false就return了。
判断一个不满足条件 就可以了
最后再判断下栈是不是空即可

//较优解
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }
            
            if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            }
            if (ch == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
            if (ch == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            }
            // stack.pop();
        }
        if (!stack.isEmpty()) return false;
        return true;
    }
}


//我的版本 
//不好的地方, pop出来的返回值可以直接判, 不需要再peek
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }
            
            if (ch == ')' && (stack.isEmpty() || stack.peek() != '(')) {
                return false;
            }
            if (ch == '}' && (stack.isEmpty() || stack.peek() != '{')) {
                return false;
            }
            if (ch == ']' && (stack.isEmpty() || stack.peek() != '[')) {
                return false;
            }
            stack.pop();
        }
        if (!stack.isEmpty()) return false;
        return true;
    }
}



//更简短的
public boolean isValid(String s) {
	Stack<Character> stack = new Stack<Character>();
	for (char c : s.toCharArray()) {
		if (c == '(')
			stack.push(')');
		else if (c == '{')
			stack.push('}');
		else if (c == '[')
			stack.push(']');
		else if (stack.isEmpty() || stack.pop() != c)
			return false;
	}
	return stack.isEmpty();
}


2020.2.14
class Solution {
    public boolean isValid(String s) {
        if (s == null) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                continue;
            }
            if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) return false;
            if (ch == ']' && (stack.isEmpty() || stack.pop() != '[')) return false;
            if (ch == '}' && (stack.isEmpty() || stack.pop() != '{')) return false;
        }
        return stack.isEmpty();
    }
}