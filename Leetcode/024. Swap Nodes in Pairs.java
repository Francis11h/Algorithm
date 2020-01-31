24. Swap Nodes in Pairs

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, one = head, two = one.next;
        
        while (one != null && two != null) {
            ListNode temp = two.next;
            two.next = one;
            one.next = temp;
            prev.next = two;
            
            if (temp == null) return dummy.next;		//这句话最关键
            
            prev = one;
            one = temp;
            two = temp.next;

        }
        
        return dummy.next;
    }
}


