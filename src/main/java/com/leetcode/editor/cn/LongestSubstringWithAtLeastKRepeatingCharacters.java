//Given a string s and an integer k, return the length of the longest substring 
//of s such that the frequency of each character in this substring is greater 
//than or equal to k. 
//
// if no such substring exists, return 0. 
//
// 
// Example 1: 
//
// 
//Input: s = "aaabb", k = 3
//Output: 3
//Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
// 
//
// Example 2: 
//
// 
//Input: s = "ababbc", k = 2
//Output: 5
//Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 
//'b' is repeated 3 times.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of only lowercase English letters. 
// 1 <= k <= 10⁵ 
// 
//
// Related Topics 哈希表 字符串 分治 滑动窗口 👍 983 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [395]Longest Substring with At Least K Repeating Characters
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
        System.out.println(solution.longestSubstring("ababbc", 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int longestSubstring(String s, int k) {

            int[] cnts = new int[256];
            int ans = 0;
            for (int require = 1; require <= 26; require++) {
                Arrays.fill(cnts, 0);
                for (int i = 0, j = 0, size = s.length(), capture = 0, satisfy = 0; j < size; j++) {
                    if (cnts[s.charAt(j)]++ == 0) {
                        // 元素个数
                        capture++;
                    }

                    if (cnts[s.charAt(j)] == k) {
                        // 达标的个数
                        satisfy++;
                    }

                    while (capture > require) {
                        if (cnts[s.charAt(i)] == 1) {
                            capture--;
                        }

                        if (cnts[s.charAt(i)] == k) {
                            satisfy--;
                        }
                        cnts[s.charAt(i++)]--;
                    }

                    if (satisfy == require) {
                        ans = Math.max(ans, j - i + 1);
                    }
                }
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}