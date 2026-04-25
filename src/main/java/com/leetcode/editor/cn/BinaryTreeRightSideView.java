//Given the root of a binary tree, imagine yourself standing on the right side 
//of it, return the values of the nodes you can see ordered from top to bottom. 
//
// 
// Example 1: 
//
// 
// Input: root = [1,2,3,null,5,null,4] 
// 
//
// Output: [1,3,4] 
//
// Explanation: 
//
// 
//
// Example 2: 
//
// 
// Input: root = [1,2,3,4,null,null,null,5] 
// 
//
// Output: [1,3,4,5] 
//
// Explanation: 
//
// 
//
// Example 3: 
//
// 
// Input: root = [1,null,3] 
// 
//
// Output: [1,3] 
//
// Example 4: 
//
// 
// Input: root = [] 
// 
//
// Output: [] 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 100]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1325 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [199]Binary Tree Right Side View
 *
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeRightSideView().new Solution();
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

        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            TreeNode[] deque = new TreeNode[101];
            int head = 0, tail = 1;
            List<Integer> ans = new ArrayList<>();
            deque[0] = root;
            while (head < tail) {
                // 收集当前层最右边那个答案
                ans.add(deque[tail - 1].val);
                // 记录当前层有多少个节点
                int size = tail - head;
                // 一次处理一层节点
                while (size-- > 0) {
                    TreeNode curr = deque[head++];
                    // 左右孩子节点放入队列，待下批次处理
                    if (curr.left != null) {
                        deque[tail++] = curr.left;
                    }
                    if (curr.right != null) {
                        deque[tail++] = curr.right;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}