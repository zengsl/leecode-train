//The median is the middle value in an ordered integer list. If the size of the 
//list is even, there is no middle value, and the median is the mean of the two 
//middle values. 
//
// 
// For example, for arr = [2,3,4], the median is 3. 
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5. 
// 
//
// Implement the MedianFinder class: 
//
// 
// MedianFinder() initializes the MedianFinder object. 
// void addNum(int num) adds the integer num from the data stream to the data 
//structure. 
// double findMedian() returns the median of all elements so far. Answers 
//within 10⁻⁵ of the actual answer will be accepted. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//[[], [1], [2], [], [3], []]
//Output
//[null, null, null, 1.5, null, 2.0]
//
//Explanation
//MedianFinder medianFinder = new MedianFinder();
//medianFinder.addNum(1);    // arr = [1]
//medianFinder.addNum(2);    // arr = [1, 2]
//medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//medianFinder.addNum(3);    // arr[1, 2, 3]
//medianFinder.findMedian(); // return 2.0
// 
//
// 
// Constraints: 
//
// 
// -10⁵ <= num <= 10⁵ 
// There will be at least one element in the data structure before calling 
//findMedian. 
// At most 5 * 10⁴ calls will be made to addNum and findMedian. 
// 
//
// 
// Follow up: 
//
// 
// If all integer numbers from the stream are in the range [0, 100], how would 
//you optimize your solution? 
// If 99% of all integer numbers from the stream are in the range [0, 100], how 
//would you optimize your solution? 
// 
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 1159 👎 0


package com.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * [295]Find Median from Data Stream
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;


        public MedianFinder() {
            // 小根堆
            maxHeap = new PriorityQueue<>();
            // 大根堆
            minHeap = new PriorityQueue<>(Comparator.reverseOrder());
        }

        public void addNum(int num) {
            if (minHeap.isEmpty() || num < minHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            balanceHeap();
        }

        public double findMedian() {
            return maxHeap.size() == minHeap.size() ?
                    (maxHeap.peek() + minHeap.peek()) / 2.0
                    : (maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek());
        }

        private void balanceHeap() {
            while (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
                if (minHeap.size() - maxHeap.size() > 0) {
                    maxHeap.add(minHeap.poll());
                } else {
                    minHeap.add(maxHeap.poll());
                }
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}