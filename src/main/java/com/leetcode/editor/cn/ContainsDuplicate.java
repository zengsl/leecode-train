//Given an integer array nums, return true if any value appears at least twice 
//in the array, and return false if every element is distinct. 
//
// 
// Example 1: 
//
// 
// Input: nums = [1,2,3,1] 
// 
//
// Output: true 
//
// Explanation: 
//
// The element 1 occurs at the indices 0 and 3. 
//
// Example 2: 
//
// 
// Input: nums = [1,2,3,4] 
// 
//
// Output: false 
//
// Explanation: 
//
// All elements are distinct. 
//
// Example 3: 
//
// 
// Input: nums = [1,1,1,3,3,4,3,2,4,2] 
// 
//
// Output: true 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 排序 👍 1108 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [217]Contains Duplicate
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        Solution solution = new ContainsDuplicate().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /*public boolean containsDuplicate(int[] nums) {
            return process(nums, 0, nums.length - 1);
        }

        private boolean process(int[] nums, int start, int end) {
            if (nums == null || start == end) {
                return false;
            }
            int mid = start + end >> 1;
            if (process(nums, start, mid - 1)) {
                return true;
            }
            if (process(nums, mid, end)) {
                return true;
            }

            return false;
        }*/

        /*public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i +1]) {
                    return true;
                }
            }
            return false;
        }*/

        public boolean containsDuplicate(int[] nums) {

            Map<Integer, Integer> set = new HashMap<>();
            for (int num : nums) {
                if (set.put(num, 1) != null) {
                    return true;
                }
            }
            return false;
        }

        /*public boolean containsDuplicate(int[] nums) {
            if (nums == null) {
                return false;
            }
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) {
                    return true;
                }
                set.add(num);
            }
            return false;
        }*/

        /*public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length < 2) {
                return false;
            }
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return true;
                }
            }
            return false;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}