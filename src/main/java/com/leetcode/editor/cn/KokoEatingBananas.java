//Koko loves to eat bananas. There are n piles of bananas, the iᵗʰ pile has 
//piles[i] bananas. The guards have gone and will come back in h hours. 
//
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she 
//chooses some pile of bananas and eats k bananas from that pile. If the pile has less 
//than k bananas, she eats all of them instead and will not eat any more bananas 
//during this hour. 
//
// Koko likes to eat slowly but still wants to finish eating all the bananas 
//before the guards return. 
//
// Return the minimum integer k such that she can eat all the bananas within h 
//hours. 
//
// 
// Example 1: 
//
// 
//Input: piles = [3,6,7,11], h = 8
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: piles = [30,11,23,4,20], h = 5
//Output: 30
// 
//
// Example 3: 
//
// 
//Input: piles = [30,11,23,4,20], h = 6
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// 1 <= piles.length <= 10⁴ 
// piles.length <= h <= 10⁹ 
// 1 <= piles[i] <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 714 👎 0


package com.leetcode.editor.cn;

/**
 * [875]Koko Eating Bananas
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        Solution solution = new KokoEatingBananas().new Solution();
//        System.out.println(solution.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
//        System.out.println(solution.minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        System.out.println(solution.minEatingSpeed(new int[]{805306368,805306368,805306368}, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int max = 0;
            for (int pile : piles) {
                max = Math.max(max, pile);
            }

            int speed = Integer.MAX_VALUE;
            // l, r, mid 用于代表可用的速度
            int l = 1, r = max, mid;
            while (l <= r) {
                mid = l + ((r - l) >> 1);
                if (computeTime(piles, mid) <= h) {
                    speed = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return speed;
        }

        private long computeTime(int[] piles, int speed) {
            long time = 0;
            for (int pile : piles) {
                time += (pile + speed - 1) / speed;
            }
            return time;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}