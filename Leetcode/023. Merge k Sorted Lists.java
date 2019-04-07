Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeHelper(lists, 0, lists.length - 1);
    }

    public ListNode mergeHelper(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];                                 //这里 一定得是lists[left]
        }
        int mid = left + (right - left) / 2;
        ListNode l = mergeHelper(lists, left, mid);
        ListNode r = mergeHelper(lists, mid + 1, right);
        return mergeTwo(l, r);
    }

    public ListNode mergeTwo(ListNode l1, ListNode l2) {
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


T O(Nlogk) where k is the number of linked lists.
    merge two sorted linked list in O(n) time,
    n is the total number of nodes in two lists.
    Sum up the merge process : O(Nlogk)
S O(1) constant space

