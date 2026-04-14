//Given an unsorted integer array nums. Return the smallest positive integer 
//that is not present in nums. 
//
// You must implement an algorithm that runs in O(n) time and uses O(1) 
//auxiliary space. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,0]
//Output: 3
//Explanation: The numbers in the range [1,2] are all in the array.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,4,-1,1]
//Output: 2
//Explanation: 1 is in the array but 2 is missing.
// 
//
// Example 3: 
//
// 
//Input: nums = [7,8,9,11,12]
//Output: 1
//Explanation: The smallest positive integer 1 is missing.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// Related Topics 数组 哈希表 👍 2427 👎 0


package com.leetcode.editor.cn;

/**
 * [41]First Missing Positive
 */
public class FirstMissingPositive2 {
    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive2().new Solution();
//        System.out.println(solution.firstMissingPositive(new int[]{2, 2}));
//        System.out.println(solution.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int l = 0, r = nums.length;
            while (l < r) {
                if (l + 1 == nums[l]) {
                    l++;
                } else if (nums[l] > r || nums[l] <= l || nums[nums[l] - 1] == nums[l]) {
                    swap(l, --r, nums);
                } else {
                    swap(l, nums[l] - 1, nums);
                }
            }
            return l + 1;
        }

        private void swap(int i, int j, int[] nums) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}