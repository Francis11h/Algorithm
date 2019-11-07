Data Structure 

Map
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


---------
HashTable
---------
It is a data structure that can map keys to values.

优点
就是把数据的存储和查找消耗的时间大大降低 O(1)
Significantly reduce the time it takes to store and Search data to O(1)


Hash-Function
	A hash table uses a Hash-Function to compute an index, also called a hashcode, 
	into an array of buckets or slots, from which the desired value can be found.

Collisions : the hash function generates the same index for more than one key.

	开散列 open hashing, 拉链法 separate chaining
			开一个数组，数组中每个元素都是一个链表头节点的引用
			通过hash函数，算index，把index相同的<key, value>插入同一链表
	闭散列 closed hashing, 开地址方法 open addressing
			如果该位置有元素了，找其他位置放入(将冲突元素顺序后移)
			线性探查 linear probing
			二次探查 quadratic probing
Rehash
		超过负载因子 load factor， 把容量扩大为原i来的两倍 capacity * 2

--------------------
HashMap vs HashTable
--------------------

1.HashMap is non-synchronized.
	It is not-thread safe and can’t be shared between many threads without proper synchronization code 
	whereas Hashtable is synchronized. It is thread-safe and can be shared with many threads.

2.HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.
	why ? 
	To successfully store and retrieve objects from a HashTable, 
	the objects used as keys must implement the hashCode() method and the equals() method. 
	Since null is not an object, it can’t implement these methods.

	HashMap is an advanced version and improvement on the Hashtable. HashMap was created later.

-------
HashMap
-------

HashMap 内部存储使用了一个 Node 数组(默认大小是16)，而 Node 类包含一个类型为 Node 的 next 的变量，也就是相当于一个链表。
在默认情况下，数组大小为16，loadFactor 为0.75，也就是说当 HashMap 中的元素超过16\0.75=12时，会把数组大小扩展为2*16=32，并且重新计算每个元素在新数组中的位置。
	 
HashMap 在并发时可能出现的问题	
	1）如果多个线程同时使用put方法添加元素，而且假设正好存在两个 put 的 key 发生了碰撞(根据 hash 值计算的 bucket 一样)
		这两个 key 会添加到数组的同一个位置，这样最终就会发生其中一个线程的 put 的数据被覆盖。		
	2）第二就是如果多个线程同时检测到元素个数超过数组大小* loadFactor
		这样就会发生多个线程同时对 Node 数组进行扩容，都在重新计算元素位置以及复制数据


--------------------
HashMap如何保证线程安全
--------------------
ConcurrentHashMap : 因为它包含一个 segment 数组, 将数据分段存储, 给每一段数据配一把锁,也就是所谓的锁分段技术.


-------
TreeMap
-------
The map is sorted according to the Natural-Ordering of its keys, 			O(log N) 不是 O(1)
	or by a Comparator provided at map creation time, depending on which constructor is used.

This proves to be an efficient way of Sorting and Storing the key-value pairs. 

The treemap implementation is Not synchronized.

红黑树 + key不能为null


----------------------------------------------
Differences TreeMap, HashMap and LinkedHashMap
----------------------------------------------
The most important distinction between these classes is the Time-Guarantees and the Ordering of the keys.

1. HashMap: HashMap offers O(1) lookup and insertion. 
	If you iterate through the keys, though, the ordering of the keys is essentially Arbitrary. 
	It is implemented by an array of linked lists.

2. TreeMap: TreeMap offers O(log N) lookup and insertion. 
	Keys are ordered, so if you need to iterate through the keys in sorted order, you can.
	It is implemented by a Red-Black Tree.

3. LinkedHashMap offers O(1) lookup and insertion.
	Keys are ordered by their insertion order. 		能保证是按插入时的顺序排列 key
	It is implemented by doubly-linked buckets.




Tree
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Binary Tree
	A binary tree is a tree data structure in which each node has At-most two children.

Full Binary tree - every node in the tree has 0 or 2 children
Perfect binary tree - all interior nodes have two children and all leaves have the same depth or level
Complete binary tree - every level except possibly the last is completely filled, and all nodes in the last level are as far left as possible



------------------
Binary Search Tree
------------------

	A binary search tree (BST) is a data structure that binary tree that keeps it’s keys in sorted order, 
	so that operations can take advantage of the binary search principle (a logarithmic search that takes happens in O(log n) time)




B-tree
	A B-tree is a self-balancing tree data structure that keeps data sorted and allows searches, sequential access, insertions, and deletions in logarithmic time. 
	It is a generalization of a binary search tree in that "a node can have more than two children". 
	A B-tree is optimized for systems that read and write large blocks of data.
	B-tree’s are commonly used in Databases and File systems.



