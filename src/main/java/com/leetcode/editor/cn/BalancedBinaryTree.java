//Given a binary tree, determine if it is height-balanced. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: root = [1,2,2,3,3,null,null,4,4]
//Output: false
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: true
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5000]. 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1568 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

/**
 * [110]Balanced Binary Tree
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
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
        public boolean isBalanced(TreeNode root) {
            return process(root).isBalanced;
        }

        private Result process(TreeNode root) {
            if (root == null) {
                return new Result(0, true);
            }

            Result left = process(root.left);
            Result right = process(root.right);
            int height = Math.max(left.height, right.height) + 1;
            boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
            return new Result(height, isBalanced);
        }

        private static class Result {
            public int height;
            public boolean isBalanced;

            public Result(int height, boolean isBalanced) {
                this.height = height;
                this.isBalanced = isBalanced;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}