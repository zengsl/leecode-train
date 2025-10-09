//Given an array of positive integers nums, remove the smallest subarray (
//possibly empty) such that the sum of the remaining elements is divisible by p. It is 
//not allowed to remove the whole array. 
//
// Return the length of the smallest subarray that you need to remove, or -1 if 
//it's impossible. 
//
// A subarray is defined as a contiguous block of elements in the array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [3,1,4,2], p = 6
//Output: 1
//Explanation: The sum of the elements in nums is 10, which is not divisible by 
//6. We can remove the subarray [4], and the sum of the remaining elements is 6, 
//which is divisible by 6.
// 
//
// Example 2: 
//
// 
//Input: nums = [6,3,5,2], p = 9
//Output: 2
//Explanation: We cannot remove a single element to get a sum divisible by 9. 
//The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
// 
//
// Example 3: 
//
// 
//Input: nums = [1,2,3], p = 3
//Output: 0
//Explanation: Here the sum is 6. which is already divisible by 3. Thus we do 
//not need to remove anything.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 1 <= p <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 前缀和 👍 270 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [1590]Make Sum Divisible by P
 */
public class MakeSumDivisibleByP {
    public static void main(String[] args) {
        Solution solution = new MakeSumDivisibleByP().new Solution();
        solution.minSubarray(new int[]{
                1000000000, 1000000000, 1000000000
        }, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minSubarray(int[] nums, int p) {
            int mod = 0;
            for (int num : nums) {
                mod = (mod + num) % p;
            }
            if (mod == 0) {
                return 0;
            }

            int ans = Integer.MAX_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            for (int i = 0, size = nums.length, currMod = 0, find; i < size; i++) {
                currMod = (currMod + nums[i]) % p;
                // mod为总体需要移除掉的余数。
                // currMod >= mode 则find = currMode - mode
                // currMod < mode  则find =  p + currMod - mod
//                find = currMod >= mod ? currMod - mod : currMod + p - mod;
                find = (currMod + p - mod) % p;
                if (map.containsKey(find)) {
                    ans = Math.min(ans, i - map.get(find));
                }
                map.put(currMod, i);
            }
            return ans == nums.length ? -1 : ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}