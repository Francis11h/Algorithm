716. Max Stack
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5


Solution 1 : maxStack only push when encounter a larger or equal num

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    
    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }
    
    public void push(int num) {
        if (maxStack.isEmpty() || num >= maxStack.peek()) {
            maxStack.push(num);
        }
        stack.push(num);
    }
    
    public int pop() {
        if (maxStack.peek().equals(stack.peek())) {
            maxStack.pop();
        }
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        Stack<Integer> temp = new Stack();
        while (!stack.peek().equals(maxStack.peek())) {
            temp.push(stack.pop());
        }
        int ans = peekMax();
        pop();

        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return ans;
    }
}






Solution 2 更垃圾的做法: maxStack push every time
            for this when we do pop just pop, donot need to compare

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }
    
    public void push(int num) {
        if (maxStack.isEmpty() || num >= maxStack.peek()) {
            maxStack.push(num);
        } else {
            maxStack.push(maxStack.peek());
        }
        stack.push(num);
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        Stack<Integer> temp = new Stack();
        while (!stack.peek().equals(maxStack.peek())) {
            temp.push(pop());
        }
        int ans = peekMax();
        pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return ans;
    }
}


Solution 3：
TreeMap and Double LinkedList
类似 LRU

import java.util.*; 

class MaxStack {
    
    static class Node {
        
        int data, max;
        Node prev, next;
        
        Node(int data) {
            this.data = data;
        }
        
        Node(int data, int max) {
            this.data = data;
            this.max = max;
        }
    }

    private Node head; 
    
    /** initialize your data structure here. */
    public MaxStack() {
        
    }
    
    public void push(int x) {
        if(head == null) {
            head = new Node(x, x);
        } else {
            Node node = new Node(x);
            
            if(head.max < x) {
                node.max = x;
            } else {
                node.max = head.max;
            }
            
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
    
    public int pop() {
        if(head != null) {
            int val = head.data;
            head = head.next;
            return val;
        } else {
            throw new EmptyStackException();
        }         
    }
    
    public int top() {
        if(head != null) {
            return head.data;
        } else {
            throw new EmptyStackException();
        }  
    }
    
    public int peekMax() {
        if(head != null) {
            return head.max;
        } else {
            throw new EmptyStackException();
        }   
    }
    
    public int popMax() {
        
        if(head == null) {            
            throw new EmptyStackException();
        }
        
        int max = peekMax();
        
        if(head.data == max) {
            head = head.next;
            return max;
        } else {
            Node cur = head;
            
            while(cur.data != max) {
                cur = cur.next;
            }
            
            // now current is the max node
            
            // watch out if the next one is not the same max
            // then we need to change their maxes too (??)         

            // detach the maxNode in the middle
            if(cur.next!=null){
            //int update = Math.max(cur.prev.max,cur.next.max);
            //cur.prev.max= update;
           cur.prev.next=cur.next;
            cur.next.prev=cur.prev;
            
            cur= cur.prev;
        }
            else {cur.prev.next=null; cur.prev.max=cur.data; cur=cur.prev;}
            // we need to replace the max for all the nodes before the deleting maxNode           
            while( cur != null) {
               int update = cur.data;
                if(cur.next!=null) update = Math.max(update,cur.next.max);
                cur.max = update;
                cur = cur.prev;
            }                       
            return max;   
        }        
    }
}