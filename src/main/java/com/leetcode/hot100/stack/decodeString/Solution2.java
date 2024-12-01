package com.leetcode.hot100.stack.decodeString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution2 {
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        String regex = "(\\d*)\\[(\\w*)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String time = matcher.group(1);
            String letter = matcher.group(2);
            s = s.replace(matcher.group(0), letter.repeat(Integer.parseInt(time)));
            matcher = pattern.matcher(s);
        }
        return  s;
    }


    public String decodeString2(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        String regex = "(\\d*)\\[(\\w*)]";
        // s = s.re

        return  s;
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Solution2 solution = new Solution2();
//        System.out.println(solution.decodeString(s));
        System.out.println(solution.decodeString("3[a2[c]]"));
//        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }
}