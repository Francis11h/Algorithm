25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out (最后剩下的节点) nodes in the end should remain as it is.


Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

prev : [要reverse的含k个nodes的链表的] 之前一个节点
last : [要reverse的含k个nodes的链表的] 之后一个节点

reverse prev 和 last 中间的元素(不含 prev 和 last)

tail : 反转完成后 子链表的最后一个node,  即为翻转开始时 的子链表第一个node => prev.next;
		tail 在整个 反转过程中不会变， tail.next会变

cur : 下一步 要移动到 子链表 最前面的node, 从子链表第二个节点开始.
		当 cur == last的时候 结束, 返回的是 tail(此时它是下一个要被反转的子链表的 之前一个节点 = (下一个 prev))

temp : 记录 cur.next 的元素, 防止丢失.


dummy -> [1 -> 2 -> 3 ]-> 4 -> 5 -> null
prev    tail  cur temp    last 


dummy -> [2 -> 1 -> 3 ]-> 4 -> 5 -> null
prev	       tail cur   last
   						  temp

dummy -> [3 -> 2 -> 1 ]-> 4 -> 5 -> null
prev	           tail   cur   
   					 	  last


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev != null) {
        	prev = reverseKNodes(prev, k);
        }
        return dummy.next;
    }

    public ListNode reverseKNodes(ListNode prev, int k) {
    	ListNode last = prev;
    	for (int i = 0; i < k + 1; i++) {			//last要走 k + 1步 才能走到 子链表之后的一个	[k], k+1.
    		last = last.next;
    		if (i != k && last == null) return null;
    	}
    	ListNode tail = prev.next;
    	ListNode cur = tail.next; 					// prev.next.next;

    	while (cur != last) {
    		ListNode temp = cur.next;
    		cur.next = prev.next;
    		prev.next = cur;
    		tail.next = temp;
    		cur = temp;
    	}
    	return tail;                           //返回的是 tail, 代表的是 下一个 kNodes的 前驱节点
    }
}