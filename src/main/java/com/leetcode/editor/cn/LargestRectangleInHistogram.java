//Given an array of integers heights representing the histogram's bar height 
//where the width of each bar is 1, return the area of the largest rectangle in the 
//histogram. 
//
// 
// Example 1: 
// 
// 
//Input: heights = [2,1,5,6,2,3]
//Output: 10
//Explanation: The above is a histogram where width of each bar is 1.
//The largest rectangle is shown in the red area, which has an area = 10 units.
// 
//
// Example 2: 
// 
// 
//Input: heights = [2,4]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= heights.length <= 10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
//
// Related Topics 栈 数组 单调栈 👍 3007 👎 0


package com.leetcode.editor.cn;

/**
 * [84]Largest Rectangle in Histogram
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram().new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{2, 0, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

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