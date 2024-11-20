package com.leecode.hot100.linkedList.linkedListCycle;


import java.util.HashSet;
import java.util.Set;

class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode curr = head;
        Set<ListNode> linkSet = new HashSet<>();
        while (curr != null) {
            if (linkSet.contains(curr)) {
                return curr;
            }
            linkSet.add(curr);
            curr = curr.next;
        }
        return null;
    }



}