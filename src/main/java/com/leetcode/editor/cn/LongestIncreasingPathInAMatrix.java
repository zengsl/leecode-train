//Given an m x n integers matrix, return the length of the longest increasing 
//path in matrix. 
//
// From each cell, you can either move in four directions: left, right, up, or 
//down. You may not move diagonally or move outside the boundary (i.e., wrap-
//around is not allowed). 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
//Output: 4
//Explanation: The longest increasing path is [1, 2, 6, 9].
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
//Output: 4
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally 
//is not allowed.
// 
//
// Example 3: 
//
// 
//Input: matrix = [[1]]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// 0 <= matrix[i][j] <= 2³¹ - 1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 数组 动态规划 矩阵 👍 925 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [329]Longest Increasing Path in a Matrix
 */
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingPathInAMatrix().new Solution();
        System.out.println(solution.longestIncreasingPath2(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(solution.longestIncreasingPath2(new int[][]{{1}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static int[][] arr;
        public static int n, m;
        public static final int[] MOVE = new int[]{0, 1, 0, -1, 0};

        public int longestIncreasingPath1(int[][] matrix) {
            n = matrix.length;
            m = matrix[0].length;
            arr = matrix;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    ans = Math.max(ans, f(i, j));
                }
            }
            return ans;
        }

        public static int f(int i, int j) {
            if (outBoundary(i, j)) {
                return 0;
            }
            int ans = 0;
            for (int m = 0, nx, ny; m < 4; m++) {
                nx = i + MOVE[m];
                ny = j + MOVE[m + 1];
                if (!outBoundary(nx, ny) && arr[nx][ny] > arr[i][j]) {
                    ans = Math.max(ans, f(nx, ny));
                }
            }
            return 1 + ans;
        }

        public int longestIncreasingPath2(int[][] matrix) {
            n = matrix.length;
            m = matrix[0].length;
            arr = matrix;
            int ans = 0;
            int[][] dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    ans = Math.max(ans, f2(i, j, dp));
                }
            }
            return ans;
        }

        public static int f2(int i, int j, int[][] dp) {
            if (outBoundary(i, j)) {
                return 0;
            }

            if (dp[i][j] != -1) {
                return dp[i][j];
            }

            int ans = 0;
            for (int m = 0, nx, ny; m < 4; m++) {
                nx = i + MOVE[m];
                ny = j + MOVE[m + 1];
                if (!outBoundary(nx, ny) && arr[nx][ny] > arr[i][j]) {
                    ans = Math.max(ans, f2(nx, ny, dp));
                }
            }
            dp[i][j] = 1 + ans;
            return dp[i][j];
        }


        public static boolean outBoundary(int i, int j) {
            return i < 0 || i >= n || j < 0 || j >= m;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}