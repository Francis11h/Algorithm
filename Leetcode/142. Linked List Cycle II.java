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
