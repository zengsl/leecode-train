//You are given an array points containing the coordinates of points on a 2D 
//plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for 
//all 1 <= i < j <= points.length. You are also given an integer k. 
//
// Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| 
//<= k and 1 <= i < j <= points.length. 
//
// It is guaranteed that there exists at least one pair of points that satisfy 
//the constraint |xi - xj| <= k. 
//
// 
// Example 1: 
//
// 
//Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
//Output: 4
//Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if 
//we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points 
//also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
//No other pairs satisfy the condition, so we return the max of 4 and 1.
// 
//
// Example 2: 
//
// 
//Input: points = [[0,0],[3,0],[9,2]], k = 3
//Output: 3
//Explanation: Only the first two points have an absolute difference of 3 or 
//less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
// 
//
// 
// Constraints: 
//
// 
// 2 <= points.length <= 10⁵ 
// points[i].length == 2 
// -10⁸ <= xi, yi <= 10⁸ 
// 0 <= k <= 2 * 10⁸ 
// xi < xj for all 1 <= i < j <= points.length 
// xi form a strictly increasing sequence. 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 185 👎 0


package com.leetcode.editor.cn;

/**
 * [1499]Max Value of Equation
 */
public class MaxValueOfEquation {
    public static void main(String[] args) {
        Solution solution = new MaxValueOfEquation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static int MAX = 100005;
        public static int[] DEQUE = new int[MAX];
        public static int h, t;

        // yi + yj + |xi - xj|
        // yi + yj + xj - xi => yj + xj + (yi - xi)
        // 求yj + xj + (yi - xi) 最大且满足|xi - xj| <= k
        public int findMaxValueOfEquation(int[][] points, int k) {
            h = t = 0;
            int size = points.length;
            int ans = Integer.MIN_VALUE;
            // DEQUE 从大到小存
            for (int i = 0; i < size; i++) {
                // 淘汰不超过k距离的节点
                while (h != t && points[i][0] - points[DEQUE[h]][0] > k) {
                    h++;
                }

                if (h != t) {
                    ans = Math.max(ans, points[i][0] + points[i][1] + points[DEQUE[h]][1] - points[DEQUE[h]][0]);
                }

                while (h != t && (points[i][1] - points[i][0]) > (points[DEQUE[t - 1]][1] - points[DEQUE[t - 1]][0])) {
                    t--;
                }
                DEQUE[t++] = i;

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}