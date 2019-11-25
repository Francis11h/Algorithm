378. Kth Smallest Element in a Sorted Matrix

given a n x n matrix where each of the rows and columns are sorted in ascending order.
find the Kth smallest element in the matrix.


matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.



两种 用 heap的写法

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new Comparator<Tuple>(){
        	@Override
        	public int compare(Tuple a, Tuple b) {
        		return a.val - b.val;
        	}
        });

        for (int i = 0; i < n; i++) pq.offer(new Tuple(0, i, matrix[0][i]));

        while (k > 1) {
        	Tuple cur = pq.poll();
            k--;
            if (cur.x == m - 1) continue;
        	pq.offer(new Tuple(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        }
        return pq.peek().val;
    }
}

class Tuple {
	int x, y, val;
	Tuple (int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}






public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {		//意味着 该类支持排序
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}





-------------------------
Comparable and Comparator 
-------------------------
if sorting of objects needs to be based on natural order then use Comparable 
	whereas if you sorting needs to be done on attributes of different objects, 
		then use Comparator in Java.

Comparable相当于“内部比较器”, 而Comparator相当于“外部比较器”


Comparable
	若一个类实现了Comparable接口(实现该接口 意味着重写 "compareTo" 方法), 就意味着“该类支持排序”
	List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序

	public interface Comparable<T> {
	    public int compareTo(T o);
	}

	A comparable object is capable of comparing itself with another object. 
	The class itself must Implements the java.lang.Comparable interface to compare its instances.

Comparator 
	Comparator 是比较器接口

	public interface Comparator<T> {

	    int compare(T o1, T o2);

	    boolean equals(Object obj);
	}

	我们若需要控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)；那么，我们可以建立一个“该类的比较器”来进行排序。这个“比较器”只需要实现Comparator接口即可。







Build a minHeap of elements from the "first row".
Do the following operations k-1 times :
	Every time when you poll out the root(Top Element in Heap), 
	you "need to know the # of row and # of column" of that element(so we can create a tuple class here), 
	replace that root with the next element from "the same column".
	

After you finish this problem, thinks more :


	Time Complexity: The above solution involves following steps.
	1) Build a min heap which takes O(n) time
	2) Heapify k times which takes O(kLogn) time.
	Therefore, overall time complexity is "O(n + kLogn)" time.

	The above code can be optimized to build a heap of size k when k is smaller than n. 
		In that case, the kth smallest element must be in first k rows and k columns.


For this question, you can also build a min Heap from the first column, and do the similar operations as above.(Replace the root with the next element from the same row)

	What is more, this problem is exact the same with 
	Leetcode 373 Find K Pairs with Smallest Sums,






https://www.jianshu.com/p/f16928ea675b


方法2 binary search



//ToDo
