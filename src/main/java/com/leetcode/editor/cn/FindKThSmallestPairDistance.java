//The distance of a pair of integers a and b is defined as the absolute 
//difference between a and b. 
//
// Given an integer array nums and an integer k, return the kᵗʰ smallest 
//distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,3,1], k = 1
//Output: 0
//Explanation: Here are all the pairs:
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//Then the 1ˢᵗ smallest distance pair is (1,1), and its distance is 0.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,1,1], k = 2
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: nums = [1,6,1], k = 3
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// 2 <= n <= 10⁴ 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= n * (n - 1) / 2 
// 
//
// Related Topics 数组 双指针 二分查找 排序 👍 492 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [719]Find K-th Smallest Pair Distance
 */
public class FindKThSmallestPairDistance {

    public static void main(String[] args) {
        Solution solution = new FindKThSmallestPairDistance().new Solution();
        System.out.println(solution.countOfLessNum(new int[]{1, 2, 3, 4, 50, 60, 65}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            Arrays.sort(nums);
            int l = 0, r = nums[nums.length - 1] - nums[0], mid, ans = Integer.MAX_VALUE;
            while (l <= r) {
                mid = l + ((r - l) >> 1);
                // 比要求的要少，那么就放大dis，在右侧继续找
                if (countOfLessNum(nums, mid) < k) {
                    l = mid + 1;
                } else {
                    ans = mid;
                    r = mid - 1;
                }
            }
            return ans;
        }

        /**
         * 滑动窗口查找
         * 数组中 小于等于差值dis的数组对总数
         * <p>
         * 3
         * 1, 2, 3, 4, 50, 60, 65
         */
        private int countOfLessNum(int[] nums, int dis) {
            int sum = 0;
            for (int i = 0, j = 1, size = nums.length; i < size - 1; i++) {
                while (j < size && Math.abs(nums[j] - nums[i]) <= dis) {
                    j++;
                }
                sum += (j - i - 1);
            }
            return sum;
        }

                /*private int countOfLessNum(int[] nums, int dis) {
            int sum = 0;
            for (int i = 0, j, size = nums.length; i < size - 1; i++) {
                j = i + 1;
                while (j < size && Math.abs(nums[j] - nums[i]) <= dis) {
                    j++;
                    sum++;
                }
            }
            return sum;
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}