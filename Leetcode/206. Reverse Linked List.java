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



2020.1.17 一遍过
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode cur = head, prev = null;
        
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}

每次换的时候 用个 临时 Node 记录下 cur节点原来next指向的 Node, 否则会丢失

O(n)
O(1)



//recursive




2019.11.28 reverse  a linkedlist recursively

Start with node cur as head

// boundary cases
1. if the cur is null, return null    
2. if the cur.next is null, this means it is the last node, 
  so make this as "head" beacuse the last node will be the head of reversed list.

3. Recursively traverse the list
// reverse  the node pointers
4. set cur.next.next to  cur
5. set cur.next to null


what should the recursion return? --->  the head of the reverseList 



2020.1.17 一遍过
class Solution {
    public ListNode reverseList(ListNode head) {    // return the head of the reversedlinkedlist 
        if (head == null) return null;        // boundary case1 empty list
        if (head.next == null) return head;   // boundary case2, cur is the last node, which should be the head of the reversed list, so we. should return cur
        
        ListNode newHead = reverseList(head.next);    // then we will do the reverse recurisvely
        
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
}


第二种写法

class Solution {
  ListNode newHead;
  public ListNode reverseList(ListNode head) {
      reverseListRecurisvely(head);
      return newHead;
  }
  // return the head of the reversedlinkedlist 
  public void reverseListRecurisvely(ListNode cur) {
      // boundary case1 empty list
      if (cur == null) return;
      // boundary case2, cur is the last node, which should be the head of the reversed list, so we. should return cur
      if (cur.next == null) {
        newHead = cur;
        return;
      }
      // then we will do the reverse recurisvely
      reverseListRecurisvely(cur.next);
      cur.next.next = cur;
      cur.next = null;
  }
}

第三种 完全模拟 iterate, 即传两个 node 一个代表现在 一个代表之前
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }
    
    private ListNode reverse(ListNode cur, ListNode prev) {
        if (cur == null) return prev;
        ListNode next = cur.next;       // save current's next
        cur.next = prev;                // point cur.next to prev
        return reverse(next, cur);      // forward current node to next, set the cur node as prev
    }
}
