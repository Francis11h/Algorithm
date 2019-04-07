92. Reverse Linked List II

Reverse a linked list from position m to n. 

Note : 1 ≤ m ≤ n ≤ length of list

Input: 1 -> 2 -> 3 -> 4 -> 5 -> NULL, m = 2, n = 4

Output: 1 -> 4 -> 3 -> 2 -> 5 -> NULL


把 mNode 永远插到 nNode的后面,
每次动m之前,先记录 mNode后面一个的节点信息 : prevM.next = mNode.next;
		 因为 要是先动m 则其后面节点的信息会丢失。

		1 -> 2 -> 3 -> 4 -> 5 -> NULL
	prevM    mNode     nNode


		1 -> 3 -> 4 -> 2 -> 5 -> NULL
	prevM	 m    n


		1 -> 4 -> 3 -> 2 -> 5 -> NULL
	prevM	 m
			 n

class Solution {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null) return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode mNode = head, nNode = head, prevM = dummy;

		for (int i = 1; i < m; i++) {
			prevM = mNode;
			mNode = mNode.next;
		}

		for (int i = 1; i < n; i++) {
			nNode = nNode.next;
		}

		while (mNode != nNode) {
			prevM.next = mNode.next;
			mNode.next = nNode.next;
			nNode.next = mNode;
			mNode = prevM.next;
		}
		return dummy.next;
	}
}