//Given two strings needle and haystack, return the index of the first 
//occurrence of needle in haystack, or -1 if needle is not part of haystack. 
//
// 
// Example 1: 
//
// 
//Input: haystack = "sadbutsad", needle = "sad"
//Output: 0
//Explanation: "sad" occurs at index 0 and 6.
//The first occurrence is at index 0, so we return 0.
// 
//
// Example 2: 
//
// 
//Input: haystack = "leetcode", needle = "leeto"
//Output: -1
//Explanation: "leeto" did not occur in "leetcode", so we return -1.
// 
//
// 
// Constraints: 
//
// 
// 1 <= haystack.length, needle.length <= 10⁴ 
// haystack and needle consist of only lowercase English characters. 
// 
//
// Related Topics 双指针 字符串 字符串匹配 👍 2498 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [28]Find the Index of the First Occurrence in a String
 *
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {
    public static void main(String[] args) {
        Solution solution = new FindTheIndexOfTheFirstOccurrenceInAString().new Solution();
        System.out.println(solution.strStr("sadbutsad", "sad"));
        System.out.println(solution.strStr("leetcode", "leeto"));
        System.out.println(solution.strStr("mississippi", "issip"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            return kmp(haystack.toCharArray(), needle.toCharArray());
        }

        // KMP算法
        public static int kmp(char[] s1, char[] s2) {
            int n = s1.length, m = s2.length, x = 0, y = 0;
            int[] next = getNextArr(s2, m);
            while (x < n && y < m) {
                if (s1[x] == s2[y]) {
                    x++;
                    y++;
                } else if (-1 != next[y]) {
                    y = next[y];
                } else {
                    x++;
                    y = 0;
                }
            }
            return y == m ? x - y : -1;
        }


        public static int[] getNextArr(char[] s, int m) {
            if (m == 1) {
                return new int[]{-1};
            }
            int[] next = new int[m];
            next[0] = -1;
            next[1] = 0;
            //i是next k是需要比的下标
            int i = 2, cn = 0;
            while (i < m) {
                if (s[i - 1] == s[cn]) {
                    next[i++] = ++cn;
                } else if (next[cn] != -1) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}