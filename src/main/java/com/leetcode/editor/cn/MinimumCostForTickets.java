//You have planned some train traveling one year in advance. The days of the 
//year in which you will travel are given as an integer array days. Each day is an 
//integer from 1 to 365. 
//
// Train tickets are sold in three different ways: 
//
// 
// a 1-day pass is sold for costs[0] dollars, 
// a 7-day pass is sold for costs[1] dollars, and 
// a 30-day pass is sold for costs[2] dollars. 
// 
//
// The passes allow that many days of consecutive travel. 
//
// 
// For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 
//2, 3, 4, 5, 6, 7, and 8. 
// 
//
// Return the minimum number of dollars you need to travel every day in the 
//given list of days. 
//
// 
// Example 1: 
//
// 
//Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//Output: 11
//Explanation: For example, here is one way to buy passes that lets you travel 
//your travel plan:
//On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, 
//..., 9.
//On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//In total, you spent $11 and covered all the days of your travel.
// 
//
// Example 2: 
//
// 
//Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
//Output: 17
//Explanation: For example, here is one way to buy passes that lets you travel 
//your travel plan:
//On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2,
// ..., 30.
//On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
//In total, you spent $17 and covered all the days of your travel.
// 
//
// 
// Constraints: 
//
// 
// 1 <= days.length <= 365 
// 1 <= days[i] <= 365 
// days is in strictly increasing order. 
// costs.length == 3 
// 1 <= costs[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 706 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [983]Minimum Cost For Tickets
 */
public class MinimumCostForTickets {
    public static void main(String[] args) {
        Solution solution = new MinimumCostForTickets().new Solution();
        System.out.println(solution.mincostTickets(new int[]{3, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 21, 23, 25, 26, 27, 29, 30, 33, 34, 35, 36, 38, 39, 40, 42, 45, 46, 47, 48, 49, 51, 53, 54, 56, 57, 58, 59, 60, 61, 63, 64, 67, 68, 69, 70, 72, 74, 77, 78, 79, 80, 81, 82, 83, 84, 85, 88, 91, 92, 93, 96},
                new int[]{3, 17, 57}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int[] TYPES = new int[]{1, 7, 30};

        public int mincostTickets(int[] days, int[] costs) {
            return mincostTicketsDp(days, costs);
        }

        public int mincostTicketsDp(int[] days, int[] costs) {
            int n = days.length;
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[n] = 0;

            for (int i = n; i >= 0; i--) {
                for (int k = 0, j = i; k < 3; k++) {
                    while (j < days.length && days[i] + TYPES[k] > days[j]) {
                        j++;
                    }
                    dp[i] = Math.min(dp[i], costs[k] + dp[j]);
                }
            }
            return dp[0];
        }

        public int mincostTicketsRec(int[] days, int[] costs) {
            return mincostTicketsRec(days, costs, 0);
        }

        public int mincostTicketsRec(int[] days, int[] costs, int i) {
            if (days.length == i) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && days[i] + TYPES[k] > days[j]) {
                    j++;
                }
                ans = Math.min(ans, costs[k] + mincostTicketsRec(days, costs, j));
            }
            return ans;
        }

        public int mincostTicketsRecCache(int[] days, int[] costs) {
            int[] dp = new int[days.length];
            Arrays.fill(dp, -1);
            return mincostTicketsRecCache(days, costs, 0, dp);
        }


        public int mincostTicketsRecCache(int[] days, int[] costs, int i, int[] dp) {
            int n = days.length;
            if (n == i) {
                return 0;
            }

            if (dp[i] != -1) {
                return dp[i];
            }

            int ans = Integer.MAX_VALUE;
            for (int k = 0, j = i; k < 3; k++) {
                while (j < n && days[i] + TYPES[k] > days[j]) {
                    j++;
                }
                ans = Math.min(ans, costs[k] + mincostTicketsRecCache(days, costs, j, dp));
            }
            dp[i] = ans;
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}