206. Reverse Linked List

Input: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
Output: 5 -> 4 -> 3 -> 2 -> 1 -> NULL

         T
    1 -> 2 -> 3 -> 4 -> 5 -> NULL
p   c

          T    
 <- 1    2 -> 3 -> 4 -> 5 -> NULL
    p    c

              T    
 <- 1 <- 2    3 -> 4 -> 5 -> NULL
         p    c

                             T    
 <- 1 <- 2 <- 3 <- 4 <- 5  NULL
                        p    c


class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode Temp = current.next;
            current.next = prev;
            prev = current;
            current = Temp;
        }
        return prev;
    }
}

每次换的时候 用个 临时 Node 记录下 current节点原来next指向的 Node, 否则会丢失

O(n)
O(1)



//recursive
class Solution {
  public ListNode reverseList (ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
  }
}