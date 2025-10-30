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
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 100001;
        public static int[] QUEUE = new int[MAX];
        public static int head, tail;
        public static int l, r;
        public static int ql;

        public int[] maxSlidingWindow(int[] nums, int k) {
            int ansLength = nums.length - k + 1;
            int[] ans = new int[ansLength];
            // init
            head = tail = ql = 0;
            // init QUEUE with window size in k - 1
            for (int i = 0; i < k - 1; i++) {
                while (tail > head && nums[QUEUE[tail - 1]] <= nums[i]) {
                    tail--;
                }
                QUEUE[tail++] = i;
            }

            // collect ans
            /*for (int i = 0; i < ansLength; i++) {
                int cur = k - 1 + i;
                while (tail > head && nums[QUEUE[tail - 1]] <= nums[cur]) {
                    tail--;
                }
                QUEUE[tail++] = cur;
                ans[i] = nums[QUEUE[head]];
                if (QUEUE[head] == cur - k + 1) {
                    head++;
                }
            }*/
            for (int l = 0, r = k - 1; l < ansLength; l++, r++) {
                while (tail > head && nums[QUEUE[tail - 1]] <= nums[r]) {
                    tail--;
                }
                QUEUE[tail++] = r;
                ans[l] = nums[QUEUE[head]];
                if (QUEUE[head] == l) {
                    head++;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}