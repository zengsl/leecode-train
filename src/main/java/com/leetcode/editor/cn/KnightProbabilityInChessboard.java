//On an n x n chessboard, a knight starts at the cell (row, column) and 
//attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left 
//cell is (0, 0), and the bottom-right cell is (n - 1, n - 1). 
//
// A chess knight has eight possible moves it can make, as illustrated below. 
//Each move is two cells in a cardinal direction, then one cell in an orthogonal 
//direction. 
// 
// Each time the knight is to move, it chooses one of eight possible moves 
//uniformly at random (even if the piece would go off the chessboard) and moves there. 
//
//
// The knight continues moving until it has made exactly k moves or has moved 
//off the chessboard. 
//
// Return the probability that the knight remains on the board after it has 
//stopped moving. 
//
// 
// Example 1: 
//
// 
//Input: n = 3, k = 2, row = 0, column = 0
//Output: 0.06250
//Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight 
//on the board.
//From each of those positions, there are also two moves that will keep the 
//knight on the board.
//The total probability the knight stays on the board is 0.0625.
// 
//
// Example 2: 
//
// 
//Input: n = 1, k = 0, row = 0, column = 0
//Output: 1.00000
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 25 
// 0 <= k <= 100 
// 0 <= row, column <= n - 1 
// 
//
// Related Topics 动态规划 👍 399 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [688]Knight Probability in Chessboard
 *
 */
public class KnightProbabilityInChessboard {
    public static void main(String[] args) {
        Solution solution = new KnightProbabilityInChessboard().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double knightProbability(int n, int k, int row, int column) {
            double[][][] dp = new double[n][n][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int r = 0; r <= k; r++) {
                        dp[i][j][r] = -1;
                    }
                }
            }
            return f(n, k, row, column, dp);
        }

        public double f(int n, int k, int row, int col, double[][][] dp) {
            if (row >= n || row < 0 || col >= n || col < 0) {
                return 0;
            }

            if (dp[row][col][k] != -1) {
                return dp[row][col][k];
            }
            double ans = 0;
            if (k == 0) {
                ans = 1;
            } else {
                ans += f(n, k - 1, row - 1, col - 2, dp) / 8;
                ans += f(n, k - 1, row - 2, col - 1, dp) / 8;
                ans += f(n, k - 1, row - 1, col + 2, dp) / 8;
                ans += f(n, k - 1, row - 2, col + 1, dp) / 8;
                ans += f(n, k - 1, row + 1, col - 2, dp) / 8;
                ans += f(n, k - 1, row + 2, col - 1, dp) / 8;
                ans += f(n, k - 1, row + 1, col + 2, dp) / 8;
                ans += f(n, k - 1, row + 2, col + 1, dp) / 8;
            }
            dp[row][col][k] = ans;
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}