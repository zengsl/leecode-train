//Given an integer array nums and an integer k, return the number of good 
//subarrays of nums. 
//
// A good array is an array where the number of different integers in that 
//array is exactly k. 
//
// 
// For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3. 
// 
//
// A subarray is a contiguous part of an array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,1,2,3], k = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1],
// [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,1,3,4], k = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2
//,1,3], [1,3,4].
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// 1 <= nums[i], k <= nums.length 
// 
//
// Related Topics 数组 哈希表 计数 滑动窗口 👍 532 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [992]Subarrays with K Different Integers
 */
public class SubarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
        System.out.println(solution.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            return numsOfMostKinds(nums, k) - numsOfMostKinds(nums, k - 1);
        }

        static int[] cnts = new int[20001];

        public static int numsOfMostKinds(int[] nums, int k) {
            Arrays.fill(cnts, 0);
            int kind = 0;
            int ans = 0;
            for (int i = 0, j = 0, size = nums.length; j < size; j++) {
                if (cnts[nums[j]]++ == 0) {
                    kind++;
                }

                while (kind > k) {
                    if (--cnts[nums[i++]] == 0) {
                        kind--;
                    }
                }

                ans += (j - i + 1);
            }
            return ans;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}