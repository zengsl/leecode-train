//Given a string s, return the number of distinct non-empty subsequences of s. 
//Since the answer may be very large, return it modulo 10⁹ + 7. A 
//subsequence of a string is a new string that is formed from the original 
//string by deleting some (can be none) of the characters without disturbing the 
//relative positions of the remaining characters. (i.e., 
//"ace" is a subsequence of 
//"abcde" while 
//"aec" is not. 
// 
// Example 1: 
//
// 
//Input: s = "abc"
//Output: 7
//Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", 
//and "abc".
// 
//
// Example 2: 
//
// 
//Input: s = "aba"
//Output: 6
//Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and 
//"aba".
// 
//
// Example 3: 
//
// 
//Input: s = "aaa"
//Output: 3
//Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 2000 
// s consists of lowercase English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 362 👎 0


package com.leetcode.editor.cn;

/**
 * [940]Distinct Subsequences II
 */
public class DistinctSubsequencesIi {
    public static void main(String[] args) {
        Solution solution = new DistinctSubsequencesIi().new Solution();
        System.out.println(solution.distinctSubseqII("abc"));
        System.out.println(solution.distinctSubseqII("aba"));
        System.out.println(solution.distinctSubseqII("aaa"));
        System.out.println(solution.distinctSubseqII("blljuffdyfrkqtwfyfztpdiyktrhftgtabxxoibcclbjvirnqyynkyaqlxgyybkgyzvcahmytjdqqtctirnxfjpktxmjkojlvvrr"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MOD = 1000000007;
        public int distinctSubseqII(String s) {
            int[] dp = new int[26];
            // 空集合
            int ans = 1, newAdd;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                newAdd = (ans - dp[c - 'a'] ) % MOD;
                dp[c - 'a'] = (dp[c - 'a'] + newAdd) % MOD;
                ans = (ans + newAdd) % MOD;

                /*ans = ans * 2 - dp[c - 'a'];
                dp[c - 'a'] = ans;*/
            }
            return (ans - 1 + MOD) % MOD;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}