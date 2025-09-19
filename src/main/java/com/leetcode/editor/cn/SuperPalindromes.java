//Let's say a positive integer is a super-palindrome if it is a palindrome, and 
//it is also the square of a palindrome. 
//
// Given two positive integers left and right represented as strings, return 
//the number of super-palindromes integers in the inclusive range [left, right]. 
//
// 
// Example 1: 
//
// 
//Input: left = "4", right = "1000"
//Output: 4
//Explanation: 4, 9, 121, and 484 are superpalindromes.
//Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a 
//palindrome.
// 
//
// Example 2: 
//
// 
//Input: left = "1", right = "2"
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= left.length, right.length <= 18 
// left and right consist of only digits. 
// left and right cannot have leading zeros. 
// left and right represent integers in the range [1, 10¹⁸ - 1]. 
// left is less than or equal to right. 
// 
//
// Related Topics 数学 字符串 枚举 👍 66 👎 0


package com.leetcode.editor.cn;

/**
 * [906]Super Palindromes
 */
public class SuperPalindromes {
    public static void main(String[] args) {
        Solution solution = new SuperPalindromes().new Solution();
//        System.out.println(solution.superpalindromesInRange("1", "2"));
        System.out.println(solution.superpalindromesInRange("40000000000000000", "50000000000000000"));
        /*System.out.println(solution.superpalindromesInRange("4", "1000"));
        System.out.println(solution.getEvenPalindrome(12));
        System.out.println(solution.getOddPalindrome(12));*/
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int superpalindromesInRange(String left, String right) {
            // 在范围 [1 - 10^18)上求超级回文
            // 转换为 [1 - 10^9) 上的回文数的平方是否是回文
            // 求回文数进一步转换，在[1 - 10^5)上遍历，并且构造回文. 10^9的一半再扩大点范围就是10^5
            long l = Long.parseLong(left);
            long r = Long.parseLong(right);
            // 范围确定了，所以可以强转
            long limit = (long)Math.sqrt(r);
            int seed = 1;
            int num;
            int count = 0;
            do {
                // 偶数回文
                num = getEvenPalindrome(seed);
                if (check(num * num, l, r)) {
                    count++;
                }

                // 基数回文
                num = getOddPalindrome(seed);
                if (check(num * num, l, r)) {
                    count++;
                }
                seed++;
            } while (num < limit);
            /*for (int seed = Integer.parseInt(left); seed < range; seed++) {
                if (seed < 4 && isLegalSuperPalindrome(seed, limit)) {
                    count++;
                    continue;
                }
                int evenPalindrome = getEvenPalindrome(seed);
                if (isLegalSuperPalindrome(evenPalindrome, limit)) {
                    count++;
                }
                int oddPalindrome = getOddPalindrome(seed);
                if (isLegalSuperPalindrome(oddPalindrome, limit)) {
                    count++;
                }
            }*/
            return count;
        }

        private boolean check(int num, long left, long right) {
            return num >= left && num <= right && isPalindrome(num);
        }

        private int getEvenPalindrome(int num) {
            int palindrome = num;
            while (num > 0) {
                palindrome = palindrome * 10 + num % 10;
                num /= 10;
            }
            return palindrome;
        }

        private int getOddPalindrome(int num) {
            int palindrome = num;
            num /= 10;
            while (num > 0) {
                palindrome = palindrome * 10 + num % 10;
                num /= 10;
            }
            return palindrome;
        }

        private boolean isLegalSuperPalindrome(int num, int limit) {
            return num < limit && isPalindrome(num) && isPalindrome(num * num);
        }

        private boolean isPalindrome(int num) {
            if (num < 0) {
                return false;
            }
            int scale = getScale(num);
            while (num > 0) {
                if (num / scale != num % 10) {
                    return false;
                }
                num = num % scale / 10;
                scale /= 100;
            }
            return true;
        }

        private int getScale(int num) {
            int scale = 1;
            while ((num /= 10) > 0) {
                scale *= 10;
            }
            return scale;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}