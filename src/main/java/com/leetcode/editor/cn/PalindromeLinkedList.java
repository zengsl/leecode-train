//Given the head of a singly linked list, return true if it is a palindrome or 
//false otherwise. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,2,2,1]
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: head = [1,2]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [1, 10⁵]. 
// 0 <= Node.val <= 9 
// 
//
// 
//Follow up: Could you do it in 
//O(n) time and 
//O(1) space?
//
// Related Topics 栈 递归 链表 双指针 👍 2115 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.linkedList.addTwoNumbers.ListNode;

/**
 * [234]Palindrome Linked List
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        /*ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2, l1);
        ListNode l3 = new ListNode(2, l2);
        ListNode l4 = new ListNode(1, l3);
*/
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1, l3);
        Solution solution = new PalindromeLinkedList().new Solution();
        System.out.println(solution.isPalindrome(l4));
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
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }

            ListNode fast = head.next, slow = head;
            ListNode last = null;
            // 寻找中点
            while (fast != null && fast.next != null) {
                last = fast.next;
                fast = fast.next.next;
                slow = slow.next;
            }


            ListNode mid = slow;
            if (fast != null) {
                last = fast;
            }
            // 右侧链表反转
            reverse(mid, last);

            ListNode left = head, right = last;
            boolean isPalindrome = true;
            while (left != null) {
                if (left.val != right.val) {
                    isPalindrome = false;
                    break;
                }
                left = left.next;
                right = right.next;
            }
            // 右侧链表还原
            reverse(last, mid);
            return isPalindrome;
        }


        private void reverse(ListNode head, ListNode tail) {
            ListNode curr = head;
            ListNode next, prev = null;
            while (curr != tail) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            curr.next = prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}