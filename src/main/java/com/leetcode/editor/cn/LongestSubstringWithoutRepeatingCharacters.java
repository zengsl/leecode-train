//Given a string s, find the length of the longest substring without repeating 
//characters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s consists of English letters, digits, symbols and spaces. 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 10464 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [3]Longest Substring Without Repeating Characters
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }

            int maxLength = 0;
            int start = 0;
            int end = 0;
            Map<Character, Character> charMap = new HashMap<>();
            char[] charArray = s.toCharArray();
            while (end < s.length()) {
                if (charMap.containsKey(charArray[end])) {
                    while (start < end) {
                        charMap.remove(charArray[start]);
                        if(charArray[start++] == charArray[end]) {
                            break;
                        }
                    }
                } else {
                    maxLength = Math.max(maxLength, end - start + 1);
                    charMap.put(charArray[end], charArray[end++]);
                }
            }
            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}