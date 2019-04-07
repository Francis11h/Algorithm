21. Merge Two Sorted Lists

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4


        1 -> 2 -> 4,    1 -> 3 -> 4
        l1              l2

dummy   1 -> 1 -> 2 -> 3 -> 4 -> 4
cur


        1 -> 2 -> 4,    1 -> 3 -> 4
        l1                  l2

        1 -> 
dummy  cur


        1 -> 2 -> 4,    1 -> 3 -> 4
                     l1              l2

        1 -> 1 -> 2 -> 3 -> 4 -> 4
dummy                              cur





public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
