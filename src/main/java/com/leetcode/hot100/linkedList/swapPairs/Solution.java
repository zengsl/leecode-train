package com.leetcode.hot100.linkedList.swapPairs;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null ) {
            return null;
        }

        ListNode prev = new ListNode(0, head);
        ListNode headPrev = prev;
        ListNode curr = head;

        while (curr.next != null) {
            prev.next = curr.next;
            curr.next = curr.next.next;
            prev.next.next = curr;
            curr = prev.next.next.next;
            if (curr == null) {
                break;
            }
            prev = prev.next.next;
        }
        return headPrev.next;
    }


    public static void main(String[] args) {
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l1 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;


        ListNode listNode = new Solution().swapPairs(l1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}