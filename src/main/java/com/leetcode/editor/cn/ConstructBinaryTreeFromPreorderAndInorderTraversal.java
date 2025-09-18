//Given two integer arrays preorder and inorder where preorder is the preorder 
//traversal of a binary tree and inorder is the inorder traversal of the same tree,
// construct and return the binary tree. 
//
// 
// Example 1: 
// 
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// Example 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder and inorder consist of unique values. 
// Each value of inorder also appears in preorder. 
// preorder is guaranteed to be the preorder traversal of the tree. 
// inorder is guaranteed to be the inorder traversal of the tree. 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 2602 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * [105]Construct Binary Tree from Preorder and Inorder Traversal
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
        private Map<Integer, Integer> inIndexMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            inIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inIndexMap.put(inorder[i], i);
            }
            return b(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        TreeNode b(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
            if (pStart > pEnd) {
                return null;
            }
            int currentVal = preorder[pStart];
            TreeNode curr = new TreeNode(currentVal);
            curr.left = b(preorder, pStart + 1, pStart + inIndexMap.get(currentVal) - iStart, inorder, iStart, inIndexMap.get(currentVal) - 1);
            curr.right = b(preorder, pStart + inIndexMap.get(currentVal) - iStart + 1, pEnd, inorder, inIndexMap.get(currentVal) + 1, iEnd);
            return curr;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}