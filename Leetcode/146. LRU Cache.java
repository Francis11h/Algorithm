146. LRU Cache

we need to implement the structure in O(1).

Get the key / Check if the key exists
Put the key
Delete the first added key

There is a structure called ordered dictionary, 
it combines behind both hashmap and linked list : LinkedHashMap


其中get函数是通过输入key来获得value，如果成功获得后，这对(key, value)升至缓存器中最常用的位置（顶部），如果key不存在，则返回-1

而put函数是插入一对新的(key, value)，如果原缓存器中有该key，则需要先删除掉原有的，将新的插入到缓存器的顶部。如果不存在，则直接插入到顶部。
								若加入新的值后缓存器超过了容量，则需要删掉一个最不常用的值，也就是底部的值.



方法一:
双向链表 + HashMap

class ListNode {
	int key;
	int val;
	ListNode prev;
	ListNode next;
	ListNode(int key, int val) {
		this.key = key;
		this.val = val;
		this.prev = this.next = null;
	}
}

class LRUCache {
	Map<Integer, ListNode> map;
	int capacity;
	ListNode dummy;
	ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        dummy = new ListNode(0, 0);
        tail = dummy;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
        	return -1;
        }
        ListNode current = map.get(key);
        //删除 这个节点, 然后再加 新的
        if (current.next == null) {
        	current.prev.next = null;
        	tail = tail.prev;
        } else {
        	current.prev.next = current.next;
        	current.next.prev = current.prev;
        }

        moveToFront(current);
        return map.get(key).val;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) {			//after we did this, the ListNode contains key moved to the 'queue' head
        	map.get(key).val = value;
        	return;
        } 

        if (map.size() == capacity) {
        	map.remove(tail.key);
        	tail.prev.next = null;
        	tail = tail.prev;
        }
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        moveToFront(node);
    }

    public void moveToFront(ListNode node) {
    	if (dummy.next == null) {
    		dummy.next = node;
    		node.prev = dummy;
    		tail = node;
    	} else {
    		node.prev = dummy;
    		node.next = dummy.next;
    		dummy.next.prev = node;
    		dummy.next = node;
    	}
    }
}







方法二:
LinkedHashMap 模拟 queue
1） 任何被 get / set 的元素 都会被移动到 LinkedHashMap的最后
2） 若set插入新元素后，超出 capacity, 则移除队首元素

class LRUCache {
	Map<Integer, Integer> map 
	int cap;

    public LRUCache(int capacity) {
        cap = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
        	return -1;
        }
        int val = 
    }
    
    public void set(int key, int value) {
        
    }
}

