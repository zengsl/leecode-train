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
public class FindTheIndexOfTheFirstOccurrenceInAString2 {
    public static void main(String[] args) {
        Solution solution = new FindTheIndexOfTheFirstOccurrenceInAString2().new Solution();
        // 0
        System.out.println(solution.strStr("sadbutsad", "sad"));
        // -1
        System.out.println(solution.strStr("leetcode", "leeto"));
        // 4
        System.out.println(solution.strStr("mississippi", "issip"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            return kmp(haystack.toCharArray(), needle.toCharArray());
        }

        // KMP算法
        public static int kmp(char[] s1, char[] s2) {
            int[] nextArr = getNextArr(s2, s2.length);
            int i = 0, j = 0, size1 = s1.length, size2 = s2.length;
            while (i < size1 && j < size2) {
                if (j == -1) {
                    i++;
                    j = 0;
                } else if (s1[i] == s2[j]) {
                    i++;
                    j++;
                } else {
                    j = nextArr[j];
                }
            }
            return j == size2 ? i - j : -1;
        }


        public static int[] getNextArr(char[] s, int m) {
            if (m == 1) {
                return new int[]{-1};
            }
            int[] next = new int[m];
            next[0] = -1;
            next[1] = 0;
            int i = 2, c = 0;
            while (i < m) {
                if (s[i - 1] == s[c]) {
                    next[i++] = ++c;
                } else if (next[c] != -1) {
                    c = next[c];
                } else {
                    next[i++] = 0;
                }
            }
            return next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}