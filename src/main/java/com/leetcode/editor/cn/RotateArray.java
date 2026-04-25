//Given an integer array nums, rotate the array to the right by k steps, where 
//k is non-negative. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4,5,6,7], k = 3
//Output: [5,6,7,1,2,3,4]
//Explanation:
//rotate 1 steps to the right: [7,1,2,3,4,5,6]
//rotate 2 steps to the right: [6,7,1,2,3,4,5]
//rotate 3 steps to the right: [5,6,7,1,2,3,4]
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,-100,3,99], k = 2
//Output: [3,99,-1,-100]
//Explanation: 
//rotate 1 steps to the right: [99,-1,-100,3]
//rotate 2 steps to the right: [3,99,-1,-100]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
// Follow up: 
//
// 
// Try to come up with as many solutions as you can. There are at least three 
//different ways to solve this problem. 
// Could you do it in-place with O(1) extra space? 
// 
//
// Related Topics 数组 数学 双指针 👍 2555 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [189]Rotate Array
 *
 */
public class RotateArray {
    public static void main(String[] args) {
        Solution solution = new RotateArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public void rotate(int[] nums, int k) {
            int len = nums.length;
            k = k % len;
            /*if (k == 0 || len == 1) {
                return;
            }*/

            reverse(nums, 0, len - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, len - 1);
        }

        public void reverse(int[] nums, int l, int r) {
            int tmp;
            while (l < r) {
                tmp = nums[l];
                nums[l++] = nums[r];
                nums[r--] = tmp;
            }
        }

        // 超时
        /*public void rotate(int[] nums, int k) {
            int len = nums.length;
            k = k % len;
            if (k == 0 || len == 1) {
                return;
            }

            int tmp;
            while (k-- > 0) {
                tmp = nums[0];
                for (int i = len - 1; i > 0; i--) {
                    nums[(i + 1) % len] = nums[i];
                }
                nums[1] = tmp;
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}