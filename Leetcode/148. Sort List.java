148. Sort List


Sort a linked list in O(n log n) time using constant space complexity.


Example 1:

Input: 4->2->1->3
Output: 1->2->3->4

Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5




nlogn -----> merge Sort


先来个 不完全对的 也是 merge sort 但是 没有用 constant space
每次 把数组 用快慢指针 分成两半 再两两合并

class Solution {
    public ListNode sortList(ListNode head) {
         if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return mergeTwo(left, right);
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










ToDo........

Bottom to Up Divide and Conquer Merge Sort
Space O(1)

    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        int len = getLen(head);
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre, cur, left, right;
        for (int step = 1; step <= len; step *= 2) {
            pre = dummy;
            cur = dummy.next;
            while (cur != null) {
                left = cur;
                right = split(left, step);
                cur = split(right, step);
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }

    public ListNode merge(ListNode left, ListNode right,ListNode pre) {
        ListNode cur = pre;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {cur.next = left;}
        if (right !=null) {cur.next = right;}
        while (cur.next!=null) {cur = cur.next;}
        return cur;
    }

    public ListNode split(ListNode head, int step) {
        if (head == null) { return null; }
        int cnt = 1;
        while (cnt < step && head.next != null) {
            head = head.next;
            cnt++;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }

    public int getLen(ListNode head){
        int cnt = 0;
        while (head != null) {
            cnt++;
            head = head.next;
        }
        return cnt;
    }



