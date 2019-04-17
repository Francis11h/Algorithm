232. Implement Queue using Stacks

进来 放第一个栈中, 再全move()到第二个栈中

class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void move() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.isEmpty()) {
            move();
        }
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (s2.isEmpty()) {
            move();
        }
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        move();
        return s2.isEmpty();
    }
}
