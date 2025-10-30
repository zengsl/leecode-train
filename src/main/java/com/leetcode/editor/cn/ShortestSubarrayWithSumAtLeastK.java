//Given an integer array nums and an integer k, return the length of the 
//shortest non-empty subarray of nums with a sum of at least k. If there is no such 
//subarray, return -1. 
//
// A subarray is a contiguous part of an array. 
//
// 
// Example 1: 
// Input: nums = [1], k = 1
//Output: 1
// 
// Example 2: 
// Input: nums = [1,2], k = 4
//Output: -1
// 
// Example 3: 
// Input: nums = [2,-1,2], k = 3
//Output: 3
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁵ <= nums[i] <= 10⁵ 
// 1 <= k <= 10⁹ 
// 
//
// Related Topics 队列 数组 二分查找 前缀和 滑动窗口 单调队列 堆（优先队列） 👍 793 👎 0


package com.leetcode.editor.cn;

/**
 * [862]Shortest Subarray with Sum at Least K
 */
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        Solution solution = new ShortestSubarrayWithSumAtLeastK().new Solution();
//        System.out.println(solution.shortestSubarray(new int[]{1}, 1));
        System.out.println(solution.shortestSubarray(new int[]{27, 20, 79, 87, -36, 78, 76, 72, 50, -26}, 453));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static int MAX = 100001;
        public static int[] DE_QUEUE = new int[MAX];
        public static long[] SUM = new long[MAX];
        public static int h, t;

        public int shortestSubarray(int[] nums, int k) {
            int size = nums.length;
            // 构建前缀和
            for (int i = 0; i < size; i++) {
                SUM[i + 1] = SUM[i] + nums[i];
            }
            // reset
            h = t = 0;
            // QUEUE 小 -> 大
            // 0 1 2
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i <= size; i++) {
                while (h < t && SUM[i] - SUM[DE_QUEUE[h]] >= k) {
                    ans = Math.min(ans, i - DE_QUEUE[h++]);
                }

                while (h < t && SUM[i] <= SUM[DE_QUEUE[t - 1]]) {
                    t--;
                }
                DE_QUEUE[t++] = i;
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}