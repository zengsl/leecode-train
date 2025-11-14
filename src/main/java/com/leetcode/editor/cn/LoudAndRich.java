//There is a group of n people labeled from 0 to n - 1 where each person has a 
//different amount of money and a different level of quietness. 
//
// You are given an array richer where richer[i] = [ai, bi] indicates that ai 
//has more money than bi and an integer array quiet where quiet[i] is the quietness 
//of the iᵗʰ person. All the given data in richer are logically correct (i.e., 
//the data will not lead you to a situation where x is richer than y and y is richer 
//than x at the same time). 
//
// Return an integer array answer where answer[x] = y if y is the least quiet 
//person (that is, the person y with the smallest value of quiet[y]) among all 
//people who definitely have equal to or more money than the person x. 
//
// 
// Example 1: 
//
// 
//Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,
//6,1,7,0]
//Output: [5,5,2,5,4,5,6,7]
//Explanation: 
//answer[0] = 5.
//Person 5 has more money than 3, which has more money than 1, which has more 
//money than 0.
//The only person who is quieter (has lower quiet[x]) is person 7, but it is 
//not clear if they have more money than person 0.
//answer[7] = 7.
//Among all people that definitely have equal to or more money than person 7 (
//which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has 
//lower quiet[x]) is person 7.
//The other answers can be filled out with similar reasoning.
// 
//
// Example 2: 
//
// 
//Input: richer = [], quiet = [0]
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// n == quiet.length 
// 1 <= n <= 500 
// 0 <= quiet[i] < n 
// All the values of quiet are unique. 
// 0 <= richer.length <= n * (n - 1) / 2 
// 0 <= ai, bi < n 
// ai != bi 
// All the pairs of richer are unique. 
// The observations in richer are all logically consistent. 
// 
//
// Related Topics 深度优先搜索 图 拓扑排序 数组 👍 271 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [851]Loud and Rich
 */
public class LoudAndRich {
    public static void main(String[] args) {
        Solution solution = new LoudAndRich().new Solution();
        System.out.println(Arrays.toString(solution.loudAndRich(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}}
                , new int[]{
                        3, 2, 5, 4, 6, 1, 7, 0
                })));
        System.out.println("[5, 5, 2, 5, 4, 5, 6, 7]");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 500;
        public static final List<List<Integer>> ADJACENCY_TABLE = new ArrayList<>();
        public static final int[] INDEGREE = new int[MAX];

        public int[] loudAndRich(int[][] richer, int[] quiet) {
            int n = quiet.length;
            init(n);

            for (int[] res : richer) {
                ADJACENCY_TABLE.get(res[0]).add(res[1]);
                INDEGREE[res[1]]++;
            }
            int[] deque = new int[n];
            int l = 0, r = 0;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                if (INDEGREE[i] == 0) {
                    deque[r++] = i;
                }
                ans[i] = i;
            }

            while (l < r) {
                int p = deque[l++];
                for (int to : ADJACENCY_TABLE.get(p)) {
                    /*if (quiet[to] > quiet[p]) {
                        ans[to] = ans[p];
                    }
                    quiet[to] = Math.min(quiet[to], quiet[p]);*/
                    if (quiet[ans[to]] > quiet[ans[p]]) {
                        ans[to] = ans[p];
                    }
                    if (--INDEGREE[to] == 0) {
                        deque[r++] = to;
                    }
                }
            }
            return ans;
        }

        public static void init(int n) {
            ADJACENCY_TABLE.clear();
            for (int i = 0; i < n; i++) {
                ADJACENCY_TABLE.add(new ArrayList<>());
                INDEGREE[i] = 0;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}