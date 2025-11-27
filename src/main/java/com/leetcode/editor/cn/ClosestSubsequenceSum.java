//You are given an integer array nums and an integer goal. 
//
// You want to choose a subsequence of nums such that the sum of its elements 
//is the closest possible to goal. That is, if the sum of the subsequence's 
//elements is sum, then you want to minimize the absolute difference abs(sum - goal). 
//
// Return the minimum possible value of abs(sum - goal). 
//
// Note that a subsequence of an array is an array formed by removing some 
//elements (possibly all or none) of the original array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [5,-7,3,5], goal = 6
//Output: 0
//Explanation: Choose the whole array as a subsequence, with a sum of 6.
//This is equal to the goal, so the absolute difference is 0.
// 
//
// Example 2: 
//
// 
//Input: nums = [7,-9,15,-2], goal = -5
//Output: 1
//Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
//The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
// 
//
// Example 3: 
//
// 
//Input: nums = [1,2,3], goal = -7
//Output: 7
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 40 
// -10⁷ <= nums[i] <= 10⁷ 
// -10⁹ <= goal <= 10⁹ 
// 
//
// Related Topics 位运算 数组 双指针 动态规划 状态压缩 排序 👍 111 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [1755]Closest Subsequence Sum
 */
public class ClosestSubsequenceSum {
    public static void main(String[] args) {
        Solution solution = new ClosestSubsequenceSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 40;
        public static final int MAX_HALF = 1 << 20;
        public static final int[] L_SUM = new int[MAX_HALF];
        public static final int[] R_SUM = new int[MAX_HALF];

        public int minAbsDifference(int[] nums, int goal) {
            int n = nums.length;
            long max = 0;
            long min = 0;
            for (int num : nums) {
                if (num >= 0) {
                    max += num;
                } else {
                    min += num;
                }
            }
            if (max < goal) {
                return Math.toIntExact(Math.abs(max - goal));
            }
            if (min > goal) {
                return Math.toIntExact(Math.abs(min - goal));
            }

            Arrays.sort(nums);
            int lSize = f(0, n >> 1, nums, 0, L_SUM, 0);
            int rSize = f(n >> 1, n, nums, 0, R_SUM, 0);
            Arrays.sort(L_SUM, 0, lSize);
            Arrays.sort(R_SUM, 0, rSize);
            int ans = Integer.MAX_VALUE;
            for (int i = 0, j = rSize - 1; i < lSize; i++) {
                while (j > 0 && Math.abs(L_SUM[i] + R_SUM[j - 1] - goal) <= Math.abs(L_SUM[i] + R_SUM[j] - goal)) {
                    j--;
                }
                ans = Math.min(ans, Math.abs(L_SUM[i] + R_SUM[j] - goal));
            }
            return ans;
        }

        public int f(int curr, int end, int[] nums, int sum, int[] sumArr, int sumIndex) {
            if (curr == end) {
                sumArr[sumIndex++] = sum;
            } else {
                // 1 2 3
                int next = curr + 1;
                while (next < end && nums[curr] == nums[next]) {
                    next++;
                }
                for (int j = 0; j <= next - curr; j++) {
                    sumIndex = f(next, end, nums, sum + j * nums[curr], sumArr, sumIndex);
                }
            }
            return sumIndex;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}