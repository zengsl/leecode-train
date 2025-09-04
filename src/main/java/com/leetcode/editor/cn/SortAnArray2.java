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
public class SortAnArray2 {
    public static void main(String[] args) {
        Solution radixSort = new SortAnArray2().new Solution();
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
            radixSort.sortArray(nums1);
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
                break;
            }
        }

    }

    private static int getRandomValue(int minValue, int maxValue) {
        Random random = new Random();
        return minValue + (int) ((maxValue - minValue + 1) * random.nextDouble());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        private final static int MAX_LENGTH = 50001;
        private final static int SCALE = 10;
        private final static int[] cnts = new int[SCALE];
        private final static int[] help = new int[MAX_LENGTH];


        public int[] sortArray(int[] nums) {

            if (nums == null || nums.length == 0) {
                return nums;
            }
            // 计算最小值
            int min = 0, max = 0;
            for (int num : nums) {
                min = Math.min(min, num);
            }

            // 减去最小值，全部调整成正数 如果可能溢出，则需要换成Long[]
            for (int i = 0; i < nums.length; i++) {
                nums[i] -= min;
                max = Math.max(max, nums[i]);
            }
            // 基数排序
            radixSort(nums, nums.length, bits(max));

            // 加回最小值，数据还原
            for (int i = 0; i < nums.length; i++) {
                nums[i] += min;
            }
            return nums;
        }

        // 基数排序
        private void radixSort(int[] nums, int length, int bits) {
            if (length < 1) {
                return;
            }
            //  从最低位开始排序
            for (int offset = 1; bits > 0; offset *= SCALE, bits--) {
                // init
                Arrays.fill(cnts, 0);
                // 计算每位的数字出现的次数
                for (int i = 0; i < length; i++) {
                    cnts[((nums[i] / offset) % SCALE)]++;
                }
                // 计算每位的数字出现的累计
                for (int i = 1; i < SCALE; i++) {
                    cnts[i] += cnts[i - 1];
                }

                for (int i = length - 1; i >= 0; i--){
                    help[--cnts[((nums[i] / offset) % SCALE)]] = nums[i];
                }

                System.arraycopy(help, 0, nums, 0, length);
            }
        }

        private int bits(int num) {
            int ans = 0;
            while (num > 0) {
                num /= SCALE;
                ans++;
            }
            return ans;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}