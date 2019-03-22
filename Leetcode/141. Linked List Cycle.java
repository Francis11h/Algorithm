/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


判断 链表 有无环
快慢指针, 初始位置:slow = head, fast = head.next
    在这之前 要判断 这两个 赋值的 不能是null, 如果是null 不可能有环
然后 慢的一次走一步, 快的一次走两步 看不会不会追上
    while 出口:要是fast 指向的是空(代表本次是null已走完),或者 fast.next 指向空(代表不存在下一个fast了), 也是已走完了 => 无环 return false

两个 判断是关键

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            /*  这么写也可以
            if (fast == null || fast.next == null) {
                return false;
            }
            */
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}