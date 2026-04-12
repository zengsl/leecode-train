package com.leetcode.editor.cn;

import java.util.PriorityQueue;

public class FindMedianFromDataStream2 {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
    }

    static class MedianFinder {
        private final PriorityQueue<Integer> smallQueue;
        private final PriorityQueue<Integer> bigQueue;

        public MedianFinder() {
            this.smallQueue = new PriorityQueue<>();
            this.bigQueue = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            if (smallQueue.isEmpty() || num < smallQueue.peek()) {
                bigQueue.add(num);
            } else {
                smallQueue.add(num);
            }
            rebalance();
        }

        private void rebalance() {
            if (Math.abs(smallQueue.size() - bigQueue.size()) == 2) {
                if (smallQueue.size() > bigQueue.size()) {
                    bigQueue.add(smallQueue.poll());
                } else {
                    smallQueue.add(bigQueue.poll());
                }
            }
        }

        public double findMedian() {
            int leftSize = bigQueue.size();
            int rightSize = smallQueue.size();
            int leftTop = bigQueue.peek() == null ? 0 : bigQueue.peek();
            int rightTop = smallQueue.peek() == null ? 0 : smallQueue.peek();
            return rightSize == leftSize ? (double) (leftTop + rightTop) / 2 : (leftSize > rightSize ? leftTop : rightTop);
        }
    }
}
