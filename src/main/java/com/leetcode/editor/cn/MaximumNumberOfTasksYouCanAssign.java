//You have n tasks and m workers. Each task has a strength requirement stored 
//in a 0-indexed integer array tasks, with the iᵗʰ task requiring tasks[i] strength 
//to complete. The strength of each worker is stored in a 0-indexed integer array 
//workers, with the jᵗʰ worker having workers[j] strength. Each worker can only 
//be assigned to a single task and must have a strength greater than or equal to 
//the task's strength requirement (i.e., workers[j] >= tasks[i]). 
//
// Additionally, you have pills magical pills that will increase a worker's 
//strength by strength. You can decide which workers receive the magical pills, 
//however, you may only give each worker at most one magical pill. 
//
// Given the 0-indexed integer arrays tasks and workers and the integers pills 
//and strength, return the maximum number of tasks that can be completed. 
//
// 
// Example 1: 
//
// 
//Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
//Output: 3
//Explanation:
//We can assign the magical pill and tasks as follows:
//- Give the magical pill to worker 0.
//- Assign worker 0 to task 2 (0 + 1 >= 1)
//- Assign worker 1 to task 1 (3 >= 2)
//- Assign worker 2 to task 0 (3 >= 3)
// 
//
// Example 2: 
//
// 
//Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
//Output: 1
//Explanation:
//We can assign the magical pill and tasks as follows:
//- Give the magical pill to worker 0.
//- Assign worker 0 to task 0 (0 + 5 >= 5)
// 
//
// Example 3: 
//
// 
//Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
//
//Output: 2
//Explanation:
//We can assign the magical pills and tasks as follows:
//- Give the magical pill to worker 0 and worker 1.
//- Assign worker 0 to task 0 (0 + 10 >= 10)
//- Assign worker 1 to task 1 (10 + 10 >= 15)
//The last pill is not given because it will not make any worker strong enough 
//for the last task.
// 
//
// 
// Constraints: 
//
// 
// n == tasks.length 
// m == workers.length 
// 1 <= n, m <= 5 * 10⁴ 
// 0 <= pills <= m 
// 0 <= tasks[i], workers[j], strength <= 10⁹ 
// 
//
// Related Topics 贪心 队列 数组 双指针 二分查找 排序 单调队列 👍 168 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [2071]Maximum Number of Tasks You Can Assign
 */
public class MaximumNumberOfTasksYouCanAssign {
    public static void main(String[] args) {
        Solution solution = new MaximumNumberOfTasksYouCanAssign().new Solution();
//        System.out.println(solution.maxTaskAssign(new int[]{5, 9, 8, 5, 9}, new int[]{1, 6, 4, 2, 6}, 1, 5));
        System.out.println(solution.maxTaskAssign(new int[]{10, 15, 30}, new int[]{0, 10, 10, 10, 10}, 3, 10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static int MAX = 5_0001;
        public static int h, t = 5_0001;
        public static int[] DE_QUE = new int[MAX];
        public static int[] tasks;
        public static int ts;

        public static int[] workers;
        public static int ws;

        public int maxTaskAssign(int[] inTasks, int[] inWorkers, int pills, int strength) {
            h = t = 0;
            tasks = inTasks;
            workers = inWorkers;
            ts = tasks.length;
            ws = workers.length;

            Arrays.sort(tasks);
            Arrays.sort(workers);

            int ans = 0;
            int l = 0, r = Math.min(tasks.length, workers.length), mid = 0;
            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (f(0, mid, ws - mid, ws, pills, strength)) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }

        public boolean f(int ts, int te, int ws, int we, int pills, int strength) {
            h = t = 0;
            int ti = ts, eat = 0;
            for (int wi = ws; wi < we; wi++) {
                // 增加一些任务
                while (ti < te && workers[wi] >= tasks[ti]) {
                    DE_QUE[t++] = ti++;
                }

                if (h != t && workers[wi] >= tasks[DE_QUE[h]]) {
                    h++;
                } else {
                    // 吃药后尝试添加一些任务
                    while (ti < te && workers[wi] + strength >= tasks[ti]) {
                        DE_QUE[t++] = ti++;
                    }

                    if (h != t) {
                        ++eat;
                        t--;
                    } else {
                        return false;
                    }
                }
            }

            return eat <= pills;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}