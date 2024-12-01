package com.leetcode.hot100.linkedList.linkedListCycle;


class Solution2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            if (fast == slow) {
                ListNode nPtr = head;
                while (nPtr != slow) {
                    nPtr = nPtr.next;
                    slow = slow.next;
                }
                return nPtr;
            }
        }
        return null;
    }


}