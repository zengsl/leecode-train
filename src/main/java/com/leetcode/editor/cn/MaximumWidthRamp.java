//A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <
//= nums[j]. The width of such a ramp is j - i. 
//
// Given an integer array nums, return the maximum width of a ramp in nums. If 
//there is no ramp in nums, return 0. 
//
// 
// Example 1: 
//
// 
//Input: nums = [6,0,8,2,1,5]
//Output: 4
//Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 
//0 and nums[5] = 5.
// 
//
// Example 2: 
//
// 
//Input: nums = [9,8,1,0,1,9,4,0,4,1]
//Output: 7
//Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 
//1 and nums[9] = 1.
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 5 * 10⁴ 
// 0 <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 栈 数组 双指针 单调栈 👍 326 👎 0


package com.leetcode.editor.cn;

/**
 * [962]Maximum Width Ramp
 */
public class MaximumWidthRamp {
    public static void main(String[] args) {
        Solution solution = new MaximumWidthRamp().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        static int MAX = 50002;
        static int[] STACK = new int[MAX];
        static int sl;

        // left < right
        public int maxWidthRamp(int[] nums) {
            sl = 0;
            int ans = 0;
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                // 等于的情况也直接忽略，只有后面的数据更小才压栈
                if (sl == 0 || nums[STACK[sl - 1]] > nums[i]) {
                    STACK[sl++] = i;
                }
            }

            for (int i = length - 1; i >= 0; i--) {
                while (sl > 0 && nums[STACK[sl - 1]] <= nums[i]) {
                    int curr = STACK[--sl];
                    ans = Math.max(ans, i - curr);
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}