//Write an efficient algorithm that searches for a value target in an m x n 
//integer matrix matrix. This matrix has the following properties: 
//
// 
// Integers in each row are sorted in ascending from left to right. 
// Integers in each column are sorted in ascending from top to bottom. 
// 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[1
//8,21,23,26,30]], target = 5
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[1
//8,21,23,26,30]], target = 20
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// All the integers in each row are sorted in ascending order. 
// All the integers in each column are sorted in ascending order. 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 分治 矩阵 👍 1820 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [240]Search a 2D Matrix II
 *
 */
public class SearchA2dMatrixIi {
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrixIi().new Solution();
//        System.out.println(solution.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
//        System.out.println(solution.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
//        System.out.println(solution.searchMatrix(new int[][]{{1, 4}, {2, 5}}, 2));
        System.out.println(solution.searchMatrix(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}}, 19));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean searchMatrix(int[][] matrix, int target) {
            int n = matrix.length, m = matrix[0].length;
            int row = 0, col = m - 1;
            while (row < n && col >= 0) {
                if (matrix[row][col] < target) {
                    row++;
                } else if (matrix[row][col] > target) {
                    col--;
                } else {
                    return true;
                }
            }
            return false;
        }

        /*public boolean searchMatrix(int[][] matrix, int target) {
            int n = matrix.length, m = matrix[0].length;
            return detect(matrix, target, 0, 0, n, m);
        }

        private boolean detect(int[][] matrix, int target, int i, int j, int n, int m) {
            if ((i >= n || j >= m) || matrix[i][j] > target) {
                return false;
            }

            if (matrix[i][j] == target) {
                return true;
            }
            boolean down = detect(matrix, target, i + 1, j, n, m);
            boolean right = detect(matrix, target, i, j + 1, n, m);
            return down || right;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}