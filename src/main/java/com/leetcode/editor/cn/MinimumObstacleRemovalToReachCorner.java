//You are given a 0-indexed 2D integer array grid of size m x n. Each cell has 
//one of two values: 
//
// 
// 0 represents an empty cell, 
// 1 represents an obstacle that may be removed. 
// 
//
// You can move up, down, left, or right from and to an empty cell. 
//
// Return the minimum number of obstacles to remove so you can move from the 
//upper left corner (0, 0) to the lower right corner (m - 1, n - 1). 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
//Output: 2
//Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a 
//path from (0, 0) to (2, 2).
//It can be shown that we need to remove at least 2 obstacles, so we return 2.
//Note that there may be other ways to remove 2 obstacles to create a path.
// 
//
// Example 2: 
// 
// 
//Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
//Output: 0
//Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles,
// so we return 0.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10⁵ 
// 2 <= m * n <= 10⁵ 
// grid[i][j] is either 0 or 1. 
// grid[0][0] == grid[m - 1][n - 1] == 0 
// 
//
// Related Topics 广度优先搜索 图 数组 矩阵 最短路 堆（优先队列） 👍 70 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayDeque;

/**
 * [2290]Minimum Obstacle Removal to Reach Corner
 */
public class MinimumObstacleRemovalToReachCorner {
    public static void main(String[] args) {
        Solution solution = new MinimumObstacleRemovalToReachCorner().new Solution();
        System.out.println(solution.minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] MOVE = new int[]{-1, 0, 1, 0, -1};

        public int minimumObstacles(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] distance = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            ArrayDeque<int[]> deque = new ArrayDeque<>();
            deque.addFirst(new int[]{0, 0});
            distance[0][0] = 0;

            while (!deque.isEmpty()) {
                int[] curr = deque.pollFirst();
                int x = curr[0];
                int y = curr[1];
                if (x == n - 1 && y == m - 1) {
                    return distance[n - 1][m - 1];
                }

                for (int j = 0; j < 4; j++) {
                    int nx = x + MOVE[j];
                    int ny = y + MOVE[j + 1];
                    if (nx < n && nx >= 0 && ny < m && ny >= 0 && distance[x][y] + grid[nx][ny] < distance[nx][ny]) {
                        distance[nx][ny] = distance[x][y] + grid[nx][ny];
                        if (grid[nx][ny] == 1) {
                            deque.addLast(new int[]{nx, ny});
                        } else {
                            deque.addFirst(new int[]{nx, ny});
                        }
                    }
                }

            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}