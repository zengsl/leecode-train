//Given an m x n integer matrix matrix, if an element is 0, set its entire row 
//and column to 0's. 
//
// You must do it in place. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -2³¹ <= matrix[i][j] <= 2³¹ - 1 
// 
//
// 
// Follow up: 
//
// 
// A straightforward solution using O(mn) space is probably a bad idea. 
// A simple improvement uses O(m + n) space, but still not the best solution. 
// Could you devise a constant space solution? 
// 
//
// Related Topics 数组 哈希表 矩阵 👍 1366 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [73]Set Matrix Zeroes
 *
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new SetMatrixZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*public void setZeroes(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            // 200位，用整形太短了
            int changeN = 0, changeM = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == 0) {
                        changeN |= (1 << i);
                        changeM |= (1 << j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((changeN & (1 << i)) != 0 || (changeM & (1 << j)) != 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }*/


        public void setZeroes(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            int[] changeN = new int[n];
            int[] changeM = new int[m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == 0) {
                        changeN[i] = 1;
                        changeM[j] = 1;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (changeN[i] == 1 || changeM[j] == 1) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}