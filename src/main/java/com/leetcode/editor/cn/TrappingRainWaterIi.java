//Given an m x n integer matrix heightMap representing the height of each unit 
//cell in a 2D elevation map, return the volume of water it can trap after raining.
// 
//
// 
// Example 1: 
// 
// 
//Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//Output: 4
//Explanation: After the rain, water is trapped between the blocks.
//We have two small ponds 1 and 3 units trapped.
//The total volume of water trapped is 4.
// 
//
// Example 2: 
// 
// 
//Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3
//]]
//Output: 10
// 
//
// 
// Constraints: 
//
// 
// m == heightMap.length 
// n == heightMap[i].length 
// 1 <= m, n <= 200 
// 0 <= heightMap[i][j] <= 2 * 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 矩阵 堆（优先队列） 👍 821 👎 0


package com.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [407]Trapping Rain Water II
 */
public class TrappingRainWaterIi {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWaterIi().new Solution();
        System.out.println(solution.trapRainWater(new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int[] MOVE = new int[]{-1, 0, 1, 0, -1};

        public int trapRainWater(int[][] heightMap) {
            int n = heightMap.length;
            int m = heightMap[0].length;
            boolean[][] visited = new boolean[n][m];
            PriorityQueue<int[]> deque = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
            // init border point
            /*for (int i = 0; i < m; i++) {
                visited[0][i] = true;
                visited[n - 1][i] = true;
                deque.add(new int[]{0, i, heightMap[0][i]});
                deque.add(new int[]{n - 1, i, heightMap[n - 1][i]});
            }

            for (int i = 1; i < n - 1; i++) {
                visited[i][0] = true;
                visited[i][m - 1] = true;
                deque.add(new int[]{i, 0, heightMap[i][0]});
                deque.add(new int[]{i, m - 1, heightMap[i][m - 1]});
            }*/
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        // 边界
                        deque.add(new int[] { i, j, heightMap[i][j] });
                        visited[i][j] = true;
                    } else {
                        visited[i][j] = false;
                    }
                }
            }

            int ans = 0;
            while (!deque.isEmpty()) {
                int[] curr = deque.poll();
                int x = curr[0];
                int y = curr[1];
                int h = curr[2];
                ans += h - heightMap[x][y];
                for (int i = 0; i < 4; i++) {
                    int nx = x + MOVE[i];
                    int ny = y + MOVE[i + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        deque.add(new int[]{nx, ny, Math.max(h, heightMap[nx][ny])});
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}