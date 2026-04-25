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
public class SortList1 {
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
        Solution solution = new SortList1().new Solution();
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
            ListNode lastTeamEnd, nextTeamStart, firstPairStart, firstPairEnd, secPairStart, secPairEnd;
            for (int step = 1; step < n; step <<= 1) {
                firstPairStart = head;
                firstPairEnd = findKEnd(firstPairStart, step);
                if (firstPairEnd == null) {
                    break;
                }
                secPairStart = firstPairEnd.next;
                secPairEnd = findKEnd(secPairStart, step);
                if (secPairEnd == null) {
                    break;
                }
                nextTeamStart = secPairEnd.next;
                mergeSort(firstPairStart, firstPairEnd, secPairStart, secPairEnd);
                head = start;
                lastTeamEnd = end;
                while (nextTeamStart != null) {
                    firstPairStart = nextTeamStart;
                    firstPairEnd = findKEnd(firstPairStart, step);
                    if (firstPairEnd == null) {
                        break;
                    }
                    secPairStart = firstPairEnd.next;
                    secPairEnd = findKEnd(secPairStart, step);
                    if (secPairEnd == null) {
                        break;
                    }
                    nextTeamStart = secPairEnd.next;
                    mergeSort(firstPairStart, firstPairEnd, secPairStart, secPairEnd);
                    lastTeamEnd.next = start;
                    lastTeamEnd = end;
                }
            }
            return head;
        }

        public ListNode findKEnd(ListNode start, int k) {
            ListNode kEnd = null;
            while (start != null && k-- > 0) {
                kEnd = start;
                start = start.next;
            }
            return kEnd;
        }


        ListNode start, end;

        public void mergeSort(ListNode start1, ListNode end1, ListNode start2, ListNode end2) {
            ListNode nextTeam = end2.next, curr;
            end1.next = null;
            end2.next = null;
            if (start1.val <= start2.val) {
                start = start1;
                start1 = start1.next;
            } else {
                start = start2;
                start2 = start2.next;
            }
            curr = start;
            while (start1 != null && start2 != null) {
                if (start1.val <= start2.val) {
                    curr.next = start1;
                    start1 = start1.next;
                } else {
                    curr.next = start2;
                    start2 = start2.next;
                }
                curr = curr.next;
            }

            if (start1 != null) {
                curr.next = start1;
                end1.next = nextTeam;
                end = end1;
            } else {
                curr.next = start2;
                end2.next = nextTeam;
                end = end2;
            }

        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}