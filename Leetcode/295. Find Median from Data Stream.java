295. Find Median from Data Stream

If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

For example,

[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
	1. void addNum(int num) - Add a integer number from the data stream to the data structure.
	2. double findMedian() - Return the median of all elements so far.


addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2


Follow up:

1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?



class MedianFinder {
    
    PriorityQueue<Integer> minHeap, maxHeap;
    //minHeap to store larger part of stream, maxHeap to store smaller part of the stream
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((n1, n2) -> (n2 - n1));
    }
    
    //
    public void addNum(int num) {
        if (minHeap.size() == 0 && maxHeap.size() == 0) {		//corner case both of the heap are empty, we will get NPE when we call heap.peek();
            minHeap.offer(num);
        } else if (minHeap.size() > maxHeap.size()) {
        	if (num > minHeap.peek()) {
        		maxHeap.offer(minHeap.poll());
        		minHeap.offer(num);
        	} else {
        		maxHeap.offer(num);
        	}
        } else if (minHeap.size() < maxHeap.size()) {
        	if (num < maxHeap.peek()) {
        		minHeap.offer(maxHeap.poll());
        		maxHeap.offer(num);
        	} else {
        		minHeap.offer(num);
        	}
        } else {
        	if (num > minHeap.peek()) minHeap.offer(num);
        	else maxHeap.offer(num);
        }
    }
    //if the size are not same, we choose the larger one the the two heaps
    public double findMedian() {
        if ((minHeap.size() == 0) && (maxHeap.size() == 0)) return 0.0;
        if (minHeap.size() != maxHeap.size()) {
            return minHeap.size() > maxHeap.size() ? (double)minHeap.peek() : (double)maxHeap.peek();
        } else {
            return ((double) (minHeap.peek() + maxHeap.peek())) / 2.0;
        }
    }
}



improvement : if num belongs to one of the side we just add it into the releated heap 
				and then balance the two heap (difference of size at most 1)

	public void addNum(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }




2020.1.5 重新写

minHeap to store larger part of stream, maxHeap to store smaller part of the stream

if num belongs to one of the side we just add it into the releated heap 
                and then balance the two heap (difference of size at most 1)


class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((n1, n2) -> (n2 - n1));
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        if (minHeap.size() - maxHeap.size() > 1) maxHeap.offer(minHeap.poll());
        if (maxHeap.size() - minHeap.size() > 1) minHeap.offer(maxHeap.poll());
    }
    
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
        }
    }
}




1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

We can maintain an integer array of length 100 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.

Time and space complexity would be O(100) = O(1).


2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
I dont think we need hashmap.
As 99% is between 0-100. So can keep a counter for less_than_hundred and greater_than_hundred.
As we know soluiton will be definately in 0-100 we dont need to know those number which are >100 or <0, only count of them will be enough.