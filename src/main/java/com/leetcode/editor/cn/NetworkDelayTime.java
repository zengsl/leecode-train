//You are given a network of n nodes, labeled from 1 to n. You are also given 
//times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui 
//is the source node, vi is the target node, and wi is the time it takes for a 
//signal to travel from source to target. 
//
// We will send a signal from a given node k. Return the minimum time it takes 
//for all the n nodes to receive the signal. If it is impossible for all the n 
//nodes to receive the signal, return -1. 
//
// 
// Example 1: 
// 
// 
//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: times = [[1,2,1]], n = 2, k = 1
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: times = [[1,2,1]], n = 2, k = 2
//Output: -1
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// All the pairs (ui, vi) are unique. (i.e., no multiple edges.) 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 👍 887 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * [743]Network Delay Time
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        Solution solution = new NetworkDelayTime().new Solution();
//        System.out.println(solution.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(solution.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            int[] distance = new int[n + 1];
            List<List<int[]>> adjacencyTable = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjacencyTable.add(new ArrayList<>());
                distance[i] = Integer.MAX_VALUE;
            }
            for (int[] time : times) {
                adjacencyTable.get(time[0]).add(new int[]{time[1], time[2]});
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            distance[k] = 0;
            heap.add(new int[]{k, 0});
            boolean[] visited = new boolean[n + 1];
            while (!heap.isEmpty()) {
                int[] cur = heap.poll();
                if (visited[cur[0]]) {
                    continue;
                }
                visited[cur[0]] = true;
                for (int[] next : adjacencyTable.get(cur[0])) {
                    if (!visited[next[0]] && distance[next[0]] > next[1] + distance[cur[0]]) {
                        heap.add(new int[]{next[0], next[1] + distance[cur[0]]});
                        distance[next[0]] = next[1] + distance[cur[0]];
                    }
                }
            }

            int ans = -1;
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                ans = Math.max(ans, distance[i]);
            }

            return  ans;
        }

        /*public int networkDelayTime(int[][] times, int n, int k) {
            int[] distance = new int[n + 1];
            List<List<int[]>> adjacencyTable = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjacencyTable.add(new ArrayList<>());
                distance[i] = Integer.MAX_VALUE;
            }
            for (int[] time : times) {
                adjacencyTable.get(time[0]).add(new int[]{time[1], time[2]});
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            int ans = -1;
            int cnt = 0;
            distance[k] = 0;
            heap.add(new int[]{k, 0});
            boolean[] visited = new boolean[n + 1];
            while (!heap.isEmpty()) {
                int[] cur = heap.poll();
                if (visited[cur[0]]) {
                    continue;
                }
                cnt++;
                visited[cur[0]] = true;
                ans = cur[1];

                for (int[] next : adjacencyTable.get(cur[0])) {
                    if (!visited[next[0]] && distance[next[0]] > next[1] + distance[cur[0]]) {
                        heap.add(new int[]{next[0], next[1] + distance[cur[0]]});
                        distance[next[0]] = next[1] + distance[cur[0]];
                    }
                }
            }
            return n == cnt ? ans : -1;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}