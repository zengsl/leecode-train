//You are given an n x n 2D matrix representing an image, rotate the image by 90
// degrees (clockwise). 
//
// You have to rotate the image in-place, which means you have to modify the 
//input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [[7,4,1],[8,5,2],[9,6,3]]
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 
// Constraints: 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
//
// Related Topics 数组 数学 矩阵 👍 2244 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [48]Rotate Image
 *
 */
public class RotateImage {
    public static void main(String[] args) {
        Solution solution = new RotateImage().new Solution();
        solution.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        solution.rotate(new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public void rotate(int[][] matrix) {
            int n = matrix.length, m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0,tmp; j < i; j++) {
                    tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
            for (int i = 0; i < n; i++) {
                int left = 0, right = m - 1, tmp;
                while (left < right) {
                    tmp = matrix[i][left];
                    matrix[i][left++] = matrix[i][right];
                    matrix[i][right--] = tmp;
                }
            }
        }

        /*public void rotate(int[][] matrix) {
            int n = matrix.length, m = matrix[0].length;
            int[][] matrixTmp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrixTmp[j][n - 1 - i] = matrix[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                System.arraycopy(matrixTmp[i], 0, matrix[i], 0, m);
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}