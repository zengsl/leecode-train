//Given an array of integers nums and an integer limit, return the size of the 
//longest non-empty subarray such that the absolute difference between any two 
//elements of this subarray is less than or equal to limit. 
//
// 
// Example 1: 
//
// 
//Input: nums = [8,2,4,7], limit = 4
//Output: 2 
//Explanation: All subarrays are: 
//[8] with maximum absolute diff |8-8| = 0 <= 4.
//[8,2] with maximum absolute diff |8-2| = 6 > 4. 
//[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//[2] with maximum absolute diff |2-2| = 0 <= 4.
//[2,4] with maximum absolute diff |2-4| = 2 <= 4.
//[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//[4] with maximum absolute diff |4-4| = 0 <= 4.
//[4,7] with maximum absolute diff |4-7| = 3 <= 4.
//[7] with maximum absolute diff |7-7| = 0 <= 4. 
//Therefore, the size of the longest subarray is 2.
// 
//
// Example 2: 
//
// 
//Input: nums = [10,1,2,4,7,2], limit = 5
//Output: 4 
//Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute 
//diff is |2-7| = 5 <= 5.
// 
//
// Example 3: 
//
// 
//Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 0 <= limit <= 10⁹ 
// 
//
// Related Topics 队列 数组 有序集合 滑动窗口 单调队列 堆（优先队列） 👍 445 👎 0


package com.leetcode.editor.cn;

/**
 * [1438]Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
        System.out.println(solution.longestSubarray(new int[]{8, 2, 4, 7}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static int MAX = 100001;
        public static final int[] MAX_DE_QUEUE = new int[MAX];
        public static int maxHead, maxTail;

        public static final int[] MIN_DE_QUEUE = new int[MAX];
        public static int minHead, minTail;

        public static int[] arr;

        public int longestSubarray(int[] nums, int limit) {
            maxHead = maxTail = 0;
            minHead = minTail = 0;
            arr = nums;
            int ans = 0;
            // 0 1 2 3 4 5 6
            int length = nums.length;
            for (int l = 0, r = 0; l < length; l++) {
                // 不断增加数，扩大子数组
                while (r < length && isNextOk(r, limit)) {
                    push(r++);
                }
                ans = Math.max(ans, r - l);
                pop(l);
            }
            return ans;
        }

        private void pop(int i) {
            if (i == MAX_DE_QUEUE[maxHead]) {
                maxHead++;
            }

            if (i == MIN_DE_QUEUE[minHead]) {
                minHead++;
            }
        }

        private void push(int i) {
            while (maxTail > maxHead && arr[i] >= arr[MAX_DE_QUEUE[maxTail - 1]]) {
                maxTail--;
            }
            MAX_DE_QUEUE[maxTail++] = i;

            while (minTail > minHead && arr[i] <= arr[MIN_DE_QUEUE[minTail - 1]]) {
                minTail--;
            }
            MIN_DE_QUEUE[minTail++] = i;
        }

        // 假如添加下一个数，是否满足要求？
        private boolean isNextOk(int i, int limit) {
            int max = maxTail == maxHead ? arr[i] : Math.max(arr[i], arr[MAX_DE_QUEUE[maxHead]]);
            int min = minTail == minHead ? arr[i] : Math.min(arr[i], arr[MIN_DE_QUEUE[minHead]]);
            return max - min <= limit;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}