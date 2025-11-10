//There is a tree (i.e. a connected, undirected graph with no cycles) 
//consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. 
//
// You are given a 0-indexed integer array vals of length n where vals[i] 
//denotes the value of the iᵗʰ node. You are also given a 2D integer array edges where 
//edges[i] = [ai, bi] denotes that there exists an undirected edge connecting 
//nodes ai and bi. 
//
// A good path is a simple path that satisfies the following conditions: 
//
// 
// The starting node and the ending node have the same value. 
// All nodes between the starting node and the ending node have values less 
//than or equal to the starting node (i.e. the starting node's value should be the 
//maximum value along the path). 
// 
//
// Return the number of distinct good paths. 
//
// Note that a path and its reverse are counted as the same path. For example, 0
// -> 1 is considered to be the same as 1 -> 0. A single node is also considered 
//as a valid path. 
//
// 
// Example 1: 
// 
// 
//Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
//Output: 6
//Explanation: There are 5 good paths consisting of a single node.
//There is 1 additional good path: 1 -> 0 -> 2 -> 4.
//(The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.
//)
//Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
// 
//
// Example 2: 
// 
// 
//Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
//Output: 7
//Explanation: There are 5 good paths consisting of a single node.
//There are 2 additional good paths: 0 -> 1 and 2 -> 3.
// 
//
// Example 3: 
// 
// 
//Input: vals = [1], edges = []
//Output: 1
//Explanation: The tree consists of only one node, so there is one good path.
// 
//
// 
// Constraints: 
//
// 
// n == vals.length 
// 1 <= n <= 3 * 10⁴ 
// 0 <= vals[i] <= 10⁵ 
// edges.length == n - 1 
// edges[i].length == 2 
// 0 <= ai, bi < n 
// ai != bi 
// edges represents a valid tree. 
// 
//
// Related Topics 树 并查集 图 数组 哈希表 排序 👍 112 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [2421]Number of Good Paths
 */
public class NumberOfGoodPaths {
    public static void main(String[] args) {
        Solution solution = new NumberOfGoodPaths().new Solution();
        //System.out.println(solution.numberOfGoodPaths(new int[]{1}, new int[][]{}));
        System.out.println(solution.numberOfGoodPaths(new int[]{1, 3, 2, 1, 3}, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MAX = 30001;
        public static final int[] FATHER = new int[MAX];
        public static final int[] MAX_SIZE = new int[MAX];
        public static int[] VALUES;

        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            build(vals);
            Arrays.sort(edges, Comparator.comparingInt(a -> Math.max(vals[a[0]], vals[a[1]])));
            int ans = vals.length;
            for (int[] edge : edges) {
                ans += union(edge[0], edge[1]);
            }
            return ans;
        }

        public static void build(int[] vals) {
            VALUES = vals;
            int n = vals.length;
            for (int i = 0; i < n; i++) {
                FATHER[i] = i;
                MAX_SIZE[i] = 1;
            }
        }

        public static int find(int n) {
            if (n != FATHER[n]) {
                FATHER[n] = find(FATHER[n]);
            }
            return FATHER[n];
        }

        public static int union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa == fb) {
                return 0;
            }

            int ans = 0;
            if (VALUES[fa] < VALUES[fb]) {
                FATHER[fa] = fb;
            } else if (VALUES[fa] > VALUES[fb]) {
                FATHER[fb] = fa;
            } else {
                ans = MAX_SIZE[fa] * MAX_SIZE[fb];
                FATHER[fb] = fa;
                MAX_SIZE[fa] += MAX_SIZE[fb];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}