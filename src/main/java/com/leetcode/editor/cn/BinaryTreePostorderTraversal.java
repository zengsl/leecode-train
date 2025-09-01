//Given the root of a binary tree, return the postorder traversal of its nodes' 
//values. 
//
// 
// Example 1: 
//
// 
// Input: root = [1,null,2,3] 
// 
//
// Output: [3,2,1] 
//
// Explanation: 
//
// 
//
// Example 2: 
//
// 
// Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] 
// 
//
// Output: [4,6,7,5,2,9,8,3,1] 
//
// Explanation: 
//
// 
//
// Example 3: 
//
// 
// Input: root = [] 
// 
//
// Output: [] 
//
// Example 4: 
//
// 
// Input: root = [1] 
// 
//
// Output: [1] 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the tree is in the range [0, 100]. 
// -100 <= Node.val <= 100 
// 
//
// 
//Follow up: Recursive solution is trivial, could you do it iteratively?
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1216 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [145]Binary Tree Postorder Traversal
 */
public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        right.left = new TreeNode(3);
        root.right = right;
        System.out.println(new Solution().postorderTraversal(root));
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
    static class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode lastPrint = root;
            List<Integer> result = new ArrayList<>();
            while (!stack.empty()) {
                TreeNode peek = stack.peek();
                if (peek.left != null && peek.left != lastPrint && peek.right != lastPrint) {
                    stack.add(peek.left);
                } else if (peek.right != null && peek.right != lastPrint) {
                    stack.add(peek.right);
                } else {
                    lastPrint = stack.pop();
                    result.add(lastPrint.val);
                }
            }
            return result;
        }

        /*public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            process(root, result);
            return result;
        }

        private void process(TreeNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            process(node.left, result);
            process(node.right, result);
            result.add(node.val);
        }*/

        /*public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            if (root.left != null) {
                result.addAll(postorderTraversal(root.left));
            }
            if (root.right != null) {
                result.addAll(postorderTraversal(root.right));
            }
            result.add(root.val);
            return result;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}