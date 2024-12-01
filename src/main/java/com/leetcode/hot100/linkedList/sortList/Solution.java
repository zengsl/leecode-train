package com.leetcode.hot100.linkedList.sortList;

public class Solution {



    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode headPrev = new ListNode(-1, head);
        ListNode prev = headPrev;
        ListNode curr = head;
        ListNode tmpCurr = curr;
        ListNode tmpPrev = prev;
        while (curr.next != null) {

            while (tmpCurr.next != null) {
                if(tmpCurr.val > tmpCurr.next.val) {

                }
            }
        }

        return null;
    }

}
