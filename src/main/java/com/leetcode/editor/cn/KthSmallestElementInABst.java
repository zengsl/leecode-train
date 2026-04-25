//Given the root of a binary search tree, and an integer k, return the kᵗʰ 
//smallest value (1-indexed) of all the values of the nodes in the tree. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,1,4,null,2], k = 1
//Output: 1
// 
//
// Example 2: 
// 
// 
//Input: root = [5,3,6,2,4,null,null,1], k = 3
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is n. 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
// Follow up: If the BST is modified often (i.e., we can do insert and delete 
//operations) and you need to find the kth smallest frequently, how would you 
//optimize? 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1090 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * [230]Kth Smallest Element in a BST
 *
 */
public class KthSmallestElementInABst {
    public static void main(String[] args) {
        Solution solution = new KthSmallestElementInABst().new Solution();
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

        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new LinkedList<>();
            int ans = 0;
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    if (--k == 0) {
                        ans = root.val;
                        break;
                    }
                    root = root.right;
                }
            }
            return ans;
        }

        /*public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new LinkedList<>();
            int ans = 0;
            while (root != null || !stack.isEmpty()) {
                // 进入到最左
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();
                if (--k == 0) {
                    ans = root.val;
                    break;
                }

                root = root.right;
            }
            return ans;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}