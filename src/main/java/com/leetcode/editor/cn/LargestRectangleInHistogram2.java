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
public class LargestRectangleInHistogram2 {
    public static void main(String[] args) {
        Solution solution = new LargestRectangleInHistogram2().new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{2, 0, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        static int[] stack = new int[100001];
        static int r;

        public int largestRectangleArea(int[] heights) {
            r = 0;
            int ans = 0, curr, left, len = heights.length;
            for (int i = 0; i < len; i++) {
                while (r > 0 && heights[i] <= heights[stack[r - 1]]) {
                    curr = stack[--r];
                    left = r == 0 ? -1 : stack[r - 1];
                    ans = Math.max(ans, (i - left - 1) * heights[curr]);
                }
                stack[r++] = i;
            }

            while (r > 0) {
                curr = stack[--r];
                left = r == 0 ? -1 : stack[r - 1];
                ans = Math.max(ans, (len - left - 1) * heights[curr]);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}