//Given an array of distinct integers candidates and a target integer target, 
//return a list of all unique combinations of candidates where the chosen numbers 
//sum to target. You may return the combinations in any order. 
//
// The same number may be chosen from candidates an unlimited number of times. 
//Two combinations are unique if the frequency of at least one of the chosen 
//numbers is different. 
//
// The test cases are generated such that the number of unique combinations 
//that sum up to target is less than 150 combinations for the given input. 
//
// 
// Example 1: 
//
// 
//Input: candidates = [2,3,6,7], target = 7
//Output: [[2,2,3],[7]]
//Explanation:
//2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple 
//times.
//7 is a candidate, and 7 = 7.
//These are the only two combinations.
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,3,5], target = 8
//Output: [[2,2,2,2],[2,3,3],[3,5]]
// 
//
// Example 3: 
//
// 
//Input: candidates = [2], target = 1
//Output: []
// 
//
// 
// Constraints: 
//
// 
// 1 <= candidates.length <= 30 
// 2 <= candidates[i] <= 40 
// All elements of candidates are distinct. 
// 1 <= target <= 40 
// 
//
// Related Topics 数组 回溯 👍 3200 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * [39]Combination Sum
 *
 */
public class CombinationSum {
    public static void main(String[] args) {
        Solution solution = new CombinationSum().new Solution();
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(solution.combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(solution.combinationSum(new int[]{8, 7, 4, 3}, 11));
//        System.out.println(solution.combinationSum(new int[]{26,21,39,38,24,16,30,7,5,4,9,29,8,35,3,17,19,11,34}, 29));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            Arrays.sort(candidates);
            f(candidates, target, 0, new ArrayList<>(), ans);
            return ans;
        }

        private void f(int[] candidates, int need, int pos, List<Integer> path, List<List<Integer>> ans) {
            if (need == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }

            if (need < 0) {
                return;
            }

            for (int i = pos, n = candidates.length; i < n; i++) {
                if (candidates[i] > need) {
                    break;
                }
                path.add(candidates[i]);
                f(candidates, need - candidates[i], i, path, ans);
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}