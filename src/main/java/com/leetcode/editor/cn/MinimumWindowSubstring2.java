//Given two strings s and t of lengths m and n respectively, return the minimum 
//window substring of s such that every character in t (including duplicates) is 
//included in the window. If there is no such substring, return the empty string 
//"". 
//
// The testcases will be generated such that the answer is unique. 
//
// 
// Example 1: 
//
// 
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' 
//from string t.
// 
//
// Example 2: 
//
// 
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.
// 
//
// Example 3: 
//
// 
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.
// 
//
// 
// Constraints: 
//
// 
// m == s.length 
// n == t.length 
// 1 <= m, n <= 10⁵ 
// s and t consist of uppercase and lowercase English letters. 
// 
//
// 
// Follow up: Could you find an algorithm that runs in O(m + n) time? 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 3407 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [76]Minimum Window Substring
 */
public class MinimumWindowSubstring2 {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring2().new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(solution.minWindow("aa", "aa"));
        System.out.println(solution.minWindow("ab", "b"));
        System.out.println(solution.minWindow("a", "b"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public String minWindow(String s, String t) {
            int m = s.length(), n = t.length();
            if (m < n) {
                return "";
            }

            int[] count = new int[57];
            int kind = 0;
            for (char c : t.toCharArray()) {
                if (count[c - 'A']++ == 0) {
                    count[c - 'A'] = 1;
                    kind++;
                }
            }

            int minLen = Integer.MAX_VALUE, pos = 0;
            char[] chars = s.toCharArray();
            for (int l = 0, r = 0; r < m; r++) {
                if (count[chars[r] - 'A']-- == 1) {
                    kind--;
                }
                // 结算
                if (kind == 0) {
                    while (count[chars[l] - 'A'] < 0 && l < r) {
                        count[chars[l] - 'A']++;
                        l++;
                    }

                    if (minLen > r - l + 1) {
                        minLen = r - l + 1;
                        pos = l;
                    }
                }
            }
            return minLen == Integer.MAX_VALUE ? "" : s.substring(pos, pos + minLen);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}