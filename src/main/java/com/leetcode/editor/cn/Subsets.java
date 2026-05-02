//Given an integer array nums of unique elements, return all possible subsets (
//the power set). 
//
// The solution set must not contain duplicate subsets. Return the solution in 
//any order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// Example 2: 
//
// 
//Input: nums = [0]
//Output: [[],[0]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// All the numbers of nums are unique. 
// 
//
// Related Topics 位运算 数组 回溯 👍 2635 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [78]Subsets
 *
 */
public class Subsets {
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /*public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            f(nums, 0, new ArrayList<>(), ans);
            return ans;
        }

        private void f(int[] nums, int start, List<Integer> path, List<List<Integer>> ans) {
            ans.add(new ArrayList<>(path));
            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                f(nums, i + 1, path, ans);
                path.removeLast();
            }
        }*/

        public List<List<Integer>> subsets(int[] nums) {
            int len = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            // 假设nums长度为5，最终结果每一位的可能性在00000 ～ 11111中。
            // 外层循环 00000 ～ 11111，按1递增，每个数字代表一种组合
            List<Integer> curr = new ArrayList<>(len);
            for (int i = 0, range = ((1 << len) - 1); i <= range; i++) {
                curr.clear();
                int mask = i, j = 0;
                while (mask != 0) {
                    if ((mask & 1) != 0) {
                        curr.add(nums[j]);
                    }
                    mask >>= 1;
                    j++;
                }
                ans.add(new ArrayList<>(curr));
            }
            return ans;
        }

        /*public List<List<Integer>> subsets(int[] nums) {
            int len = nums.length;
            List<List<Integer>> ans = new ArrayList<>();
            // 假设nums长度为5，最终结果每一位的可能性在00000 ～ 11111中。
            // 外层循环 00000 ～ 11111，按1递增，每个数字代表一种组合
            List<Integer> curr = new ArrayList<>(len);
            for (int i = 0, range = ((1 << len) - 1); i <= range; i++) {
                curr.clear();
                for (int j = 0; j < len; j++) {
                    if (((1 << j) & i) != 0) {
                        curr.add(nums[j]);
                    }
                }
                ans.add(new ArrayList<>(curr));
            }
            return ans;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}