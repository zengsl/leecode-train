//Given a rows x cols binary matrix filled with 0's and 1's, find the largest 
//rectangle containing only 1's and return its area. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 6
//Explanation: The maximal rectangle is shown in the above picture.
// 
//
// Example 2: 
//
// 
//Input: matrix = [["0"]]
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: matrix = [["1"]]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// rows == matrix.length 
// cols == matrix[i].length 
// 1 <= row, cols <= 200 
// matrix[i][j] is '0' or '1'. 
// 
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1765 👎 0


package com.leetcode.editor.cn;

/**
 * [85]Maximal Rectangle
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new MaximalRectangle().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int ans = 0;
            int length = matrix[0].length;
            int[] arr = new int[length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < length; j++) {
                    arr[j] = matrix[i][j] == '0' ? 0 : arr[j] + 1;
                }
                ans = Math.max(ans, largestRectangleArea(arr));
            }
            return ans;
        }


        private static int MAX = 100001;
        private static int[] STACK = new int[MAX];
        public static int sl;

        public int largestRectangleArea(int[] heights) {
            sl = 0;
            int ans = 0;
            // 0 1 [2 3] 4 5
            // 2 1 [5 6] 2 3
            // 3 2 4  5  1
            int length = heights.length;
            for (int i = 0; i < length; i++) {
                while (sl > 0 && heights[STACK[sl - 1]] > heights[i]) {
                    int curr = STACK[--sl];
                    int left = sl > 0 ? STACK[sl - 1] : -1;
                    ans = Math.max(ans, (i - left - 1) * heights[curr]);
                }
                STACK[sl++] = i;
            }

            while (sl > 0) {
                int curr = STACK[--sl];
                int left = sl > 0 ? STACK[sl - 1] : -1;
                ans = Math.max(ans, (length - left - 1) * heights[curr]);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}