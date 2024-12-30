//Given an array nums of size n, return the majority element. 
//
// The majority element is the element that appears more than ⌊n / 2⌋ times. 
//You may assume that the majority element always exists in the array. 
//
// 
// Example 1: 
// Input: nums = [3,2,3]
//Output: 3
// 
// Example 2: 
// Input: nums = [2,2,1,1,1,2,2]
//Output: 2
// 
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//Follow-up: Could you solve the problem in linear time and in 
//O(1) space?
//
// Related Topics 数组 哈希表 分治 计数 排序 👍 2357 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [169]Majority Element
 */
public class MajorityElement {
    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int majorityElement(int[] nums) {
            int count = 0, majority = -1;
            // 每次消去两个不同的元素。
            for (int num : nums) {
                if (count == 0) {
                    majority = num;
                }

                if (num == majority) {
                    count++;
                } else {
                    count--;
                }
            }
            return majority;
        }

        /*public int majorityElement(int[] nums) {
            Map<Integer, Integer> result = new HashMap<>();
            int midNum = nums.length >> 1;
            for (int num : nums) {
                Integer count = result.getOrDefault(num, 0);
                if (++count > midNum) {
                    return num;
                } else {
                    result.put(num, count);
                }
            }
            return -1;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}