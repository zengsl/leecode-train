//You are given an m x n grid grid where: 
//
// 
// '.' is an empty cell. 
// '#' is a wall. 
// '@' is the starting point. 
// Lowercase letters represent keys. 
// Uppercase letters represent locks. 
// 
//
// You start at the starting point and one move consists of walking one space 
//in one of the four cardinal directions. You cannot walk outside the grid, or walk 
//into a wall. 
//
// If you walk over a key, you can pick it up and you cannot walk over a lock 
//unless you have its corresponding key. 
//
// For some 1 <= k <= 6, there is exactly one lowercase and one uppercase 
//letter of the first k letters of the English alphabet in the grid. This means that 
//there is exactly one key for each lock, and one lock for each key; and also that 
//the letters used to represent the keys and locks were chosen in the same order as 
//the English alphabet. 
//
// Return the lowest number of moves to acquire all keys. If it is impossible, 
//return -1. 
//
// 
// Example 1: 
// 
// 
//Input: grid = ["@.a..","###.#","b.A.B"]
//Output: 8
//Explanation: Note that the goal is to obtain all the keys not to open all the 
//locks.
// 
//
// Example 2: 
// 
// 
//Input: grid = ["@..aA","..B#.","....b"]
//Output: 6
// 
//
// Example 3: 
// 
// 
//Input: grid = ["@Aa"]
//Output: -1
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 30 
// grid[i][j] is either an English letter, '.', '#', or '@'. 
// There is exactly one '@' in the grid. 
// The number of keys in the grid is in the range [1, 6]. 
// Each key in the grid is unique. 
// Each key in the grid has a matching lock. 
// 
//
// Related Topics 位运算 广度优先搜索 数组 矩阵 👍 322 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [864]Shortest Path to Get All Keys
 */
public class ShortestPathToGetAllKeys {
    public static void main(String[] args) {
        Solution solution = new ShortestPathToGetAllKeys().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MAX_N = 31;
        public static final int MAX_M = 31;
        public static final int KEY_MAX = 6;
        public static final int[] MOVE = new int[]{-1, 0, 1, 0, -1};

        // BFS
        public static final int[][] DEQUE = new int[MAX_N * MAX_M][3];

        // DJ
        public int shortestPathAllKeys(String[] grid) {
            int n = grid.length;
            int m = grid[0].length();
            char[][] chars = new char[n][m];
            int startX = 0, startY = 0;
            int allKey = 0;
            for (int i = 0; i < n; i++) {
                chars[i] = grid[i].toCharArray();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (chars[i][j] == '@') {
                        startX = i;
                        startY = j;
                    } else if (chars[i][j] >= 'a' && chars[i][j] <= 'z') {
                        allKey |= 1 << (chars[i][j] - 'a');
                    }
                }
            }

            boolean[][][] visited = new boolean[n][m][1 << KEY_MAX];
            int[][][] steps = new int[n][m][1 << KEY_MAX];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Arrays.fill(steps[i][j], Integer.MAX_VALUE);
                }
            }
            // x, y, state, time
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[3]));
            heap.add(new int[]{startX, startY, 0, 0});
            while (!heap.isEmpty()) {
                int[] curr = heap.poll();
                int x = curr[0];
                int y = curr[1];
                int s = curr[2];
                int t = curr[3];
                if (visited[x][y][s]) {
                    continue;
                }
                visited[x][y][s] = true;
                if (s == allKey) {
                    return t;
                }
                for (int i = 0, nx, ny, ns; i < 4; i++) {
                    nx = x + MOVE[i];
                    ny = y + MOVE[i + 1];
                    // 未超边界 不是墙
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny][s] && chars[nx][ny] != '#') {
                        char nc = chars[nx][ny];
                        ns = s;
                        // if you can open the lock with the keys
                        if (nc >= 'A' && nc <= 'Z' && (s & (1 << nc - 'A')) == 0) {
                            continue;
                        } else if (nc >= 'a' && nc <= 'z') {
                            // get the key
                            ns |= (1 << (nc - 'a'));
                        }
                        if (steps[nx][ny][ns] > t + 1) {
                            steps[nx][ny][ns] = t + 1;
                            heap.add(new int[]{nx, ny, ns, t + 1});
                        }
                    }
                }
            }
            return -1;
        }


        // BFS
        public int shortestPathAllKeys2(String[] grid) {
            int n = grid.length;
            int m = grid[0].length();
            int[][] deque = new int[n * m * (1 << KEY_MAX)][3];
            int l = 0, r = 0;
            char[][] chars = new char[n][m];

            int allKey = 0;
            for (int i = 0; i < n; i++) {
                chars[i] = grid[i].toCharArray();
            }
            boolean[][][] visited = new boolean[n][m][1 << KEY_MAX];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (chars[i][j] == '@') {
                        deque[r][0] = i;
                        deque[r][1] = j;
                        deque[r++][2] = 0;
                        visited[i][j][0] = true;
                    } else if (chars[i][j] >= 'a' && chars[i][j] <= 'z') {
                        allKey |= 1 << (chars[i][j] - 'a');
                    }
                }
            }

            int level = 0;
            while (l < r) {
                level++;
                for (int z = 0, size = r - l; z < size; z++) {
                    int[] curr = deque[l++];
                    int x = curr[0];
                    int y = curr[1];
                    int s = curr[2];
                    for (int i = 0, nx, ny, ns; i < 4; i++) {
                        nx = x + MOVE[i];
                        ny = y + MOVE[i + 1];
                        // 未超边界 不是墙
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && chars[nx][ny] != '#') {
                            char nc = chars[nx][ny];
                            ns = s;
                            // if you can open the lock with the keys
                            if (nc >= 'A' && nc <= 'Z' && (s & (1 << nc - 'A')) == 0) {
                                continue;
                            } else if (nc >= 'a' && nc <= 'z') {
                                // get the key
                                ns |= (1 << (nc - 'a'));
                            }
                            if (ns == allKey) {
                                return level;
                            }

                            if (!visited[nx][ny][ns]) {
                                deque[r][0] = nx;
                                deque[r][1] = ny;
                                deque[r++][2] = ns;
                                visited[nx][ny][ns] = true;
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}