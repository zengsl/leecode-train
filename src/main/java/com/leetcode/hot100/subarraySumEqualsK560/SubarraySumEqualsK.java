//Given an array of integers nums and an integer k, return the total number of 
//subarrays whose sum equals to k. 
//
// A subarray is a contiguous non-empty sequence of elements within an array. 
//
// 
// Example 1: 
// Input: nums = [1,1,1], k = 2
//Output: 2
// 
// Example 2: 
// Input: nums = [1,2,3], k = 3
//Output: 2
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
//
// Related Topics 数组 哈希表 前缀和 👍 2858 👎 0


package com.leetcode.hot100.subarraySumEqualsK560;

import java.util.Arrays;

/**
 * [560]Subarray Sum Equals K
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraySum(int[] nums, int k) {
            Arrays.sort(nums);


            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}