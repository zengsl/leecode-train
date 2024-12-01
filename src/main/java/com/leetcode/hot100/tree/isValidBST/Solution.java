package com.leetcode.hot100.tree.isValidBST;

public class Solution {

    public boolean isValidBST(TreeNode root) {
        return getTreeResult(root).isBST;
    }

    private TreeResult getTreeResult(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeResult left = getTreeResult(node.left);
        TreeResult right = getTreeResult(node.right);
        int min = node.val;
        int max = node.val;
        boolean isBST = true;
        if (left != null) {
            isBST = left.isBST && node.val > left.max;
            min = Math.min(left.min, min);
            max = Math.max(left.max, max);
        }
        if (right != null) {
            isBST = isBST && right.isBST && node.val < right.min;
            min = Math.min(right.min, min);
            max = Math.max(right.max, max);
        }

        return new TreeResult(isBST, min, max);
    }

    private class TreeResult {
        boolean isBST;
        int min;
        int max;

        public TreeResult(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
