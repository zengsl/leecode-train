//Given the root of a binary tree, return the zigzag level order traversal of 
//its nodes' values. (i.e., from left to right, then right to left for the next 
//level and alternate between). 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 983 👎 0


package com.leetcode.editor.cn;


import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * [103]Binary Tree Zigzag Level Order Traversal
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return List.of();
            }

            List<List<Integer>> res = new ArrayList<>();
            List<TreeNode> list = new ArrayList<>();
            int l = 0, r = 0;
            list.add(root);
            r++;
            boolean isReverse = false;
            while (l < r) {
                int size = r - l;
                List<Integer> levelValues = new ArrayList<>();
                res.add(levelValues);
                for (int i = isReverse ? r - 1 : l, step = isReverse ? -1 : 1, count = size; count > 0; i += step, count--) {
                    levelValues.add(list.get(i).val);
                }

                while (size-- > 0) {
                    TreeNode curr = list.get(l++);
                    if (curr.left != null) {
                        list.add(curr.left);
                        r++;
                    }
                    if (curr.right != null) {
                        list.add(curr.right);
                        r++;
                    }
                }
                isReverse = !isReverse;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}