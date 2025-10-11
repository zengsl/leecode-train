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

/**
 * [76]Minimum Window Substring
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(solution.minWindow("aa", "aa"));
//        System.out.println(solution.minWindow("ab", "b"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public String minWindow(String s, String t) {
            int[] cnts = new int[256];
            for (char c : t.toCharArray()) {
                cnts[c]--;
            }

            int length = Integer.MAX_VALUE, start = 0, debt = t.length();
            for (int i = 0, j = 0, size = s.length(); j < size; j++) {
                if (cnts[s.charAt(j)]++ < 0) {
                    debt--;
                }

                if (debt == 0) {
                    while (cnts[s.charAt(i)] > 0) {
                        cnts[s.charAt(i++)]--;
                    }

                    if (length > j - i + 1 ) {
                        start = i;
                        length = j - i + 1;
                    }
                }
            }

            return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
        }


        /*public String minWindow(String s, String t) {
            Map<Character, Integer> oweMap = new HashMap<>();
            int oweCount = 0;
            for (int i = 0, size = t.length(); i < size; i++) {
                if (!oweMap.containsKey(t.charAt(i))) {
                    oweCount++;
                }
                oweMap.put(t.charAt(i), oweMap.getOrDefault(t.charAt(i), 0) - 1);
            }

            int i = 0, j = 0;
            String ans = "";
            while (j < s.length()) {
                char c = s.charAt(j);
                if (oweMap.containsKey(c)) {
                    oweMap.put(c, oweMap.get(c) + 1);
                    if (oweMap.get(c) == 0) {
                        oweCount--;
                    }
                }

                if (oweCount == 0) {
                    ans = (!ans.isBlank() && ans.length() < (j - i + 1)) ? ans : s.substring(i, j + 1);
                }

                while (oweCount == 0 && i <= j) {
                    char del = s.charAt(i++);
                    if (oweMap.containsKey(del)) {
                        oweMap.put(del, oweMap.get(del) - 1);
                        if (oweMap.get(del) == -1) {
                            oweCount++;
                        } else {
                            ans = ans.length() < (j - i + 1) ? ans : s.substring(i, j + 1);
                        }
                    } else {
                        ans = ans.length() < (j - i + 1) ? ans : s.substring(i, j + 1);
                    }
                }
                j++;
            }
            return ans;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}