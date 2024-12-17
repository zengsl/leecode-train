//Given the root of a binary tree, return the preorder traversal of its nodes' 
//values. 
//
// 
// Example 1: 
//
// 
// Input: root = [1,null,2,3] 
// 
//
// Output: [1,2,3] 
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
// Output: [1,2,4,5,6,7,3,8,9] 
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
// The number of nodes in the tree is in the range [0, 100]. 
// -100 <= Node.val <= 100 
// 
//
// 
// Follow up: Recursive solution is trivial, could you do it iteratively? 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1300 👎 0


package com.leetcode.editor.cn;

import utils.bst.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * [144]Binary Tree Preorder Traversal
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
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

        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            return process(root);
        }

        private List<Integer> process(TreeNode root) {

            if (root == null) {
                return null;
            }

            List<Integer> result = new ArrayList<>();
            result.add(root.val);
            List<Integer> left = process(root.left);
            if (left != null) {
                result.addAll(left);
            }
            List<Integer> right = process(root.right);
            if (right != null) {
                result.addAll(right);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}