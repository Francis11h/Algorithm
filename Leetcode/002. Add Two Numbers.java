
2. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored In-Reverse order and each of their nodes contain a single digit


Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807,

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

按理说 加法应该是 从各位往十位这么加 
但是listnode 只有 next 所以 无法这么做
但是 list 存的是 in-reverse order
所以 从list 的 头往尾加 就 等于加法的 低位到高位

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = l1, h2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;

        while (h1 != null || h2 != null || carry != 0) {
            if (h1 != null) {
                carry += h1.val;
                h1 = h1.next;
            }
            if (h2 != null) {
                carry += h2.val;
                h2 = h2.next;
            }
            ListNode now = new ListNode(carry % 10);
            head.next = now;
            head = head.next;

            carry /= 10;
        }
        return dummy.next;
    }
}









415. Add Strings

给两串字符串 每个char就是一个digit
然后从后往前加起来

'99' + '99' = '1818' 

从后往前 遍历 然后 处理 carry,

class Solution {
    public String addStrings(String nums1, String nums2) {
        int i = nums1.length() - 1, j = nums2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        
        while (i >= 0 || j >= 0 || carry != 0) {        // carry != 0 这个是最后的时候进位要加上
            if (i >= 0) carry += nums1.charAt(i--) - '0';
            if (j >= 0) carry += nums2.charAt(j--) - '0';
            sb.append(carry % 10);
            carry /= 10;
        } 
        return sb.reverse().toString();
    }
}