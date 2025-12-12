//Given two strings text1 and text2, return the length of their longest common 
//subsequence. If there is no common subsequence, return 0. 
//
// A subsequence of a string is a new string generated from the original string 
//with some characters (can be none) deleted without changing the relative order 
//of the remaining characters. 
//
// 
// For example, "ace" is a subsequence of "abcde". 
// 
//
// A common subsequence of two strings is a subsequence that is common to both 
//strings. 
//
// 
// Example 1: 
//
// 
//Input: text1 = "abcde", text2 = "ace" 
//Output: 3  
//Explanation: The longest common subsequence is "ace" and its length is 3.
// 
//
// Example 2: 
//
// 
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.
// 
//
// Example 3: 
//
// 
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 and text2 consist of only lowercase English characters. 
// 
//
// Related Topics 字符串 动态规划 👍 1826 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [1143]Longest Common Subsequence
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence().new Solution();
        System.out.println(solution.longestCommonSubsequence4("abcde", "ace"));
        System.out.println(solution.longestCommonSubsequence4("abc", "abc"));
        System.out.println(solution.longestCommonSubsequence4("abc", "def"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestCommonSubsequence1(String text1, String text2) {
            return f(text1.toCharArray(), 0, text2.toCharArray(), 0);
        }

        public static int f(char[] s1, int i1, char[] s2, int i2) {
            if (i1 == s1.length || i2 == s2.length) {
                return 0;
            }
            int ans;
            if (s1[i1] == s2[i2]) {
                ans = f(s1, i1 + 1, s2, i2 + 1) + 1;
            } else {
                ans = Math.max(f(s1, i1 + 1, s2, i2), f(s1, i1, s2, i2 + 1));
            }
            return ans;
        }

        public int longestCommonSubsequence2(String text1, String text2) {
            int n = text1.length();
            int[][] dp = new int[n][text2.length()];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            return f(text1.toCharArray(), 0, text2.toCharArray(), 0, dp);
        }

        public static int f(char[] s1, int i1, char[] s2, int i2, int[][] dp) {
            if (i1 == s1.length || i2 == s2.length) {
                return 0;
            }

            if (dp[i1][i2] != -1) {
                return dp[i1][i2];
            }

            int ans;
            if (s1[i1] == s2[i2]) {
                ans = f(s1, i1 + 1, s2, i2 + 1, dp) + 1;
            } else {
                ans = Math.max(f(s1, i1 + 1, s2, i2, dp), f(s1, i1, s2, i2 + 1, dp));
            }
            dp[i1][i2] = ans;
            return ans;
        }

        public int longestCommonSubsequence3(String text1, String text2) {
            int n = text1.length();
            int m = text2.length();
            int[][] dp = new int[n + 1][m + 1];
            char[] c1 = text1.toCharArray();
            char[] c2 = text2.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (c1[i] == c2[j]) {
                        dp[i][j] = dp[i + 1][j + 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                    }
                }
            }
            return dp[0][0];
        }

        // dp + 空间压缩
        public int longestCommonSubsequence4(String text1, String text2) {
            int n = text1.length();
            int m = text2.length();
            int[] dp = new int[m + 1];
            char[] c1 = text1.toCharArray();
            char[] c2 = text2.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                int rightDown = 0;
                for (int j = m - 1, backup; j >= 0; j--) {
                    backup = dp[j];
                    if (c1[i] == c2[j]) {
                        dp[j] = rightDown + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j + 1]);
                    }
                    // 右下角
                    rightDown = backup;
                }
            }
            return dp[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}