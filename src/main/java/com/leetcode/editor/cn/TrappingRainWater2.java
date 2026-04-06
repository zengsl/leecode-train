//Given n non-negative integers representing an elevation map where the width 
//of each bar is 1, compute how much water it can trap after raining. 
//
// 
// Example 1: 
// 
// 
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [
//0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) 
//are being trapped.
// 
//
// Example 2: 
//
// 
//Input: height = [4,2,0,3,2,5]
//Output: 9
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 5918 👎 0


package com.leetcode.editor.cn;

/**
 * [42]Trapping Rain Water
 */
public class TrappingRainWater2 {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater2().new Solution();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(solution.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*public int trap(int[] height) {
            int len = height.length;
            int[] rightMax = new int[len];
            rightMax[len - 1] = Integer.MIN_VALUE;
            for (int i = len - 2; i >= 0; i--) {
                rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
            }

            int ans = 0;
            int leftMax = Integer.MIN_VALUE, curH = 0;
            for (int i = 0; i < len; i++) {
                curH = Math.min(leftMax, rightMax[i]);
                ans += (curH > height[i] ? curH - height[i] : 0);
                if (height[i] > leftMax) {
                    leftMax = height[i];
                }
            }
            return ans;
        }*/

        public int trap(int[] height) {
            int ans = 0,size = height.length;
            int l = 1, r = size - 2, leftMax = height[0], rightMax = height[size - 1], max = 0;
            while (l <= r) {
                if (leftMax <= rightMax) {
                    ans += Math.max(0, leftMax - height[l]);
                    leftMax = Math.max(leftMax, height[l++]);
                } else {
                    ans += Math.max(0, rightMax - height[r]);
                    rightMax = Math.max(rightMax, height[r--]);
                }
            }
            return ans;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}