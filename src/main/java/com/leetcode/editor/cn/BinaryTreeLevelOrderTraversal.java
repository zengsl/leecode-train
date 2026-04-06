//Given the root of a binary tree, return the level order traversal of its 
//nodes' values. (i.e., from left to right, level by level). 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[9,20],[15,7]]
// 
//
// Example 2: 
//
// 
//Input: root = [1]
//Output: [[1]]
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 2000]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 2182 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.*;

/**
 * [102]Binary Tree Level Order Traversal
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
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

        // 按层遍历处理
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            List<List<Integer>> res = new ArrayList<>();
            List<TreeNode> list = new ArrayList<>();
            // 遍历的左右区间，左闭右开
            int l = 0, r = 0;
            list.add(root);
            r++;
            while (l < r) {
                int size = r - l;
                List<Integer> levelValues = new ArrayList<>();
                res.add(levelValues);
                while (size-- > 0) {
                    TreeNode curr = list.get(l++);
                    levelValues.add(curr.val);
                    if (curr.left != null) {
                        list.add(curr.left);
                        r++;
                    }
                    if (curr.right != null) {
                        list.add(curr.right);
                        r++;
                    }
                }
            }
            return res;
        }

        // 单个元素处理
        public List<List<Integer>> levelOrder2(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            Map<TreeNode, Integer> levelMap = new HashMap<>();
            queue.add(root);
            levelMap.put(root, 1);
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> levelNodeValues = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();
                Integer level = levelMap.get(curr);
                if (level > res.size()) {
                    levelNodeValues = new ArrayList<>();
                    res.add(levelNodeValues);
                }
                levelNodeValues.add(curr.val);

                if (curr.left != null) {
                    queue.add(curr.left);
                    levelMap.put(curr.left, level + 1);
                }

                if (curr.right != null) {
                    queue.add(curr.right);
                    levelMap.put(curr.right, level + 1);
                }
            }
            return res;
        }


        public List<List<Integer>> levelOrder3(TreeNode root) {
            if (root == null) {
                return List.of();
            }
            List<List<Integer>> res = new ArrayList<>();
            List<TreeNode> list = new ArrayList<>();
            int l = 0, r = 1;
            int nl = 0;
            list.add(root);
            while (l < r) {
                List<Integer> level = new ArrayList<>();
                nl = r;
                for (int i = l, size = r; i < size; i++) {
                    TreeNode treeNode = list.get(i);
                    level.add(treeNode.val);
                    if (treeNode.left != null) {
                        list.add(treeNode.left);
                        r++;
                    }

                    if (treeNode.right != null) {
                        list.add(treeNode.right);
                        r++;
                    }
                }
                l = nl;
                res.add(level);
            }
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}