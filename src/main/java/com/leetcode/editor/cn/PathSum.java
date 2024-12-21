//Given the root of a binary tree and an integer targetSum, return true if the 
//tree has a root-to-leaf path such that adding up all the values along the path 
//equals targetSum. 
//
// A leaf is a node with no children. 
//
// 
// Example 1: 
// 
// 
//Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//Output: true
//Explanation: The root-to-leaf path with the target sum is shown.
// 
//
// Example 2: 
// 
// 
//Input: root = [1,2,3], targetSum = 5
//Output: false
//Explanation: There are two root-to-leaf paths in the tree:
//(1 --> 2): The sum is 3.
//(1 --> 3): The sum is 4.
//There is no root-to-leaf path with sum = 5.
// 
//
// Example 3: 
//
// 
//Input: root = [], targetSum = 0
//Output: false
//Explanation: Since the tree is empty, there are no root-to-leaf paths.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5000]. 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1408 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

/**
 * [112]Path Sum
 */
public class PathSum {
    public static void main(String[] args) {
        Solution solution = new PathSum().new Solution();

        TreeNode node3 = new TreeNode(0);
        TreeNode node2 = new TreeNode(11);
        TreeNode node1 = new TreeNode(2);
        TreeNode root = new TreeNode(10);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
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
        public boolean hasPathSum(TreeNode root, int targetSum) {
            return process(root, targetSum);
        }


        private boolean process(TreeNode node, int targetSum) {
            if (node == null) {
                return false;
            }

            if (node.left == null && node.right == null) {
                return node.val == targetSum;
            }

            boolean left = false;
            if (node.left != null) {
                left = process(node.left, targetSum - node.val);
            }

            boolean right = false;
            if (node.right != null) {
                right = process(node.right, targetSum - node.val);
            }

            return  left || right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}