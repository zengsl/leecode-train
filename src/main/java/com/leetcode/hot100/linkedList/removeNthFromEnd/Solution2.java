package com.leetcode.hot100.linkedList.removeNthFromEnd;


class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }
        ListNode headPre = new ListNode(0, head);
        ListNode first = head;
        ListNode second = head;
        int distance = --n;
        while (first.next != null) {// -1 ,1 2 3 4 5
            first = first.next;
            if (distance > 0) {
                --distance;
            } else {
                second = second.next;
                headPre = headPre.next;
            }
        }

        if (distance < 0) {
            return head;
        }
        if (second == head) {
            head = head.next;
        } else {
            headPre.next = second.next;
            second.next = null;
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

        ListNode head = new Solution2().removeNthFromEnd(l1, 1);
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