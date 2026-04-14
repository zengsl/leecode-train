//You are given an array of k linked-lists lists, each linked-list is sorted in 
//ascending order. 
//
// Merge all the linked-lists into one sorted linked-list and return it. 
//
// 
// Example 1: 
//
// 
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted linked list:
//1->1->2->3->4->4->5->6
// 
//
// Example 2: 
//
// 
//Input: lists = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: lists = [[]]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// k == lists.length 
// 0 <= k <= 10⁴ 
// 0 <= lists[i].length <= 500 
// -10⁴ <= lists[i][j] <= 10⁴ 
// lists[i] is sorted in ascending order. 
// The sum of lists[i].length will not exceed 10⁴. 
// 
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 3205 👎 0


package com.leetcode.editor.cn;

import utils.base.ListNode;

import java.util.List;

/**
 *
 * [23]Merge k Sorted Lists
 *
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;

        ListNode[] arr = {node1, node4, node7};
        Solution solution = new MergeKSortedLists().new Solution();
        ListNode node = solution.mergeKLists(arr);
        System.out.println(node);
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
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            return f(lists, 0, lists.length - 1);
        }

        // 0 1 2
        private ListNode f(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }

            int mid = end - ((end - start) >> 1);
            ListNode left = f(lists, start, mid - 1);
            ListNode right = f(lists, mid, end);
            if (left == null && right == null) {
                return null;
            }
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            ListNode head, curr;
            if (left.val <= right.val) {
                head = left;
                left = left.next;
            } else {
                head = right;
                right = right.next;
            }
            curr = head;
            while (left != null && right != null) {
                if (left.val <= right.val) {
                    curr.next = left;
                    left = left.next;
                } else {
                    curr.next = right;
                    right = right.next;
                }
                curr = curr.next;
            }
            if (left == null) {
                curr.next = right;
            }
            if (right == null) {
                curr.next = left;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}