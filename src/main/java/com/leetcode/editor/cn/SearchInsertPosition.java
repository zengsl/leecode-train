//Given a sorted array of distinct integers and a target value, return the 
//index if the target is found. If not, return the index where it would be if it were 
//inserted in order. 
//
// You must write an algorithm with O(log n) runtime complexity. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,3,5,6], target = 5
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: nums = [1,3,5,6], target = 2
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: nums = [1,3,5,6], target = 7
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums contains distinct values sorted in ascending order. 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 2402 👎 0


package com.leetcode.editor.cn;

/**
 * [35]Search Insert Position
 */
public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new SearchInsertPosition().new Solution();
//        int[] nums1 = new int[]{1, 3, 5, 6};
//        System.out.println(solution.searchInsert(nums1, 2));
//        System.out.println(solution.searchInsert(nums1, 5));

        int[] nums1 = new int[]{1, 3};
        System.out.println(solution.searchInsert(nums1, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int searchInsert(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            // fast check
            int length = nums.length;
            if (target <= nums[0]) {
                return 0;
            }
            if (target >= nums[length - 1]) {
                return length;
            }

            // binary search
            int left = 0, right = length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
            return left;
        }

        /*private int process(int[] nums, int start, int end, int target) {
            *//*if (start == end) {

                if (nums[start] == target) {
                  return start;
                } else if (target < nums[start]) {
                    return start;

//                    return Math.max(start - 1, 0);
                } else if (target > nums[start]) {
                    return start + 1;
                }
            }*//*

            int middle = start + (end - start) / 2;
            if (nums[middle] > target) {
                return process(nums, start, middle - 1, target);
            } else if (nums[middle] < target) {
                return process(nums, middle + 1, end, target);
            } else if (nums[middle] == target) {
                return middle;
            } else {
                return -1;
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}