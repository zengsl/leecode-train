//An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [
//ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note 
//that there may be multiple edges between two nodes. 
//
// Given an array queries, where queries[j] = [pj, qj, limitj], your task is to 
//determine for each queries[j] whether there is a path between pj and qj such 
//that each edge on the path has a distance strictly less than limitj . 
//
// Return a boolean array answer, where answer.length == queries.length and the 
//jᵗʰ value of answer is true if there is a path for queries[j] is true, and 
//false otherwise. 
//
// 
// Example 1: 
// 
// 
//Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2
//],[0,2,5]]
//Output: [false,true]
//Explanation: The above figure shows the given graph. Note that there are two 
//overlapping edges between 0 and 1 with distances 2 and 16.
//For the first query, between 0 and 1 there is no path where each distance is 
//less than 2, thus we return false for this query.
//For the second query, there is a path (0 -> 1 -> 2) of two edges with 
//distances less than 5, thus we return true for this query.
// 
//
// Example 2: 
// 
// 
//Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,
//14],[1,4,13]]
//Output: [true,false]
//Explanation: The above figure shows the given graph.
// 
//
// 
// Constraints: 
//
// 
// 2 <= n <= 10⁵ 
// 1 <= edgeList.length, queries.length <= 10⁵ 
// edgeList[i].length == 3 
// queries[j].length == 3 
// 0 <= ui, vi, pj, qj <= n - 1 
// ui != vi 
// pj != qj 
// 1 <= disi, limitj <= 10⁹ 
// There may be multiple edges between two nodes. 
// 
//
// Related Topics 并查集 图 数组 双指针 排序 👍 197 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [1697]Checking Existence of Edge Length Limited Paths
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {
    public static void main(String[] args) {
        Solution solution = new CheckingExistenceOfEdgeLengthLimitedPaths().new Solution();
        System.out.println(Arrays.toString(solution.distanceLimitedPathsExist(3, new int[][]{{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}}, new int[][]{{0, 1, 2}, {0, 2, 5}})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            build(n);
            int k = queries.length;
            int[][] questions = new int[k][4];
            for (int i = 0; i < k; i++) {
                questions[i][0] = queries[i][0];
                questions[i][1] = queries[i][1];
                questions[i][2] = queries[i][2];
                questions[i][3] = i;
            }
            Arrays.sort(questions, Comparator.comparingInt(q -> q[2]));
            Arrays.sort(edgeList, Comparator.comparingInt(e -> e[2]));
            boolean[] ans = new boolean[k];

            for (int i = 0, j = 0, edgeCount = edgeList.length; i < k; i++) {
                for (; j < edgeCount && edgeList[j][2] < questions[i][2]; j++) {
                    union(edgeList[j][0], edgeList[j][1]);
                }
                ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);
            }
            return ans;
        }

        public static final int MAX = 100000;
        public static final int[] FATHER = new int[MAX];

        public static int find(int a) {
            if (a != FATHER[a]) {
                FATHER[a] = find(FATHER[a]);
            }
            return FATHER[a];
        }

        public static void union(int a, int b) {
            /*int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                FATHER[fa] = fb;
            }*/
            FATHER[find(a)] = find(b);
        }

        public static boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }

        public static void build(int n) {
            for (int i = 0; i < n; i++) {
                FATHER[i] = i;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

