//Given the root of a binary tree, determine if it is a complete binary tree. 
//
// In a complete binary tree, every level, except possibly the last, is 
//completely filled, and all nodes in the last level are as far left as possible. It can 
//have between 1 and 2ʰ nodes inclusive at the last level h. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3,4,5,6]
//Output: true
//Explanation: Every level before the last is full (ie. levels with node-values 
//{1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as 
//possible.
// 
//
// Example 2: 
// 
// 
//Input: root = [1,2,3,4,5,null,7]
//Output: false
//Explanation: The node with value 7 isn't as far left as possible.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 100]. 
// 1 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 318 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * [958]Check Completeness of a Binary Tree
 */
public class CheckCompletenessOfABinaryTree {
    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        TreeNode l6 = new TreeNode(6);
        l1.left = l2;
        l1.right = l3;
        l2.left = l4;
        l2.right = l5;
        l3.left = l6;
        Solution solution = new CheckCompletenessOfABinaryTree().new Solution();
        System.out.println(solution.isCompleteTree(l1));
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
        public boolean isCompleteTree(TreeNode root) {
            return isCompleteTreeBFS2(root);
        }

        private boolean isCompleteTreeBFS(TreeNode root) {
            int l = 0, r = 0;
            List<TreeNode> arr = new ArrayList<>();
            arr.add(root);
            r++;
            boolean completed = true;
            boolean isFull = true;
            while (l < r) {
                int size = r - l;
                while (size-- > 0) {
                    TreeNode curr = arr.get(l++);
                    if (curr != null) {
                        if (!isFull) {
                            completed = false;
                            break;
                        }
                        arr.add(curr.left);
                        r++;

                        arr.add(curr.right);
                        r++;
                    } else {
                        isFull = false;
                    }
                }
                if (!completed) {
                    break;
                }
            }

            return completed;
        }

        private boolean isCompleteTreeBFS2(TreeNode root) {
            if (root == null) {
                return true;
            }
            int l = 0, r = 0;
            List<TreeNode> arr = new ArrayList<>();
            arr.add(root);
            r++;
            boolean notFull = false;
            while (l < r) {
                TreeNode curr = arr.get(l++);
                // 当前节点不满
                if (curr.left == null && curr.right != null) {
                    return false;
                }

                // 出现叶子节点之后，所有节点都应该是叶子节点
                if (notFull && curr.left != null) {
                    return false;
                }
                if (curr.left != null) {
                    arr.add(curr.left);
                    r++;
                }

                if (curr.right != null) {
                    arr.add(curr.right);
                    r++;
                }

                if (curr.left == null || curr.right == null) {
                    // 出现叶子节点
                    notFull = true;
                }

            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}