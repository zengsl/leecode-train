package com.leetcode.hot100.tree.inorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> leftList = inorderTraversal(root.left);
        System.out.println(root.val);
        List<Integer> rightList = inorderTraversal(root.right);
        if (leftList != null) {
            result.addAll(leftList);
        }

        result.add(root.val);
        if (rightList != null) {
            result.addAll(rightList);
        }
        return result;
    }

}
