//You have n computers. You are given the integer n and a 0-indexed integer 
//array batteries where the iᵗʰ battery can run a computer for batteries[i] minutes. 
//You are interested in running all n computers simultaneously using the given 
//batteries. 
//
// Initially, you can insert at most one battery into each computer. After that 
//and at any integer time moment, you can remove a battery from a computer and 
//insert another battery any number of times. The inserted battery can be a totally 
//new battery or a battery from another computer. You may assume that the removing 
//and inserting processes take no time. 
//
// Note that the batteries cannot be recharged. 
//
// Return the maximum number of minutes you can run all the n computers 
//simultaneously. 
//
// 
// Example 1: 
// 
// 
//Input: n = 2, batteries = [3,3,3]
//Output: 4
//Explanation: 
//Initially, insert battery 0 into the first computer and battery 1 into the 
//second computer.
//After two minutes, remove battery 1 from the second computer and insert 
//battery 2 instead. Note that battery 1 can still run for one minute.
//At the end of the third minute, battery 0 is drained, and you need to remove 
//it from the first computer and insert battery 1 instead.
//By the end of the fourth minute, battery 1 is also drained, and the first 
//computer is no longer running.
//We can run the two computers simultaneously for at most 4 minutes, so we 
//return 4.
// 
//
//
// Example 2: 
// 
// 
//Input: n = 2, batteries = [1,1,1,1]
//Output: 2
//Explanation: 
//Initially, insert battery 0 into the first computer and battery 2 into the 
//second computer. 
//After one minute, battery 0 and battery 2 are drained so you need to remove 
//them and insert battery 1 into the first computer and battery 3 into the second 
//computer. 
//After another minute, battery 1 and battery 3 are also drained so the first 
//and second computers are no longer running.
//We can run the two computers simultaneously for at most 2 minutes, so we 
//return 2.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= batteries.length <= 10⁵ 
// 1 <= batteries[i] <= 10⁹ 
// 
//
// Related Topics 贪心 数组 二分查找 排序 👍 110 👎 0


package com.leetcode.editor.cn;

/**
 * [2141]Maximum Running Time of N Computers
 */
public class MaximumRunningTimeOfNComputers {
    public static void main(String[] args) {
        Solution solution = new MaximumRunningTimeOfNComputers().new Solution();
        System.out.println(solution.maxRunTime(3, new int[]{10, 10, 3, 5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public long maxRunTime(int n, int[] batteries) {
            long sum = 0;
            int max = 0;
            for (int battery : batteries) {
                sum += battery;
                max = Math.max(max, battery);
            }

            // 总和大于等于max * n 说明电池特别多，每块电池都是碎片
            if ((long) max * n <= sum) {
                return sum / n;
            }

            // 总和小于max * n，最大值从max开始2分（原来从sum，减少了数据范围）
            long l = 0, r = max, mid, ans = 0;
            // detect all minutes
            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (!isSatisfy(batteries, mid, n)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                    ans = mid;
                }
            }
            return ans;
        }

        public long maxRunTime1(int n, int[] batteries) {
            long sum = 0;
            for (int battery : batteries) {
                sum += battery;
            }

            long l = 0, r = sum, mid, ans = 0;
            // detect all minutes
            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (!isSatisfy(batteries, mid, n)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                    ans = mid;
                }
            }
            return ans;
        }

        private boolean isSatisfy(int[] batteries, long minute, int n) {
            long chipCount = 0;
            for (int battery : batteries) {
                if (battery >= minute) {
                    n--;
                } else {
                    chipCount += battery;
                }
                if (chipCount >= n * minute) {
                    return true;
                }
            }
            return false;
        }

        private long satisfyCount(int[] batteries, long minute) {
            long count = 0;
            long chipCount = 0;
            for (int battery : batteries) {
                if (battery >= minute) {
                    count++;
                } else {
                    chipCount += battery;
                }
            }
            return count + (minute != 0 ? chipCount / minute : 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}