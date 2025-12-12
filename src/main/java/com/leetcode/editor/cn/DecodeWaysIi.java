//A message containing letters from A-Z can be encoded into numbers using the 
//following mapping: 
//
// 
//'A' -> "1"
//'B' -> "2"
//...
//'Z' -> "26"
// 
//
// To decode an encoded message, all the digits must be grouped then mapped 
//back into letters using the reverse of the mapping above (there may be multiple 
//ways). For example, "11106" can be mapped into: 
//
// 
// "AAJF" with the grouping (1 1 10 6) 
// "KJF" with the grouping (11 10 6) 
// 
//
// Note that the grouping (1 11 06) is invalid because "06" cannot be mapped 
//into 'F' since "6" is different from "06". 
//
// In addition to the mapping above, an encoded message may contain the '*' 
//character, which can represent any digit from '1' to '9' ('0' is excluded). For 
//example, the encoded message "1*" may represent any of the encoded messages "11", "1
//2", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to 
//decoding any of the encoded messages it can represent. 
//
// Given a string s consisting of digits and '*' characters, return the number 
//of ways to decode it. 
//
// Since the answer may be very large, return it modulo 10⁹ + 7. 
//
// 
// Example 1: 
//
// 
//Input: s = "*"
//Output: 9
//Explanation: The encoded message can represent any of the encoded messages "1
//", "2", "3", "4", "5", "6", "7", "8", or "9".
//Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G",
// "H", and "I" respectively.
//Hence, there are a total of 9 ways to decode "*".
// 
//
// Example 2: 
//
// 
//Input: s = "1*"
//Output: 18
//Explanation: The encoded message can represent any of the encoded messages "11
//", "12", "13", "14", "15", "16", "17", "18", or "19".
//Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be 
//decoded to "AA" or "K").
//Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
// 
//
// Example 3: 
//
// 
//Input: s = "2*"
//Output: 15
//Explanation: The encoded message can represent any of the encoded messages "21
//", "22", "23", "24", "25", "26", "27", "28", or "29".
//"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27",
// "28", and "29" only have 1 way.
//Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*
//".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁵ 
// s[i] is a digit or '*'. 
// 
//
// Related Topics 字符串 动态规划 👍 256 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [639]Decode Ways II
 */
