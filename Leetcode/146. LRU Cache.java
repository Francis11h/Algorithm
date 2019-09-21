146. LRU Cache

Least Recently Used 最近最少使用(把这个pop出)

we need to implement the structure in O(1). do both operations in O(1) time complexity


It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

获取数据get(key) : 如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。

写入数据set(key, value) : 如果key还没有在缓存中，则写入其数据值。
当缓存达到上限，它应该在写入新数据之前删除 最近最少 使用的数据 来腾出空闲位置。


LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4









Map中存的是 <Integer 值, ListNode 链表节点>

每次删都从tail删, 每次使用(添加) 都把对应的Node放到head。
=====
capacity = 2

用得多               用的少
head                tail
--------------------->
cache.put(2, 2);
[2, 2]-->[1, 1]                             

cache.get(1);       // returns 1
[1, 1]-->[2, 2]                            

cache.put(3, 3);    // evicts key 2
[1, 1]
[3, 3]-->[1, 1] 

cache.get(2);---> returns -1 (not found)

cache.put(4, 4);    // evicts key 1
[3, 3]
[4, 4]--->[3, 3]

cache.get(1);---> returns -1 (not found)

cache.get(3);       // returns 3
[3, 3]--->[4, 4]

cache.get(4);       // returns 4
[4, 4]--->[3, 3]


        [key, value]----->

[]---->[]---->[]

方法一:
双向链表 + HashMap

class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;
    ListNode (int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = this.next = null;
    }
}

class LRUCache {
    Map<Integer, ListNode> map;
    int capacity;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(0, 0);
        tail = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode current = map.get(key);

        //先切割原有的关系
        if (current.next == null) {                 //如果 current 是尾部节点 tail的赋值就要变了
            current.prev.next = null;
            tail = tail.prev;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        //再移动到头部
        moveToFront(current);
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {                   //这里是 get 不是 map.get。 这里的get是上面写的方法
            map.get(key).val = value;
            return;
        }
        if (map.size() == capacity) {
            //移除尾部元素对应的
            map.remove(tail.key);
            tail.prev.next = null;               //这里决定了我们要用双链表, 要是用singly 这里要遍历到tail 会是O(n) 因为没有 prev这个属性！！
            tail = tail.prev;
        }

        ListNode newNode = new ListNode(key, value);
        map.put(key, newNode);
        moveToFront(newNode);
    }

    //把新插入的节点 移动到头节点
    public void moveToFront(ListNode node) {
        //如果 head 没有指向 代表 该链表中没有节点 tail也就没有指向 新加入节点 tail也需要赋值
        if (head.next == null) {
            head.next = node;
            node.prev = head;
            tail = node;
        } else {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    }
}



head          node
             [    ]

head               tail         node
[   ]---->[  ]---->[ ]         [    ]
     <----    <----





One advantage of double linked list is that the node can remove itself without other reference.
(enables us to quickly move node)
In addition, it takes constant time to add and remove from the head or tail.


S : O(capacity), hashmap and linkedlist




更加简洁的代码 把tail也设为一个dummy 这样子get()中 切割原有的关系时 就没有 cur.next = null 的特殊情况了

class Node {
    int key;
    int val;
    Node prev;
    Node next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        prev = null;
        next = null;
    }
}

class LRUCache {
    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
    }
    
    private void moveToFront(Node node) {
        //whole list is empty initialize head & tail
        if (head.next == null) {
            head.next = node;
            node.prev = head;
            tail.prev = node;
            node.next = tail;
        } else {
            node.prev = head;
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
        }
        
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        //found remove its relationship within list
        Node cur = map.get(key);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        
        moveToFront(cur);    
        return cur.val;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        
        if (map.size() == capacity) {
            map.remove(tail.prev.key);
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
        }
        
        Node node = new Node(key, value);
        map.put(key, node);
        moveToFront(node);
    }
}












我就想用单链表试试


class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode (int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}

class LRUCache {
    //需要使用一个hash table来存储目前节点指向的前一个节点(模拟双链表的prev)是什么
    Map<Integer, ListNode> keyToPrev;
    int capacity, size;
    ListNode dummy;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToPrev = new HashMap<>();
        dummy = new ListNode(0, 0);
        tail = dummy;
    }

    public int get(int key) {
        if (!keyToPrev.containsKey(key)) {
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {                   //这里是 get 不是 map.get。 这里的get是上面写的方法
            ListNode prev = keyToPrev.get(key);
            prev.next.val = value;
            return;
        }
        if (size < capacity) {
            size++;
            ListNode cur = new ListNode(key, value);
            tail.next = cur;
            keyToPrev.put(key, tail);
            
            tail = cur;
            return;
        }
        
        // replace the first node with new key, value
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        
        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);
        
        moveToTail(key);
    }

    public void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);
        ListNode cur = prev.next;

        if (tail == cur) {
            return;
        }

        prev.next = prev.next.next;
        tail.next = cur;
        if (prev.next != null) {
            keyToPrev.put(prev.next.key, prev);
        }
        keyToPrev.put(cur.key, tail);
        tail = cur;
    }
}


                      tail  
prev ----> cur -----> qwer


