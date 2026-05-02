//You are given a string s. We want to partition the string into as many parts 
//as possible so that each letter appears in at most one part. For example, the 
//string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as [
//"aba", "bcc"] or ["ab", "ab", "cc"] are invalid. 
//
// Note that the partition is done so that after concatenating all the parts in 
//order, the resultant string should be s. 
//
// Return a list of integers representing the size of these parts. 
//
// 
// Example 1: 
//
// 
//Input: s = "ababcbacadefegdehijhklij"
//Output: [9,7,8]
//Explanation:
//The partition is "ababcbaca", "defegde", "hijhklij".
//This is a partition so that each letter appears in at most one part.
//A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it 
//splits s into less parts.
// 
//
// Example 2: 
//
// 
//Input: s = "eccbbbbdec"
//Output: [10]
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 500 
// s consists of lowercase English letters. 
// 
//
// Related Topics 贪心 哈希表 双指针 字符串 👍 1418 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * [763]Partition Labels
 *
 */
public class PartitionLabels {
    public static void main(String[] args) {
        Solution solution = new PartitionLabels().new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(solution.partitionLabels("eccbbbbdec"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<Integer> partitionLabels(String s) {
            int[] lastIndex = new int[26];
            char[] chars = s.toCharArray();
            for (int i = 0, n = chars.length; i < n; i++) {
                lastIndex[chars[i] - 'a'] = i;
            }

            List<Integer> ans = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0, n = chars.length; i < n; i++) {
                end = Math.max(end, lastIndex[chars[i] - 'a']);
                if (i == end) {
                    ans.add(end - start + 1);
                    start = end + 1;
                }
            }
            return ans;
        }



        /*public List<Integer> partitionLabels(String s) {
            int[] freq = new int[26];
            int[] currSet = new int[26];
            char[] chars = s.toCharArray();
            for (char c : chars) {
                freq[c - 'a']++;
            }

            List<Integer> ans = new ArrayList<>();
            int count = 0, remain = 0;
            // 当前集合内的元素，以及当前集合内元素在后面是否还有
            for (char c : chars) {
                count++;
                if (currSet[c - 'a'] != 0) {
                    remain -= 1;
                } else {
                    currSet[c - 'a'] = 1;
                    remain += (freq[c - 'a'] - 1);
                }

                if (remain == 0) {
                    ans.add(count);
                    count = 0;
                }
            }
            return ans;
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}