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
public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
//        System.out.println(solution.solveNQueens(1));
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int all;
        private char[] placeHolder;

        public List<List<String>> solveNQueens(int n) {
            placeHolder = new char[n];
            List<List<String>> ans = new ArrayList<>();
            List<String> plan = new ArrayList<>();
            int available = all = (1 << n) - 1;
            f(0, n, available, 0, 0, 0, plan, ans);
            System.out.println("ans size：" + ans.size());
            return ans;
        }

        private void f(int row, int n, int available, int placed, int left, int right, List<String> plan, List<List<String>> ans) {

            if (row == n) {
                ans.add(new ArrayList<>(plan));
                return;
            }
            if (available == 0) {
                return;
            }

            for (int i = 0, remain, used; i < n; i++) {
                if ((available & (1 << i)) == 0) {
                    continue;
                }
                Arrays.fill(placeHolder, '.');
                placeHolder[i] = 'Q';
                // 收集
                plan.add(String.valueOf(placeHolder));
                // 占位
                used = 1 << i;
                available ^= used;
                placed |= used;
                left = ((placed | left) << 1) & all;
                right = (placed | right) >> 1;
                remain = all ^ (used | left | right);
                f(row + 1, n, remain, used | left | right, left, right, plan, ans);
                plan.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}