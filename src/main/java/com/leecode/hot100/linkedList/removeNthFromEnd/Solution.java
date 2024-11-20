package com.leecode.hot100.linkedList.removeNthFromEnd;


class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }

        int size = 0;
        ListNode ptr = head;
        while (ptr != null) {
            size++;
            ptr = ptr.next;
        }

        if (n > size) {
            return null;
        }

        // from head
        int jumpTimes = size - n;
        ptr = head;
        ListNode pre = new ListNode(-1 , head);
        while (jumpTimes > 0) { // -1 1 2 3 4 5
            ptr = ptr.next;
            pre =  pre.next;
            --jumpTimes;
        }

        if (ptr == head) {
            head = head.next;
        }
        else {
            pre.next = ptr.next;
            ptr.next = null;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1, l2);

        ListNode curr = l1;
        while (curr != null) {
            System.out.println(curr.val
            );
            curr = curr.next;
        }

        ListNode head = new Solution().removeNthFromEnd(l1, 2);
        if (head == null) {
            System.out.println("empty");
        }

        curr = head;
        while (curr != null) {
            System.out.println(curr.val
            );
            curr = curr.next;
        }
    }
}