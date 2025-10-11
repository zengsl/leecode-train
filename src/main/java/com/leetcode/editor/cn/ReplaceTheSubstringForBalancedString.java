//You are given a string s of length n containing only four kinds of characters:
// 'Q', 'W', 'E', and 'R'. 
//
// A string is said to be balanced if each of its characters appears n / 4 
//times where n is the length of the string. 
//
// Return the minimum length of the substring that can be replaced with any 
//other string of the same length to make s balanced. If s is already balanced, 
//return 0. 
//
// 
// Example 1: 
//
// 
//Input: s = "QWER"
//Output: 0
//Explanation: s is already balanced.
// 
//
// Example 2: 
//
// 
//Input: s = "QQWE"
//Output: 1
//Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is 
//balanced.
// 
//
// Example 3: 
//
// 
//Input: s = "QQQW"
//Output: 2
//Explanation: We can replace the first "QQ" to "ER". 
// 
//
// 
// Constraints: 
//
// 
// n == s.length 
// 4 <= n <= 10⁵ 
// n is a multiple of 4. 
// s contains only 'Q', 'W', 'E', and 'R'. 
// 
//
// Related Topics 字符串 滑动窗口 👍 358 👎 0


package com.leetcode.editor.cn;

/**
 * [1234]Replace the Substring for Balanced String
 */
public class ReplaceTheSubstringForBalancedString {
    public static void main(String[] args) {
        Solution solution = new ReplaceTheSubstringForBalancedString().new Solution();
        System.out.println(solution.balancedString("QQQW"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int balancedString(String s) {
            // init
            char[] charArray = s.toCharArray();
            int length = charArray.length;
            int[] cnts = new int[4];
            int[] p = new int[length];
            for (int i = 0; i < length; i++) {
                char c = charArray[i];
                // index
                p[i] = c == 'Q' ? 0 : (c == 'W' ? 1 : (c == 'E' ? 2 : 3));
                cnts[p[i]]++;
            }

            // count
            int need = length >> 2;
            int debt = 0;
            for (int i = 0; i < cnts.length; i++) {
                if (cnts[i] < need) {
                    cnts[i] = 0;
                } else {
                    cnts[i] = need - cnts[i];
                    debt -= cnts[i];
                }
            }

            if (debt == 0) {
                return 0;
            }

            // cal
            int ans = Integer.MAX_VALUE;
            for (int i = 0, j = 0; j < length; j++) {
                if (cnts[p[j]]++ < 0) {
                    debt--;
                }

                if (debt == 0) {
                    while (i <= j && cnts[p[i]] > 0) {
                        cnts[p[i++]]--;
                    }
                    ans = Math.min(ans, j - i + 1);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}