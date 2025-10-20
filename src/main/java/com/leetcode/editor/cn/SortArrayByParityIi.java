//Given an array of integers nums, half of the integers in nums are odd, and 
//the other half are even. 
//
// Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[
//i] is even, i is even. 
//
// Return any answer array that satisfies this condition. 
//
// 
// Example 1: 
//
// 
//Input: nums = [4,2,5,7]
//Output: [4,5,2,7]
//Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
// 
//
// Example 2: 
//
// 
//Input: nums = [2,3]
//Output: [2,3]
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 2 * 10⁴ 
// nums.length is even. 
// Half of the integers in nums are even. 
// 0 <= nums[i] <= 1000 
// 
//
// 
// Follow Up: Could you solve it in-place? 
//
// Related Topics 数组 双指针 排序 👍 341 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [922]Sort Array By Parity II
 */
public class SortArrayByParityIi {
    public static void main(String[] args) {
        Solution solution = new SortArrayByParityIi().new Solution();
        System.out.println(Arrays.toString(solution.sortArrayByParityII(new int[]{4, 2, 5, 7})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArrayByParityII(int[] nums) {
            // i is even, j is odd
            for (int i = 0, j = 1, size = nums.length; j < size && i < size; ) {
                if (nums[i] % 2 != 0) {
//                    swap(nums, i, j);
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    j += 2;
                } else {
                    i += 2;
                }
            }
            return nums;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}