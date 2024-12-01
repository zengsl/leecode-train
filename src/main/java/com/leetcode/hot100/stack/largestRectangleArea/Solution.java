package com.leetcode.hot100.stack.largestRectangleArea;

import java.util.PriorityQueue;

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // 大根堆
        PriorityQueue<Integer> stack = new PriorityQueue<>();
        for (int height : heights) {
            stack.add(height);
        }

        int max = 0;
        /*int start = 0;
        int end = heights.length - 1;*/
        /*while (start <= end) {
            // 获取当前计算的最大值
            max = Math.max(max, (end - start + 1) * stack.peek());
            *//*if(heights[start] <= heights[end]) {
                stack.remove(heights[start++]);
            } else {
                stack.remove(heights[end--]);
            }*//*
        }*/
        int length = heights.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                max = Math.max(max, (j - i + 1) * stack.peek());
            }
        }

        return max;
    }
}
