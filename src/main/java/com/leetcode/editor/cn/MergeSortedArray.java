//You are given two integer arrays nums1 and nums2, sorted in non-decreasing 
//order, and two integers m and n, representing the number of elements in nums1 and 
//nums2 respectively. 
//
// Merge nums1 and nums2 into a single array sorted in non-decreasing order. 
//
// The final sorted array should not be returned by the function, but instead 
//be stored inside the array nums1. To accommodate this, nums1 has a length of m + 
//n, where the first m elements denote the elements that should be merged, and the 
//last n elements are set to 0 and should be ignored. nums2 has a length of n. 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//Output: [1,2,2,3,5,6]
//Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
//The result of the merge is [1,2,2,3,5,6] with the underlined elements coming 
//from nums1.
// 
//
// Example 2: 
//
// 
//Input: nums1 = [1], m = 1, nums2 = [], n = 0
//Output: [1]
//Explanation: The arrays we are merging are [1] and [].
//The result of the merge is [1].
// 
//
// Example 3: 
//
// 
//Input: nums1 = [0], m = 0, nums2 = [1], n = 1
//Output: [1]
//Explanation: The arrays we are merging are [] and [1].
//The result of the merge is [1].
//Note that because m = 0, there are no elements in nums1. The 0 is only there 
//to ensure the merge result can fit in nums1.
// 
//
// 
// Constraints: 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -10⁹ <= nums1[i], nums2[j] <= 10⁹ 
// 
//
// 
// Follow up: Can you come up with an algorithm that runs in O(m + n) time? 
//
// Related Topics 数组 双指针 排序 👍 2606 👎 0


package com.leetcode.editor.cn;

/**
 * [88]Merge Sorted Array
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        int[] arr1 = {2, 0};
        int[] arr2 = {1};
        solution.merge(arr1, 1, arr2, 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int index = m + n - 1;
            int i = m - 1, j = n - 1;
            while (i >= 0 && j >= 0) {
                nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
            }

            while (j >= 0) {
                nums1[index--] = nums2[j--];
            }
        }

        /*public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tmp = new int[m];
            System.arraycopy(nums1, 0, tmp, 0, m);
            int index = 0;
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (tmp[i] <= nums2[j]) {
                    nums1[index++] = tmp[i++];
                } else {
                    nums1[index++] = nums2[j++];
                }
            }

            while (i < m) {
                nums1[index++] = tmp[i++];
            }

            while (j < n) {
                nums1[index++] = nums2[j++];
            }

        }*/

    /*public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}