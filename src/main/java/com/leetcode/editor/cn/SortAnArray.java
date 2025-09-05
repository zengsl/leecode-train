//Given an array of integers nums, sort the array in ascending order and return 
//it. 
//
// You must solve the problem without using any built-in functions in O(nlog(n))
// time complexity and with the smallest space complexity possible. 
//
// 
// Example 1: 
//
// 
//Input: nums = [5,2,3,1]
//Output: [1,2,3,5]
//Explanation: After sorting the array, the positions of some numbers are not 
//changed (for example, 2 and 3), while the positions of other numbers are changed (
//for example, 1 and 5).
// 
//
// Example 2: 
//
// 
//Input: nums = [5,1,1,2,0,0]
//Output: [0,0,1,1,2,5]
//Explanation: Note that the values of nums are not necessarily unique.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 1150 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Random;

/**
 * [912]Sort an Array
 *
 * @author zengsl
 */
public class SortAnArray {
    public static void main(String[] args) {
        Solution quickSort = new SortAnArray().new Solution();
        Solution2 heapSort = new SortAnArray().new Solution2();
        int times = 100;
        int maxLen = 5 * 10000;
        int maxValue = 5 * 10000;
        int minValue = -5 * 10000;
        for (int i = 0; i < times; i++) {
            int length = (int) (Math.random() * maxLen) + 1;
            int[] nums = new int[length];
            for (int j = 0; j < length; j++) {
                nums[j] = getRandomValue(minValue, maxValue);
            }
            int[] nums1 = Arrays.copyOf(nums, length);
            int[] nums2 = Arrays.copyOf(nums, length);
//            quickSort.sortArray(nums1);
            heapSort.sortArray(nums1);
            Arrays.sort(nums2);
            boolean isSuccess = true;
            for (int j = 0; j < length; j++) {
                if (nums1[j] != nums2[j]) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        }

    }

    private static int getRandomValue(int minValue, int maxValue) {
        Random random = new Random();
        return minValue + (int) ((maxValue - minValue + 1) * random.nextDouble());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 等于区域的开始和结束索引
         */
        private int eqStartIndex = 0, eqEndIndex = 0;

        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 快速排序
         * <p>
         * 荷兰国旗版本
         * <p>
         * N * logN
         *
         * @param start 第一个数字索引
         * @param end   最后一个数字索引
         */
        private void quickSort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }
            // 随机在数组中选择一个数
            int k = nums[(int) (Math.random() * (1 + end - start)) + start];
            // 将数组分为三个部分
            partition(nums, start, end, k);
            // 记录分区之后的等于区域边界
            int left = eqStartIndex;
            int right = eqEndIndex;
            // 分别对左侧与右侧区域递归进行排序
            quickSort(nums, start, left - 1);
            quickSort(nums, right + 1, end);
        }

        private void partition(int[] nums, int left, int right, int k) {
            // 等于区域初始化，k是从[left - right]中随机取的数值，一定是存在的，所以一定会修改eqStartIndex和eqEndIndex
            eqStartIndex = left;
            eqEndIndex = right;
            // 数组遍历的索引，从数组左侧区间开始，遍历到右侧大于区间结束
            int i = left;
            while (i <= eqEndIndex) {
                // 当前便利的数据如果小于k，则放到左侧区域
                if (nums[i] < k) {
                    // 交换并且增大小于区域，遍历索引增加
                    // 因为 i一定是大于等于eqStartIndex的，所以交换后，i索引位置的元素，一定小于k是之前已经遍历过的，所以不需要再次查看，i + 1即可
                    swap(nums, i++, eqStartIndex++);
                } else if (nums[i] == k) {
                    i++;
                } else {
                    // i > eqEndIndex会跳出while循环，当前i一定<=eqEndIndex
                    // 当i位置数据大于k时，交换i位置数据与eqEndIndex位置数据，右侧区域增大
                    // 此时由于刚换到i位置的数据还未查看过，所以i不变，等待下次遍历继续查看。
                    swap(nums, i, eqEndIndex--);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    class Solution2 {

        /**
         * 堆排序
         */
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }
            int size = nums.length;
            for (int i = 0; i < size; i++) {
                heapInsert(nums, i);
            }

            while (size > 1) {
                // 最大值放到最后，有效大小减一
                swap(nums, 0, --size);
                heapify(nums, 0, size);
            }

            return nums;
        }

        private void heapInsert(int[] nums, int i) {
            while (i > 0 && nums[(i - 1) >> 1] < nums[i]) {
                swap(nums, i, (i - 1) >> 1);
                i = (i - 1) >> 1;
            }
        }

        private void heapify(int[] nums, int i, int heapSize) {
            while (((i << 1) + 1) < heapSize) {
                // 值较大的子节点索引
                int k = ((i << 1) + 2) < heapSize && nums[(i << 1) + 2] > nums[(i << 1) + 1] ? (i << 1) + 2 : (i << 1) + 1;
                if (nums[i] >= nums[k]) {
                    break;
                }
                swap(nums, i, k);
                i = k;
            }
        }


        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}