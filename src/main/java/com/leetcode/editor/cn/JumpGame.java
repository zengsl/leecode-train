//You are given an integer array nums. You are initially positioned at the 
//array's first index, and each element in the array represents your maximum jump 
//length at that position. 
//
// Return true if you can reach the last index, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum 
//jump length is 0, which makes it impossible to reach the last index.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 动态规划 👍 3256 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [55]Jump Game
 *
 */
public class JumpGame {
    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(solution.canJump(new int[]{2, 0}));
        System.out.println(solution.canJump(new int[]{2, 5, 0, 0}));
        System.out.println(solution.canJump(new int[]{0, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int curr = 0;
            for (int i = 0; i < n - 1; i++) {
                if (i <= curr) {
                    curr = Math.max(curr, i + nums[i]);
                    if (curr >= n - 1) {
                        return true;
                    }
                }
            }
            return curr >= n - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}