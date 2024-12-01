package com.leetcode.hot100.stack.decodeString;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        char[] charArray = s.toCharArray();
        return decodeString(charArray);
    }


    private String decodeString(char[] charArray) {

        Deque<String> textStack = new LinkedList<>();
        Deque<Integer> timeStack = new LinkedList<>();
        StringBuilder partText = new StringBuilder();
        StringBuilder currentTime = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (char c : charArray) {
            if (Character.isDigit(c)) {
                currentTime.append(c);
            } else if (Character.isLetter(c)) {
                partText.append(c);
            } else if (c == '[') {
                textStack.push(partText.toString());
                timeStack.push(Integer.parseInt(currentTime.toString()));
                partText.setLength(0);
                currentTime.setLength(0);
            } else if (c == ']') {
                String popText = textStack.pop();
                int time = timeStack.pop();
                partText = new StringBuilder(popText + partText.toString().repeat(Math.max(0, time)));
                if (timeStack.isEmpty()) {
                    result.append(partText);
                    partText.setLength(0);
                }
            }
        }
        result.append(partText);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Solution solution = new Solution();
//        System.out.println(solution.decodeString(s));
//        System.out.println(solution.decodeString("3[a2[c]]"));
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }
}