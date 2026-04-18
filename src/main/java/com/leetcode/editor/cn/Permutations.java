//Given an array nums of distinct integers, return all the possible 
//permutations. You can return the answer in any order. 
//
// 
// Example 1: 
// Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
// Example 2: 
// Input: nums = [0,1]
//Output: [[0,1],[1,0]]
// 
// Example 3: 
// Input: nums = [1]
//Output: [[1]]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// All the integers of nums are unique. 
// 
//
// Related Topics 数组 回溯 👍 3312 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [46]Permutations
 *
 */
public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
        System.out.println(solution.permute(new int[]{0, 1}));
        System.out.println(solution.permute(new int[]{1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            int length = nums.length;
            f(nums, length, 0, new boolean[length], new int[length], ans);
            return ans;
        }

        private void f(int[] nums, int len, int curr, boolean[] visited, int[] currRes, List<List<Integer>> ans) {
            if (curr == len) {
                List<Integer> path = new ArrayList<>();
                for (int currRe : currRes) {
                    path.add(nums[currRe]);
                }
                ans.add(path);
                return;
            }

            for (int i = 0; i < len; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                currRes[curr] = i;
                f(nums, len, curr + 1, visited, currRes, ans);
                currRes[curr] = 0;
                visited[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}