public class DecodeWaysIi {
    public static void main(String[] args) {
        Solution solution = new DecodeWaysIi().new Solution();
        System.out.println(solution.numDecodings("3*"));
        System.out.println(solution.numDecodings("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final long MOD = 1000000007;

        public int numDecodings(String s) {
            return numDecodings4(s);
        }

        // 普通递归
        public int numDecodings1(String s) {
            return (int) numDecodings1(s.toCharArray(), 0);
        }

        // 没有区膜，暴力解
        public long numDecodings1(char[] chars, int p) {
            int n = chars.length;
            if (p == n) {
                return 1;
            }

            if (chars[p] == '0') {
                return 0;
            }

            // p位置单独转
            long ans = (chars[p] == '*' ? 9 : 1) * numDecodings1(chars, p + 1);
            if (p + 1 < n) {
                // p和p+1位置一起转
                if (chars[p] == '*' && chars[p + 1] == '*') {
                    // **
                    ans += 15 * numDecodings1(chars, p + 2);
                } else if (chars[p] == '*') {
                    // *1
                    ans += (chars[p + 1] < '7' ? 2 : 1) * numDecodings1(chars, p + 2);
                } else if (chars[p + 1] == '*') {
                    // 1*
                    if (chars[p] == '1') {
                        ans += 9 * numDecodings1(chars, p + 2);
                    } else if (chars[p] == '2') {
                        ans += 6 * numDecodings1(chars, p + 2);
                    }
                } else if (((chars[p] - '0') * 10 + (chars[p + 1] - '0')) <= 26) {
                    ans += numDecodings1(chars, p + 2);
                }
            }
            return Math.toIntExact(ans % MOD);
        }

        public int numDecodings2(String s) {
            long[] dp = new long[s.length()];
            Arrays.fill(dp, -1);
            return (int) numDecodings2(s.toCharArray(), 0, dp);
        }

        // 没有区膜，暴力解
        public long numDecodings2(char[] chars, int p, long[] dp) {
            int n = chars.length;
            if (p == n) {
                return 1;
            }

            if (dp[p] != -1) {
                return dp[p];
            }

            if (chars[p] == '0') {
                dp[p] = 0;
                return 0;
            }

            // p位置单独转
            long ans = (chars[p] == '*' ? 9 : 1) * numDecodings2(chars, p + 1, dp);
            if (p + 1 < n) {
                // p和p+1位置一起转
                if (chars[p] == '*' && chars[p + 1] == '*') {
                    // **
                    ans += 15 * numDecodings2(chars, p + 2, dp);
                } else if (chars[p] == '*') {
                    // *1
                    ans += (chars[p + 1] < '7' ? 2 : 1) * numDecodings2(chars, p + 2, dp);
                } else if (chars[p + 1] == '*') {
                    // 1*
                    if (chars[p] == '1') {
                        ans += 9 * numDecodings2(chars, p + 2, dp);
                    } else if (chars[p] == '2') {
                        ans += 6 * numDecodings2(chars, p + 2, dp);
                    }
                } else if (((chars[p] - '0') * 10 + (chars[p + 1] - '0')) <= 26) {
                    ans += numDecodings2(chars, p + 2, dp);
                }
            }
            ans %= MOD;
            dp[p] = ans;
            return ans;
        }

        public int numDecodings3(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();
            long[] dp = new long[n + 1];
            dp[n] = 1;
            for (int p = n - 1; p >= 0; p--) {
                if (chars[p] == '0') {
                    continue;
                }
                dp[p] = (chars[p] == '*' ? 9 : 1) * dp[p + 1];
                if (p + 1 < n) {
                    // p和p+1位置一起转
                    if (chars[p] == '*' && chars[p + 1] == '*') {
                        // **
                        dp[p] += 15 * dp[p + 2];
                    } else if (chars[p] == '*') {
                        // *1
                        dp[p] += (chars[p + 1] < '7' ? 2 : 1) * dp[p + 2];
                    } else if (chars[p + 1] == '*') {
                        // 1*
                        if (chars[p] == '1') {
                            dp[p] += 9 * dp[p + 2];
                        } else if (chars[p] == '2') {
                            dp[p] += 6 * dp[p + 2];
                        }
                    } else if (((chars[p] - '0') * 10 + (chars[p + 1] - '0')) <= 26) {
                        dp[p] += dp[p + 2];
                    }
                }
                dp[p] %= MOD;
            }

            return (int)dp[0];
        }


        public int numDecodings4(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();
            long curr = 0, next = 1, nextNext = 0;

            for (int p = n - 1; p >= 0; p--) {
                curr = 0;
                if (chars[p] != '0') {
                    curr = (chars[p] == '*' ? 9 : 1) * next;
                    if (p + 1 < n) {
                        // p和p+1位置一起转
                        if (chars[p] == '*' && chars[p + 1] == '*') {
                            // **
                            curr += 15 * nextNext;
                        } else if (chars[p] == '*') {
                            // *1
                            curr += (chars[p + 1] < '7' ? 2 : 1) * nextNext;
                        } else if (chars[p + 1] == '*') {
                            // 1*
                            if (chars[p] == '1') {
                                curr += 9 * nextNext;
                            } else if (chars[p] == '2') {
                                curr += 6 * nextNext;
                            }
                        } else if (((chars[p] - '0') * 10 + (chars[p + 1] - '0')) <= 26) {
                            curr += nextNext;
                        }
                    }
                    curr %= MOD;
                }

                nextNext = next;
                next = curr;
            }

            return (int)curr;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}