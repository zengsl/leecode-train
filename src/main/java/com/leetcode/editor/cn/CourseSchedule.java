//There are a total of numCourses courses you have to take, labeled from 0 to 
//numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
// bi] indicates that you must take course bi first if you want to take course ai.
// 
//
// 
// For example, the pair [0, 1], indicates that to take course 0 you have to 
//first take course 1. 
// 
//
// Return true if you can finish all courses. Otherwise, return false. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0. So it is possible.
// 
//
// Example 2: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0, and to take course 0 you 
//should also have finished course 1. So it is impossible.
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// All the pairs prerequisites[i] are unique. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 2354 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 *
 * [207]Course Schedule
 *
 */
public class CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new CourseSchedule().new Solution();
        System.out.println(solution.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private final int[] head = new int[2001];
        private final int[] next = new int[5001];
        private final int[] to = new int[5001];
        int seq;

        private final int[] deque = new int[2001];
        private int h, t;

        private void addEdge(int from, int t) {
            next[++seq] = head[from];
            head[from] = seq;
            to[seq] = t;
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] indegree = new int[numCourses];
            seq = 0;
            Arrays.fill(head, -1);
            Arrays.fill(next, -1);
            for (int[] prerequisite : prerequisites) {
                addEdge(prerequisite[1], prerequisite[0]);
                // 入度增加
                indegree[prerequisite[0]]++;
            }

            h = t = 0;
            int learned = 0;
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    deque[t++] = i;
                    learned++;
                }
            }

            while (h < t) {
                int size = t - h;
                while (size-- > 0) {
                    int top = deque[h++];
                    for (int n = head[top]; n != -1; n = next[n]) {
                        // 入度增加
                        if (--indegree[to[n]] == 0) {
                            deque[t++] = to[n];
                            learned++;
                        }
                    }
                }
            }

            return learned == numCourses;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}