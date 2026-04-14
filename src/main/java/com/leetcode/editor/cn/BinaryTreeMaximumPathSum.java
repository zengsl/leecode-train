//A path in a binary tree is a sequence of nodes where each pair of adjacent 
//nodes in the sequence has an edge connecting them. A node can only appear in the 
//sequence at most once. Note that the path does not need to pass through the root. 
//
//
// The path sum of a path is the sum of the node's values in the path. 
//
// Given the root of a binary tree, return the maximum path sum of any non-
//empty path. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3]
//Output: 6
//Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//
// 
//
// Example 2: 
// 
// 
//Input: root = [-10,9,20,null,null,15,7]
//Output: 42
//Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 
//= 42.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3 * 10⁴]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 2561 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

/**
 *
 * [124]Binary Tree Maximum Path Sum
 *
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
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

        int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            f(root);
            return maxSum;
        }

        private int f(TreeNode curr) {
            if (curr == null) {
                return 0;
            }
            int left = f(curr.left);
            int right = f(curr.right);
            // 当前节点、当前节点 + 左儿子、 当前节点 + 右儿子
            int notAllCurrMax = curr.val + Math.max(0, Math.max(left, right));
            // 4种情况和当前最大值比较
            // 自己计算最大路径
            // 当前节点 + 左右儿子
            int allCurrMax = left + right + curr.val;
            maxSum = Math.max(maxSum, Math.max(notAllCurrMax, allCurrMax));
            // 给父级计算，返回包含当前节点的最大路径和（最多包含一个子节点）
            return notAllCurrMax;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}