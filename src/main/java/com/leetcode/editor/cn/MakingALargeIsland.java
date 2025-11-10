//You are given an n x n binary matrix grid. You are allowed to change at most 
//one 0 to be 1. 
//
// Return the size of the largest island in grid after applying this operation. 
//
//
// An island is a 4-directionally connected group of 1s. 
//
// 
// Example 1: 
//
// 
//Input: grid = [[1,0],[0,1]]
//Output: 3
//Explanation: Change one 0 to 1 and connect two 1s, then we get an island with 
//area = 3.
// 
//
// Example 2: 
//
// 
//Input: grid = [[1,1],[1,0]]
//Output: 4
//Explanation: Change the 0 to 1 and make the island bigger, only one island 
//with area = 4. 
//
// Example 3: 
//
// 
//Input: grid = [[1,1],[1,1]]
//Output: 4
//Explanation: Can't change any 0 to 1, only one island with area = 4.
// 
//
// 
// Constraints: 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 500 
// grid[i][j] is either 0 or 1. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 472 👎 0


package com.leetcode.editor.cn;

/**
 * [827]Making A Large Island
 */
public class MakingALargeIsland {
    public static void main(String[] args) {
        Solution solution = new MakingALargeIsland().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MAX = 500;
        public static int[] COUNTS;
        public static int[][] arr;
        public static int n, m;

        public int largestIsland(int[][] grid) {
            int id = 2;
            arr = grid;
            n = arr.length;
            m = arr[0].length;

            // number
            int max = 0;
            for (int i = 0, count; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        count = dfs(i, j, id++);
                        max = Math.max(max, count);
                    }
                }
            }

            COUNTS = new int[id];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] > 1) {
                        ++COUNTS[grid[i][j]];
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0, will; j < m; j++) {
                    if (grid[i][j] == 0) {
                        will = sum(i, j);
                        max = Math.max(max, will);
                    }
                }
            }

            return max;
        }

        public static int sum(int i, int j) {
            // find id
            int top = i - 1 >= 0 ? arr[i - 1][j] : 0;
            int right = j + 1 < m ? arr[i][j + 1] : 0;
            int bottom = i + 1 < n ? arr[i + 1][j] : 0;
            int left = j - 1 >= 0 ? arr[i][j - 1] : 0;
            int count = 1 + COUNTS[top];
            if (right != top) {
                count += COUNTS[right];
            }

            if (bottom != top && bottom != right) {
                count += COUNTS[bottom];
            }

            if (left != top && left != right && left != bottom) {
                count += COUNTS[left];
            }

            return count;
        }


        public static int dfs(int i, int j, int id) {
            if (illegalIndex(i, j) || arr[i][j] != 1) {
                return 0;
            }

            arr[i][j] = id;
            return 1 + dfs(i + 1, j, id) + dfs(i - 1, j, id) + dfs(i, j + 1, id) + dfs(i, j - 1, id);
        }

        public static boolean illegalIndex(int i, int j) {
            return i < 0 || i >= n || j < 0 || j >= m;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}