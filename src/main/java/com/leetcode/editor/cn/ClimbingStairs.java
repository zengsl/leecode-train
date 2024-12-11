//You are climbing a staircase. It takes n steps to reach the top. 
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can 
//you climb to the top? 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
// 
//
// Example 2: 
//
// 
//Input: n = 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 3668 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [70]Climbing Stairs
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        public int climbStairs2(int n) {
            int[] cache = new int[n + 1];
            Arrays.fill(cache, -1);
            return process(n, cache);
        }

        private int process(int rest, int[] cache) {
            if (rest == 0) {
                return 1;
            }

            if (rest < 0) {
                return 0;
            }

            int p1 = cache[rest - 1];
            if (p1 == -1) {
                p1 = process(rest - 1, cache);
                cache[rest - 1] = p1;
            }

            int p2 = rest - 2 < 0 ? 0 : cache[rest - 2];
            if (p2 == -1) {
                p2 = process(rest - 2, cache);
                cache[rest - 2] = p2;
            }
            return p1 + p2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}