//Given an n x n grid containing only values 0 and 1, where 0 represents water 
//and 1 represents land, find a water cell such that its distance to the nearest 
//land cell is maximized, and return the distance. If no land or water exists in 
//the grid, return -1. 
//
// The distance used in this problem is the Manhattan distance: the distance 
//between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
//Output: 2
//Explanation: The cell (1, 1) is as far as possible from all the land with 
//distance 2.
// 
//
// Example 2: 
// 
// 
//Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
//Output: 4
//Explanation: The cell (2, 2) is as far as possible from all the land with 
//distance 4.
// 
//
// 
// Constraints: 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] is 0 or 1 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 矩阵 👍 416 👎 0


package com.leetcode.editor.cn;

/**
 * [1162]As Far from Land as Possible
 */
public class AsFarFromLandAsPossible {
    public static void main(String[] args) {
        Solution solution = new AsFarFromLandAsPossible().new Solution();
        System.out.println(solution.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 100;
        public static final int[][] DEQUE = new int[MAX * MAX][2];
        public static int l, r, seas;
        public static boolean[][] visited = new boolean[MAX][MAX];
        public static int[] move = new int[]{0, 1, 0, -1, 0};

        public int maxDistance(int[][] grid) {
            l = r = seas = 0;
            int n = grid.length;
            int m = grid[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        DEQUE[r][0] = i;
                        DEQUE[r++][1] = j;
                        visited[i][j] = true;
                    } else {
                        seas++;
                        visited[i][j] = false;
                    }
                }
            }

            if (seas == 0 || seas == m * n) {
                return -1;
            }
            int distance = 0;
            // 1 2 3 4
            while (l < r) {
                int size = r - l;
                distance++;
                for (int offset = 0; offset < size; offset++) {
                    int i = DEQUE[l][0];
                    int j = DEQUE[l++][1];
                    for (int mv = 0, newI, newJ; mv < 4; mv++) {
                        newI = i + move[mv];
                        newJ = j + move[mv + 1];
                        boolean isNotOver = newI < n && newI >= 0 && newJ < m && newJ >= 0;
                        if (isNotOver && !visited[newI][newJ]) {
                            DEQUE[r][0] = newI;
                            DEQUE[r++][1] = newJ;
                            visited[newI][newJ] = true;
                        }
                    }
                }
            }
            return distance - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}