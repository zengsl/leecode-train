//Given an array of integers arr, find the sum of min(b), where b ranges over 
//every (contiguous) subarray of arr. Since the answer may be large, return the 
//answer modulo 10⁹ + 7. 
//
// 
// Example 1: 
//
// 
//Input: arr = [3,1,2,4]
//Output: 17
//Explanation: 
//Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,
//2,4]. 
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//Sum is 17.
// 
//
// Example 2: 
//
// 
//Input: arr = [11,81,94,43,3]
//Output: 444
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 3 * 10⁴ 
// 1 <= arr[i] <= 3 * 10⁴ 
// 
//
// Related Topics 栈 数组 动态规划 单调栈 👍 885 👎 0


package com.leetcode.editor.cn;

/**
 * [907]Sum of Subarray Minimums
 * // 测试链接 : https://leetcode.cn/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {
    public static void main(String[] args) {
        Solution solution = new SumOfSubarrayMinimums().new Solution();
        System.out.println(solution.sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static int MOD = 1000000007;

        private final static int MAX = 30001;

        private final static int[] STACK = new int[MAX];

        private static int sl;

        public int sumSubarrayMins(int[] arr) {
            sl = 0;
            int length = arr.length;
            long sum = 0;
            for (int i = 0; i < length; i++) {
                while (sl > 0 && arr[STACK[sl - 1]] >= arr[i]) {
                    int cur = STACK[--sl];
                    int left = sl > 0 ? STACK[sl - 1] : -1;
                    sum = (sum + (long) (i - cur) * (cur - left) * arr[cur]) % MOD;
                }
                STACK[sl++] = i;
            }

            // -1 (0 1 2 3 4) 5
            //0 1 2 3 4 5 6
            //  l c   r
            //2 5 4 5 2 3 6
            while (sl > 0) {
                int curr = STACK[--sl];
                int left = sl > 0 ? STACK[sl - 1] : -1;
                sum = (sum + (long) (length - curr) * (curr - left) * arr[curr]) % MOD;
            }

            return (int) sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}