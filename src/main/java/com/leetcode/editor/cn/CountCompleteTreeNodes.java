//Given the root of a complete binary tree, return the number of the nodes in 
//the tree. 
//
// According to Wikipedia, every level, except possibly the last, is completely 
//filled in a complete binary tree, and all nodes in the last level are as far 
//left as possible. It can have between 1 and 2ʰ nodes inclusive at the last level h.
// 
//
// Design an algorithm that runs in less than O(n) time complexity. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3,4,5,6]
//Output: 6
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: root = [1]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5 * 10⁴]. 
// 0 <= Node.val <= 5 * 10⁴ 
// The tree is guaranteed to be complete. 
// 
//
// Related Topics 位运算 树 二分查找 二叉树 👍 1208 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

/**
 * [222]Count Complete Tree Nodes
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution solution = new CountCompleteTreeNodes().new Solution();
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
        public int countNodes(TreeNode root) {
            return process(root);
        }

        public int countNodes2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return doCountNode(root, 1, mostLeftLevel(root, 1));
        }

        private int process(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return process(node.left) + process(node.right) + 1;
        }

        private int doCountNode(TreeNode curr, int currLevel, int totalLevel) {
            // 叶子节点
            if (currLevel == totalLevel) {
                return 1;
            }

            if (totalLevel == mostLeftLevel(curr.right, currLevel + 1)) {
                return (1 << (totalLevel - currLevel)) + doCountNode(curr.right, currLevel + 1, totalLevel);
            } else {
                return (1 << (totalLevel - currLevel - 1)) + doCountNode(curr.left, currLevel + 1, totalLevel);
            }
        }


        // 因为是满二叉树，所以直接找到最左叶子节点，然后求出层数
        private int mostLeftLevel(TreeNode curr, int currentLevel) {
            while (curr != null) {
                curr = curr.left;
                currentLevel++;
            }
            return currentLevel - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}