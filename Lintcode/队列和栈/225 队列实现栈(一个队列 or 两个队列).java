225. Implement Stack using Queues

队列实现栈


就用一个 每次新加进去一个 把之前的全poll出来 再放进去

class MyStack {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        int n = queue.size();
        while(n > 1) {
            queue.offer(queue.poll());
            n--;
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}




两个队列

保持一个 queue 空 每次放空的里面 然后把另一个queue 全放过来。 每次都是在 push操作。
何方法一 大同小异

class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        } else {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
        
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue1.isEmpty()) {
            return queue2.poll();
        } else {
            return queue1.poll();
        }
         
    }
    
    /** Get the top element. */
    public int top() {
        if (queue1.isEmpty()) {
            return queue2.peek();
        } else {
            return queue1.peek();
        }
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}










