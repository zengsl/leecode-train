package com.leetcode.hot100.longestConsecutive;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 将数组中所有元素放入集合
        Set <Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;
        // 遍历集合中所有的元素，依次判断是否为最长连续子序列的起点，并且计算最长连续子序列的长度。
        // 最终取“以每个元素为起点的子序列中长度最长”的作为最长子序列，并返回其长度。
        for (int currentNum : numSet) {
            // 当前数的前一位数字不在集合中，说明当前数是连续子序列的起点
            if (!numSet.contains(currentNum - 1)) {
                int currentStreak = 1;
                // 循环判断集合中是否存在连续数字
                while (numSet.contains(currentNum + 1)) {
                    // 最长子序列长度 + 1
                    currentStreak += 1;
                    // 当前数字 + 1 ，为继续判断下一个数字做准备
                    currentNum += 1;
                }
                // 每次循环结束之后，将当前最长子序列长度与最长子序列长度进行比较，取较大的值
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        System.out.println(solution.longestConsecutive(nums1)); // 输出: 4
        System.out.println(solution.longestConsecutive(nums2)); // 输出: 9
    }
}