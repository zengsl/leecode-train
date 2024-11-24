package com.leecode.hot100.tree.isSymmetric;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if(left.val != right.val) {
                return false;
            }
            queue.add(left.right);
            queue.add(right.left);
            queue.add(left.left);
            queue.add(right.right);
        }

        return true;
    }


}
