//Given a non-negative integer x, return the square root of x rounded down to 
//the nearest integer. The returned integer should be non-negative as well. 
//
// You must not use any built-in exponent function or operator. 
//
// 
// For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python. 
// 
//
// 
// Example 1: 
//
// 
//Input: x = 4
//Output: 2
//Explanation: The square root of 4 is 2, so we return 2.
// 
//
// Example 2: 
//
// 
//Input: x = 8
//Output: 2
//Explanation: The square root of 8 is 2.82842..., and since we round it down 
//to the nearest integer, 2 is returned.
// 
//
// 
// Constraints: 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 二分查找 👍 1607 👎 0


package com.leetcode.editor.cn;

/**
 * [69]Sqrt(x)
 */
public class Sqrtx {
    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
        System.out.println(solution.mySqrt(2147395599));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            int left = 1, right = x / 2;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                // 避免mid * mid溢出
                if (mid > (x / mid)) {
                    right = mid - 1;
                } else if (mid < (x / mid)) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
            return left  > (x / left) ? left - 1 : left;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}