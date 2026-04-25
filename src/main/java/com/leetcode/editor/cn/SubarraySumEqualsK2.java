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
// Related Topics 数组 哈希表 前缀和 👍 2889 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [560]Subarray Sum Equals K
 */
public class SubarraySumEqualsK2 {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK2().new Solution();
        /*System.out.println(solution.subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(solution.subarraySum(new int[]{1}, 1));
        System.out.println(solution.subarraySum(new int[]{1, 2, 3}, 3));
        System.out.println(solution.subarraySum(new int[]{1, 1, 1}, 2));*/
        System.out.println(solution.subarraySum(new int[]{1}, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraySum(int[] nums, int k) {
            int ans = 0, sum = 0;
            Map<Integer, Integer> his = new HashMap<>();
            his.put(0, 1);
            for (int num : nums) {
                sum += num;
                ans += his.getOrDefault(sum - k, 0);
                his.merge(sum, 1, Integer::sum);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}