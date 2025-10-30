//Given a string s, remove duplicate letters so that every letter appears once 
//and only once. You must make sure your result is the smallest in lexicographical 
//order among all possible results. 
//
// 
// Example 1: 
//
// 
//Input: s = "bcabc"
//Output: "abc"
// 
//
// Example 2: 
//
// 
//Input: s = "cbacdcbc"
//Output: "acdb"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of lowercase English letters. 
// 
//
// 
// Note: This question is the same as 1081: https://leetcode.com/problems/
//smallest-subsequence-of-distinct-characters/ 
//
// Related Topics 栈 贪心 字符串 单调栈 👍 1164 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [316]Remove Duplicate Letters
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicateLetters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        static int sl;
        static int MAX = 10001;
        static char[] STACK = new char[MAX];

        static int[] CNTS = new int[26];
        static int[] CONTAINS = new int[26];

        public String removeDuplicateLetters(String s) {

            Arrays.fill(CNTS, 0);
            Arrays.fill(CONTAINS, 0);
            // 从左往右依次遍历入栈
            // 1.如果当前字符大于栈顶，那么就放入
            // 2.如果当前字符小于栈顶，且后面还有栈顶元素，那么就弹出栈顶后放入
            // 3.如果当前字符已经放过，那么则忽略
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                CNTS[c - 'a']++;
            }

            sl = 0;
            for (char c : charArray) {
                if (CONTAINS[c - 'a'] == 1) {
                    CNTS[c - 'a']--;
                    continue;
                }

                while (sl > 0 && STACK[sl - 1] >= c && CNTS[STACK[sl - 1] - 'a'] > 0) {
                    char curr = STACK[--sl];
                    CONTAINS[curr - 'a'] = 0;
                }
                STACK[sl++] = c;
                CONTAINS[c - 'a'] = 1;
                CNTS[c - 'a']--;
            }
            return String.valueOf(STACK, 0, sl);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}