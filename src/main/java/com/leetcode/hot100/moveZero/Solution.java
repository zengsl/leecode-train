package com.leetcode.hot100.moveZero;

import java.util.Arrays;

class Solution {
    //[0,1,0,3,12]
    public void moveZeroes(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }

        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroIndex == -1) {
                zeroIndex = i;
            } else if(nums[i] != 0){
                if (zeroIndex != -1) {
                    nums[zeroIndex] = nums[i];
                    nums[i] = 0;
                    zeroIndex += 1;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}