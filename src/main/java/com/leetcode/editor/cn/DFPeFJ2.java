//English description is not available for the problem. Please switch to 
//Chinese.
// Related Topics 图 最短路 堆（优先队列） 👍 54 👎 0


package com.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [LCP 35]电动车游城市
 */
public class DFPeFJ2 {
    public static void main(String[] args) {
        Solution solution = new DFPeFJ2().new Solution();
        System.out.println(solution.electricCarPlan(new int[][]{{3, 6, 9}, {0, 7, 24}, {5, 3, 27}, {7, 6, 1}, {1, 2, 41}, {3, 6, 28}, {2, 3, 30}, {5, 0, 41}, {0, 3, 13}, {6, 4, 4}, {3, 5, 20}, {0, 5, 22}, {0, 1, 6}, {7, 5, 11}, {5, 6, 17}, {0, 6, 22}, {1, 6, 32}, {2, 4, 25}, {0, 7, 34}, {0, 4, 36}, {3, 0, 25}}
                , 43, 4, 3, new int[]{34, 15, 30, 64, 67, 11, 33, 98}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        public static final List<List<int[]>> ADJACENCY_TABLE = new ArrayList<>();

        public static final int MAX_PATH = 200;
        /*public static final int[] HEAD = new int[MAX_PATH];
        public static final int[] NEXT = new int[MAX_PATH];
        public static final int[] POINT = new int[MAX_PATH];
        public static final int[] WEIGHT = new int[MAX_PATH];
        public static int cnt;*/

        public int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
            int n = charge.length;
            int m = paths.length;

            // dest, elect
            int edgeCount = (m + 1) << 1;
            int[] starHead = new int[n];
            int[] starNext = new int[edgeCount];
            int[] starTo = new int[edgeCount];
            int[] starWeight = new int[edgeCount];
            int id = 0;

            int[][] costs = new int[n][cnt + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= cnt; j++) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int[] path : paths) {
                starNext[++id] = starHead[path[0]];
                starHead[path[0]] = id;
                starTo[id] = path[1];
                starWeight[id] = path[2];

                starNext[++id] = starHead[path[1]];
                starHead[path[1]] = id;
                starTo[id] = path[0];
                starWeight[id] = path[2];
            }

            boolean[][] visited = new boolean[n][cnt + 1];
            // b, electric, cost
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
            heap.add(new int[]{start, 0, 0});
            while (!heap.isEmpty()) {
                int[] curr = heap.poll();
                int dest = curr[0];
                int elect = curr[1];
                int cost = curr[2];
                if (visited[dest][elect]) {
                    continue;
                }
                if (dest == end) {
                    return cost;
                }
                visited[dest][elect] = true;
                // 充一格电
                if (elect < cnt && !visited[dest][elect + 1] && costs[dest][elect + 1] > cost + charge[dest]) {
                    costs[dest][elect + 1] = cost + charge[dest];
                    heap.add(new int[]{dest, elect + 1, cost + charge[dest]});
                }
                // 不充电往下走
                for (int nextEdge = starHead[dest]; nextEdge > 0; nextEdge = starNext[nextEdge]) {
                    // 能够到下一个城市
                    int nextDest = starTo[nextEdge];
                    int nextCost = starWeight[nextEdge];
                    // 够电量去下一个城市 && 下一个城市状态没有处理过 && 下一个城市状态的花费能减少
                    int newElect = elect - nextCost;
                    if (elect >= nextCost && !visited[nextDest][newElect] && costs[nextDest][newElect] > cost + nextCost) {
                        // 更新花费
                        costs[nextDest][newElect] = cost + nextCost;
                        heap.add(new int[]{nextDest, newElect, cost + nextCost});
                    }
                }
            }
            return -1;
        }

        public static void addEdge(int from, int to, int c) {

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}