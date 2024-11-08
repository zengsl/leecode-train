package com.leecode.hot100.threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果重复就跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int current = nums[j] + nums[k];
                if (current > target) {
                    k--;
                } else if (current < target) {
                    j++;
                } else {
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        j++;
                        continue;
                    }
                    if (k < nums.length -1 && nums[k] == nums[k + 1]) {
                        k--;
                        continue;
                    }

                    resultList.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++;
                    k--;
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {

        int[] nums = {-2,0,0,2,2};
//        [-2,0,0,2,2]
        System.out.println(new Solution().threeSum(nums));
    }
}