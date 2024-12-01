package com.leetcode.hot100.easy.longestCommonPrefix;

public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                // 不断减短前缀
                prefix = prefix.substring(0 , prefix.length() - 1);
            }
        }
        return prefix;
    }
}
