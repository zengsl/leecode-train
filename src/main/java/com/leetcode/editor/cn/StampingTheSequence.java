//You are given two strings stamp and target. Initially, there is a string s of 
//length target.length with all s[i] == '?'. 
//
// In one turn, you can place stamp over s and replace every letter in the s 
//with the corresponding letter from stamp. 
//
// 
// For example, if stamp = "abc" and target = "abcba", then s is "?????" 
//initially. In one turn you can: 
// 
//
// 
// place stamp at index 0 of s to obtain "abc??", 
// place stamp at index 1 of s to obtain "?abc?", or 
// place stamp at index 2 of s to obtain "??abc". 
// 
// Note that stamp must be fully contained in the boundaries of s in order to 
//stamp (i.e., you cannot place stamp at index 3 of s).
//
//
// We want to convert s to target using at most 10 * target.length turns. 
//
// Return an array of the index of the left-most letter being stamped at each 
//turn. If we cannot obtain target from s within 10 * target.length turns, return 
//an empty array. 
//
// 
// Example 1: 
//
// 
//Input: stamp = "abc", target = "ababc"
//Output: [0,2]
//Explanation: Initially s = "?????".
//- Place stamp at index 0 to get "abc??".
//- Place stamp at index 2 to get "ababc".
//[1,0,2] would also be accepted as an answer, as well as some other answers.
// 
//
// Example 2: 
//
// 
//Input: stamp = "abca", target = "aabcaca"
//Output: [3,0,1]
//Explanation: Initially s = "???????".
//- Place stamp at index 3 to get "???abca".
//- Place stamp at index 0 to get "abcabca".
//- Place stamp at index 1 to get "aabcaca".
// 
//
// 
// Constraints: 
//
// 
// 1 <= stamp.length <= target.length <= 1000 
// stamp and target consist of lowercase English letters. 
// 
//
// Related Topics 栈 贪心 队列 字符串 👍 95 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [936]Stamping The Sequence
 */
public class StampingTheSequence {
    public static void main(String[] args) {
        Solution solution = new StampingTheSequence().new Solution();
//        System.out.println(Arrays.toString(solution.movesToStamp("abc", "ababc")));
//        System.out.println(Arrays.toString(solution.movesToStamp("e", "eeeeeeeeee")));
//        System.out.println(Arrays.toString(solution.movesToStamp("mda", "mdadddaaaa")));
//        System.out.println(Arrays.toString(solution.movesToStamp("uskh", "uskhkhhskh")));
        System.out.println(Arrays.toString(solution.movesToStamp("k", "kkkkkkkkkkkkkkk")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 1000;
        public static final List<List<Integer>> ADJACENCY_TABLE = new ArrayList<>();
        public static final int[] INDEGREE = new int[MAX];
        public static final int[] DEQUE = new int[MAX];
        public static int l, r;

        public static void build(String stamp, String target) {
            ADJACENCY_TABLE.clear();
            for (int i = 0, stampCount = stamp.length(); i < stampCount; i++) {
                INDEGREE[i] = 0;
                DEQUE[i] = 0;
            }
            for (int i = 0; i < target.length(); i++) {
                ADJACENCY_TABLE.add(new ArrayList<>());
            }
            l = r = 0;
        }

        public int[] movesToStamp(String stamp, String target) {
            build(stamp, target);
            int count = target.length() - stamp.length() + 1;
            int stampLength = stamp.length();
            for (int i = 0, wrong; i < count; i++) {
                wrong = 0;
                for (int j = 0, tp; j < stampLength; j++) {
                    tp = i + j;
                    if (stamp.charAt(j) != target.charAt(tp)) {
                        wrong++;
                        ADJACENCY_TABLE.get(tp).add(i);
                    }
                }
                if ((INDEGREE[i] = wrong) == 0) {
                    DEQUE[r++] = i;
                }
            }

            boolean[] visited = new boolean[target.length()];
            int times = 0, maxTimes = 10 * target.length();
            while (l < r && times < maxTimes) {
                int p = DEQUE[l++];
                for (int i = p, size = p + stampLength; i < size; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        for (int s : ADJACENCY_TABLE.get(i)) {
                            if (--INDEGREE[s] == 0) {
                                DEQUE[r++] = s;
                            }
                        }
                    }
                }
                ++times;
            }
            if (times > maxTimes || times != count) {
                return new int[0];
            }
            int[] ans = new int[times];
            // 0 1 2 3 4
            // 5
            // 4 3 2 1 0
            for (int i = times - 1; i >= 0; i--) {
                ans[i] = DEQUE[times - i - 1];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}