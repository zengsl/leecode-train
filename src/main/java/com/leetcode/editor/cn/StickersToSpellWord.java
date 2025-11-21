//We are given n different types of stickers. Each sticker has a lowercase 
//English word on it. 
//
// You would like to spell out the given string target by cutting individual 
//letters from your collection of stickers and rearranging them. You can use each 
//sticker more than once if you want, and you have infinite quantities of each 
//sticker. 
//
// Return the minimum number of stickers that you need to spell out target. If 
//the task is impossible, return -1. 
//
// Note: In all test cases, all words were chosen randomly from the 1000 most 
//common US English words, and target was chosen as a concatenation of two random 
//words. 
//
// 
// Example 1: 
//
// 
//Input: stickers = ["with","example","science"], target = "thehat"
//Output: 3
//Explanation:
//We can use 2 "with" stickers, and 1 "example" sticker.
//After cutting and rearrange the letters of those stickers, we can form the 
//target "thehat".
//Also, this is the minimum number of stickers necessary to form the target 
//string.
// 
//
// Example 2: 
//
// 
//Input: stickers = ["notice","possible"], target = "basicbasic"
//Output: -1
//Explanation:
//We cannot form the target "basicbasic" from cutting letters from the given 
//stickers.
// 
//
// 
// Constraints: 
//
// 
// n == stickers.length 
// 1 <= n <= 50 
// 1 <= stickers[i].length <= 10 
// 1 <= target.length <= 15 
// stickers[i] and target consist of lowercase English letters. 
// 
//
// Related Topics 位运算 记忆化搜索 数组 哈希表 字符串 动态规划 回溯 状态压缩 👍 316 👎 0


package com.leetcode.editor.cn;

import java.util.*;

/**
 * [691]Stickers to Spell Word
 */
public class StickersToSpellWord {
    public static void main(String[] args) {
        Solution solution = new StickersToSpellWord().new Solution();
//        System.out.println(solution.minStickers(new String[]{"with", "example", "science"}, "thehat"));
//        System.out.println(solution.minStickers(new String[]{"notice", "possible"}, "basicbasic"));
        System.out.println(solution.minStickers(new String[]{"these", "guess", "about", "garden", "him"}, "atomher"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static List<List<String>> ADJACENCY_LIST = new ArrayList<>();
        // 试一试出来的队列长度
        public static final int MAX = 401;
        public static String[] DEQUE = new String[MAX];
        public static int l, r;
        public static Set<String> visited = new HashSet<>();

        public int minStickers(String[] stickers, String target) {
            init();
            for (String sticker : stickers) {
                sticker = sort(sticker);
                for (int i = 0, l = sticker.length(); i < l; i++) {
                    if (i == 0 || sticker.charAt(i) != sticker.charAt(i - 1)) {
                        ADJACENCY_LIST.get(sticker.charAt(i) - 'a').add(sticker);
                    }
                }
            }

            target = sort(target);
            DEQUE[r++] = target;
            visited.add(target);
            int times = 0;
            while (l < r) {
                times++;
                int size = r - l;
                for (int i = 0; i < size; i++) {
                    String tar = DEQUE[l++];
                    for (String sticker : ADJACENCY_LIST.get(tar.charAt(0) - 'a')) {
                        String remain = cut(tar, sticker);
                        if (remain.isBlank()) {
                            return times;
                        }
                        if (!visited.contains(remain)) {
                            visited.add(remain);
                            DEQUE[r++] = remain;
                        }
                    }
                }
            }
            return -1;
        }

        //aehmort adegnr
        public static String cut(String target, String sticker) {
            StringBuilder res = new StringBuilder();
            for (int i = 0, j = 0, len = target.length(), sLen = sticker.length(); i < len;) {
                if (j >= sLen) {
                    res.append(target.charAt(i++));
                } else {
                    if (target.charAt(i) == sticker.charAt(j)) {
                        i++;
                        j++;
                    } else if (target.charAt(i) > sticker.charAt(j)) {
                        j++;
                    } else if (target.charAt(i) < sticker.charAt(j)) {
                        res.append(target.charAt(i++));
                    }
                }
            }
            return res.toString();
        }

        public static String sort(String s) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return String.valueOf(charArray);
        }

        public static void init() {
            l = r = 0;
            visited.clear();
            ADJACENCY_LIST.clear();
            for (int i = 0; i < 26; i++) {
                ADJACENCY_LIST.add(new ArrayList<>());
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}