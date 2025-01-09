//Write an algorithm to determine if a number n is happy. 
//
// A happy number is a number defined by the following process: 
//
// 
// Starting with any positive integer, replace the number by the sum of the 
//squares of its digits. 
// Repeat the process until the number equals 1 (where it will stay), or it 
//loops endlessly in a cycle which does not include 1. 
// Those numbers for which this process ends in 1 are happy. 
// 
//
// Return true if n is a happy number, and false if not. 
//
// 
// Example 1: 
//
// 
//Input: n = 19
//Output: true
//Explanation:
//1² + 9² = 82
//8² + 2² = 68
//6² + 8² = 100
//1² + 0² + 0² = 1
// 
//
// Example 2: 
//
// 
//Input: n = 2
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
//
// Related Topics 哈希表 数学 双指针 👍 1641 👎 0


package com.leetcode.editor.cn;

/**
 * [202]Happy Number
 */
public class HappyNumber {
    public static void main(String[] args) {
        Solution solution = new HappyNumber().new Solution();
        System.out.println(solution.isHappy(19));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean isHappy(int n) {
            int fast = getNext(n);
            int slow = n;
            while (fast != 1 && fast != slow) {
                fast = getNext(getNext(fast));
                slow = getNext(slow);
            }
            return fast == 1;
        }


        private int getNext(int n) {
            int result = 0;
            while (n > 0) {
                int tmp = n % 10;
                result += tmp * tmp;
                n /= 10;
            }
            return result;
        }

        /*public boolean isHappy(int n) {
            Set<Integer> resultMap = new HashSet<>();
            resultMap.add(n);
            while (true) {
                n = process(n);
                if (n == 1) {
                    return true;
                } else if (resultMap.contains(n)) {
                    return false;
                }
                resultMap.add(n);
            }
        }

        private int process(int n) {
            char[] chars = String.valueOf(n).toCharArray();
            int result = 0;
            for (char curr : chars) {
                int r = curr - 48;
                result += r * r;
            }
            return result;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}