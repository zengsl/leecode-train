//Serialization is the process of converting a data structure or object into a 
//sequence of bits so that it can be stored in a file or memory buffer, or 
//transmitted across a network connection link to be reconstructed later in the same or 
//another computer environment. 
//
// Design an algorithm to serialize and deserialize a binary tree. There is no 
//restriction on how your serialization/deserialization algorithm should work. You 
//just need to ensure that a binary tree can be serialized to a string and this 
//string can be deserialized to the original tree structure. 
//
// Clarification: The input/output format is the same as how LeetCode 
//serializes a binary tree. You do not necessarily need to follow this format, so please be 
//creative and come up with different approaches yourself. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3,null,null,4,5]
//Output: [1,2,3,null,null,4,5]
// 
//
// Example 2: 
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
// The number of nodes in the tree is in the range [0, 10⁴]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 1299 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [297]Serialize and Deserialize Binary Tree
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(5);
        l3.left = l4;
        l3.right = l5;
        TreeNode l1 = new TreeNode(1);
        l1.left = l2;
        l1.right = l3;
       /* TreeNode l1 = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        l1.left = l2;*/
        Codec codec = new SerializeAndDeserializeBinaryTree().new Codec();
        String serialize = codec.serialize(l1);
        System.out.println(serialize);
        TreeNode deserialize = codec.deserialize(serialize);
        System.out.println(deserialize.val);
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
    public class Codec {



        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            s(root, sb);
            return sb.toString();
        }

        private void s(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            sb.append(root.val).append(",");
            if (root.left != null) {
                s(root.left, sb);
            } else {
                sb.append("#,");
            }

            if (root.right != null) {
                s(root.right, sb);
            } else {
                sb.append("#,");
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] valueList = data.split(",");
            return f(valueList);
        }

        int index;
        private TreeNode f( String[] valueList) {
            if (index >= valueList.length) {
                return null;
            }
             TreeNode node = null;
             String value = valueList[index++];
             if (!"#".equals(value)) {
                 node = new TreeNode(Integer.parseInt(value));
                 node.left = f(valueList);
                 node.right = f(valueList);
             }
             return node;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}