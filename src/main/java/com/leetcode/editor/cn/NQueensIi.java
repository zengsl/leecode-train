//The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
//such that no two queens attack each other. 
//
// Given an integer n, return the number of distinct solutions to the n-queens 
//puzzle. 
//
// 
// Example 1: 
// 
// 
//Input: n = 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
//
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 9 
// 
//
// Related Topics 回溯 👍 579 👎 0


package com.leetcode.editor.cn;

/**
 * [52]N-Queens II
 */
public class NQueensIi {
    public static void main(String[] args) {
        Solution solution = new NQueensIi().new Solution();
        int i = solution.totalNQueens(2);
        System.out.println(i);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalNQueens(int n) {
            if (n < 1) {
                return 0;
            }
            int limit = (1 << n) - 1;
            int col = 0;
            int left = 0;
            int right = 0;
            return doCountNums(limit, col, left, right);
        }

        private int doCountNums(int limit, int col, int left, int right) {
            if (limit == col) {
                return 1;
            }
            // can not place queen here
            int ban = col | left | right;
            int can = ~(ban) & limit;
            int count = 0;
            while (can != 0) {
                int place = can & (~can + 1);
                can ^= place;
                count += doCountNums(limit, col | place, (left | place) >> 1, (right | place) << 1);
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}