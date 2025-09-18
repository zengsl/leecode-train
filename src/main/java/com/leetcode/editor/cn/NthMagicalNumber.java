//A positive integer is magical if it is divisible by either a or b. 
//
// Given the three integers n, a, and b, return the nᵗʰ magical number. Since 
//the answer may be very large, return it modulo 10⁹ + 7. 
//
// 
// Example 1: 
//
// 
//Input: n = 1, a = 2, b = 3
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: n = 4, a = 2, b = 3
//Output: 6
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 10⁹ 
// 2 <= a, b <= 4 * 10⁴ 
// 
//
// Related Topics 数学 二分查找 👍 254 👎 0


package com.leetcode.editor.cn;

/**
 * [878]Nth Magical Number
 */
public class NthMagicalNumber {
    public static void main(String[] args) {
        Solution solution = new NthMagicalNumber().new Solution();
        System.out.println(solution.lcm(18, 12));
        System.out.println(solution.gcd(12, 16));
        System.out.println(solution.nthMagicalNumber(1, 2, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int nthMagicalNumber(int n, int a, int b) {
            long num = 0;
            long lcm = a > b ? lcm(a, b) : lcm(b, a);
            for (long l = 0, r = (long) Math.min(a, b) * n, mid; l <= r; ) {
                mid = l + ((r - l) >> 1);
                if (mid / a + mid / b - mid / lcm < n) {
                    r = mid - 1;
                    num = mid;
                } else {
                    l = mid + 1;
                }
            }
            return (int) (num % 1000000007);
        }

        public long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public long lcm(long a, long b) {
            long x = gcd(a, b);
            return a / x * b;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}