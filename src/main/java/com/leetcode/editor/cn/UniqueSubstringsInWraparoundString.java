//We define the string base to be the infinite wraparound string of 
//"abcdefghijklmnopqrstuvwxyz", so base will look like this: 
//
// 
// "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
// 
//
// Given a string s, return the number of unique non-empty substrings of s are 
//present in base. 
//
// 
// Example 1: 
//
// 
//Input: s = "a"
//Output: 1
//Explanation: Only the substring "a" of s is in base.
// 
//
// Example 2: 
//
// 
//Input: s = "cac"
//Output: 2
//Explanation: There are two substrings ("a", "c") of s in base.
// 
//
// Example 3: 
//
// 
//Input: s = "zab"
//Output: 6
//Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab") 
//of s in base.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁵ 
// s consists of lowercase English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 452 👎 0


package com.leetcode.editor.cn;

/**
 * [467]Unique Substrings in Wraparound String
 */
public class UniqueSubstringsInWraparoundString {
    public static void main(String[] args) {
        Solution solution = new UniqueSubstringsInWraparoundString().new Solution();
        System.out.println(solution.findSubstringInWraproundString("zab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int findSubstringInWraproundString(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();

            // 按字符存储 a ~ z，以某个字符结尾的最长子串
            int[] dp = new int[26];
            dp[chars[0] - 'a'] = 1;

            int prev = 1, curr;
            for (int i = 1; i < n; i++) {
                if ((chars[i] == 'a' && chars[i - 1] == 'z') || (chars[i] - chars[i - 1] == 1)) {
                    curr = prev + 1;
                } else {
                    curr = 1;
                }
                dp[chars[i] - 'a'] = Math.max(dp[chars[i] - 'a'], curr);
                prev = curr;
            }

            int ans = 0;
            for (int j : dp) {
                ans += j;
            }
            return ans;
        }

        public int findSubstringInWraproundString2(String s) {
            int n = s.length();
            char[] chars = s.toCharArray();
            int[] si = new int[n];
            // 字符转换为数字，防止下面循环的时候处理繁琐
            for (int i = 0; i < n; i++) {
                si[i] = chars[i] - 'a';
            }
            // 按字符存储 a ~ z，以某个字符结尾的最长子串
            int[] dp = new int[26];
            dp[si[0]] = 1;

            int prev = 1, curr;
            for (int i = 1; i < n; i++) {
                if ((si[i] == 0 && si[i - 1] == 25) || (si[i] - si[i - 1] == 1)) {
                    curr = prev + 1;
                } else {
                    curr = 1;
                }
                dp[si[i]] = Math.max(dp[si[i]], curr);
                prev = curr;
            }

            int ans = 0;
            for (int j : dp) {
                ans += j;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}