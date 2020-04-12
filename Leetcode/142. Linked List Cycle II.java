142. Linked List Cycle II

返回的是 链表环 开始的 node
没有 返回null

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}



slow 从 head 开始 fast从 head.next 开始

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //after that, the List must contain a cycle
        //At now 第一次 fast == slow 
        //要找 cycle的开始, 那么 和 head 应该 就有关系了

        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}


证明:
	当 slow 和 fast 第一次 相遇的时候
	slow 走了 N 步,  N = D + K + n*L
	fast 走了 2N 步, 2N = D - 1 + K + m*L

	D是 head 到环入口的距离
	K是从环入口顺时针到相遇点的位置
	L是环的长度

	--->
	D = (m - 2n)*L - K - 1

	而此时 head 到 入口 的距离 = D 
	而 slow  再走 D步 则 slow.next = 入口

	所以 当 第一次 head = slow.next 的时候 head处在的位置 就是 环的开始
 




slow 和 fast 都从 head开始

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (true) {
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
            
            if (slow == fast)
                break;
        }
        
        while (slow != head) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
