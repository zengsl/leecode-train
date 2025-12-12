//Given a m x n grid filled with non-negative numbers, find a path from top 
//left to bottom right, which minimizes the sum of all numbers along its path. 
//
// Note: You can only move either down or right at any point in time. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
// 
//
// Example 2: 
//
// 
//Input: grid = [[1,2,3],[4,5,6]]
//Output: 12
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 200 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 1884 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [64]Minimum Path Sum
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
//        System.out.println(solution.minPathSum4(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(solution.minPathSum4(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum1(int[][] grid) {
            // 0, 0 开始到 x, y的路径和
            return f(grid, grid.length - 1, grid[0].length - 1);
        }

        public static int f(int[][] grid, int x, int y) {
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                return Integer.MAX_VALUE;
            }

            if (x == 0 && y == 0) {
                return grid[0][0];
            }

            int path1 = f(grid, x - 1, y);
            int path2 = f(grid, x, y - 1);
            int ans = Math.min(path1, path2);
            return grid[x][y] + ans;
        }

        public int minPathSum2(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            // 0, 0 开始到 x, y的路径和
            return f(grid, n - 1, m - 1, dp);
        }

        public static int f(int[][] grid, int x, int y, int[][] dp) {

            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                return Integer.MAX_VALUE;
            }

            if (x == 0 && y == 0) {
                return grid[0][0];
            }

            if (dp[x][y] != -1) {
                return dp[x][y];
            }

            int path1 = f(grid, x - 1, y);
            int path2 = f(grid, x, y - 1);
            int ans = Math.min(path1, path2);
            // cache
            dp[x][y] = grid[x][y] + ans;
            return dp[x][y];
        }

        public int minPathSum3(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] dp = new int[n][m];

            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }

            // 0, 0 开始到 x, y的路径和
            return dp[n - 1][m - 1];
        }


        public int minPathSum4(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[] dp = new int[m];
            dp[0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[i] = dp[i - 1] + grid[0][i];
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j == 0) {
                        dp[j] = grid[i][j] + dp[j];
                    } else {
                        dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                    }
                }
            }
            // 0, 0 开始到 x, y的路径和
            return dp[m - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}