//Given a positive integer n, write a function that returns the number of set 
//bits in its binary representation (also known as the Hamming weight). 
//
// 
// Example 1: 
//
// 
// Input: n = 11 
// 
//
// Output: 3 
//
// Explanation: 
//
// The input binary string 1011 has a total of three set bits. 
//
// Example 2: 
//
// 
// Input: n = 128 
// 
//
// Output: 1 
//
// Explanation: 
//
// The input binary string 10000000 has a total of one set bit. 
//
// Example 3: 
//
// 
// Input: n = 2147483645 
// 
//
// Output: 30 
//
// Explanation: 
//
// The input binary string 1111111111111111111111111111101 has a total of 
//thirty set bits. 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
//
// 
//Follow up: If this function is called many times, how would you optimize it?
//
// Related Topics 位运算 分治 👍 663 👎 0


package com.leetcode.editor.cn;

/**
 * [191]Number of 1 Bits
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new NumberOf1Bits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int hammingWeight(int n) {
            int result = 0;
            while (n > 0) {
                int rightOne = n & -n;
                n -= rightOne;
                result++;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}