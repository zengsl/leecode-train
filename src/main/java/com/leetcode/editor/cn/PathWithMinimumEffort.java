//You are a hiker preparing for an upcoming hike. You are given heights, a 2D 
//array of size rows x columns, where heights[row][col] represents the height of 
//cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to 
//travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can 
//move up, down, left, or right, and you wish to find a route that requires the 
//minimum effort. 
//
// A route's effort is the maximum absolute difference in heights between two 
//consecutive cells of the route. 
//
// Return the minimum effort required to travel from the top-left cell to the 
//bottom-right cell. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//Output: 2
//Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 
//in consecutive cells.
//This is better than the route of [1,2,2,2,5], where the maximum absolute 
//difference is 3.
// 
//
// Example 2: 
//
// 
//
// 
//Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//Output: 1
//Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 
//in consecutive cells, which is better than route [1,3,5,3,5].
// 
//
// Example 3: 
// 
// 
//Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//
//Output: 0
//Explanation: This route does not require any effort.
// 
//
// 
// Constraints: 
//
// 
// rows == heights.length 
// columns == heights[i].length 
// 1 <= rows, columns <= 100 
// 1 <= heights[i][j] <= 10⁶ 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 566 👎 0


package com.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [1631]Path With Minimum Effort
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        Solution solution = new PathWithMinimumEffort().new Solution();
        System.out.println(solution.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int[] MOVE = new int[]{-1, 0, 1, 0, -1};

        public int minimumEffortPath(int[][] heights) {
            int n = heights.length, m = heights[0].length;
            int[][] costs = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }
            boolean[][] visited = new boolean[n][m];
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
            heap.add(new int[]{0, 0, 0});
            costs[0][0] = 0;
            while (!heap.isEmpty()) {
                int[] curr = heap.poll();
                int x = curr[0];
                int y = curr[1];
                int c = curr[2];
                if (visited[x][y]) {
                    continue;
                }
                if (x == n - 1 && y == m - 1) {
                    return curr[2];
                }
                visited[x][y] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = x + MOVE[i];
                    int ny = y + MOVE[i + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        int max = Math.max(c, Math.abs(heights[nx][ny] - heights[x][y]));
                        if (max < costs[nx][ny]) {
                            costs[nx][ny] = max;
                            heap.add(new int[]{nx, ny, max});
                        }
                    }
                }
            }
            return costs[n - 1][m - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}