//There is a group of n members, and a list of various crimes they could commit.
// The iᵗʰ crime generates a profit[i] and requires group[i] members to 
//participate in it. If a member participates in one crime, that member can't participate 
//in another crime. 
//
// Let's call a profitable scheme any subset of these crimes that generates at 
//least minProfit profit, and the total number of members participating in that 
//subset of crimes is at most n. 
//
// Return the number of schemes that can be chosen. Since the answer may be 
//very large, return it modulo 10⁹ + 7. 
//
// 
// Example 1: 
//
// 
//Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
//Output: 2
//Explanation: To make a profit of at least 3, the group could either commit 
//crimes 0 and 1, or just crime 1.
//In total, there are 2 schemes. 
//
// Example 2: 
//
// 
//Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
//Output: 7
//Explanation: To make a profit of at least 5, the group could commit any 
//crimes, as long as they commit one.
//There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 100 
// 0 <= minProfit <= 100 
// 1 <= group.length <= 100 
// 1 <= group[i] <= 100 
// profit.length == group.length 
// 0 <= profit[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 347 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [879]Profitable Schemes
 *
 */
public class ProfitableSchemes {
    public static void main(String[] args) {
        Solution solution = new ProfitableSchemes().new Solution();
        System.out.println(solution.profitableSchemes1(5, 3, new int[]{2, 2}, new int[]{2, 3}));
        System.out.println(solution.profitableSchemes1(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MOD = 1000000007;
        public static int[] GROUP, PROFIT;
        public static int LEN;

        public int profitableSchemes2(int n, int minProfit, int[] g, int[] p) {
            GROUP = g;
            PROFIT = p;
            // i [0, LEN]
            //  [0, N]
            // [0, minProfit]
            int[][] dp = new int[n + 1][minProfit + 1];
            for (int r = 0; r <= n; r++) {
                dp[r][0] = 1;
            }

            for (int i = g.length - 1, a, b; i >= 0; i--) {
                for (int r = n; r >= 0; r--) {
                    for (int k = minProfit; k >= 0; k--) {
                        a = dp[r][k];
                        b = r >= GROUP[i] ? dp[r - GROUP[i]][Math.max(k - PROFIT[i], 0)] : 0;
                        dp[r][k] = (a + b) % MOD;
                    }
                }
            }
            return dp[n][minProfit];
        }

        public int profitableSchemes1(int n, int minProfit, int[] g, int[] p) {
            GROUP = g;
            PROFIT = p;
            LEN = g.length;
            return f(0, n, minProfit);
        }

        public int f(int i, int n, int minProfit) {

            if (i == LEN) {
                return minProfit <= 0 ? 1 : 0;
            }

            if (n <= 0) {
                return minProfit <= 0 ? 1 : 0;
            }

            int a = f(i + 1, n, minProfit) % MOD;
            int b = 0;
            if (n >= GROUP[i]) {
                b = f(i + 1, n - GROUP[i], minProfit - PROFIT[i]) % MOD;
            }
            return a + b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}