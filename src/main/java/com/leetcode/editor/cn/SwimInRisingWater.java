//You are given an n x n integer matrix grid where each value grid[i][j] 
//represents the elevation at that point (i, j). 
//
// It starts raining, and water gradually rises over time. At time t, the water 
//level is t, meaning any cell with elevation less than equal to t is submerged 
//or reachable. 
//
// You can swim from a square to another 4-directionally adjacent square if and 
//only if the elevation of both squares individually are at most t. You can swim 
//infinite distances in zero time. Of course, you must stay within the boundaries 
//of the grid during your swim. 
//
// Return the minimum time until you can reach the bottom right square (n - 1, 
//n - 1) if you start at the top left square (0, 0). 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[0,2],[1,3]]
//Output: 3
//Explanation:
//At time 0, you are in grid location (0, 0).
//You cannot go anywhere else because 4-directionally adjacent neighbors have a 
//higher elevation than t = 0.
//You cannot reach point (1, 1) until time 3.
//When the depth of water is 3, we can swim anywhere inside the grid.
// 
//
// Example 2: 
// 
// 
//Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[
//10,9,8,7,6]]
//Output: 16
//Explanation: The final route is shown.
//We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
// 
//
// 
// Constraints: 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 50 
// 0 <= grid[i][j] < n² 
// Each value grid[i][j] is unique. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 364 👎 0


package com.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [778]Swim in Rising Water
 */
public class SwimInRisingWater {

    public static void main(String[] args) {
        Solution solution = new SwimInRisingWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] MOVE = new int[]{-1, 0, 1, 0, -1};

        public int swimInWater(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            int[][] times = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    times[i][j] = Integer.MAX_VALUE;
                }
            }
            boolean[][] visited = new boolean[n][m];
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
            times[0][0] = grid[0][0];
            heap.add(new int[]{0, 0, grid[0][0]});
            while (!heap.isEmpty()) {
                int[] curr = heap.poll();
                int x = curr[0];
                int y = curr[1];
                int c = curr[2];
                if (visited[x][y]) {
                    continue;
                }
                if (x == n - 1 && y == m - 1) {
                    return c;
                }
                visited[x][y] = true;
                for (int i = 0, nx, ny, nc; i < 4; i++) {
                    nx = x + MOVE[i];
                    ny = y + MOVE[i + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        nc = Math.max(grid[nx][ny], c);
                        if (nc < times[nx][ny]) {
                            times[nx][ny] = nc;
                            heap.add(new int[]{nx, ny, nc});
                        }
                    }
                }
            }
            return times[n - 1][m - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}