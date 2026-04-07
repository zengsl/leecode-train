//Given the head of a linked list and a value x, partition it such that all 
//nodes less than x come before nodes greater than or equal to x. 
//
// You should preserve the original relative order of the nodes in each of the 
//two partitions. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,4,3,2,5,2], x = 3
//Output: [1,2,2,4,3,5]
// 
//
// Example 2: 
//
// 
//Input: head = [2,1], x = 2
//Output: [1,2]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 200]. 
// -100 <= Node.val <= 100 
// -200 <= x <= 200 
// 
//
// Related Topics 链表 双指针 👍 972 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.linkedList.addTwoNumbers.ListNode;

/**
 *
 * [86]Partition List
 *
 */
public class PartitionList {
    public static void main(String[] args) {
        Solution solution = new PartitionList().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1, l1);
        ListNode partition = solution.partition(l2, 2);
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
        public static void main(String[] args) {

        }

        public ListNode partition(ListNode head, int x) {
            ListNode leftHead = null, leftTail = null;
            ListNode rightHead = null, rightTail = null;
            ListNode next;
            while (head != null) {
                next = head.next;
                head.next = null;
                if (head.val < x) {
                    if (leftHead == null) {
                        leftHead = leftTail = head;
                    } else {
                        leftTail.next = head;
                        leftTail = head;
                    }
                } else {
                    if (rightHead == null) {
                        rightHead = rightTail = head;
                    } else {
                        rightTail.next = head;
                        rightTail = head;
                    }
                }
                head = next;
            }
            if (leftTail != null) {
                leftTail.next = rightHead;
                return leftHead;
            }
            return rightHead;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}