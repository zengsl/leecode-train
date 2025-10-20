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
public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int trap(int[] height) {
            int size = height.length;
            int lMax = height[0], rMax = height[size - 1], l = 1, r = size - 2;
            int ans = 0;
            while (l <= r) {
                if (lMax <= rMax) {
                    ans += Math.max(0, lMax - height[l]);
                    lMax = Math.max(lMax, height[l++]);
                } else{
                    ans += Math.max(0, rMax - height[r]);
                    rMax = Math.max(rMax, height[r--]);
                }
            }

            return ans;
        }

        /*public int trap(int[] height) {
            int size = height.length;
            int[] leftMax = new int[size];
            int[] rightMax = new int[size];
            int rain = 0;
            leftMax[0] = 0;
            for (int i = 1; i < size; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            }

            rightMax[size - 1] = 0;
            for (int i = size - 2 ; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
            }

            for (int i = 1; i < size - 1; i++) {
                rain += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
            }
            return rain;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}