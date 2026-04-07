//Given the roots of two binary trees root and subRoot, return true if there is 
//a subtree of root with the same structure and node values of subRoot and false 
//otherwise. 
//
// A subtree of a binary tree tree is a tree that consists of a node in tree 
//and all of this node's descendants. The tree tree could also be considered as a 
//subtree of itself. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,4,5,1,2], subRoot = [4,1,2]
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the root tree is in the range [1, 2000]. 
// The number of nodes in the subRoot tree is in the range [1, 1000]. 
// -10⁴ <= root.val <= 10⁴ 
// -10⁴ <= subRoot.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 二叉树 字符串匹配 哈希函数 👍 1167 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [572]Subtree of Another Tree
 *
 */
public class SubtreeOfAnotherTree {
    public static void main(String[] args) {
        Solution solution = new SubtreeOfAnotherTree().new Solution();
        /* System.out.println(solution.strStr("sadbutsad", "sad"));
        System.out.println(solution.strStr("leetcode", "leeto"));
        System.out.println(solution.strStr("mississippi", "issip"));*/
       /* System.out.println(Solution.idxOf("sadbutsad".toCharArray(), "sad".toCharArray()));
        System.out.println(Solution.idxOf("leetcode".toCharArray(), "leeto".toCharArray()));
        System.out.println(Solution.idxOf("mississippi".toCharArray(), "issip".toCharArray()));*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        TreeNode subTree = new TreeNode(1);
        System.out.println(solution.isSubtree2(root, subTree));
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
        public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
            if (root == null && subRoot == null) {
                return true;
            }
            if (root == null || subRoot == null) {
                return false;
            }
            return isSameTree(root, subRoot) || isSubtree1(root.left, subRoot) || isSubtree1(root.right, subRoot);
        }

        public boolean isSameTree(TreeNode root, TreeNode subRoot) {
            if (root == null && subRoot == null) {
                return true;
            }
            if (root == null || subRoot == null) {
                return false;
            }
            return root.val == subRoot.val && isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
        }

        public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
            if (root == null && subRoot == null) {
                return true;
            }
            if (root == null || subRoot == null) {
                return false;
            }

            List<String> s1 = serialize(root);
            List<String> s2 = serialize(subRoot);
            return idxOf(s1, s2) != -1;
        }

        public static int idxOf(List<String> s1, List<String> s2) {
            int n = s1.size();
            int m = s2.size();
            int[] next = nextArr(s2, m);
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s1.get(i).equals(s2.get(j))) {
                    i++;
                    j++;
                } else if (j > 0) {
                    j = next[j];
                } else {
                    i++;
                }
            }
            return j == m ? i - j : -1;
        }

        public static int[] nextArr(List<String> chars, int m) {
            if (m == 1) {
                return new int[]{-1};
            }
            int[] next = new int[m];
            next[0] = -1;
            next[1] = 0;
            int i = 2, cn = 0;
            while (i < m) {
                if (chars.get(i - 1).equals(chars.get(cn))) {
                    next[i++] = ++cn;
                } else if (cn > 0) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }


        public List<String> serialize(TreeNode node) {
            List<String> res = new ArrayList<>();
            doSerialize(node, res);
            return res;
        }

        public void doSerialize(TreeNode node, List<String> res) {
            if (node == null) {
                res.add("null");
            } else {
                res.add(String.valueOf(node.val));
                doSerialize(node.left, res);
                doSerialize(node.right, res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}