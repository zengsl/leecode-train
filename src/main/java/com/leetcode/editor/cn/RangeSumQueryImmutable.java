//Given an integer array nums, handle multiple queries of the following type: 
//
// 
// Calculate the sum of the elements of nums between indices left and right 
//inclusive where left <= right. 
// 
//
// Implement the NumArray class: 
//
// 
// NumArray(int[] nums) Initializes the object with the integer array nums. 
// int sumRange(int left, int right) Returns the sum of the elements of nums 
//between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + 
//nums[right]). 
// 
//
// 
// Example 1: 
//
// 
//Input
//["NumArray", "sumRange", "sumRange", "sumRange"]
//[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//Output
//[null, 1, -1, -3]
//
//Explanation
//NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
//numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
//numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁵ <= nums[i] <= 10⁵ 
// 0 <= left <= right < nums.length 
// At most 10⁴ calls will be made to sumRange. 
// 
//
// Related Topics 设计 数组 前缀和 👍 705 👎 0


package com.leetcode.editor.cn;

/**
 * [303]Range Sum Query - Immutable
 */
public class RangeSumQueryImmutable {
    public static void main(String[] args) {

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {

        private final int[] counts;

        public NumArray(int[] nums) {
            this.counts = new int[nums.length + 1];
            for (int i = 0, size = nums.length; i < size; i++) {
                counts[i + 1] = counts[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return counts[right + 1] - counts[left];
        }


        /*private int[] counts;

        public NumArray(int[] nums) {
            this.counts = new int[nums.length];
            counts[0] = nums[0];
            for (int i = 1, size = nums.length; i < size; i++) {
                counts[i] = counts[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return left == 0 ? counts[right] : counts[right] - counts[left - 1];
        }*/
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)

}