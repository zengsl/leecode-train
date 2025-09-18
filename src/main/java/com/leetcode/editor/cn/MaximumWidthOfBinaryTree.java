//Given the root of a binary tree, return the maximum width of the given tree. 
//
// The maximum width of a tree is the maximum width among all levels. 
//
// The width of one level is defined as the length between the end-nodes (the 
//leftmost and rightmost non-null nodes), where the null nodes between the end-
//nodes that would be present in a complete binary tree extending down to that level 
//are also counted into the length calculation. 
//
// It is guaranteed that the answer will in the range of a 32-bit signed 
//integer. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,3,2,5,3,null,9]
//Output: 4
//Explanation: The maximum width exists in the third level with length 4 (5,3,
//null,9).
// 
//
// Example 2: 
// 
// 
//Input: root = [1,3,2,5,null,null,9,6,null,7]
//Output: 7
//Explanation: The maximum width exists in the fourth level with length 7 (6,
//null,null,null,null,null,7).
// 
//
// Example 3: 
// 
// 
//Input: root = [1,3,2,5]
//Output: 2
//Explanation: The maximum width exists in the second level with length 2 (3,2).
//
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3000]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 707 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [662]Maximum Width of Binary Tree
 */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MaximumWidthOfBinaryTree().new Solution();
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
        public int widthOfBinaryTree(TreeNode root) {
            Map<TreeNode, Integer> idxMap = new HashMap<>();
            List<TreeNode> list = new ArrayList<>();
            int l = 0, r = 0;
            list.add(root);
            idxMap.put(root, 0);
            r++;
            int maxWidth = 0;
            while (l < r) {
                maxWidth = Math.max(maxWidth, idxMap.get(list.get(r - 1)) - idxMap.get(list.get(l)) + 1);
                int size = r - l;
                while (size-- > 0) {
                    TreeNode curr = list.get(l++);
                    if (curr.left != null) {
                        list.add(curr.left);
                        r++;
                        idxMap.put(curr.left, idxMap.get(curr) * 2 + 1);
                    }
                    if (curr.right != null) {
                        list.add(curr.right);
                        r++;
                        idxMap.put(curr.right, idxMap.get(curr) * 2 + 2);
                    }
                }
            }
            return maxWidth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}