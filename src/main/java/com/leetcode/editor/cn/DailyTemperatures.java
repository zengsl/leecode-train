//Given an array of integers temperatures represents the daily temperatures, 
//return an array answer such that answer[i] is the number of days you have to wait 
//after the iᵗʰ day to get a warmer temperature. If there is no future day for 
//which this is possible, keep answer[i] == 0 instead. 
//
// 
// Example 1: 
// Input: temperatures = [73,74,75,71,69,72,76,73]
//Output: [1,1,4,2,1,1,0,0]
// 
// Example 2: 
// Input: temperatures = [30,40,50,60]
//Output: [1,1,1,0]
// 
// Example 3: 
// Input: temperatures = [30,60,90]
//Output: [1,1,0]
// 
// 
// Constraints: 
//
// 
// 1 <= temperatures.length <= 10⁵ 
// 30 <= temperatures[i] <= 100 
// 
//
// Related Topics 栈 数组 单调栈 👍 2052 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [739]Daily Temperatures
 *
 * @author zengsl
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        Solution solution = new DailyTemperatures().new Solution();
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private static final int[] STACK = new int[100001];
        private static int sl;

        public int[] dailyTemperatures(int[] temperatures) {
            // reset
            sl = 0;

            // 求下一个较大的值
            int n = temperatures.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                if (sl == 0 || temperatures[i] <= temperatures[STACK[sl - 1]]) {
                    STACK[sl++] = i;
                    continue;
                }

                while (sl > 0 && temperatures[i] > temperatures[STACK[sl - 1]]) {
                    --sl;
                    ans[STACK[sl]] = i - STACK[sl];
                }
                STACK[sl++] = i;
            }

            while (sl > 0) {
                ans[STACK[--sl]] = 0;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}