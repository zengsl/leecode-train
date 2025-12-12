//You have intercepted a secret message encoded as a string of numbers. The 
//message is decoded via the following mapping: 
//
// "1" -> 'A' "2" -> 'B' ... "25" -> 'Y' "26" -> 'Z' 
//
// However, while decoding the message, you realize that there are many 
//different ways you can decode the message because some codes are contained in other 
//codes ("2" and "5" vs "25"). 
//
// For example, "11106" can be decoded into: 
//
// 
// "AAJF" with the grouping (1, 1, 10, 6) 
// "KJF" with the grouping (11, 10, 6) 
// The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6
//" is valid). 
// 
//
// Note: there may be strings that are impossible to decode. Given a string s 
//containing only digits, return the number of ways to decode it. If the entire 
//string cannot be decoded in any valid way, return 0. 
//
// The test cases are generated so that the answer fits in a 32-bit integer. 
//
// 
// Example 1: 
//
// 
// Input: s = "12" 
// 
//
// Output: 2 
//
// Explanation: 
//
// "12" could be decoded as "AB" (1 2) or "L" (12). 
//
// Example 2: 
//
// 
// Input: s = "226" 
// 
//
// Output: 3 
//
// Explanation: 
//
// "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6). 
//
// Example 3: 
//
// 
// Input: s = "06" 
// 
//
// Output: 0 
//
// Explanation: 
//
// "06" cannot be mapped to "F" because of the leading zero ("6" is different 
//from "06"). In this case, the string is not a valid encoding, so return 0. 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 100 
// s contains only digits and may contain leading zero(s). 
// 
//
// Related Topics 字符串 动态规划 👍 1631 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [91]Decode Ways
 */
public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
        System.out.println(solution.numDecodings("12"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            return numDecodings4(s);
        }

        public int numDecodings4(String s) {
            int n = s.length();
            int curr = 0, next = 1, nextNext = 0;
            char[] chars = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                curr = 0;
                if (chars[i] != '0') {
                    curr += next;
                    if (i + 1 < n && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) <= 26) {
                        curr += nextNext;
                    }
                }
                nextNext = next;
                next = curr;
            }
            return curr;
        }

        public int numDecodings3(String s) {
            int n = s.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            char[] chars = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {
                if (chars[i] == '0') {
                    dp[i] = 0;
                } else {
                    dp[i] += dp[i + 1];
                    if (i + 1 < n && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
            return dp[0];
        }

        public int numDecodings1(String s) {
            return numDecodings1(s.toCharArray(), 0);
        }

        public int numDecodings1(char[] chars, int i) {
            if (i == chars.length) {
                return 1;
            }

            if (chars[i] == '0') {
                return 0;
            }

            int ans = 0;
            ans += numDecodings1(chars, i + 1);
            if (i + 1 < chars.length && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) <= 26) {
                ans += numDecodings1(chars, i + 2);
            }

            return ans;
        }

        public int numDecodings2(String s) {
            int[] dp = new int[s.length()];
            Arrays.fill(dp, -1);
            return numDecodings2(s.toCharArray(), 0, dp);
        }

        public int numDecodings2(char[] chars, int i, int[] dp) {
            if (i == chars.length) {
                return 1;
            }
            if (dp[i] != -1) {
                return dp[i];
            }
            if (chars[i] == '0') {
                dp[i] = 0;
                return 0;
            }

            int ans = 0;
            ans += numDecodings2(chars, i + 1, dp);
            if (i + 1 < chars.length && ((chars[i] - '0') * 10 + (chars[i + 1] - '0')) <= 26) {
                ans += numDecodings2(chars, i + 2, dp);
            }
            dp[i] = ans;
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}