//You are given an array people where people[i] is the weight of the iᵗʰ person,
// and an infinite number of boats where each boat can carry a maximum weight of 
//limit. Each boat carries at most two people at the same time, provided the sum 
//of the weight of those people is at most limit. 
//
// Return the minimum number of boats to carry every given person. 
//
// 
// Example 1: 
//
// 
//Input: people = [1,2], limit = 3
//Output: 1
//Explanation: 1 boat (1, 2)
// 
//
// Example 2: 
//
// 
//Input: people = [3,2,2,1], limit = 3
//Output: 3
//Explanation: 3 boats (1, 2), (2) and (3)
// 
//
// Example 3: 
//
// 
//Input: people = [3,5,3,4], limit = 5
//Output: 4
//Explanation: 4 boats (3), (3), (4), (5)
// 
//
// 
// Constraints: 
//
// 
// 1 <= people.length <= 5 * 10⁴ 
// 1 <= people[i] <= limit <= 3 * 10⁴ 
// 
//
// Related Topics 贪心 数组 双指针 排序 👍 380 👎 0


package com.leetcode.editor.cn;

import java.util.Arrays;

/**
 * [881]Boats to Save People
 */
public class BoatsToSavePeople {
    public static void main(String[] args) {
        Solution solution = new BoatsToSavePeople().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int l = 0, r = people.length - 1, ans = 0, sum = 0;
            while (l <= r) {
                // 最终l和r指向同一个时，只是一个人，所以不要相加。实际当前算法不考虑次情况也不影响结果，因为每次只算一条船，反而能减少判断提高速度。
//                sum = l == r ? people[l] : people[l] + people[r];
                sum = people[l] + people[r];
                if (sum <= limit) {
                    l++;
                }
                ans++;
                r--;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}