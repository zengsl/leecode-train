//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']
//', determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// Every close bracket has a corresponding open bracket of the same type. 
// 
//
// 
// Example 1: 
//
// 
// Input: s = "()" 
// 
//
// Output: true 
//
// Example 2: 
//
// 
// Input: s = "()[]{}" 
// 
//
// Output: true 
//
// Example 3: 
//
// 
// Input: s = "(]" 
// 
//
// Output: false 
//
// Example 4: 
//
// 
// Input: s = "([])" 
// 
//
// Output: true 
//
// Example 5: 
//
// 
// Input: s = "([)]" 
// 
//
// Output: false 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of parentheses only '()[]{}'. 
// 
//
// Related Topics 栈 字符串 👍 4951 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [20]Valid Parentheses
 *
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid("()"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean isValid(String s) {
            int length = s.length();
            if (length % 2 != 0) {
                return false;
            }
            char[] stack = new char[length];
            int r = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (c == '(') {
                    stack[r++] = ')';
                } else if (c == '{') {
                    stack[r++] = '}';
                } else if (c == '[') {
                    stack[r++] = ']';
                } else if (r == 0 || stack[--r] != c) {
                    return false;
                }
            }
            return r == 0;
        }

        /*public boolean isValid(String s) {
            int length = s.length();
            if (length % 2 != 0) {
                return false;
            }
            int[] stack = new int[length];
            int r = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < length; i++) {
                if (isEndChar(chars[i])) {
                    if (r == 0 || !isPair(chars[stack[r - 1]], chars[i])) {
                        return false;
                    } else {
                        r--;
                    }
                } else {
                    stack[r++] = i;
                }
            }
            return r == 0;
        }*/

        /*public boolean isValid(String s) {
            if (s.length() % 2 != 0) {
                return false;
            }
            char[] stack = new char[s.length()];
            int r = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (isEndChar(c)) {
                    if (r == 0 || !isPair(stack[r - 1], c)) {
                        return false;
                    } else {
                        r--;
                    }
                } else {
                    stack[r++] = c;
                }
            }
            return r == 0;
        }*/

        /*public boolean isValid(String s) {
            char[] chars = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char c : chars) {
                if (isEndChar(c)) {
                    if (stack.isEmpty() || !isPair(stack.peek(), c)) {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
*/
        private boolean isEndChar(char c) {
            return c == ')' || c == '}' || c == ']';
        }

        private boolean isPair(char c1, char c2) {
            return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}