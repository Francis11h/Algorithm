460. LFU Cache

Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

现一个 key-value 存储结构，能够通过 key 快速找到对应的 value
在 cache 已满时，"快速定位" 到访问上次访问次数最少（如果访问次数相同，则访问最早的）的 key，删除之

get(key) 
    - Get the value (will always be positive) of the key if the key exists in the cache, 
    otherwise return -1.    

put(key, value)
    - Set or insert the value if the key is not already present. 
    When the cache reaches its capacity, 
    it should invalidate the "least frequently used" item before inserting a new item. 

    For the purpose of this problem, when there is a tie 
        (i.e., two or more keys that have the same frequency), 
        the least recently used(LRU) key would be evicted.

        Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. 
        This number is set to zero when the item is removed.



what is difference between with LRU ?

a simple case: 1, 1, 2, put xxx
LRU evict 1
LFU evict 2


LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4



1.这个题比lru（146）要复杂得多。原因是我们需要考虑freq。

2.最直接的想法就是使用一个heapq来保存freq。然后每次evict的时候都把freq最小的pop出来。
    但是问题在于：因为每次get()之后freq要改变。所以我们需要去heapq里面改变对应的freq，然后重新heapify。
    复杂度是o(logn）

3.如果我们要让set()和get()都是o(1)的复杂度，那么就需要设计一个特殊的数据结构。如下：

    freq1 ----> freq2 ----> freq3 ---->...
    | | |
    node1 node3 node5
    | | |
    node2 node4 node6
    ... ... ...

使用这种结构，那么我们在evict的时候，总得到最小的 freq list(freq1)，然后evict node1。

所以我们可以建立两个dict，一个保存key -> node的映射，一个保存freq -> freq_list的映射。
这样我们在get()的时候，就可以直接通过key -> node返回结果。在set()的时候，如果存在对应的node，我们就update node freq；否则insert这个新的节点。

实现：

按hierarchy的linked list结构设计两个class，一个是freq，一个是node。
然后进行链表操作即可。
代码比较长，可能面试的时候不会要求写完。

也可以 一种class node 再一个 class DoubleLinkedList
但是要掌握一些关键步骤，比如 add(), remove() removeLast()。



LRU 用的是一维双向链表，LFU 用的是二维的双向链表









2019.11.28

确实 清晰 屌！！！！！！！！！！！！！！！！！！！！！！！！！！！！

        一种 Node + 自定义的双向链表 DoubleLinkedList
        HashMap + Doubly linked list 比 LRU 复杂不少
        但是 需要 再多来一个 HashMap 记录 <freq, node>

class Node {
    int key, val, freq;
    Node prev, next;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}

class DoubleLinkedList {
    Node head;
    Node tail;
    int size;
    DoubleLinkedList() {
        this.size = 0;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void add(Node node) {
        if (node == null) return;
        
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        size++;
    }

    public void remove(Node node) {
        if (node == null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    public Node removeLast() {
        if (size <= 0) return null;
        Node rmv = tail.prev;
        remove(rmv); 
        return rmv;
    }
}

class LFUCache {
    Map<Integer, Node>  nodeMap;
    Map<Integer, DoubleLinkedList> freqMap;
    int capacity;
    int minFreq = 1;
    public LFUCache(int capacity) {
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;
        addFreq(nodeMap.get(key));
        return nodeMap.get(key).val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;      // corner case that LFU capacity is empty

        if (get(key) != -1) {
            nodeMap.get(key).val = value;
            return;
        }
        // new node and full nodeMap, need to evict
        if (nodeMap.size() == capacity) {
            Node rmv = freqMap.get(minFreq).removeLast();
            nodeMap.remove(rmv.key);
        }
        // add new node, at nodeMap, add new list at freqMap, initialize minFreq to 1
        Node newNode = new Node(key, value);
        nodeMap.put(key, newNode);
        DoubleLinkedList list = freqMap.getOrDefault(1, new DoubleLinkedList());
        list.add(newNode);
        freqMap.put(1, list);
        minFreq = 1;
    }

    private void addFreq(Node node) {   // node.freq must exist, no check
        DoubleLinkedList oldList = freqMap.get(node.freq);
        oldList.remove(node);
        if (minFreq == node.freq && oldList.size == 0) {
            minFreq++;
        }
        node.freq++;
        DoubleLinkedList newList = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
        newList.add(node);
        freqMap.put(node.freq, newList);
    }
}











别人的解法 两种不同的node

class LFUCache {
    class ListNode{
        ListNode prev;
        ListNode next;
        int freq;
        int key;
        int val;
        public ListNode(int key, int val){
            this.key = key;
            this.val = val;
            freq = 0;
        }
    }
    
    class FreqNode{
        int freq;
        FreqNode prev;
        FreqNode next;
        ListNode head;
        ListNode tail;
        public FreqNode(int freq){
            this.freq = freq;
            head = new ListNode(-1,-1);
            tail = new ListNode(-1,Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
        }
    }

    int capacity;
    HashMap<Integer,ListNode> cache;
    HashMap<Integer,FreqNode> freqMap;
    FreqNode freqHead;
    FreqNode freqTail;
    
    public LFUCache(int capacity) {
        cache = new HashMap();
        freqMap = new HashMap();
        this.capacity = capacity;
        freqHead = new FreqNode(0);
        freqTail = new FreqNode(0);
        freqHead.next = freqTail;
        freqTail.prev = freqHead;
    }
    
    public int get(int key) {
        if(!cache.containsKey(key) || capacity==0) return -1;

        ListNode node = cache.get(key);
        int originalFreq = node.freq;
        FreqNode freqNode = freqMap.get(originalFreq);
        int newFreq = ++node.freq;
        //remove node from original list
        remove(node);

        //put node in the new freqNode
        if(freqMap.containsKey(newFreq)){
            insert(freqMap.get(newFreq).head,node);
        }
        else{
            FreqNode newFreqNode = new FreqNode(newFreq);
            insert(newFreqNode.head,node);
            insert(freqNode,newFreqNode);
            freqMap.put(newFreq,newFreqNode);
        }
        
        //if freqNode is empty remove it 
        if(freqNode.head.next.val==Integer.MAX_VALUE){
            remove(freqNode);
            freqMap.remove(originalFreq);
        }
        
        return node.val;
    }
    
    public void put(int key, int value) {
        if(capacity==0) return;
        
        int newFreq = 0;
        FreqNode prevFreqNode = freqHead;
        if(cache.containsKey(key)){
            ListNode node = cache.get(key);
            newFreq = node.freq+1;
            prevFreqNode = freqMap.get(node.freq);
            //remove node from original list
            remove(node);
            //if freqNode is empty remove it 
            if(prevFreqNode.head.next.val==Integer.MAX_VALUE){
                prevFreqNode = prevFreqNode.prev;
                remove(prevFreqNode.next);
                freqMap.remove(node.freq);
            }
        }
        //evict (LFU and LRU) node
        else if(cache.size()==capacity){
            FreqNode freqNode = freqHead.next;
            ListNode evictNode = freqNode.tail.prev;
            remove(evictNode);
            cache.remove(evictNode.key);
            //if freqNode is empty remove it 
            if(freqNode.head.next.val==Integer.MAX_VALUE){
                remove(freqNode);
                freqMap.remove(evictNode.freq);
            }
        }
        
        //new node
        ListNode newNode = new ListNode(key,value);
        newNode.freq = newFreq;
        cache.put(key,newNode);
        //put node in the freqNode
        if(freqMap.containsKey(newFreq)){
            insert(freqMap.get(newFreq).head,newNode);
        }
        else{
            FreqNode newFreqNode = new FreqNode(newFreq);
            insert(newFreqNode.head,newNode);
            insert(prevFreqNode,newFreqNode);
            freqMap.put(newFreq,newFreqNode);
        }
    }
    
    private void insert(ListNode prev, ListNode node){
        ListNode next = prev.next;
        prev.next = node;
        node.next = next;
        next.prev = node;
        node.prev = prev;
    }

    private void insert(FreqNode prev, FreqNode node){
        FreqNode next = prev.next;
        prev.next = node;
        node.next = next;
        next.prev = node;
        node.prev = prev;

    }
    
    private void remove(ListNode node){
        ListNode prev = node.prev;
        ListNode next = node.next;
        node.prev = null;
        node.next = null;
        prev.next = next;
        next.prev = prev;
    }

    private void remove(FreqNode node){
        FreqNode prev = node.prev;
        FreqNode next = node.next;
        node.prev = null;
        node.next = null;
        prev.next = next;
        next.prev = prev;
    }
}
