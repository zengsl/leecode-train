//Given the head of a sorted linked list, delete all duplicates such that each 
//element appears only once. Return the linked list sorted as well. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,1,2]
//Output: [1,2]
// 
//
// Example 2: 
// 
// 
//Input: head = [1,1,2,3,3]
//Output: [1,2,3]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 300]. 
// -100 <= Node.val <= 100 
// The list is guaranteed to be sorted in ascending order. 
// 
//
// Related Topics 链表 👍 1173 👎 0


package com.leetcode.editor.cn;

import utils.LinkedListUtil;
import utils.base.ListNode;

/**
 * [83]Remove Duplicates from Sorted List
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
        ListNode listNode5 = new ListNode(3);
        ListNode listNode4 = new ListNode(3, listNode5);
        ListNode listNode3 = new ListNode(2, listNode4);
        ListNode listNode2 = new ListNode(1, listNode3);
        ListNode listNode = new ListNode(1, listNode2);
      /*  ListNode listNode3 = new ListNode(1);
        ListNode listNode2 = new ListNode(1, listNode3);
        ListNode listNode = new ListNode(1, listNode2);*/
        ListNode deleted = solution.deleteDuplicates(listNode);
        LinkedListUtil.printList(deleted);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

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
        public ListNode deleteDuplicates(ListNode head) {

            if (head == null) {
                return null;
            }

            if (head.next == null) {
                return head;
            }

            ListNode start = head;
            ListNode curr = head.next;
            while (curr != null) {
                if (curr.val != start.val) {
                    start.next = curr;
                    start = curr;
                }
                curr = curr.next;
            }
            start.next = null;
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}