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
public class LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses(")()())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            char[] chars = s.toCharArray();
            int n = s.length();
            int[] dp = new int[n];
            int ans = 0;
            for (int i = 1, pre; i < n; i++) {
                if (chars[i] == ')') {
                    // 0 1 2 3 4 5 6
                    // 0 1 ( ( ) ) )
                    // i - 1 == ')'
                    pre = i - dp[i - 1] - 1;
                    if (pre >= 0 && chars[pre] == '(') {
                        dp[i] = 2 + dp[i - 1] + (pre - 1 >= 0 ? dp[pre - 1] : 0);
                        ans = Math.max(ans, dp[i]);
                    }
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}