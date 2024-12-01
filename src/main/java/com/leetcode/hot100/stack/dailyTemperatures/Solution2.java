package com.leetcode.hot100.stack.dailyTemperatures;

class Solution2 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            result[i] = 0;
            if (i == 0 || temperatures[i] < temperatures[i - 1]) {
                for (int j = i + 1; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        result[i] = j - i;
                        break;
                    }
                }
            } else {
                if (temperatures[i] >= temperatures[i - 1]) {
                    for (int j = Math.max(i - 1 + result[i - 1], i + 1); j < temperatures.length; j++) {
                        if (temperatures[j] > temperatures[i]) {
                            result[i] = j - i;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}