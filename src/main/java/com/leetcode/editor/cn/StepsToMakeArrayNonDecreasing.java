//You are given a 0-indexed integer array nums. In one step, remove all 
//elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length. 
//
// Return the number of steps performed until nums becomes a non-decreasing 
//array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
//Output: 3
//Explanation: The following are the steps performed:
//- Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
//- Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
//- Step 3: [5,4,7,11,11] becomes [5,7,11,11]
//[5,7,11,11] is a non-decreasing array. Therefore, we return 3.
// 
//
// Example 2: 
//
// 
//Input: nums = [4,5,7,7,13]
//Output: 0
//Explanation: nums is already a non-decreasing array. Therefore, we return 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 
//
// Related Topics 栈 数组 链表 单调栈 👍 165 👎 0


package com.leetcode.editor.cn;

/**
 * [2289]Steps to Make Array Non-decreasing
 */
public class StepsToMakeArrayNonDecreasing {
    public static void main(String[] args) {
        Solution solution = new StepsToMakeArrayNonDecreasing().new Solution();
//        System.out.println(solution.totalSteps(new int[]{4, 5, 7, 7, 13}));
//        System.out.println(solution.totalSteps(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}));
        System.out.println(solution.totalSteps(new int[]{5, 11, 7, 8, 11}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        static int MAX = 100001;
        static int[][] STACK = new int[MAX][2];
        static int sl;

        // 输入：nums = [5,3,4,4,7,3,6,11,8,5,11]
        // 输出：3
        //解释：执行下述几个步骤：
        //- 步骤 1 ：[5,3,4,4,7,3,6,11,8,5,11] 变为 [5,4,4,7,6,11,11]
        //- 步骤 2 ：[5,4,4,7,6,11,11] 变为 [5,4,7,11,11]
        //- 步骤 3 ：[5,4,7,11,11] 变为 [5,7,11,11]
        //[5,7,11,11] 是一个非递减数组，因此，返回 3 。
        public int totalSteps(int[] nums) {
            int length = nums.length;
            sl = 0;
            STACK[0][0] = nums[length - 1];
            STACK[0][1] = 0;
            sl++;

            // 5,3,4,4,7,3,6,11,8,5,11
            int ans = 0;
            int step;
            for (int i = length - 2; i >= 0; i--) {
                step = 0;
                // 8 6 7
                // 大于栈顶元素则开始结算
                while (sl > 0 && nums[i] > STACK[sl - 1][0]) {
                    step = Math.max(step + 1, STACK[--sl][1]);
                }
                STACK[sl][0] = nums[i];
                STACK[sl][1] = step;
                sl++;
                ans = Math.max(ans, step);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}