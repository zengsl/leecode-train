//Given an m x n 2D binary grid grid which represents a map of '1's (land) and 
//'0's (water), return the number of islands. 
//
// An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all 
//surrounded by water. 
//
// 
// Example 1: 
//
// 
//Input: grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] is '0' or '1'. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 2860 👎 0


package com.leetcode.editor.cn;

/**
 * [200]Number of Islands
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        System.out.println(solution.numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        }));
    }

    class Solution2 {

        public static int N, M;

        public static int numIslands(char[][] grid) {
            N = grid.length;
            M = grid[0].length;
            int island = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == '1') {
                        island++;
                        dfs(grid, i, j);
                    }
                }
            }
            return island;
        }

        public static void dfs(char[][] grid, int i, int j) {
            if (i < 0 || i >= N || j < 0 || j > M || grid[i][j] != '1') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 90001;
        public static int[] father = new int[MAX];
        public static int m, n, sets;

        public int numIslands(char[][] grid) {
            build(grid);
            for (int i = 0; i < m; i++) {
                for (int j = 0, top, left, curr; j < n; j++) {
                    if (grid[i][j] == '0') {
                        continue;
                    }

                    curr = index(i, j);
                    if (i > 0 && grid[i - 1][j] == '1') {
                        top = index(i - 1, j);
                        union(curr, top);
                    }

                    if (j > 0 && grid[i][j - 1] == '1') {
                        left = index(i, j - 1);
                        union(curr, left);
                    }
                }
            }
            return sets;
        }


        public static int find(int x) {
            if (x != father[x]) {
                x = find(father[x]);
            }
            return x;
        }

        public static void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                father[fa] = fb;
                sets--;
            }
        }

        public static int index(int i, int j) {
            return i * n + j;
        }

        public static void build(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        sets++;
                        int curr = index(i, j);
                        father[curr] = curr;
                    }
                }
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}