B+ Tree
	B+ tree is a B-tree in which each node only contains keys (not key-values), and to which an additional level is added at the bottom with linked leaves.
	This makes for more efficient retrieval of data in block-oriented storage (once you find the start of the block, you can read sequentially without having to traverse up and down the tree to retrieve data nodes). 
	Additionally, all leave nodes must be the same distance from the root node.

	SQL Server & Oracle store table indexes in B+ trees, which are similar to B-trees, 
	except that "data is only stored in leaf nodes" - all other nodes hold only key values and pointers to the next nodes.


AVL Tree
	An AVL Tree is a self-balancing binary search tree. 
	The "height of the two child subtrees of any node differ at most by one", 
	otherwise the tree is re-balanced. 
	Lookup, insertion, and deletion take O(log n) time.
	Insertions and Deletion may cause a tree rotation

Red-Black Tree
	A red-black tree is a self-balancing binary search tree. 
	Each node of the tree has an extra bit, which is interpreted as either black or red. 
	The color bits are used to ensure the tree remains balanced during insertions and deletions. 
	Operations occur in O(log n) time.















Collections
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------------------
Set extends Collection<E> 
-------------------------
Set is an interface which extends Collection. 
It is an unordered collection of objects in which duplicate values cannot be stored.



------------------------
ArrayList and LinkedList
------------------------
ArrayList和LinkedList都实现了List接口，他们有以下的不同点： 
	• ArrayList是基于索引的数据接口，它的底层是数组Array。
	它可以以O(1)时间复杂度对元素进行随机访问。

	• LinkedList是以元素链表的形式存储它的数据，每一个元素都和它的前一个和	后一个元素链接在一起，在这种情况下，查找某个元素的时间复杂度是O(n)。 

	• 相对于ArrayList，LinkedList的插入，添加，删除操作速度更快，因为当元素	被添加到集合任意位置的时候，不需要像数组那样重新计算大小或者是更新索		引。 
	• LinkedList比ArrayList更占内存，因为LinkedList为每一个节点存储了两个引	用，一个指向前一个元素，一个指向下一个元素。



-----------------------
Stack extends Vector<E> 
-----------------------

A stack is a linear data structure in which 
	elements can be inserted and deleted only from one side of the list, called the Top栈顶. 
A stack follows the LIFO (Last In First Out) principle, i.e., the element inserted at the last is the first element to come out. 
The insertion of an element into stack is called push() operation, 
and deletion of an element from the stack is called pop() operation. 
In stack we always keep track of the last element present in the list with a pointer called peek().


--------------------------
Queue extends Collection<E> 
--------------------------

A queue is a linear data structure in which e
	lements can be inserted only from one side of the list called Rear队尾进, 
	and the elements can be deleted only from the other side called the Front队首出. 
A queue data structure follows the FIFO (First In First Out) principle, i.e. the element inserted at first in the list, is the first element to be removed from the list. 
The insertion of an element in a queue is called an enqueue() operation 
and the deletion of an element is called a dequeue() operation.





-------------------------
Dequeue  extends Queue<E>
-------------------------









Graph
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A graph is an abstract data type that consists of a finite set of Vertices together with a set of Edges connecting the vertices.


------------
Graph Storage
------------

Edge List
	store an array of edges 
	 [[0,1], [0,6], [0,8], [1,6]]

Adjacency List
	Vertices stored as records or objects
	each vertex stores a list of adjacent vertices

Adjacency Matrix

								adjacency matrix		adjacency list
inspect all edges					O(V^2)					O(E)
Find all edges incident on i 		O(V)					O(k)
Is there an edge from i to j		O(1)					O(logk)


------------------------
BFS和DFS 英文定义 and 差别
------------------------


DFS : One starts at the root (selecting some arbitrary node as the root in the case of a graph) 
		and explores As-Far-As possible along each Branch "before" Backtracking.
		从根节点/图中随便选一点 沿着 每个分支  回溯之前 走越远越好
		You explore one path, hit a dead end, and go back and try a different one.

BFS :  It starts at the tree root (or some arbitrary node of a graph, sometimes referred to as a ‘search key’)
		 and explores the Neighbor-Nodes first, "before" moving to the Next Level neighbors.



Depth-first search on a binary tree generally requires Less Memory than breadth-first.
Depth-first search can be easily implemented with Recursion.

A DFS doesn‘t necessarily find the Shortest path to a node, while breadth-first search does






-------

-------












-------

-------








