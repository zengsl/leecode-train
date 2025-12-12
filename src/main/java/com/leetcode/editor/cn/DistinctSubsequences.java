//Given two strings s and t, return the number of distinct subsequences of s 
//which equals t. 
//
// The test cases are generated so that the answer fits on a 32-bit signed 
//integer. 
//
// 
// Example 1: 
//
// 
//Input: s = "rabbbit", t = "rabbit"
//Output: 3
//Explanation:
//As shown below, there are 3 ways you can generate "rabbit" from s.
//rabbbit
//rabbbit
//rabbbit
// 
//
// Example 2: 
//
// 
//Input: s = "babgbag", t = "bag"
//Output: 5
//Explanation:
//As shown below, there are 5 ways you can generate "bag" from s.
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 1000 
// s and t consist of English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 1384 👎 0


package com.leetcode.editor.cn;

/**
 * [115]Distinct Subsequences
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        Solution solution = new DistinctSubsequences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDistinct1(String s, String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            int n = sChars.length + 1;
            int m = tChars.length + 1;
            int[][] dp = new int[n][m];
            // 依赖 左和上
            // n = 0 0
            // m = 0 1
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (sChars[i - 1] == tChars[j - 1]) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                }
            }
            return dp[n - 1][m - 1];
        }

        // 空间压缩
        public int numDistinct2(String s, String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            int n = sChars.length + 1;
            int m = tChars.length + 1;
            int[] dp = new int[m];
            // 依赖 左和上
            // n = 0 0
            // m = 0 1
            dp[0] = 1;
            for (int i = 1, leftTop; i < n; i++) {
                leftTop = 1;
                for (int j = 1, backup; j < m; j++) {
                    backup = dp[j];
                    if (sChars[i - 1] == tChars[j - 1]) {
                        dp[j] += leftTop;
                    }
                    leftTop = backup;
                }
            }
            return dp[m - 1];
        }

        // 空间压缩 + 减少变量
        public int numDistinct3(String s, String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            int n = sChars.length + 1;
            int m = tChars.length + 1;
            int[] dp = new int[m];
            // 依赖 左和上
            // n = 0 0
            // m = 0 1
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                for (int j = m - 1; j >= 1; j--) {
                    if (sChars[i - 1] == tChars[j - 1]) {
                        dp[j] += dp[j - 1];
                    }
                }
            }
            return dp[m - 1];
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

