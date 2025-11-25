//Given an m x n grid. Each cell of the grid has a sign pointing to the next 
//cell you should visit if you are currently in this cell. The sign of grid[i][j] 
//can be: 
//
// 
// 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i]
//[j + 1]) 
// 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][
//j - 1]) 
// 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j]
//) 
// 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j]
//) 
// 
//
// Notice that there could be some signs on the cells of the grid that point 
//outside the grid. 
//
// You will initially start at the upper left cell (0, 0). A valid path in the 
//grid is a path that starts from the upper left cell (0, 0) and ends at the 
//bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does 
//not have to be the shortest. 
//
// You can modify the sign on a cell with cost = 1. You can modify the sign on 
//a cell one time only. 
//
// Return the minimum cost to make the grid have at least one valid path. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
//Output: 3
//Explanation: You will start at point (0, 0).
//The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) 
//change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) 
//change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3
//) change the arrow to down with cost = 1 --> (3, 3)
//The total cost = 3.
// 
//
// Example 2: 
// 
// 
//Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
//Output: 0
//Explanation: You can follow the path from (0, 0) to (2, 2).
// 
//
// Example 3: 
// 
// 
//Input: grid = [[1,2],[4,3]]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 100 
// 1 <= grid[i][j] <= 4 
// 
//
// Related Topics 广度优先搜索 图 数组 矩阵 最短路 堆（优先队列） 👍 166 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayDeque;

/**
 * [1368]Minimum Cost to Make at Least One Valid Path in a Grid
 */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {
    public static void main(String[] args) {
        Solution solution = new MinimumCostToMakeAtLeastOneValidPathInAGrid().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int[][] MOVE = new int[][]{{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] cost = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }

            ArrayDeque<int[]> deque = new ArrayDeque<>();
            deque.addFirst(new int[]{0, 0});
            cost[0][0] = 0;
            while (!deque.isEmpty()) {
                int[] curr = deque.pollFirst();
                int x = curr[0];
                int y = curr[1];
                if (x == n - 1 && y == m - 1) {
                    return cost[x][y];
                }

                for (int i = 1; i <= 4; i++) {
                    int nx = x + MOVE[i][0];
                    int ny = y + MOVE[i][1];
                    int w = grid[x][y] == i ? 0 : 1;
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && cost[x][y] + w < cost[nx][ny]) {
                        cost[nx][ny] = cost[x][y] + w;
                        if (w == 1) {
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