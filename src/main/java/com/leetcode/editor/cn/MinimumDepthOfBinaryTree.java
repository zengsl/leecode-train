//Given a binary tree, find its minimum depth. 
//
// The minimum depth is the number of nodes along the shortest path from the 
//root node down to the nearest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: root = [2,null,3,null,4,null,5,null,6]
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 10⁵]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1250 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [111]Minimum Depth of Binary Tree
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
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

        public int minDepth(TreeNode root) {
            int minDepth = 0;
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            minDepth++;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode currNode = queue.poll();
                    // 找到了叶子节点
                    if(currNode.left == null &&  currNode.right == null) {
                        return minDepth;
                    }
                    if (currNode.left != null) {
                        queue.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        queue.add(currNode.right);
                    }
                }
                minDepth++;
            }

            return minDepth;
        }

        /*public int minDepth(TreeNode root) {
            return process(root);
        }

        private int process(TreeNode node) {
            if (node == null) {
                return 0;
            }

            if (node.left == null && node.right == null) {
                return 1;
            }

            int leftDepth = node.left != null ? process(node.left) : Integer.MAX_VALUE;
            int rightDepth = node.right != null ? process(node.right) : Integer.MAX_VALUE;
            return 1 + Math.min(leftDepth, rightDepth);
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}