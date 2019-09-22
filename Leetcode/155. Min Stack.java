155. Min Stack

Design a stack that supports push, pop, top and 
retrieving the minimum element in the constant time O(1)

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.


------> O(1) getMin() -----> space time trade off 
-------> use another auxiliary stack


Solution 1 : minStack only push when encounter a smaller or equal num

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }
    
    public void push(int num) {
        if (minStack.isEmpty() || num <= minStack.peek()) {
            minStack.push(num);
        }
        stack.push(num);
    }
    
    public void pop() {
        if (minStack.peek().equals(stack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}






Solution 2 : minStack push every time
			for this when we do pop just pop, donot need to compare

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
    }
    
    public void push(int num) {
        if (minStack.isEmpty() || num <= minStack.peek()) {
            minStack.push(num);
        } else {
        	minStack.push(minStack.peek());
        }
        stack.push(num);
    }
    
    public void pop() {
        minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}



Solution 3: use one stack 

class MinStack {
    Stack<Integer> stack;
    static int min;
    public MinStack() {
        stack = new Stack();
        min = Integer.MAX_VALUE;
    }
    // only push the old minimum value when the current 
    // minimum value changes after pushing the new value num
    public void push(int num) {
        if (num <= min) {
            stack.push(min);					//这里得是 min
            min = num;
        } 
        stack.push(num);
    }
    // if pop operation could result in the changing of the current minimum value, 
    // pop twice and change the current minimum value to the last minimum value.
    public void pop() {
        if (stack.pop() == min) {
        	min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;						//这里也是 min
    }
}