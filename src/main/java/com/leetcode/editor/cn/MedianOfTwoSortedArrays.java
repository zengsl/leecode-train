//Given two sorted arrays nums1 and nums2 of size m and n respectively, return 
//the median of the two sorted arrays. 
//
// The overall run time complexity should be O(log (m+n)). 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
// 
//
// Example 2: 
//
// 
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
// 
//
// 
// Constraints: 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 7944 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [4]Median of Two Sorted Arrays
 *
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{2}, new int[]{}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{}, new int[]{2, 3}));
//        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}));
        System.out.println(solution.findMedianSortedArrays(new int[]{2, 2, 4, 4}, new int[]{2, 2, 2, 4, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums2.length < nums1.length) {
                int[] tmp = nums1;
                nums1 = nums2;
                nums2 = tmp;
            }
            // n + (m - n + 1) / 2
            // leftCount为左侧区间元素总和，总数为奇数时，让左侧区间多一个元素
            int n = nums1.length, m = nums2.length, leftCount = n + (m - n + 1) / 2;
            //  0 1 2 3 4 | 5
            int left = 0, right = n, i, j;
            while (left < right) {
                i = left + (right - left) / 2;
                j = leftCount - i;
                if (nums1[i - 1] > nums2[j]) {
                    right = i - 1;
                } else {
                    left = i;
                }
            }
            i = left;
            j = leftCount - i;
            int left1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int left2 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int right1 = i == n ? Integer.MAX_VALUE : nums1[i];
            int right2 = j == m ? Integer.MAX_VALUE : nums2[j];

            return (m + n) % 2 == 0 ? (double) (Math.max(left1, left2) + Math.min(right1, right2)) / 2 : Math.max(left1, left2);
        }

        /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length == 0 && nums2.length == 0) {
                return 0;
            }
            int size1 = nums1.length, size2 = nums2.length;
            int total = size1 + size2;
            int target1, target2, targetNum1 = 0, targetNum2 = 0;
            if (total % 2 == 0) {
                target1 = total / 2 - 1;
                target2 = total / 2;
            } else {
                target1 = target2 = total / 2;
            }
            int i = 0, j = 0, count = 0;
            while (i < size1 && j < size2) {
                if (nums1[i] <= nums2[j]) {
                    if (count == target1) {
                        targetNum1 = nums1[i];
                    }
                    if (count == target2) {
                        targetNum2 = nums1[i];
                        return (double) (targetNum1 + targetNum2) / 2;
                    }
                    i++;
                } else {
                    if (count == target1) {
                        targetNum1 = nums2[j];
                    }
                    if (count == target2) {
                        targetNum2 = nums2[j];
                        return (double) (targetNum1 + targetNum2) / 2;
                    }
                    j++;
                }
                count++;
            }


            while (i < size1) {
                if (count == target1) {
                    targetNum1 = nums1[i];
                }
                if (count == target2) {
                    targetNum2 = nums1[i];
                    return (double) (targetNum1 + targetNum2) / 2;
                }
                count++;
                i++;
            }

            while (j < size2) {
                if (count == target1) {
                    targetNum1 = nums2[j];
                }
                if (count == target2) {
                    targetNum2 = nums2[j];
                    return (double) (targetNum1 + targetNum2) / 2;
                }
                count++;
                j++;
            }
            return 0;
        }*/

        /*PriorityQueue<Integer> bigHeap = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            for (int i : nums1) {
                add(i);
            }
            for (int i : nums2) {
                add(i);
            }

            int bigTop = bigHeap.isEmpty() ? 0 : bigHeap.peek();
            int smallTop = smallHeap.isEmpty() ? 0 : smallHeap.peek();
            if (bigHeap.size() == smallHeap.size()) {
                return (double) (bigTop + smallTop) / 2;
            } else  {
                return bigHeap.size() > smallHeap.size() ? bigHeap.peek() : smallHeap.peek();
            }
        }

        private void add(Integer num) {
            if (bigHeap.isEmpty() || num <= bigHeap.peek()) {
                bigHeap.add(num);
            } else {
                smallHeap.add(num);
            }

            // rebalance
            if (bigHeap.size() - smallHeap.size() == 2) {
                smallHeap.add(bigHeap.poll());
            } else if(smallHeap.size() - bigHeap.size() == 2){
                bigHeap.add(smallHeap.poll());
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}