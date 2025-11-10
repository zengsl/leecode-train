//You are given an m x n matrix board containing letters 'X' and 'O', capture 
//regions that are surrounded: 
//
// 
// Connect: A cell is connected to adjacent cells horizontally or vertically. 
// Region: To form a region connect every 'O' cell. 
// Surround: The region is surrounded with 'X' cells if you can connect the 
//region with 'X' cells and none of the region cells are on the edge of the board. 
// 
//
// To capture a surrounded region, replace all 'O's with 'X's in-place within 
//the original board. You do not need to return anything. 
//
// 
// Example 1: 
//
// 
// Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X",
//"O","X","X"]] 
// 
//
// Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X",
//"X"]] 
//
// Explanation: 
// 
// In the above diagram, the bottom region is not captured because it is on the 
//edge of the board and cannot be surrounded. 
//
// Example 2: 
//
// 
// Input: board = [["X"]] 
// 
//
// Output: [["X"]] 
//
// 
// Constraints: 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] is 'X' or 'O'. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1247 👎 0


package com.leetcode.editor.cn;

/**
 * [130]Surrounded Regions
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new SurroundedRegions().new Solution();
        solution.solve(new char[][]{{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'O', 'X'}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static char[][] BOARD;
        public static char TMP = '1';
        public static int N, M;

        public static void solve(char[][] board) {
            BOARD = board;
            N = board.length;
            M = board[0].length;
            for (int i = 0; i < N; i++) {
                if (board[i][0] == 'O') {
                    dfs(i, 0, 'O', TMP);
                }

                if (board[i][M - 1] == 'O') {
                    dfs(i, M - 1, 'O', TMP);
                }
            }

            for (int i = 1; i < M - 1; i++) {
                if (board[0][i] == 'O') {
                    dfs(0, i, 'O', TMP);
                }

                if (board[N - 1][i] == 'O') {
                    dfs(N - 1, i, 'O', TMP);
                }

            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 'O') {
                        /*dfs(i, j + 1, 'O', 'X');
                        dfs(i, j - 1, 'O', 'X');
                        dfs(i + 1, j, 'O', 'X');
                        dfs(i - 1, j, 'O', 'X');*/
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == TMP) {
                        board[i][j] = 'O';
                    }
                }
            }

            /*for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == TMP) {
                        dfs(i, j, TMP, 'O');
                    }
                }
            }*/

        }

        public static void dfs(int i, int j, char oldVal, char newVal) {
            if (i < 0 || i >= N || j < 0 || j >= M || BOARD[i][j] != oldVal) {
                return;
            }
            BOARD[i][j] = newVal;
            dfs(i, j + 1, oldVal, newVal);
            dfs(i, j - 1, oldVal, newVal);
            dfs(i + 1, j, oldVal, newVal);
            dfs(i - 1, j, oldVal, newVal);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}