//You are given an array of binary strings strs and two integers m and n. 
//
// Return the size of the largest subset of strs such that there are at most m 0
//'s and n 1's in the subset. 
//
// A set x is a subset of a set y if all elements of x are also elements of y. 
//
// 
// Example 1: 
//
// 
//Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
//Output: 4
//Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001",
// "1", "0"}, so the answer is 4.
//Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
//{"111001"} is an invalid subset because it contains 4 1's, greater than the 
//maximum of 3.
// 
//
// Example 2: 
//
// 
//Input: strs = ["10","0","1"], m = 1, n = 1
//Output: 2
//Explanation: The largest subset is {"0", "1"}, so the answer is 2.
// 
//
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] consists only of digits '0' and '1'. 
// 1 <= m, n <= 100 
// 
//
// Related Topics 数组 字符串 动态规划 👍 1315 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [474]Ones and Zeroes
 *
 */
public class OnesAndZeroes {

    public static void main(String[] args) {
        Solution solution = new OnesAndZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 普通三维dp
        public int findMaxForm2(String[] strs, int m, int n) {
            int len = strs.length;
            int[][][] dp = new int[len + 1][m + 1][n + 1];
            for (int i = len - 1, a, b; i >= 0; i--) {
                countZeroAndOne(strs[i]);
                for (int z = 0; z <= m; z++) {
                    for (int o = 0; o <= n; o++) {
                        a = dp[i + 1][z][o];
                        b = 0;
                        if (z >= zero && o >= one) {
                            b = 1 + dp[i + 1][z - zero][o - one];
                        }
                        dp[i][z][o] = Math.max(a, b);
                    }
                }
            }
            return dp[0][m][n];
        }

        // 空间压缩三维dp
        public int findMaxForm3(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (int i = strs.length - 1, a, b; i >= 0; i--) {
                countZeroAndOne(strs[i]);
                for (int z = m; z >= 0; z--) {
                    for (int o = n; o >= 0; o--) {
                        a = dp[z][o];
                        b = 0;
                        if (z >= zero && o >= one) {
                            b = 1 + dp[z - zero][o - one];
                        }
                        dp[z][o] = Math.max(a, b);
                    }
                }
            }
            return dp[m][n];
        }

        // 空间压缩三维dp 调整
        public int findMaxForm4(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String str : strs) {
                countZeroAndOne(str);
                for (int z = m; z >= zero; z--) {
                    for (int o = n; o >= one; o--) {
                        dp[z][o] = Math.max(dp[z][o], 1 + dp[z - zero][o - one]);
                    }
                }
            }
            return dp[m][n];
        }


        // m 0, n 1
        public int findMaxForm1(String[] strs, int m, int n) {
            return f1(strs, 0, m, n);
        }

        public int f1(String[] strs, int i, int m, int n) {
            if (i == strs.length || (m == 0 && n == 0)) {
                return 0;
            }
            int a = f1(strs, i + 1, m, n);
            // 不达标
            int b = 0;
            countZeroAndOne(strs[i]);
            if (m >= zero && n >= one) {
                b = 1 + f1(strs, i + 1, m - zero, n - one);
            }
            return Math.max(a, b);
        }


        private static int zero, one;

        public static void countZeroAndOne(String s) {
            zero = one = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}