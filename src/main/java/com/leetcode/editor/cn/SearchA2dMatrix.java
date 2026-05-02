//You are given an m x n integer matrix matrix with the following two 
//properties: 
//
// 
// Each row is sorted in non-decreasing order. 
// The first integer of each row is greater than the last integer of the 
//previous row. 
// 
//
// Given an integer target, return true if target is in matrix or false 
//otherwise. 
//
// You must write a solution in O(log(m * n)) time complexity. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 矩阵 👍 1154 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [74]Search a 2D Matrix
 *
 */
public class SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new SearchA2dMatrix().new Solution();
        System.out.println(solution.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println(solution.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean searchMatrix1(int[][] matrix, int target) {
            int n = matrix.length, m = matrix[0].length;
            int row = 0, col = m - 1;
            while (row < n && col >= 0) {
                if (matrix[row][col] > target) {
                    col--;
                } else if (matrix[row][col] < target) {
                    row++;
                } else {
                    return true;
                }
            }

            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            int n = matrix.length, m = matrix[0].length;
            int left = 0, right = m * n - 1, mid = 0;
            int i, j;
            while (left <= right) {
                mid = left + ((right - left) >> 1);
                i = mid / m;
                j = mid % m;
                if (matrix[i][j] > target) {
                    right = mid - 1;
                } else if (matrix[i][j] < target) {
                    left = mid + 1;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}