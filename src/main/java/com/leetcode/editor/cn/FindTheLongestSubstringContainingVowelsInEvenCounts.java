//Given the string s, return the size of the longest substring containing each 
//vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear 
//an even number of times. 
//
// 
// Example 1: 
//
// 
//Input: s = "eleetminicoworoep"
//Output: 13
//Explanation: The longest substring is "leetminicowor" which contains two each 
//of the vowels: e, i and o and zero of the vowels: a and u.
// 
//
// Example 2: 
//
// 
//Input: s = "leetcodeisgreat"
//Output: 5
//Explanation: The longest substring is "leetc" which contains two e's.
// 
//
// Example 3: 
//
// 
//Input: s = "bcbcbc"
//Output: 6
//Explanation: In this case, the given string "bcbcbc" is the longest because 
//all vowels: a, e, i, o and u appear zero times.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 5 x 10^5 
// s contains only lowercase English letters. 
// 
//
// Related Topics 位运算 哈希表 字符串 前缀和 👍 512 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [1371]Find the Longest Substring Containing Vowels in Even Counts
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        Solution solution = new FindTheLongestSubstringContainingVowelsInEvenCounts().new Solution();
        System.out.println(solution.findTheLongestSubstring("eleetminicoworoep"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int findTheLongestSubstring(String s) {
            int[] arr = new int[32];
            Arrays.fill(arr, -2);

            int ans = 0;
            int status = 0;
            arr[status] = -1;
            for (int i = 0, size = s.length(), t; i < size; i++) {
                t = type(s.charAt(i));
                if (t != -1) {
                    status ^= 1 << t;
                }
                if (arr[status] != -2) {
                    ans = Math.max(ans, i - arr[status]);
                } else {
                    arr[status] = i;
                }
            }
            return ans;
        }


        /*// a e i o u
        Map<Integer, Integer> map = new HashMap<>();

        public int findTheLongestSubstring(String s) {
            map.clear();
            int ans = 0;
            int status = 0;
            map.put(status, -1);
            for (int i = 0, size = s.length(), t; i < size; i++) {
                t = type(s.charAt(i));
                if (t != -1) {
                    status ^= 1 << t;
                }
                if (map.containsKey(status)) {
                    ans = Math.max(ans, i - map.get(status));
                } else {
                    map.put(status, i);
                }
            }
            return ans;
        }*/

        private int type(char c) {

            return switch (c) {
                case 'a' -> 0;
                case 'e' -> 1;
                case 'i' -> 2;
                case 'o' -> 3;
                case 'u' -> 4;
                default -> -1;
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}