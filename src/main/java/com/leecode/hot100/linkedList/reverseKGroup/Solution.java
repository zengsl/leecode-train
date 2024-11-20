package com.leecode.hot100.linkedList.reverseKGroup;



class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int size = 1;
        ListNode curr = head;
        ListNode headPrev = new ListNode(-1, head);
        ListNode kGroupPrev = headPrev;
        while (curr != null) {
            if ((size++) % k == 0) {
                ListNode next = curr.next;
                ListNode newCurr = kGroupPrev.next;
                ListNode[] reverseList = reverseList(kGroupPrev.next, curr);
                kGroupPrev.next = reverseList[0];
                reverseList[1].next = next;
                curr = newCurr;
                kGroupPrev = newCurr;
            }
            curr = curr.next;
        }
        return headPrev.next;
    }

    public ListNode[] reverseList(ListNode head, ListNode tail) {
        ListNode curr = head;
        ListNode last = null;
        while (curr != tail) {
            ListNode next = curr.next;
            curr.next = last;
            last = curr;
            curr = next;
        }
        tail.next = last;
        return new ListNode[]{tail, head};
    }

}