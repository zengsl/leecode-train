package com.leetcode.hot100.linkedList.isPalindrome;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null  ) {
            return false;
        }

        if (head.next == null) {
            return true;
        }

        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }

        int left = 0, right = nums.size() - 1;
        while (left < right) {
            if (!Objects.equals(nums.get(left++), nums.get(right--))) {
                return false;
            }
        }

        return true;
    }

}