//Given a string containing just the characters '(' and ')', return the length 
//of the longest valid (well-formed) parentheses substring. 
//
// 
// Example 1: 
//
// 
//Input: s = "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()".
// 
//
// Example 2: 
//
// 
//Input: s = ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()".
// 
//
// Example 3: 
//
// 
//Input: s = ""
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] is '(', or ')'. 
// 
//
// Related Topics 栈 字符串 动态规划 👍 2809 👎 0


package com.leetcode.editor.cn;

/**
 * [32]Longest Valid Parentheses
 */
public class LongestValidParentheses2 {
    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses2().new Solution();
        System.out.println(solution.longestValidParentheses(")()())"));
        System.out.println(solution.longestValidParentheses("()(())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            char[] charArray = s.toCharArray();
            int len = charArray.length, max = 0;
            int[] ans = new int[len];
            for (int i = 1, pre; i < len; i++) {
                if (charArray[i] == '(') {
                    continue;
                }
                pre = i - ans[i - 1] - 1;
                if (pre >= 0 && charArray[pre] == '(') {
                    ans[i] = 2 + ans[i - 1] + (pre - 1 > 0 ? ans[pre - 1] : 0);
                }
                max = Math.max(max, ans[i]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}