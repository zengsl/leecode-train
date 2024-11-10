package com.leecode.hot100.intersectLinkedList;

import com.leecode.hot100.reverseLinkedList.ListNode;

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA,b=headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return  a;
    }

    public static void main(String[] args) {
    }

}