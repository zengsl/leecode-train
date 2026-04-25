//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
//in the tree. 
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor 
//is defined between two nodes p and q as the lowest node in T that has both p 
//and q as descendants (where we allow a node to be a descendant of itself).” 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
// 
//
// Example 2: 
// 
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant 
//of itself according to the LCA definition.
// 
//
// Example 3: 
//
// 
//Input: root = [1,2], p = 1, q = 2
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [2, 10⁵]. 
// -10⁹ <= Node.val <= 10⁹ 
// All Node.val are unique. 
// p != q 
// p and q will exist in the tree. 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 3227 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

/**
 *
 * [236]Lowest Common Ancestor of a Binary Tree
 *
 */
public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {


        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) {
                return root;
            }

            if (left != null) {
                return left;
            }

            return right;
        }

        /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            return f(root, p, q).parent;
        }


        public Info f(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return new Info(0, null, null);
            }

            if (root == p || root == q) {
                return new Info(1, root, root);
            }
            Info leftInfo = f(root.left, p, q);
            Info rightInfo = f(root.right, p, q);
            if (leftInfo.state == 2 || rightInfo.state == 2) {
                return leftInfo.state == 2 ? leftInfo : rightInfo;
            }

            if (rightInfo.state == 1 && leftInfo.state == 1) {
                return new Info(2, root, root);
            }

            if (leftInfo.state == 1 || rightInfo.state == 1) {
                return leftInfo.state == 1 ? leftInfo : rightInfo;
            }

            return new Info(0, null, null);
        }

        static class Info {
            public Info(int state, TreeNode curr, TreeNode parent) {
                this.state = state;
                this.curr = curr;
                this.parent = parent;
            }
            public int state;
            public TreeNode curr;
            public TreeNode parent;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}