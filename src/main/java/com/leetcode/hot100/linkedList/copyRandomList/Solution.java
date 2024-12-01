package com.leetcode.hot100.linkedList.copyRandomList;


class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node curr = head;
        // copy node
        while (curr != null) {
            Node dummy = new Node(curr.val);
            dummy.next = curr.next;

            curr.next = dummy;
            curr = curr.next.next;
        }

        Node pre = head;
        curr = head.next;
        // set random
        while (curr != null) {
            curr.random = pre.random != null ? pre.random.next : null;
            pre = curr.next;
            curr = curr.next != null ? curr.next.next : null;
        }

        Node newHead = head.next;
        pre = head;
        curr = newHead;
        // release
        while (curr != null) {
            pre.next = curr.next;
            curr.next = curr.next != null ? curr.next.next : null;
            pre = pre.next;
            curr = curr.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node l1 = new Node(7);
        l1.random = null;
        Node l11 = new Node(13);
        l11.random = l1;
        Node l12 = new Node(11);
        Node l4 = new Node(10);

        Node l5 = new Node(1);
        l1.next = l11;
        l11.next = l12;
        l12.next = l4;
        l4.next = l5;
        l12.random = l5;
        l4.random = l12;
        l5.random = l1;



        Node listNode = new Solution().copyRandomList(l1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}