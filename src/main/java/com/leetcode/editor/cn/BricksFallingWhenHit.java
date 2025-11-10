//You are given an m x n binary grid, where each 1 represents a brick and 0 
//represents an empty space. A brick is stable if: 
//
// 
// It is directly connected to the top of the grid, or 
// At least one other brick in its four adjacent cells is stable. 
// 
//
// You are also given an array hits, which is a sequence of erasures we want to 
//apply. Each time we want to erase the brick at the location hits[i] = (rowi, 
//coli). The brick on that location (if it exists) will disappear. Some other bricks 
//may no longer be stable because of that erasure and will fall. Once a brick 
//falls, it is immediately erased from the grid (i.e., it does not land on other 
//stable bricks). 
//
// Return an array result, where each result[i] is the number of bricks that 
//will fall after the iᵗʰ erasure is applied. 
//
// Note that an erasure may refer to a location with no brick, and if it does, 
//no bricks drop. 
//
// 
// Example 1: 
//
// 
//Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
//Output: [2]
//Explanation: Starting with the grid:
//[[1,0,0,0],
// [1,1,1,0]]
//We erase the underlined brick at (1,0), resulting in the grid:
//[[1,0,0,0],
// [0,1,1,0]]
//The two underlined bricks are no longer stable as they are no longer 
//connected to the top nor adjacent to another stable brick, so they will fall. The 
//resulting grid is:
//[[1,0,0,0],
// [0,0,0,0]]
//Hence the result is [2].
// 
//
// Example 2: 
//
// 
//Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
//Output: [0,0]
//Explanation: Starting with the grid:
//[[1,0,0,0],
// [1,1,0,0]]
//We erase the underlined brick at (1,1), resulting in the grid:
//[[1,0,0,0],
// [1,0,0,0]]
//All remaining bricks are still stable, so no bricks fall. The grid remains 
//the same:
//[[1,0,0,0],
// [1,0,0,0]]
//Next, we erase the underlined brick at (1,0), resulting in the grid:
//[[1,0,0,0],
// [0,0,0,0]]
//Once again, all remaining bricks are still stable, so no bricks fall.
//Hence the result is [0,0].
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// grid[i][j] is 0 or 1. 
// 1 <= hits.length <= 4 * 10⁴ 
// hits[i].length == 2 
// 0 <= xi <= m - 1 
// 0 <= yi <= n - 1 
// All (xi, yi) are unique. 
// 
//
// Related Topics 并查集 数组 矩阵 👍 286 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [803]Bricks Falling When Hit
 */
public class BricksFallingWhenHit {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(Solution.hitBricks(new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}}, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(Solution.hitBricks(new int[][]{{1, 0, 1}, {0, 0, 1}}, new int[][]{{1, 0}, {0, 0}})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {

        public final static int TMP = 2;
        public static int[][] arr;
        public static int n, m;

        public static int[] hitBricks(int[][] grid, int[][] hits) {
            arr = grid;
            n = grid.length;
            m = grid[0].length;
            int hitSize = hits.length;
            int[] ans = new int[hitSize];
            // tag hits
            for (int[] hit : hits) {
                grid[hit[0]][hit[1]]--;
            }

            // tag bricks which connected roof
            for (int i = 0; i < m; i++) {
                dfs(0, i);
            }

            // time back
            for (int h = hitSize - 1, i, j; h >= 0; h--) {
                i = hits[h][0];
                j = hits[h][1];
                ++grid[i][j];
                if (worth(i, j)) {
                    ans[h] = dfs(i, j) - 1;
                }
            }

            return ans;
        }

        public static boolean worth(int i, int j) {
            boolean top = i - 1 >= 0 && arr[i - 1][j] == TMP;
            boolean right = j + 1 < m && arr[i][j + 1] == TMP;
            boolean bottom = i + 1 < n && arr[i + 1][j] == TMP;
            boolean left = j - 1 >= 0 && arr[i][j - 1] == TMP;
            return arr[i][j] == 1 && (i == 0 || top || right || bottom || left);
        }

        public static int dfs(int i, int j) {
            if (i < 0 || i >= n || j < 0 || j >= m || arr[i][j] != 1) {
                return 0;
            }
            arr[i][j] = TMP;
            return 1 + dfs(i + 1, j)
                    + dfs(i - 1, j)
                    + dfs(i, j + 1)
                    + dfs(i, j - 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}