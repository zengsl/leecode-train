package com.leetcode.hot100.stack.dailyTemperatures;

class Solution3 {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for (int i = temperatures.length - 2; i >= 0; i--) {
            // 比后面一个数字小，则结果为1
            if (temperatures[i] < temperatures[i + 1]) {
                result[i] = 1;
            } else if (temperatures[i] == temperatures[i + 1]) {
                // 与后面一个数字相等，则：
                // 后面一个结果为0，则当前结果也为0
                // 后面一个结果不为0，则当前结果在后面一个结果的基础上 + 1
                result[i] = result[i + 1] == 0 ? 0 : 1 + result[i + 1];
            } else {
                // 当前温度大于第二天:
                // 从大于第二天的位置开始往前找第一个比当前温度大的位置，
                // 每次跳跃的步长为当前比较值temperatures[j]对应的result[j]——当前比较的温度都小的话，那只能去找下一个比他更大的温度。
                for (int j = (i + 1 + result[i + 1]); j < temperatures.length; j += result[j]) {
                    if (temperatures[j] > temperatures[i]) {
                        result[i] = j - i;
                        break;
                    } else if (result[j] == 0) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}