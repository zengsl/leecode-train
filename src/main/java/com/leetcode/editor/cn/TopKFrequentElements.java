//Given an integer array nums and an integer k, return the k most frequent 
//elements. You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
// Input: nums = [1,1,1,2,2,3], k = 2 
// 
//
// Output: [1,2] 
//
// Example 2: 
//
// 
// Input: nums = [1], k = 1 
// 
//
// Output: [1] 
//
// Example 3: 
//
// 
// Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2 
// 
//
// Output: [1,2] 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// k is in the range [1, the number of unique elements in the array]. 
// It is guaranteed that the answer is unique. 
// 
//
// 
// Follow up: Your algorithm's time complexity must be better than O(n log n), 
//where n is the array's size. 
//
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 2178 👎 0


package com.leetcode.editor.cn;

import java.util.*;

/**
 *
 * [347]Top K Frequent Elements
 *
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.merge(num, 1, Integer::sum);
            }

            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.<int[]>comparingInt(o -> o[1]).reversed());
            for (Map.Entry<Integer, Integer> pair : freqMap.entrySet()) {
                if (queue.size() == k) {
                    if (queue.peek()[1] < pair.getValue()) {
                        queue.poll();
                        queue.add(new int[]{pair.getKey(), pair.getValue()});
                    }
                } else {
                    queue.add(new int[]{pair.getKey(), pair.getValue()});
                }
            }
            int[] ans = new int[k];
            int i = 0;
            while (i < k) {
                ans[i++] = queue.poll()[0];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}