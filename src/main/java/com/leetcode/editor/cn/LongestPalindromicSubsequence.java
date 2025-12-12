//Given a string s, find the longest palindromic subsequence's length in s. 
//
// A subsequence is a sequence that can be derived from another sequence by 
//deleting some or no elements without changing the order of the remaining elements. 
//
// 
// Example 1: 
//
// 
//Input: s = "bbbab"
//Output: 4
//Explanation: One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: 2
//Explanation: One possible longest palindromic subsequence is "bb".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists only of lowercase English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 1385 👎 0


package com.leetcode.editor.cn;

/**
 * [516]Longest Palindromic Subsequence
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
        System.out.println(solution.longestPalindromeSubseq4("bbbab"));
        System.out.println(solution.longestPalindromeSubseq4("cbbd"));
        System.out.println(solution.longestPalindromeSubseq4("aaa"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int longestPalindromeSubseq1(String s) {
            return f(s.toCharArray(), 0, s.length() - 1);

        }

        public static int f(char[] s, int l, int r) {
            if (s.length == 1 || l == r) {
                return 1;
            }

            if (l > r || s.length == 0) {
                return 0;
            }

            int ans;
            if (s[l] == s[r]) {
                ans = f(s, l + 1, r - 1) + 2;
            } else {
                ans = Math.max(f(s, l + 1, r), f(s, l, r - 1));
            }
            return ans;
        }

        public int longestPalindromeSubseq3(String s) {
            int n = s.length();
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            char[] c = s.toCharArray();
            int[][] dp = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = 1;
            }
            // leftDown down left
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (c[i] == c[j]) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[0][n - 1];
        }

        public int longestPalindromeSubseq4(String s) {
            int n = s.length();
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            char[] c = s.toCharArray();
            int[] dp = new int[n];
            // leftDown down left
            for (int i = n - 1; i >= 0; i--) {
                int leftDown = 0;
                dp[i] = 1;
                for (int j = i + 1, backup; j < n; j++) {
                    backup = dp[j];
                    if (c[i] == c[j]) {
                        dp[j] = leftDown + 2;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                    leftDown = backup;
                }
            }
            return dp[n - 1];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}