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
// Return the ordering of courses you should take to finish all courses. If 
//there are many valid answers, return any of them. If it is impossible to finish all 
//courses, return an empty array. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you 
//should have finished course 0. So the correct course order is [0,1].
// 
//
// Example 2: 
//
// 
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you 
//should have finished both courses 1 and 2. Both courses 1 and 2 should be taken 
//after you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3
//].
// 
//
// Example 3: 
//
// 
//Input: numCourses = 1, prerequisites = []
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// All the pairs [ai, bi] are distinct. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1062 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [210]Course Schedule II
 */
public class CourseScheduleIi {
    public static void main(String[] args) {
        Solution solution = new CourseScheduleIi().new Solution();
//        System.out.println(Arrays.toString(solution.findOrder(2, new int[][]{{0, 1}, {1, 0}})));
//        System.out.println(Arrays.toString(solution.findOrder(2, new int[][]{{0, 1}})));
        System.out.println(Arrays.toString(solution.findOrder(3, new int[][]{{1, 0}, {1, 2}, {0, 1}})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 2001;
        public static final List<List<Integer>> ADJACENCY_LIST = new ArrayList<>();
        public static final int[] POINT_INDEGREE = new int[MAX];

        /*public static final int[] DEQUE = new int[MAX];
        public static int l, r;*/

        public static void build(int n) {
            ADJACENCY_LIST.clear();
            for (int i = 0; i < n; i++) {
                ADJACENCY_LIST.add(new ArrayList<>());
                POINT_INDEGREE[i] = 0;
            }
//            l = r = 0;
        }

        public static void createGraph(int[][] prerequisites) {
            for (int[] prerequisite : prerequisites) {
                POINT_INDEGREE[prerequisite[0]]++;
                ADJACENCY_LIST.get(prerequisite[1]).add(prerequisite[0]);
            }
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            build(numCourses);
            createGraph(prerequisites);
            int l = 0, r = 0;
            int[] deque = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if (POINT_INDEGREE[i] == 0) {
                    deque[r++] = i;
//                    DEQUE[r++] = i;
                }
            }
            List<Integer> result = new ArrayList<>();
            while (l < r) {
//                int from = DEQUE[l++];
                int from = deque[l++];
                result.add(from);
                for (int to : ADJACENCY_LIST.get(from)) {
                    if (--POINT_INDEGREE[to] == 0) {
//                        DEQUE[r++] = to;
                        deque[r++] = to;
                    }
                }
            }
//            return numCourses == result.size() ? result.stream().mapToInt(Integer::intValue).toArray() : new int[0];
            return numCourses == result.size() ? deque : new int[0];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}