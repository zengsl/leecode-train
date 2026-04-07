//We can scramble a string s to get a string t using the following algorithm: 
//
// 
// If the length of the string is 1, stop. 
// If the length of the string is > 1, do the following: 
// 
// Split the string into two non-empty substrings at a random index, i.e., if 
//the string is s, divide it to x and y where s = x + y. 
// Randomly decide to swap the two substrings or to keep them in the same order.
// i.e., after this step, s may become s = x + y or s = y + x. 
// Apply step 1 recursively on each of the two substrings x and y. 
// 
// 
//
// Given two strings s1 and s2 of the same length, return true if s2 is a 
//scrambled string of s1, otherwise, return false. 
//
// 
// Example 1: 
//
// 
//Input: s1 = "great", s2 = "rgeat"
//Output: true
//Explanation: One possible scenario applied on s1 is:
//"great" --> "gr/eat" // divide at random index.
//"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings 
//and keep them in order.
//"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both 
//substrings. divide at random index each of them.
//"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first 
//substring and to keep the second substring in the same order.
//"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, 
//divide "at" to "a/t".
//"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both 
//substrings in the same order.
//The algorithm stops now, and the result string is "rgeat" which is s2.
//As one possible scenario led s1 to be scrambled to s2, we return true.
// 
//
// Example 2: 
//
// 
//Input: s1 = "abcde", s2 = "caebd"
//Output: false
// 
//
// Example 3: 
//
// 
//Input: s1 = "a", s2 = "a"
//Output: true
// 
//
// 
// Constraints: 
//
// 
// s1.length == s2.length 
// 1 <= s1.length <= 30 
// s1 and s2 consist of lowercase English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 614 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [87]Scramble String
 *
 */
public class ScrambleString {
    public static void main(String[] args) {
        Solution solution = new ScrambleString().new Solution();
        /*System.out.println(solution.isScramble2("great", "rgeat"));
        System.out.println(solution.isScramble2("abcde", "caebd"));
        System.out.println(solution.isScramble3("great", "rgeat"));
        System.out.println(solution.isScramble3("abcde", "caebd"));
        System.out.println(solution.isScramble4("great", "rgeat"));
        System.out.println(solution.isScramble4("abcde", "caebd"));*/
        System.out.println(solution.isScramble4("abc", "bca"));
        //System.out.println(solution.isScramble5("abc", "bca"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isScramble1(String s1, String s2) {
            return f1(s1.toCharArray(), 0, s1.length() - 1, s2.toCharArray(), 0, s2.length() - 1);
        }

        // 0 ~ 4
        // 0, 1, 2, 3, 4, 5
        // 0 ,
        public boolean f1(char[] chars1, int b1, int e1, char[] chars2, int b2, int e2) {
            if (b1 == e1) {
                return chars1[b1] == chars2[b2];
            }

            for (int i = 0, range = (e1 - b1); i < range; i++) {
                if (f1(chars1, b1, b1 + i, chars2, b2, b2 + i) && f1(chars1, b1 + i + 1, e1, chars2, b2 + i + 1, e2)) {
                    return true;
                }

                if (f1(chars1, b1, b1 + i, chars2, e2 - i, e2) && f1(chars1, b1 + i + 1, e1, chars2, b2, e2 - i - 1)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isScramble2(String s1, String s2) {
            int n = s1.length();
            int[][][] dp = new int[n][n][n + 1];
            return f2(s1.toCharArray(), 0, s2.toCharArray(), 0, s2.length(), dp);
        }

        // len: 6
        // 0, 1, 2, 3, 4, 5
        // 2/4
        public boolean f2(char[] chars1, int b1, char[] chars2, int b2, int len, int[][][] dp) {
            if (len == 1) {
                return chars1[b1] == chars2[b2];
            }

            if (dp[b1][b2][len] != 0) {
                return dp[b1][b2][len] == 1;
            }

            int ans = -1;
            for (int i = 1; i < len; i++) {
                if (f2(chars1, b1, chars2, b2, i, dp) && f2(chars1, b1 + i, chars2, b2 + i, len - i, dp)) {
                    ans = 1;
                    break;
                }

                if (f2(chars1, b1, chars2, b2 + len - i, i, dp) && f2(chars1, b1 + i, chars2, b2, len - i, dp)) {
                    ans = 1;
                    break;
                }
            }
            dp[b1][b2][len] = ans;
            return ans == 1;
        }

        public boolean isScramble3(String s1, String s2) {
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();
            int n = s1.length();
            boolean[][][] dp = new boolean[n][n][n + 1];
            // len == 1
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][1] = chars1[i] == chars2[j];
                }
            }
            // 按总长度从小到大遍历
            for (int len = 2; len <= n; len++) {
                // 注意i/j边界, 需要保障后面还够len个字符
                // 0 1 2 3 4 5 / n = 6 len = 2
                for (int i = 0; i <= n - len; i++) {
                    for (int j = 0; j <= n - len; j++) {
                        // 遍历分割长度
                        // 0 1 2 3 4
                        for (int k = 1; k < len; k++) {
                            if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                            if (!dp[i][j][len] && dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                                dp[i][j][len] = true;
                                break;
                            }
                            /*dp[i][j][len] = dp[i][j][k] && dp[i + k][j + k][len - k];
                            if (!dp[i][j][len]) {
                                dp[i][j][len] = dp[i][j + len - k][k] && dp[i + k][j][len - k];
                            }
                            if (dp[i][j][len]) {
                                break;
                            }*/
                        }
                    }
                }
            }
            return dp[0][0][n];
        }


        //isScramble3 微调
        public boolean isScramble4(String s1, String s2) {
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();
            int n = s1.length();
            boolean[][][] dp = new boolean[n][n][n + 1];
            // len == 1
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][1] = chars1[i] == chars2[j];
                }
            }
            // 按总长度从小到大遍历
            for (int len = 2; len <= n; len++) {
                // 注意i/j边界, 需要保障后面还够len个字符
                // 0 1 2 3 4 5 / n = 6 len = 2
                for (int i = 0; i <= n - len; i++) {
                    for (int j = 0; j <= n - len; j++) {
                        // 遍历分割长度
                        // 0 1 2 3 4
                        for (int k = 1, i2 = i + k, j2 = j + k, len2 = len - k; k < len; k++, i2++, j2++, len2--) {
                            if (dp[i][j][k] && dp[i2][j2][len2]) {
                                dp[i][j][len] = true;
                                break;
                            }
                        }

                        if (!dp[i][j][len]) {
                            for (int k = 1, j2 = j + len - k, i2 = i + k, len2 = len - k; k < len; k++, i2++, j2--, len2--) {
                                if (dp[i][j2][k] && dp[i2][j][len2]) {
                                    dp[i][j][len] = true;
                                    break;
                                }
                            }
                        }

                    }
                }
            }
            return dp[0][0][n];
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)


}

