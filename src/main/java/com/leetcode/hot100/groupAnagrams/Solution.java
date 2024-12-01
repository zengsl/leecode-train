package com.leetcode.hot100.groupAnagrams;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return null;
        }

        if (strs.length == 1 && strs[0].isEmpty()) {
            resultList.add(List.of(""));
            return resultList;
        }


        Map<String,List<String>> groupMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> currentGroup = groupMap.computeIfAbsent(key, (el)-> new ArrayList<>());
            currentGroup.add(str);
        }


        if (!groupMap.isEmpty()) {
            resultList.addAll(groupMap.values());
        }
        return resultList;
    }

    public static void main(String[] args) {
        String[] strs = {""};
        System.out.println(new Solution().groupAnagrams(strs));
    }
}