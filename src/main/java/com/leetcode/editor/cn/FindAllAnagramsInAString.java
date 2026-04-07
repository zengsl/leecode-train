//Given two strings s and p, return an array of all the start indices of p's 
//anagrams in s. You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: s = "cbaebabacd", p = "abc"
//Output: [0,6]
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
// 
//
// Example 2: 
//
// 
//Input: s = "abab", p = "ab"
//Output: [0,1,2]
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s and p consist of lowercase English letters. 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1925 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * [438]Find All Anagrams in a String
 *
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
        System.out.println(solution.findAnagrams("abab", "ab"));
        System.out.println(solution.findAnagrams("af", "be"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> ansList = new ArrayList<>();
            if (s.length() < p.length()) {
                return ansList;
            }
            char[] sc = s.toCharArray();
            char[] pc = p.toCharArray();
            int sLen = sc.length, pLen = pc.length;
            int[] countS = new int[26];
            int[] countP = new int[26];
            for (int i = 0; i < pLen; i++) {
                ++countS[sc[i] - 'a'];
                ++countP[pc[i] - 'a'];
            }

            if (Arrays.equals(countS, countP)) {
                ansList.add(0);
            }

            for (int i = 0; i < sLen - pLen; i++) {
                --countS[sc[i] - 'a'];
                ++countS[sc[(i) + pLen] - 'a'];
                if (Arrays.equals(countS, countP)) {
                    ansList.add(i + 1);
                }
            }
            return ansList;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}