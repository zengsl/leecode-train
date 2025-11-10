//On a 2D plane, we place n stones at some integer coordinate points. Each 
//coordinate point may have at most one stone. 
//
// A stone can be removed if it shares either the same row or the same column 
//as another stone that has not been removed. 
//
// Given an array stones of length n where stones[i] = [xi, yi] represents the 
//location of the iᵗʰ stone, return the largest possible number of stones that can 
//be removed. 
//
// 
// Example 1: 
//
// 
//Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//Output: 5
//Explanation: One way to remove 5 stones is as follows:
//1. Remove stone [2,2] because it shares the same row as [2,1].
//2. Remove stone [2,1] because it shares the same column as [0,1].
//3. Remove stone [1,2] because it shares the same row as [1,0].
//4. Remove stone [1,0] because it shares the same column as [0,0].
//5. Remove stone [0,1] because it shares the same row as [0,0].
//Stone [0,0] cannot be removed since it does not share a row/column with 
//another stone still on the plane.
// 
//
// Example 2: 
//
// 
//Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//Output: 3
//Explanation: One way to make 3 moves is as follows:
//1. Remove stone [2,2] because it shares the same row as [2,0].
//2. Remove stone [2,0] because it shares the same column as [0,0].
//3. Remove stone [0,2] because it shares the same row as [0,0].
//Stones [0,0] and [1,1] cannot be removed since they do not share a row/column 
//with another stone still on the plane.
// 
//
// Example 3: 
//
// 
//Input: stones = [[0,0]]
//Output: 0
//Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
// 
//
// 
// Constraints: 
//
// 
// 1 <= stones.length <= 1000 
// 0 <= xi, yi <= 10⁴ 
// No two stones are at the same coordinate point. 
// 
//
// Related Topics 深度优先搜索 并查集 图 哈希表 👍 384 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [947]Most Stones Removed with Same Row or Column
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        Solution solution = new MostStonesRemovedWithSameRowOrColumn().new Solution();
        System.out.println(solution.removeStones(new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        }));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public final static int MAX = 1001;
        public final static int[] FATHER = new int[MAX];
        public final static Map<Integer, Integer> ROW_FIRST = new HashMap<>();
        public final static Map<Integer, Integer> COL_FIRST = new HashMap<>();
        public static int sets;

        public int removeStones(int[][] stones) {
            int n = stones.length;
            build(stones);
            for (int i = 0, row, col; i < n; i++) {
                row = stones[i][0];
                if (ROW_FIRST.containsKey(row)) {
                    union(i, ROW_FIRST.get(row));
                } else {
                    ROW_FIRST.put(row, i);
                }

                col = stones[i][1];
                if (COL_FIRST.containsKey(col)) {
                    union(i, COL_FIRST.get(col));
                } else {
                    COL_FIRST.put(col, i);
                }
            }
            return n - sets;
        }

        public static void build(int[][] stones) {
            ROW_FIRST.clear();
            COL_FIRST.clear();
            int n = stones.length;
            for (int i = 0; i < n; i++) {
                FATHER[i] = i;
            }
            sets = n;
        }

        public static int find(int i) {
            if (i != FATHER[i]) {
                i = find(FATHER[i]);
            }
            return i;
        }

        public static void union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a != b) {
                FATHER[a] = b;
                sets--;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}