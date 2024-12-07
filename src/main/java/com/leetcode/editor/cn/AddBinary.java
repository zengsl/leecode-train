//Given two binary strings a and b, return their sum as a binary string. 
//
// 
// Example 1: 
// Input: a = "11", b = "1"
//Output: "100"
// 
// Example 2: 
// Input: a = "1010", b = "1011"
//Output: "10101"
// 
// 
// Constraints: 
//
// 
// 1 <= a.length, b.length <= 10⁴ 
// a and b consist only of '0' or '1' characters. 
// Each string does not contain leading zeros except for the zero itself. 
// 
//
// Related Topics 位运算 数学 字符串 模拟 👍 1258 👎 0


package com.leetcode.editor.cn;

/**
 * [67]Add Binary
 */
public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        System.out.println(solution.addBinary("1010", "1011"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            int carryOver = 0;
            StringBuilder result = new StringBuilder();
            int i = a.length() - 1, j = b.length() - 1;
            while (i >= 0 || j >= 0) {
                int sum = carryOver;
                if (i >= 0) {
                    sum += a.charAt(i--) - '0';
                }
                if (j >= 0) {
                    sum += b.charAt(j--) - '0';
                }
                carryOver = sum / 2;
                result.append(sum % 2);
            }

            if (carryOver != 0) {
                result.append(carryOver);
            }
            return result.reverse().toString();

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}