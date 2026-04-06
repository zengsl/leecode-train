//Given the head of a linked list, reverse the nodes of the list k at a time, 
//and return the modified list. 
//
// k is a positive integer and is less than or equal to the length of the 
//linked list. If the number of nodes is not a multiple of k then left-out nodes, in 
//the end, should remain as it is. 
//
// You may not alter the values in the list's nodes, only nodes themselves may 
//be changed. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,2,3,4,5], k = 2
//Output: [2,1,4,3,5]
// 
//
// Example 2: 
// 
// 
//Input: head = [1,2,3,4,5], k = 3
//Output: [3,2,1,4,5]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is n. 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
// Follow-up: Can you solve the problem in O(1) extra memory space? 
//
// Related Topics 递归 链表 👍 2641 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.linkedList.addTwoNumbers.ListNode;

/**
 * [25]Reverse Nodes in k-Group
 */
public class ReverseNodesInKGroup2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(4, l1);
        ListNode l3 = new ListNode(3, l2);
        ListNode l4 = new ListNode(2, l3);
        ListNode l5 = new ListNode(1, l4);


        Solution solution = new ReverseNodesInKGroup2().new Solution();
        ListNode listNode = solution.reverseKGroup(l5, 3);
        System.out.println(listNode);
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
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode newHead = null, newTail = null, start = head, end = head, next;
            int count = 0;
            while (end != null) {
                next = end.next;
                if ((++count) % k == 0) {
                    // reverse start\end ->end\start
                    reverse(start, end);
                    // set newHead
                    if (newHead == null) {
                        newHead = end;
                    }

                    if (newTail != null) {
                        newTail.next = end;
                    }
                    newTail = start;
                    newTail.next = next;
                    start = end = next;
                } else {
                    end = end.next;
                }

            }
            return newHead;
        }

        private void reverse(ListNode start, ListNode end) {
            ListNode endNext = end.next;
            end.next = null;
            ListNode prev = start, curr = start.next, next;
            start.next = null;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
//            start.next = endNext;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}