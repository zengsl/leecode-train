//You are given a 0-indexed m x n integer matrix grid and an integer k. You are 
//currently at position (0, 0) and you want to reach position (m - 1, n - 1) 
//moving only down or right. 
//
// Return the number of paths where the sum of the elements on the path is 
//divisible by k. Since the answer may be very large, return it modulo 10⁹ + 7. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
//Output: 2
//Explanation: There are two paths where the sum of the elements on the path is 
//divisible by k.
//The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which 
//is divisible by 3.
//The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which 
//is divisible by 3.
// 
//
// Example 2: 
// 
// 
//Input: grid = [[0,0]], k = 5
//Output: 1
//Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is 
//divisible by 5.
// 
//
// Example 3: 
// 
// 
//Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
//Output: 10
//Explanation: Every integer is divisible by 1 so the sum of the elements on 
//every possible path is divisible by k.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 5 * 10⁴ 
// 1 <= m * n <= 5 * 10⁴ 
// 0 <= grid[i][j] <= 100 
// 1 <= k <= 50 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 98 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [2435]Paths in Matrix Whose Sum Is Divisible by K
 *
 */
public class PathsInMatrixWhoseSumIsDivisibleByK {
    public static void main(String[] args) {
        Solution solution = new PathsInMatrixWhoseSumIsDivisibleByK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MOD = 1000000007;

        public int numberOfPaths1(int[][] grid, int k) {
            int n = grid.length;
            int m = grid[0].length;
            return f1(grid, n, m, k, 0, 0, 0);
        }

        public int f1(int[][] grid, int n, int m, int k, int i, int j, int r) {
            if (i == n - 1 && j == m - 1) {
                return grid[i][j] % k == r ? 1 : 0;
            }

            int ans = 0, need = (k + r - (grid[i][j] % k)) % k;
            if (i + 1 < n) {
                ans += f1(grid, n, m, k, i + 1, j, need) % MOD;
            }

            if (j + 1 < m) {
                ans = (ans + f1(grid, n, m, k, i, j + 1, need)) % MOD;
            }
            return ans;
        }

        public int numberOfPaths2(int[][] grid, int k) {
            int n = grid.length;
            int m = grid[0].length;
            int[][][] dp = new int[n][m][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int r = 0; r <= k; r++) {
                        dp[i][j][r] = -1;
                    }
                }
            }
            return f2(grid, n, m, k, 0, 0, 0, dp);
        }

        public int f2(int[][] grid, int n, int m, int k, int i, int j, int r, int[][][] dp) {
            if (dp[i][j][r] != -1) {
                return dp[i][j][r];
            }

            if (i == n - 1 && j == m - 1) {
                return grid[i][j] % k == r ? 1 : 0;
            }

            int ans = 0, need = (k + r - (grid[i][j] % k)) % k;
            if (i + 1 < n) {
                ans += f2(grid, n, m, k, i + 1, j, need, dp) % MOD;
            }

            if (j + 1 < m) {
                ans = (ans + f2(grid, n, m, k, i, j + 1, need, dp)) % MOD;
            }
            dp[i][j][r] = ans;
            return ans;
        }

        public int numberOfPaths3(int[][] grid, int k) {
            int n = grid.length;
            int m = grid[0].length;
            int[][][] dp = new int[n][m][k];
            dp[n - 1][m - 1][grid[n - 1][m - 1] % k] = 1;
            for (int i = n - 2; i >= 0; i--) {
                for (int r = 0, need; r < k; r++) {
                    need = (k + r - (grid[i][m - 1] % k)) % k;
                    dp[i][m - 1][r] = dp[i + 1][m - 1][need] % MOD;
                }
            }

            for (int j = m - 2; j >= 0; j--) {
                for (int r = 0, need; r < k; r++) {
                    need = (k + r - (grid[n - 1][j] % k)) % k;
                    dp[n - 1][j][r] = dp[n - 1][j + 1][need] % MOD;
                }
            }

            for (int i = n - 2; i >= 0; i--) {
                for (int j = m - 2; j >= 0; j--) {
                    for (int r = 0, need; r < k; r++) {
                        need = (k + r - (grid[i][j] % k)) % k;
                        dp[i][j][r] = dp[i][j + 1][need] % MOD;
                        dp[i][j][r] = (dp[i][j][r] + dp[i + 1][j][need]) % MOD;
                    }
                }
            }

            return dp[0][0][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}