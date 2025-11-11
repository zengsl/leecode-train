//English description is not available for the problem. Please switch to 
//Chinese. 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 数组 字符串 👍 176 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [LCR 114]火星词典
 */
public class Jf1JuT {
    public static void main(String[] args) {
        Solution solution = new Jf1JuT().new Solution();
        System.out.println(solution.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 26;
        public static final List<List<Integer>> ADJACENCY_TABLE = new ArrayList<>();
        public static final int[] INDEGREE = new int[MAX];
        public static final int[] DEQUE = new int[MAX];
        public static int l, r;

        public static void build(String[] words) {
            Arrays.fill(INDEGREE, -1);
            for (String word : words) {
                for (int i = 0, size = word.length(); i < size; i++) {
                    INDEGREE[word.charAt(i) - 'a'] = 0;
                }
            }
            ADJACENCY_TABLE.clear();
            for (int i = 0; i < 26; i++) {
                ADJACENCY_TABLE.add(new ArrayList<>());
            }
            l = r = 0;
        }

        public static void addEdge(char from, char to) {
            ADJACENCY_TABLE.get(from - 'a').add(to - 'a');
            INDEGREE[to - 'a']++;
        }

        public String alienOrder(String[] words) {
            build(words);
            for (int i = 0, j = 1, size = words.length; i < size - 1; i++, j++) {
                char[] prev = words[i].toCharArray();
                char[] next = words[j].toCharArray();
                int min = Math.min(prev.length, next.length);
                int p = 0;
                // detect first different char
                while (p < min && prev[p] == next[p]) {
                    p++;
                }
                if (p == min && prev.length > next.length) {
                    return "";
                }

                if (p == min) {
                    continue;
                }

                addEdge(prev[p], next[p]);
            }

            //
            int kind = 0;
            // init queue
            for (int i = 0; i < MAX; i++) {
                if (INDEGREE[i] != -1) {
                    kind++;
                    if (INDEGREE[i] == 0) {
                        DEQUE[r++] = i;
                    }
                }

            }

            StringBuilder sb = new StringBuilder();
            while (l < r) {
                int from = DEQUE[l++];
                sb.append((char) (from + 'a'));
                for (int to : ADJACENCY_TABLE.get(from)) {
                    if (--INDEGREE[to] == 0) {
                        DEQUE[r++] = to;
                    }
                }
            }
            return sb.length() == kind ? sb.toString() : "";
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}