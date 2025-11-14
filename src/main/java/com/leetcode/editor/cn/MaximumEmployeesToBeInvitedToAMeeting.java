//A company is organizing a meeting and has a list of n employees, waiting to 
//be invited. They have arranged for a large circular table, capable of seating any 
//number of employees. 
//
// The employees are numbered from 0 to n - 1. Each employee has a favorite 
//person and they will attend the meeting only if they can sit next to their favorite 
//person at the table. The favorite person of an employee is not themself. 
//
// Given a 0-indexed integer array favorite, where favorite[i] denotes the 
//favorite person of the iᵗʰ employee, return the maximum number of employees that can 
//be invited to the meeting. 
//
// 
// Example 1: 
// 
// 
//Input: favorite = [2,2,1,2]
//Output: 3
//Explanation:
//The above figure shows how the company can invite employees 0, 1, and 2, and 
//seat them at the round table.
//All employees cannot be invited because employee 2 cannot sit beside 
//employees 0, 1, and 3, simultaneously.
//Note that the company can also invite employees 1, 2, and 3, and give them 
//their desired seats.
//The maximum number of employees that can be invited to the meeting is 3. 
// 
//
// Example 2: 
//
// 
//Input: favorite = [1,2,0]
//Output: 3
//Explanation: 
//Each employee is the favorite person of at least one other employee, and the 
//only way the company can invite them is if they invite every employee.
//The seating arrangement will be the same as that in the figure given in 
//example 1:
//- Employee 0 will sit between employees 2 and 1.
//- Employee 1 will sit between employees 0 and 2.
//- Employee 2 will sit between employees 1 and 0.
//The maximum number of employees that can be invited to the meeting is 3.
// 
//
// Example 3: 
// 
// 
//Input: favorite = [3,0,1,4,1]
//Output: 4
//Explanation:
//The above figure shows how the company will invite employees 0, 1, 3, and 4, 
//and seat them at the round table.
//Employee 2 cannot be invited because the two spots next to their favorite 
//employee 1 are taken.
//So the company leaves them out of the meeting.
//The maximum number of employees that can be invited to the meeting is 4.
// 
//
// 
// Constraints: 
//
// 
// n == favorite.length 
// 2 <= n <= 10⁵ 
// 0 <= favorite[i] <= n - 1 
// favorite[i] != i 
// 
//
// Related Topics 深度优先搜索 图 拓扑排序 👍 246 👎 0


package com.leetcode.editor.cn;

/**
 * [2127]Maximum Employees to Be Invited to a Meeting
 */
public class MaximumEmployeesToBeInvitedToAMeeting {
    public static void main(String[] args) {
        Solution solution = new MaximumEmployeesToBeInvitedToAMeeting().new Solution();
//        System.out.println(solution.maximumInvitations(new int[]{2, 2, 1, 2}));
        System.out.println(solution.maximumInvitations(new int[]{1, 0, 3, 2, 5, 6, 7, 4, 9, 8, 11, 10, 11, 12, 10}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maximumInvitations(int[] favorites) {
            int length = favorites.length;
            int[] indegree = new int[length];
            int[] deque = new int[length];
            int l = 0, r = 0;
            int[] maxDeep = new int[length];
            for (int favorite : favorites) {
                indegree[favorite]++;
            }

            for (int i = 0; i < length; i++) {
                if (indegree[i] == 0) {
                    deque[r++] = i;
                }
            }

            while (l < r) {
                int from = deque[l++];
                int to = favorites[from];
                maxDeep[to] = Math.max(maxDeep[to], maxDeep[from] + 1);
                if (--indegree[to] == 0) {
                    deque[r++] = to;
                }
            }

            int smallRingCount = 0, bigRing = 0;
            for (int p = 0; p < length; p++) {
                // 环
                if (indegree[p] > 0) {
                    indegree[p] = 0;
                    int ringSize = 1;
                    for (int j = favorites[p]; p != j; j = favorites[j]) {
                        indegree[j] = 0;
                        ringSize++;
                    }
                    if (ringSize == 2) {
                        smallRingCount += maxDeep[p] + maxDeep[favorites[p]] + 2;
                    } else {
                        bigRing = Math.max(bigRing, ringSize);
                    }
                }
            }
            return Math.max(smallRingCount, bigRing);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}