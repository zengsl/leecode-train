//You are given a large integer represented as an integer array digits, where 
//each digits[i] is the iᵗʰ digit of the integer. The digits are ordered from most 
//significant to least significant in left-to-right order. The large integer does 
//not contain any leading 0's. 
//
// Increment the large integer by one and return the resulting array of digits. 
//
//
// 
// Example 1: 
//
// 
//Input: digits = [1,2,3]
//Output: [1,2,4]
//Explanation: The array represents the integer 123.
//Incrementing by one gives 123 + 1 = 124.
//Thus, the result should be [1,2,4].
// 
//
// Example 2: 
//
// 
//Input: digits = [4,3,2,1]
//Output: [4,3,2,2]
//Explanation: The array represents the integer 4321.
//Incrementing by one gives 4321 + 1 = 4322.
//Thus, the result should be [4,3,2,2].
// 
//
// Example 3: 
//
// 
//Input: digits = [9]
//Output: [1,0]
//Explanation: The array represents the integer 9.
//Incrementing by one gives 9 + 1 = 10.
//Thus, the result should be [1,0].
// 
//
// 
// Constraints: 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// digits does not contain any leading 0's. 
// 
//
// Related Topics 数组 数学 👍 1448 👎 0


package com.leetcode.editor.cn;

/**
 * [66]Plus One
 */
public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int[] plusOne(int[] digits) {

            for (int i = digits.length - 1; i >= 0; i--) {
                // 数字小于9，直接+1后返回
                if (digits[i] < 9) {
                    digits[i] += 1;
                    return digits;
                }
                // 数字等于9，则变为0，说明产生了进位
                digits[i] = 0;
            }
            // 没从for循环中return，说明数字全是9，需要进位，数组大小 + 1
            int[] result = new int[digits.length + 1];
            // 数组第一个元素为1（其他位，数组初始化默认为0）
            result[0] = 1;
            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}