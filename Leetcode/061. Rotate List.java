61. Rotate List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

和 rotate array 很不一样的是 这里都连好了 相当于是 组成一个新环然后从新头部断开即可
就需要找到 新的尾部 新的尾部后面的是新的头部


class Solution {
    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        //get the length of the list
        int length = getLength(head);
        //remove the redundant k
        k = k % length;
        //add a dummy node to handle corner case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        // two pointers new and old tail through move the old tail to origrinal tail we can induce the new tail
        ListNode old_tail = dummy, new_tail = dummy;
        //move old_tail from dummy with k steps to maintain the interval with it and the new tail
        for (int i = 0; i < k; i++) {
            old_tail = old_tail.next;
        }
        //move the old_tail to the origrinal tail of the list, then find the new_tail
        while (old_tail.next != null) {
            old_tail = old_tail.next;
            new_tail = new_tail.next;
        }
        // connect it with the head
        old_tail.next = dummy.next;
        //find the new head
        dummy.next = new_tail.next;
        //break the ring
        new_tail.next = null;
        return dummy.next;
    }
}



d->1->2->3->4->5   k = 2 
newT
     oldT
         
         
        newT
             oldT


    
O(N)
O(1)    

