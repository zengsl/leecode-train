//Given the head of a linked list, return the list after sorting it in 
//ascending order. 
//
// 
// Example 1: 
// 
// 
//Input: head = [4,2,1,3]
//Output: [1,2,3,4]
// 
//
// Example 2: 
// 
// 
//Input: head = [-1,5,3,4,0]
//Output: [-1,0,3,4,5]
// 
//
// Example 3: 
//
// 
//Input: head = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 5 * 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
// Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.
//e. constant space)? 
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 2609 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.linkedList.addTwoNumbers.ListNode;

/**
 * [148]Sort List
 */
public class SortList {
    public static void main(String[] args) {
        /*ListNode l0 = new ListNode(0);
        ListNode l1 = new ListNode(4, l0);
        ListNode l2 = new ListNode(3, l1);
        ListNode l3 = new ListNode(5, l2);
        ListNode l4 = new ListNode(-1, l3);*/
        /*ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2, l1);
        ListNode l3 = new ListNode(3, l2);
        ListNode l4 = new ListNode(-4, l3);*/
        /*ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2, l1);
        ListNode l3 = new ListNode(3, l2);
        ListNode l4 = new ListNode(4, l3);*/
        ListNode l0 = new ListNode(0);
        ListNode l1 = new ListNode(4, l0);
        ListNode l2 = new ListNode(3, l1);
        ListNode l3 = new ListNode(5, l2);
        ListNode l4 = new ListNode(-1, l3);
        Solution solution = new SortList().new Solution();
        solution.sortList(l4);
        System.out.println("end");
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
        public ListNode sortList(ListNode head) {
            if (head == null) {
                return null;
            }

            int n = 0;
            ListNode curr = head;
            while (curr != null) {
                n++;
                curr = curr.next;
            }
            ListNode lastTeamEnd;
            ListNode start, end, nextStart, nextEnd;
            ListNode[] mergeResult;
            for (int i = 1; i < n; i <<= 1) {
                // 处理头节点
                curr = head;
                start = curr;
                end = findEnd(curr, i);
                nextStart = end.next;
                nextEnd = findEnd(nextStart, i);
                if (nextEnd == null) {
                    continue;
                }
                curr = nextEnd.next;
                end.next = null;
                nextEnd.next = null;
                mergeResult = merge(start, end, nextStart, nextEnd);
                head = mergeResult[0];
                lastTeamEnd = mergeResult[1];
//                lastTeamEnd.next = curr;

                while (curr != null) {
                    start = curr;
                    end = findEnd(curr, i);
                    nextStart = end.next;
                    if (nextStart == null) {
                        lastTeamEnd.next = start;
                        break;
                    }
                    nextEnd = findEnd(nextStart, i);

                    curr = nextEnd.next;
                    end.next = null;
                    nextEnd.next = null;
                    mergeResult = merge(start, end, nextStart, nextEnd);
                    lastTeamEnd.next = mergeResult[0];
                    lastTeamEnd = mergeResult[1];
                    lastTeamEnd.next = curr;
                }
            }
            return head;
        }

        private ListNode[] merge(ListNode start1, ListNode end1, ListNode start2, ListNode end2) {
            ListNode head = null, end = null, curr = null;
            if (start2 == null || start1.val <= start2.val) {
                curr = head = end = start1;
                start1 = start1.next;
            } else {
                curr = head = end = start2;
                start2 = start2.next;
            }

            while (start1 != null && start2 != null) {
                if (start1.val <= start2.val) {
                    curr.next = start1;
                    curr = curr.next;
                    start1 = start1.next;
                } else {
                    curr.next = start2;
                    curr = curr.next;
                    start2 = start2.next;
                }
            }

            if (start1 != null) {
                curr.next = start1;
                end = end1;
            }

            if (start2 != null) {
                curr.next = start2;
                end = end2;
            }

            return new ListNode[]{head, end};
        }


        private ListNode findEnd(ListNode cur, int step) {
            while (cur.next != null && --step > 0) {
                cur = cur.next;
            }
            return cur;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}