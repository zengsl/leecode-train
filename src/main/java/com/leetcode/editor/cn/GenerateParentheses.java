//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// 
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 4080 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [22]Generate Parentheses
 *
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new ArrayList<>();
            f(n, 0, 0, new StringBuilder(), ans);
            return ans;
        }

        private void f(int n, int left, int right, StringBuilder sb, List<String> ans) {
            if (left == n && right == n) {
                ans.add(sb.toString());
                return;
            }

            if (left < n) {
                sb.append("(");
                f(n, left + 1, right, sb, ans);
                sb.deleteCharAt(sb.length() - 1);
            }

            if (left > right) {
                sb.append(")");
                f(n, left, right + 1, sb, ans);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}