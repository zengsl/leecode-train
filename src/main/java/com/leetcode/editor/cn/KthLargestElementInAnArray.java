//Given an integer array nums and an integer k, return the kᵗʰ largest element 
//in the array. 
//
// Note that it is the kᵗʰ largest element in the sorted order, not the kᵗʰ 
//distinct element. 
//
// Can you solve it without sorting? 
//
// 
// Example 1: 
// Input: nums = [3,2,1,5,6,4], k = 2
//Output: 5
// 
// Example 2: 
// Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
//Output: 4
// 
// 
// Constraints: 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 2994 👎 0


package com.leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 *
 * [215]Kth Largest Element in an Array
 *
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new KthLargestElementInAnArray().new Solution();
        System.out.println(solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(solution.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        System.out.println(solution.findKthLargest(new int[]{2, 1}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int findKthLargest(int[] nums, int k) {
            initHeap(nums.length);
            for (int num : nums) {
                headAdd(num, k);
            }
            return peek();
        }

        int heapSize;
        int[] heap;

        private int peek() {
            return heap[0];
        }
        private void initHeap(int len) {
            heapSize = 0;
            heap = new int[len];
        }

        private void headAdd(int num, int k) {
            if (heapSize == k) {
                if (heap[0] < num) {
                    heap[0] = num;
                    heapify(0);
                }
            } else {
                heapInsert(num);
            }
        }

        private void heapify(int i) {
            while (i < heapSize - 1) {
                int left = (i << 1) + 1;
                if (left >= heapSize) {
                    left = i;
                }
                int right = (i << 1) + 2;
                if (right >= heapSize) {
                    right = i;
                }
                int best = heap[left] < heap[right] ? left : right;
                if (i == best || heap[i] <= heap[best]) {
                    break;
                }
                swap(heap, best, i);
                i = best;
            }
        }

        private void heapInsert(int num) {
            heap[heapSize++] = num;
            int i = heapSize - 1, p;
            while (i > 0) {
                p = (i - 1) >> 1;
                if (num >= heap[p]) {
                    break;
                }
                swap(heap, p, i);
                i = p;
            }
        }

        public int findKthLargest3(int[] nums, int k) {
            ans = 0;
            quickSelect(nums, k, 0, nums.length - 1);
            return ans;
        }

        int ans;

        private void quickSelect(int[] nums, int k, int start, int end) {
            if (start > end) {
                return;
            }

            if (start == end) {
                ans = nums[start];
                return;
            }

            int target = nums[start + (int) (Math.random() * (end - start + 1))];
            partition(nums, k, start, end, target);
            if (k <= (end - right + 1)) {
                quickSelect(nums, k, right, end);
            } else if (k > (end - left + 1)) {
                quickSelect(nums, k - (end - left + 1), start, left - 1);
            } else {
                ans = target;
            }
        }

        int left = 0, right = 0;

        private void partition(int[] nums, int k, int start, int end, int target) {
            // 1 2 4 4 3 5 69 10
            // 1 2 2 3 4
            left = start;
            right = end + 1;
            int i = start;
            while (i < right) {
                if (nums[i] < target) {
                    swap(nums, i, left);
                    i++;
                    left++;
                } else if (nums[i] == target) {
                    i++;
                } else if (nums[i] > target) {
                    swap(nums, i, right - 1);
                    right--;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        public int findKthLargest2(int[] nums, int k) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int num : nums) {
                if (queue.size() == k) {
                    if (num > queue.peek()) {
                        queue.poll();
                        queue.add(num);
                    }
                } else {
                    queue.add(num);
                }
            }
            return queue.peek();
        }
//leetcode submit region end(Prohibit modification and deletion)

    }
}