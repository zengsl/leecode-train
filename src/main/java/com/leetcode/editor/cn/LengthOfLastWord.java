//Given a string s consisting of words and spaces, return the length of the 
//last word in the string. 
//
// A word is a maximal substring consisting of non-space characters only. 
//
// 
// Example 1: 
//
// 
//Input: s = "Hello World"
//Output: 5
//Explanation: The last word is "World" with length 5.
// 
//
// Example 2: 
//
// 
//Input: s = "   fly me   to   the moon  "
//Output: 4
//Explanation: The last word is "moon" with length 4.
// 
//
// Example 3: 
//
// 
//Input: s = "luffy is still joyboy"
//Output: 6
//Explanation: The last word is "joyboy" with length 6.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of only English letters and spaces ' '. 
// There will be at least one word in s. 
// 
//
// Related Topics 字符串 👍 727 👎 0


package com.leetcode.editor.cn;

/**
 * [58]Length of Last Word
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        Solution solution = new LengthOfLastWord().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLastWord2(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }

            String[] split = s.split(" ");
            return split[split.length - 1].length();
        }

        public int lengthOfLastWord3(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            int lastWordLength = 0;
            boolean isPreSpace = false;
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) == ' ') {
                    isPreSpace = true;
                } else {
                    if (isPreSpace) {
                        lastWordLength = 0;
                        isPreSpace = false;
                    }
                    lastWordLength += 1;
                }
                i++;
            }

            return lastWordLength;
        }

        public int lengthOfLastWord(String s) {
            if (s == null || s.isEmpty()) {
                return 0;
            }
            int lastWordLength = 0;
            boolean hasWord = false;
            int j = s.length() - 1;
            // 已经出现过有效字符之后再出现空白字符 或者 下标越界 跳出循环
            while (j>=0 && !(s.charAt(j) == ' ' && hasWord)) {
                if (s.charAt(j) != ' ') {
                    hasWord = true;
                }
                j--;
                if (hasWord) {
                    lastWordLength++;
                }
            }

            return lastWordLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}