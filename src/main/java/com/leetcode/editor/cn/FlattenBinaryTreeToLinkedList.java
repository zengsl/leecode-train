//Given the root of a binary tree, flatten the tree into a "linked list": 
//
// 
// The "linked list" should use the same TreeNode class where the right child 
//pointer points to the next node in the list and the left child pointer is always 
//null. 
// The "linked list" should be in the same order as a pre-order traversal of 
//the binary tree. 
// 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,5,3,4,null,6]
//Output: [1,null,2,null,3,null,4,null,5,null,6]
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: root = [0]
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 2000]. 
// -100 <= Node.val <= 100 
// 
//
// 
//Follow up: Can you flatten the tree in-place (with 
//O(1) extra space)?
//
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1999 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * [114]Flatten Binary Tree to Linked List
 *
 */
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {

        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            while (root != null) {
                if (root.left != null) {
                    TreeNode leftRight = root.left;
                    while (leftRight.right != null) {
                        leftRight = leftRight.right;
                    }
                    leftRight.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }

       /* public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            TreeNode prev = null;
            while (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                if (prev != null) {
                    prev.left = null;
                    prev.right = top;
                }

                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
                prev = top;
            }
        }*/

        /*public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            List<TreeNode> res = new ArrayList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                res.add(top);
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }

            root.left = null;
            TreeNode prev = root, curr;
            for (int i = 1, len = res.size(); i < len; i++) {
                curr = res.get(i);
                curr.left = null;
                prev.right = curr;
                prev = curr;
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}