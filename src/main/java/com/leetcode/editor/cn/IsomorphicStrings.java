//Given two strings s and t, determine if they are isomorphic. 
//
// Two strings s and t are isomorphic if the characters in s can be replaced to 
//get t. 
//
// All occurrences of a character must be replaced with another character while 
//preserving the order of characters. No two characters may map to the same 
//character, but a character may map to itself. 
//
// 
// Example 1: 
//
// 
// Input: s = "egg", t = "add" 
// 
//
// Output: true 
//
// Explanation: 
//
// The strings s and t can be made identical by: 
//
// 
// Mapping 'e' to 'a'. 
// Mapping 'g' to 'd'. 
// 
//
// Example 2: 
//
// 
// Input: s = "foo", t = "bar" 
// 
//
// Output: false 
//
// Explanation: 
//
// The strings s and t can not be made identical as 'o' needs to be mapped to 
//both 'a' and 'r'. 
//
// Example 3: 
//
// 
// Input: s = "paper", t = "title" 
// 
//
// Output: true 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 5 * 10⁴ 
// t.length == s.length 
// s and t consist of any valid ascii character. 
// 
//
// Related Topics 哈希表 字符串 👍 752 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * [205]Isomorphic Strings
 */
public class IsomorphicStrings {
    public static void main(String[] args) {
        Solution solution = new IsomorphicStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        /**
         * 官方题解
         *
         */
        /*public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            Map<Character, Character> indexMap1 = new HashMap<>();
            Map<Character, Character> indexMap2 = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char sC = s.charAt(i);
                char tC = t.charAt(i);
                if ((indexMap1.containsKey(sC) && indexMap1.get(sC) != tC) || (indexMap2.containsKey(tC) && indexMap2.get(tC) != sC)) {
                    return false;
                }
                indexMap1.put(sC, tC);
                indexMap2.put(tC, sC);
            }
            return true;
        }*/


        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            Map<Character, Integer> indexMap1 = new HashMap<>();
            Map<Character, Integer> indexMap2 = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Integer prevIndex1 = indexMap1.put(s.charAt(i), i);
                Integer prevIndex2 = indexMap2.put(t.charAt(i), i);
                if(!Objects.equals(prevIndex1, prevIndex2)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}