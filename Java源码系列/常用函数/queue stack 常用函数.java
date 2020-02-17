Queue<Integer> queue = new LinkedList<>();

queue.offer(val);
queue.poll();
queue.isEmpty();



Stack<Integer> stack = new Stack<>();
Stack<Character> stack1 = new Stack<>();

stack.push(val);
stack.pop();
stack.isEmpty();
stack.peek();



PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(val);
minHeap.size();
minHeap.poll();


PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
            public int compare(Integer a, Integer b) {
                return b - a;
        });




//双端队列 double end queue (同时包含双向队列和栈操作)
Deque<Integer> dq = new ArrayDeque<>();

//双向队列操作
//一般队列 last进元素 first出元素 peek也是一般peek()first
dq.offer();	
dq.poll(); dq.pollLast(); 		[1, 2 ,3]
dq.peek(); dq.peekLast(); 


//栈操作
dq.push() = dq.offerFirst();
dq.pop() = dq.poll();
