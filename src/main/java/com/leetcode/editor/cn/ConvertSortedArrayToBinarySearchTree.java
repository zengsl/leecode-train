//Given an integer array nums where the elements are sorted in ascending order, 
//convert it to a height-balanced binary search tree. 
//
// 
// Example 1: 
// 
// 
//Input: nums = [-10,-3,0,5,9]
//Output: [0,-3,9,-10,null,5]
//Explanation: [0,-10,5,null,-3,null,9] is also accepted:
//
// 
//
// Example 2: 
// 
// 
//Input: nums = [1,3]
//Output: [3,1]
//Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums is sorted in a strictly increasing order. 
// 
//
// Related Topics 树 二叉搜索树 数组 分治 二叉树 👍 1580 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

/**
 * [108]Convert Sorted Array to Binary Search Tree
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new ConvertSortedArrayToBinarySearchTree().new Solution();
        TreeNode process = solution.process(new int[]{-10, -3, 0, 5, 9}, 0, 4);
        System.out.println(process);
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
        public TreeNode sortedArrayToBST(int[] nums) {
            return process(nums, 0, nums.length - 1);
        }


        private TreeNode process(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = start + ((end - start) >> 1);
            TreeNode root = new TreeNode(nums[mid]);
            root.left = process(nums, start, mid - 1);
            root.right = process(nums, mid + 1, end);
            return root;
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}