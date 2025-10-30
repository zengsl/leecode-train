//Given an m x n binary matrix mat, return the number of submatrices that have 
//all ones. 
//
// 
// Example 1: 
// 
// 
//Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
//Output: 13
//Explanation: 
//There are 6 rectangles of side 1x1.
//There are 2 rectangles of side 1x2.
//There are 3 rectangles of side 2x1.
//There is 1 rectangle of side 2x2. 
//There is 1 rectangle of side 3x1.
//Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
// 
//
// Example 2: 
// 
// 
//Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
//Output: 24
//Explanation: 
//There are 8 rectangles of side 1x1.
//There are 5 rectangles of side 1x2.
//There are 2 rectangles of side 1x3. 
//There are 4 rectangles of side 2x1.
//There are 2 rectangles of side 2x2. 
//There are 2 rectangles of side 3x1. 
//There is 1 rectangle of side 3x2. 
//Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
// 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 150 
// mat[i][j] is either 0 or 1. 
// 
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 267 👎 0


package com.leetcode.editor.cn;

/**
 * [1504]Count Submatrices With All Ones
 */
public class CountSubmatricesWithAllOnes {
    public static void main(String[] args) {
        Solution solution = new CountSubmatricesWithAllOnes().new Solution();
        System.out.println(solution.numSubmat(new int[][]{
                {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}
        }));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int[] STACK = new int[151];
        public static int sl;

        public int numSubmat(int[][] mat) {
            int ans = 0;
            int[] arr = new int[mat[0].length];
            for (int[] ints : mat) {
                for (int j = 0; j < mat[0].length; j++) {
                    arr[j] = ints[j] == 0 ? 0 : arr[j] + ints[j];
                }
                ans += numOfRectangle(arr);
            }
            return ans;
        }

        private int numOfRectangle(int[] arr) {
            sl = 0;
            int ans = 0;
            // 2 3 4 5 6 1 8 5
            // 0 1 2 3 4 5 6 7
            // 2 3 4 5 6 7 8 5
            // 8  6
            for (int i = 0; i < arr.length; i++) {
                while (sl > 0 && arr[STACK[sl - 1]] >= arr[i]) {
                    int curr = STACK[--sl];
                    if (arr[curr] > arr[i]) {
                        int left = sl > 0 ? STACK[sl - 1] : -1;
                        int length = i - left - 1;
                        int bottom = Math.max(left == -1 ? 0 : arr[left], arr[i]);
                        ans += (arr[curr] - bottom) * length * (length + 1) / 2;
                    }
                }
                STACK[sl++] = i;
            }

            while (sl > 0) {
                int curr = STACK[--sl];
                int left = sl > 0 ? STACK[sl - 1] : -1;
                int length = arr.length - left - 1;
                int bottom = left == -1 ? 0 : arr[left];
                ans += (arr[curr] - bottom) * length * (length + 1) / 2;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}