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

import java.util.ArrayList;
import java.util.List;

/**
 * [906]Super Palindromes
 */
public class SuperPalindromes {
    public static void main(String[] args) {
        Solution solution = new SuperPalindromes().new Solution();
//        System.out.println(solution.superpalindromesInRange("1", "2"));
//        System.out.println(solution.superpalindromesInRange("40000000000000000", "50000000000000000"));
        /*System.out.println(solution.superpalindromesInRange("4", "1000"));
        System.out.println(solution.getEvenPalindrome(12));
        System.out.println(solution.getOddPalindrome(12));*/
//        System.out.println(solution.superpalindromesInRange("1", "99999999999999999"));

        solution.collect();
//        System.out.println(solution.superpalindromesInRange2("40000000000000000", "50000000000000000"));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {



        public int superpalindromesInRange2(String left, String right) {
            long l = Long.parseLong(left);
            long r = Long.parseLong(right);
            int start = -1, end = 0;
            for (int i = 0; i < nums.length; i++) {
                if (start == -1 && nums[i] >= l) {
                    start = i;
                }

                if (nums[i] > r) {
                    break;
                }
                end = i;
            }
            return end - start + 1;
        }
        private static Long[] nums = new Long[]{
                1L,
                4L,
                9L,
                121L,
                484L,
                10201L,
                12321L,
                14641L,
                40804L,
                44944L,
                1002001L,
                1234321L,
                4008004L,
                100020001L,
                102030201L,
                104060401L,
                121242121L,
                123454321L,
                125686521L,
                400080004L,
                404090404L,
                10000200001L,
                10221412201L,
                12102420121L,
                12345654321L,
                40000800004L,
                1000002000001L,
                1002003002001L,
                1004006004001L,
                1020304030201L,
                1022325232201L,
                1024348434201L,
                1210024200121L,
                1212225222121L,
                1214428244121L,
                1232346432321L,
                1234567654321L,
                4000008000004L,
                4004009004004L,
                100000020000001L,
                100220141022001L,
                102012040210201L,
                102234363432201L,
                121000242000121L,
                121242363242121L,
                123212464212321L,
                123456787654321L,
                400000080000004L,
                10000000200000001L,
                10002000300020001L,
                10004000600040001L,
                10020210401202001L,
                10022212521222001L,
                10024214841242001L,
                10201020402010201L,
                10203040504030201L,
                10205060806050201L,
                10221432623412201L,
                10223454745432201L,
                12100002420000121L,
                12102202520220121L,
                12104402820440121L,
                12122232623222121L,
                12124434743442121L,
                12321024642012321L,
                12323244744232321L,
                12343456865434321L,
                12345678987654321L,
                40000000800000004L,
                40004000900040004L,
                1000000002000000001L,
                1000220014100220001L,
                1002003004003002001L,
                1002223236323222001L,
                1020100204020010201L,
                1020322416142230201L,
                1022123226223212201L,
                1022345658565432201L,
                1210000024200000121L,
                1210242036302420121L,
                1212203226223022121L,
                1212445458545442121L,
                1232100246420012321L,
                1232344458544432321L,
                1234323468643234321L,
                4000000008000000004L,
        };

        public int superpalindromesInRange(String left, String right) {
            // 在范围 [1 - 10^18)上求超级回文
            // 转换为 [1 - 10^9) 上的回文数的平方是否是回文
            // 求回文数进一步转换，在[1 - 10^5)上遍历，并且构造回文. 10^9的一半再扩大点范围就是10^5
            long l = Long.parseLong(left);
            long r = Long.parseLong(right);
            // 范围确定了，所以可以强转
            long limit = (long) Math.sqrt(r);
            int seed = 1;
            long num;
            int count = 0;
            do {
                // 偶数回文
                num = getEvenPalindrome(seed);
                if (check(num * num, l, r)) {
                    count++;
                    System.out.println(num + "L,");
                }

                // 基数回文
                num = getOddPalindrome(seed);
                if (check(num * num, l, r)) {
                    count++;
                    System.out.println(num + "L,");
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

        private boolean check(long num, long left, long right) {
            return num >= left && num <= right && isPalindrome(num);
        }

        private long getEvenPalindrome(long num) {
            long palindrome = num;
            while (num > 0) {
                palindrome = palindrome * 10 + num % 10;
                num /= 10;
            }
            return palindrome;
        }

        private long getOddPalindrome(long num) {
            long palindrome = num;
            num /= 10;
            while (num > 0) {
                palindrome = palindrome * 10 + num % 10;
                num /= 10;
            }
            return palindrome;
        }

        /*private boolean isLegalSuperPalindrome(int num, int limit) {
            return num < limit && isPalindrome(num) && isPalindrome(num * num);
        }*/

        private boolean isPalindrome(long num) {
            if (num < 0) {
                return false;
            }
            long scale = getScale(num);
            while (num > 0) {
                if (num / scale != num % 10) {
                    return false;
                }
                num = num % scale / 10;
                scale /= 100;
            }
            return true;
        }

        private long getScale(long num) {
            long scale = 1;
            while (num / scale >= 10) {
                scale *= 10;
            }
            return scale;
        }

        private void collect() {
            // 在范围 [1 - 10^18)上求超级回文
            // 转换为 [1 - 10^9) 上的回文数的平方是否是回文
            // 求回文数进一步转换，在[1 - 10^5)上遍历，并且构造回文. 10^9的一半再扩大点范围就是10^5
            long l = 1L;
            long r = Long.MAX_VALUE;
            // 范围确定了，所以可以强转
            long limit = (long) Math.sqrt(r);
            int seed = 1;
            long num;
            List<Long> ans = new ArrayList<>();
            do {
                // 偶数回文
                num = getEvenPalindrome(seed);
                if (check(num * num, l, r)) {
                    ans.add(num * num);
                }

                // 基数回文
                num = getOddPalindrome(seed);
                if (check(num * num, l, r)) {
                    ans.add(num * num);
                }
                seed++;
            } while (num < limit);
            ans.sort(Long::compareTo);
            ans.forEach(a -> System.out.println(a + "L,"));

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}