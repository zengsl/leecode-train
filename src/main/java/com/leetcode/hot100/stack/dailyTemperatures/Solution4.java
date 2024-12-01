package com.leetcode.hot100.stack.dailyTemperatures;

class Solution4 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for (int i = temperatures.length - 2; i >= 0; i--) {
                // 从后一天位置开始往前找第一个比当前温度大的位置，
                // 每次跳跃的步长为当前比较值temperatures[j]对应的result[j]——当前比较的温度都小的话，那只能去找下一个比他更大的温度。
                for (int j = i + 1; j < temperatures.length; j += result[j]) {
                    if (temperatures[j] > temperatures[i]) {
                        result[i] = j - i;
                        break;
                    } else if (result[j] == 0) {
                        break;
                    }
                }

        }
        return result;
    }
}