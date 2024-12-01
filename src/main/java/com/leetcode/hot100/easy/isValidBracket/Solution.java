package com.leetcode.hot100.easy.isValidBracket;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        char[] charArray = s.toCharArray();
        boolean isValid = true;
        Deque<Character> stack = new LinkedList<>();
       for (char c : charArray) {
            if (c == ')' || c == '}' || c == ']') {
               if (stack.isEmpty()) {
                   isValid = false;
                   break;
               } else {
                   Character top = stack.pop();
                   if ((top == '(' && c != ')') || top == '{' && c != '}' || top == '[' && c != ']') {
                       isValid = false;
                       break;
                   }
               }
           } else {
                stack.push(c);
            }
       }
        return stack.isEmpty() && isValid;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("(]"));
    }
}

