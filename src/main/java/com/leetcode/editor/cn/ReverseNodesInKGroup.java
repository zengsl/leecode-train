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
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(4, l1);
        ListNode l3 = new ListNode(3, l2);
        ListNode l4 = new ListNode(2, l3);
        ListNode l5 = new ListNode(1, l4);


        Solution solution = new ReverseNodesInKGroup().new Solution();
        solution.reverseKGroup(l5, 2);
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
            // 创建一个虚拟节点方便编写
            ListNode dummy = new ListNode(-1, head);
            ListNode preK = dummy;
            ListNode curr = head;
            int count = 0;
            while (curr != null) {
                count++;
                curr = curr.next;
                if (count % k == 0) {
                    ListNode tmp = preK.next;
                    // reverse node
                    reverseNode(preK, curr);
                    preK = tmp;
                }
            }
            head = dummy.next;
            // 断开虚拟节点的连接
            dummy.next = null;
            return head;
        }

        // @param end not include end node
        private void reverseNode(ListNode prevK, ListNode endK) {
            ListNode cur = prevK.next;
            // 反转之后的结束节点
            ListNode end = prevK.next;
            ListNode pre = null;
            while (cur != endK) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            prevK.next = pre;
            end.next = endK;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}