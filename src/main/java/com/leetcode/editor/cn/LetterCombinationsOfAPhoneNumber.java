//Given a string containing digits from 2-9 inclusive, return all possible 
//letter combinations that the number could represent. Return the answer in any order. 
//
//
// A mapping of digits to letters (just like on the telephone buttons) is given 
//below. Note that 1 does not map to any letters. 
// 
// 
// Example 1: 
//
// 
//Input: digits = "23"
//Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// Example 2: 
//
// 
//Input: digits = "2"
//Output: ["a","b","c"]
// 
//
// 
// Constraints: 
//
// 
// 1 <= digits.length <= 4 
// digits[i] is a digit in the range ['2', '9']. 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 3241 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [17]Letter Combinations of a Phone Number
 *
 */
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations("7"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private final static String[] KEYS = new String[]{
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz",
        };

        public List<String> letterCombinations(String digits) {
            int[] ints = digits.chars().map(Character::getNumericValue).toArray();
            List<String> ans = new ArrayList<>();
            f(ints, 0, ints.length, new char[ints.length], ans);
            return ans;
        }

        private void f(int[] ints, int p, int len, char[] path, List<String> ans) {
            if (p == len) {
                ans.add(new String(path));
                return;
            }

            String letters = KEYS[ints[p]];
            for (int i = 0, n = letters.length(); i < n; i++) {
                path[p] = letters.charAt(i);
                f(ints, p + 1, len, path, ans);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}