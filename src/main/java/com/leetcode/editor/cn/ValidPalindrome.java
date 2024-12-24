//A phrase is a palindrome if, after converting all uppercase letters into 
//lowercase letters and removing all non-alphanumeric characters, it reads the same 
//forward and backward. Alphanumeric characters include letters and numbers. 
//
// Given a string s, return true if it is a palindrome, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: s = "A man, a plan, a canal: Panama"
//Output: true
//Explanation: "amanaplanacanalpanama" is a palindrome.
// 
//
// Example 2: 
//
// 
//Input: s = "race a car"
//Output: false
//Explanation: "raceacar" is not a palindrome.
// 
//
// Example 3: 
//
// 
//Input: s = " "
//Output: true
//Explanation: s is an empty string "" after removing non-alphanumeric 
//characters.
//Since an empty string reads the same forward and backward, it is a palindrome.
//
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// s consists only of printable ASCII characters. 
// 
//
// Related Topics 双指针 字符串 👍 786 👎 0


package com.leetcode.editor.cn;

/**
 * [125]Valid Palindrome
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
//        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome(".,"));
//        System.out.println(solution.isPalindrome(".a"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isPalindrome(String s) {
            int i = 0, j = s.length() - 1;
            while (i < j) {
                while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                    i++;
                }
                while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                    j--;
                }
                if (Character.toUpperCase(s.charAt(i++)) != Character.toUpperCase(s.charAt(j--))) {
                    return false;
                }
            }
            return true;
        }

        /*public boolean isPalindrome(String s) {
            char[] charArray = s.toCharArray();
            int maxIndex = charArray.length - 1;
            int i = 0, j = maxIndex;
            while (i < j) {
                while (i < j && isIllegalChar(charArray[i])  ) {
                    i ++;
                }
                while (i < j && isIllegalChar(charArray[j])) {
                    j--;
                }

                if (Character.toUpperCase(charArray[i++]) != Character.toUpperCase(charArray[j--])) {
                    return false;
                }
            }
            return true;
        }


        boolean isIllegalChar(char c) {
            return !Character.isDigit(c) && !Character.isLetter(c);
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}