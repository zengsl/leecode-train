//Given an integer array nums and an integer k, split nums into k non-empty 
//subarrays such that the largest sum of any subarray is minimized. 
//
// Return the minimized largest sum of the split. 
//
// A subarray is a contiguous part of the array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [7,2,5,10,8], k = 2
//Output: 18
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8], where the largest sum 
//among the two subarrays is only 18.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,4,5], k = 2
//Output: 9
//Explanation: There are four ways to split nums into two subarrays.
//The best way is to split it into [1,2,3] and [4,5], where the largest sum 
//among the two subarrays is only 9.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= min(50, nums.length) 
// 
//
// Related Topics 贪心 数组 二分查找 动态规划 前缀和 👍 1079 👎 0


package com.leetcode.editor.cn;

/**
 * [410]Split Array Largest Sum
 */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new SplitArrayLargestSum().new Solution();
        System.out.println(solution.splitArray(new int[]{1, 4, 4}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int splitArray(int[] nums, int k) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            int ans = Integer.MAX_VALUE, l = 0, r = sum;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (needPart(nums, mid) <= k) {
                    ans = Math.min(ans, mid);
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            return ans;
        }

        private int needPart(int[] nums, int groupSum) {
            int part = 1;
            int currSum = 0;
            for (int num : nums) {
                if (num > groupSum) {
                    return Integer.MAX_VALUE;
                }
                if ((currSum + num) > groupSum) {
                    part++;
                    currSum = num;
                } else {
                    currSum += num;
                }
            }
            return part;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}