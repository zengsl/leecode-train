//You are given an array of integers nums, there is a sliding window of size k
//which is moving from the very left of the array to the very right. You can only 
//see the k numbers in the window. Each time the sliding window moves right by one 
//position. 
//
// Return the max sliding window. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
//Output: [3,3,5,5,6,7]
//Explanation: 
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// Example 2: 
//
// 
//Input: nums = [1], k = 1
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 3293 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [239]Sliding Window Maximum
 */
public class SlidingWindowMaximum2 {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum2().new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private static final int[] QUEUE = new int[100001];
        private static int head, tail;

        public int[] maxSlidingWindow(int[] nums, int k) {
            head = tail = 0;
            Arrays.fill(QUEUE, 0);

            int len = nums.length;
            int[] ans = new int[len - k + 1];
            int idx = 0;
            while (idx < len) {
                while (head < tail && tail > 0 && nums[QUEUE[tail - 1]] <= nums[idx]) {
                    tail--;
                }
                QUEUE[tail++] = idx;

                if (idx > k - 1 && QUEUE[head] == (idx - k)) {
                    head++;
                }

                if (idx >= k - 1) {
                    ans[idx - k + 1] = nums[QUEUE[head]];
                }
                idx++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}