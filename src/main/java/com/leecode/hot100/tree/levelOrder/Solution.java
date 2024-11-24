package com.leecode.hot100.tree.levelOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        TreeNode currentLast = root;
        TreeNode nextLast = null;
        while (!queue.isEmpty()) {
             TreeNode node = queue.poll();
            if (node != null) {
                // 增加入结果队列
                level.add(node.val);

                // 子树压入队列
                queue.add(node.left);
                queue.add(node.right);
                // 更新下一层的最后节点
                if (node.left != null) {
                    nextLast = node.left;
                }
                if (node.right != null) {
                    nextLast = node.right;
                }
                // 更新当前层的最后节点时，用nextLast更新currentLast为下层遍历做准备 并添加结果
                if (currentLast == node) {
                    currentLast = nextLast;
                    nextLast = null;
                    result.add(level);
                    level = new ArrayList<>();
                }
            }
        }
        return result;
    }
}
