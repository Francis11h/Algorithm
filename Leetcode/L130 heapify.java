L130 heapify
堆化

给出一个整数数组，堆化操作就是把它变成一个 最小 堆数组。

对于堆数组A，A[0]是堆的根，并对于每个A[i]，A [i * 2 + 1]是A[i]的左儿子并且A[i * 2 + 2]是A[i]的右儿子。


		0
	1		2
  3   4   5   6

parent--->child   				  i ----> 2i + 1  left     2i+2  right

child --->parent   不管左右        i ----->(i - 1)/2



堆化 : 给定一个数组 对每个有孩子的节点 做一次 shiftDown() 				---> O(N)  
	  从下往上开始做 shiftDown 从第一个有孩子的节点开始 每次和最小的孩子换 





class Solution {
	public void heapify(int[] A) {
		for (int i = (A.length - 1) / 2; i >= 0; i--) {
			shiftDown(A, i);
		}
	}
	//shiftDown the nodes with index k to leaves 
	private void shiftDown(int[] A, int k) {
		while (2 * k + 1 < A.length) {			//its son's index is valid
			int left = 2 * k + 1;
			int right = 2 * k + 2;
			int son = left;
			//find the smaller child
			if (right < A.length && A[right] < A[left]) {
				son = right;
			}
			//if the smaller son still bigger than parent we don't need to swap 
			if (A[son] >= A[k]) {
				break;
			}
			//change with the smaller child cause we need to guarantee the new parent < its children
			int temp = A[son];
			A[son] = A[k];
			A[k] = temp;
			// parent pointer points to the son's index shiftDown once
			k = son;
		}

	}
}








