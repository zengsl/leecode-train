//English description is not available for the problem. Please switch to 
//Chinese.
// Related Topics 图 最短路 堆（优先队列） 👍 54 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [LCP 35]电动车游城市
 */
public class DFPeFJ {
    public static void main(String[] args) {
        Solution solution = new DFPeFJ().new Solution();
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
            int[][] costs = new int[n][cnt + 1];

            // dest, elect
            List<List<int[]>> adjacencyTable = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacencyTable.add(new ArrayList<>());
//                Arrays.fill(costs[i], Integer.MAX_VALUE);
                for (int j = 0; j <= cnt; j++) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }
            /*for (int i = 0; i < n; i++) {
                for (int j = 0; j <= cnt; j++) {
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }*/
            for (int[] path : paths) {
                adjacencyTable.get(path[0]).add(new int[]{path[1], path[2]});
                adjacencyTable.get(path[1]).add(new int[]{path[0], path[2]});
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
                for (int[] next : adjacencyTable.get(dest)) {
                    // 能够到下一个城市
                    int nextDest = next[0];
                    int nextCost = next[1];
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

        public static void init(int[][] paths) {
            /*Arrays.fill(HEAD, 0);
            cnt = 0;
            for (int[] path : paths) {
                NEXT[++cnt] = HEAD[path[0]];
                HEAD[path[0]] = NEXT[cnt];
                POINT[cnt] = path[1];
                WEIGHT[cnt] = path[2];
            }*/
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}