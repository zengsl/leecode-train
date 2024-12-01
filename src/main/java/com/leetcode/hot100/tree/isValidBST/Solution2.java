package com.leetcode.hot100.tree.isValidBST;

public class Solution2 {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        return node.val > min && node.val < max && isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2147483647);
        System.out.println(new Solution2().isValidBST(root));
    }
}
