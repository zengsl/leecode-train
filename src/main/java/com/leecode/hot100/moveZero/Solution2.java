package com.leecode.hot100.moveZero;

import java.util.Arrays;

class Solution2 {
    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }
        // 定义快慢指针：慢指针用于定位替换的目标位置， 快指针寻找非0数。
        int n = nums.length, slow = 0, fast = 0;
        // 当快指针小于数组长度时，执行循环
        while (fast < n) {
            // 快指针寻找到非0元素之后，与慢指针指向的元素进行交换，并将慢指针向后移动一位，为下一次替换做准备。
            if (nums[fast] != 0) {
                swap(nums, slow++, fast);
            }
            // 快指针向后移动一位，不断向后寻找非0元素
            fast++;
        }
    }

    // 交换0元素
    private void swap(int[] nums, int slow, int fast) {
        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        int[] nums2 = {1};
        Solution2 solution2 = new Solution2();
        solution2.moveZeroes(nums);
        solution2.moveZeroes(nums2);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
    }
}