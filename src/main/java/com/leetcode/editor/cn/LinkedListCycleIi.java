//Given the head of a linked list, return the node where the cycle begins. If 
//there is no cycle, return null. 
//
// There is a cycle in a linked list if there is some node in the list that can 
//be reached again by continuously following the next pointer. Internally, pos is 
//used to denote the index of the node that tail's next pointer is connected to (0
//-indexed). It is -1 if there is no cycle. Note that pos is not passed as a 
//parameter. 
//
// Do not modify the linked list. 
//
// 
// Example 1: 
// 
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the 
//second node.
// 
//
// Example 2: 
// 
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the 
//first node.
// 
//
// Example 3: 
// 
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the list is in the range [0, 10⁴]. 
// -10⁵ <= Node.val <= 10⁵ 
// pos is -1 or a valid index in the linked-list. 
// 
//
// 
// Follow up: Can you solve it using O(1) (i.e. constant) memory? 
//
// Related Topics 哈希表 链表 双指针 👍 2866 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.linkedList.addTwoNumbers.ListNode;

/**
 * [142]Linked List Cycle II
 */
public class LinkedListCycleIi {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(-4);
        ListNode l2 = new ListNode(0, l1);
        ListNode l3 = new ListNode(2, l2);
        ListNode l4 = new ListNode(3, l3);
        l1.next = l3;

        Solution solution = new LinkedListCycleIi().new Solution();
        solution.detectCycle(l4);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }

            ListNode fast = head.next.next, slow = head.next;
            while (fast != null && fast != slow) {
                slow = slow.next;
                if (fast.next == null || fast.next.next == null) {
                    return null;
                }
                fast = fast.next.next;
            }
            if (fast == null) {
                return null;
            }
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}