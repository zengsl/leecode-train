//Given the root of a binary tree and an integer targetSum, return the number 
//of paths where the sum of the values along the path equals targetSum. 
//
// The path does not need to start or end at the root or a leaf, but it must go 
//downwards (i.e., traveling only from parent nodes to child nodes). 
//
// 
// Example 1: 
// 
// 
//Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//Output: 3
//Explanation: The paths that sum to 8 are shown.
// 
//
// Example 2: 
//
// 
//Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 1000]. 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 2288 👎 0


package com.leetcode.editor.cn;

import com.leetcode.hot100.tree.inorderTraversal.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * [437]Path Sum III
 *
 */
public class PathSumIii {
    public static void main(String[] args) {
        Solution solution = new PathSumIii().new Solution();
        System.out.println(solution.pathSum(new TreeNode(1), 0));
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

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            Map<Long, Integer> preSumMap = new HashMap<>();
            preSumMap.put(0L, 1);
            return f(root, 0, preSumMap, targetSum);
        }


        public int f(TreeNode root, long sum, Map<Long, Integer> preSumMap, int targetSum) {
            if (root == null) {
                return 0;
            }
            sum += root.val;
            int ans = preSumMap.getOrDefault(sum - targetSum, 0);
            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
            ans += f(root.left, sum, preSumMap, targetSum);
            ans += f(root.right, sum, preSumMap, targetSum);
            preSumMap.put(sum, preSumMap.get(sum) - 1);
            return ans;
        }


        public int pathSum1(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            int count = rootSum(root, targetSum);
            count += pathSum1(root.left, targetSum);
            count += pathSum1(root.right, targetSum);
            return count;
        }

        public int rootSum(TreeNode root, long targetSum) {
            int count = 0;
            if (root == null) {
                return 0;
            }
            if (root.val == targetSum) {
                count++;
            }

            count += rootSum(root.left, targetSum - root.val);
            count += rootSum(root.right, targetSum - root.val);
            return count;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}