//You are given an m x n grid where each cell can have one of three values: 
//
// 
// 0 representing an empty cell, 
// 1 representing a fresh orange, or 
// 2 representing a rotten orange. 
// 
//
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
//orange becomes rotten. 
//
// Return the minimum number of minutes that must elapse until no cell has a 
//fresh orange. If this is impossible, return -1. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation: The orange in the bottom left corner (row 2, column 0) is never 
//rotten, because rotting only happens 4-directionally.
// 
//
// Example 3: 
//
// 
//Input: grid = [[0,2]]
//Output: 0
//Explanation: Since there are already no fresh oranges at minute 0, the answer 
//is just 0.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10 
// grid[i][j] is 0, 1, or 2. 
// 
//
// Related Topics 广度优先搜索 数组 矩阵 👍 1155 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [994]Rotting Oranges
 *
 */
public class RottingOranges {
    public static void main(String[] args) {
        Solution solution = new RottingOranges().new Solution();
//        System.out.println(solution.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(solution.orangesRotting(new int[][]{{1, 2}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int orangesRotting(int[][] grid) {
            int rottenCount = 0, freshCount = 0, n = grid.length, m = grid[0].length, mins = 0;
            int[][] queue = new int[n * m][2];
            int head = 0, tail = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        freshCount++;
                    } else if (grid[i][j] == 2) {
                        rottenCount++;
                        queue[tail++] = new int[]{i, j};
                    }
                }
            }
            int total = freshCount + rottenCount;
            if (freshCount == 0) {
                return 0;
            }
            if (rottenCount == 0) {
                return -1;
            }

            while (head < tail) {
                int size = tail - head;
                if (size > 0) {
                    mins++;
                }
                while (size-- > 0) {
                    int[] top = queue[head++];
                    int i = top[0], j = top[1];
                    if (i + 1 < n && grid[i + 1][j] == 1) {
                        rottenCount++;
                        grid[i + 1][j] = 2;
                        queue[tail++] = new int[]{i + 1, j};
                    }

                    if (j + 1 < m && grid[i][j + 1] == 1) {
                        rottenCount++;
                        grid[i][j + 1] = 2;
                        queue[tail++] = new int[]{i, j + 1};
                    }

                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        rottenCount++;
                        grid[i - 1][j] = 2;
                        queue[tail++] = new int[]{i - 1, j};
                    }

                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        rottenCount++;
                        grid[i][j - 1] = 2;
                        queue[tail++] = new int[]{i, j - 1};
                    }
                }
            }
            return total == rottenCount ? (mins - 1) : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}