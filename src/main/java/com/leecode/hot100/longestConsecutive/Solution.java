package com.leecode.hot100.longestConsecutive;

import java.util.Arrays;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 排序
        Arrays.sort(nums);
        int maxLength = 1;
        int currentMaxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                currentMaxLength += 1;
            }
            else if(nums[i] != nums[i - 1]){
                maxLength = Math.max(maxLength, currentMaxLength);
                currentMaxLength = 1;
            }
        }
        return Math.max(maxLength, currentMaxLength);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{1,2,0,1


        }));
    }

}