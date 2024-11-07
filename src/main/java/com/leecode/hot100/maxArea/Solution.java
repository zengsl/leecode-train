package com.leecode.hot100.maxArea;

import java.util.*;

class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        // 设置双指针，指向首尾
        int left = 0, right = height.length - 1, maxArea = 0;
        // 双指针不相等时循环
        while (left != right) {
            // a边为【双指针之间距离】，b边【双指针对应的最小高度】
            // 判断当前面积a * b 是否大于 maxArea，maxArea取两者最大值
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            // 高度小的指针移动
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}