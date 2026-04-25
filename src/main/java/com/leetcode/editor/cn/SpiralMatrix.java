//Given an m x n matrix, return all elements of the matrix in spiral order. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,3,6,9,8,7,4,5]
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 2121 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [54]Spiral Matrix
 *
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new SpiralMatrix().new Solution();
       /* System.out.println(solution.spiralOrder(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        }));

        System.out.println(solution.spiralOrder(new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        }));*/

        System.out.println(solution.spiralOrder(new int[][]{
                {3}, {2}
        }));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            // 右0; 下1; 左2; 上3; 一共四个方向
            int direct = 0, n = matrix.length, m = matrix[0].length, i = 0, j = 0, cnt, total = n * m;
            int[][] history = new int[n][m];
            List<Integer> ans = new ArrayList<>();
            ans.add(matrix[0][0]);
            history[0][0] = 1;
            cnt = 1;
            while (cnt < total) {
                switch (direct) {
                    case 0: {
                        // 可以往右走
                        while (j + 1 < m && history[i][j + 1] == 0) {
                            history[i][++j] = 1;
                            ans.add(matrix[i][j]);
                            cnt++;
                        }
                        direct = 1;
                        break;
                    }
                    case 1: {
                        while (i + 1 < n && history[i + 1][j] == 0) {
                            history[++i][j] = 1;
                            ans.add(matrix[i][j]);
                            cnt++;
                        }
                        direct = 2;
                        break;
                    }
                    case 2: {
                        while (j - 1 >= 0 && history[i][j - 1] == 0) {
                            history[i][--j] = 1;
                            ans.add(matrix[i][j]);
                            cnt++;
                        }
                        direct = 3;
                        break;
                    }
                    case 3: {
                        while (i - 1 >= 0 && history[i - 1][j] == 0) {
                            history[--i][j] = 1;
                            ans.add(matrix[i][j]);
                            cnt++;
                        }
                        direct = 0;
                        break;
                    }
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}