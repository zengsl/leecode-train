package com.leecode.hot100.linkedList.hasCycle;


class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next != null ? fast.next.next : null;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }



}