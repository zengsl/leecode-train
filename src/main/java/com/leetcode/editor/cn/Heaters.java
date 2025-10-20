//Winter is coming! During the contest, your first job is to design a standard 
//heater with a fixed warm radius to warm all the houses. 
//
// Every house can be warmed, as long as the house is within the heater's warm 
//radius range. 
//
// Given the positions of houses and heaters on a horizontal line, return the 
//minimum radius standard of heaters so that those heaters could cover all houses. 
//
// Notice that all the heaters follow your radius standard, and the warm radius 
//will be the same. 
//
// 
// Example 1: 
//
// 
//Input: houses = [1,2,3], heaters = [2]
//Output: 1
//Explanation: The only heater was placed in the position 2, and if we use the 
//radius 1 standard, then all the houses can be warmed.
// 
//
// Example 2: 
//
// 
//Input: houses = [1,2,3,4], heaters = [1,4]
//Output: 1
//Explanation: The two heaters were placed at positions 1 and 4. We need to use 
//a radius 1 standard, then all the houses can be warmed.
// 
//
// Example 3: 
//
// 
//Input: houses = [1,5], heaters = [2]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= houses.length, heaters.length <= 3 * 10⁴ 
// 1 <= houses[i], heaters[i] <= 10⁹ 
// 
//
// Related Topics 数组 双指针 二分查找 排序 👍 515 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [475]Heaters
 */
public class Heaters {
    public static void main(String[] args) {
        Solution solution = new Heaters().new Solution();
//        System.out.println(solution.findRadius(new int[]{1, 2, 3}, new int[]{1, 2}));
        System.out.println(solution.findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
//        System.out.println(solution.findRadius(new int[]{1, 2, 3, 5, 15}, new int[]{2, 30}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);
            int ans = 0;
            for (int i = 0, j = 0, houseSize = houses.length, heaterSize = heaters.length; i < houseSize; i++) {
                while (!best(houses, heaters, i, j)) {
                    j++;
                }
                ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
            }
            return ans;
        }

        private boolean best(int[] houses, int[] heaters, int i, int j) {
            return j == heaters.length - 1 || Math.abs(houses[i] - heaters[j]) < Math.abs(houses[i] - heaters[j + 1]);
        }


        public int findRadius2(int[] houses, int[] heaters) {
            Arrays.sort(houses);
            Arrays.sort(heaters);
            int ans = 0, bestRadius;
            for (int i = 0, j = 0, houseSize = houses.length, heaterSize = heaters.length; i < houseSize; i++) {
                bestRadius = Math.abs(heaters[j] - houses[i]);
                while (j != heaterSize - 1 && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                    bestRadius = Math.abs(heaters[++j] - houses[i]);
                }
                ans = Math.max(ans, bestRadius);
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}