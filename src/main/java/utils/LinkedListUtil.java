package utils;

import utils.RemoveDuplicatesFromSortedList.ListNode;

public class LinkedListUtil {

    public static void printList(ListNode listNode) {

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
