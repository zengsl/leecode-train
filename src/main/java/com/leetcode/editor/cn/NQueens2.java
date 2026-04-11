//The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
//such that no two queens attack each other. 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//You may return the answer in any order. 
//
// Each solution contains a distinct board configuration of the n-queens' 
//placement, where 'Q' and '.' both indicate a queen and an empty space, respectively. 
//
// 
// Example 1: 
// 
// 
//Input: n = 4
//Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as 
//shown above
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: [["Q"]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 9 
// 
//
// Related Topics 数组 回溯 👍 2467 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * [51]N-Queens
 *
 */
public class NQueens2 {
    public static void main(String[] args) {
        Solution solution = new NQueens2().new Solution();
//        System.out.println(solution.solveNQueens(1));
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private char[] placeHolder;

        public List<List<String>> solveNQueens(int n) {
            placeHolder = new char[n];
            Arrays.fill(placeHolder, '.');
            int target = (1 << n) - 1;
            List<List<String>> ans = new ArrayList<>();
            f(target, 0, 0, 0, new ArrayList<>(), ans);
            return ans;
        }

        private void f(int target, int col, int left, int right, List<String> plan, List<List<String>> ans) {
            if (target == col) {
                ans.add(new ArrayList<>(plan));
                return;
            }

            int disable = col | left | right;
            int can = (~disable) & target, place;
            while (can != 0) {
                place = can & (-can);
                can ^= place;
                plan.add(getRes(place));
                f(target, col | place, ((left | place) << 1) & target, (right | place) >> 1, plan, ans);
                plan.removeLast();
            }
        }

        private String getRes(int i) {
            int pos = Integer.numberOfTrailingZeros(i);
            Arrays.fill(placeHolder, '.');
            placeHolder[pos] = 'Q';
            return String.valueOf(placeHolder);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}