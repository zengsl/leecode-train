//We are given hours, a list of the number of hours worked per day for a given 
//employee. 
//
// A day is considered to be a tiring day if and only if the number of hours 
//worked is (strictly) greater than 8. 
//
// A well-performing interval is an interval of days for which the number of 
//tiring days is strictly larger than the number of non-tiring days. 
//
// Return the length of the longest well-performing interval. 
//
// 
// Example 1: 
//
// 
//Input: hours = [9,9,6,0,6,6,9]
//Output: 3
//Explanation: The longest well-performing interval is [9,9,6].
// 
//
// Example 2: 
//
// 
//Input: hours = [6,6,6]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= hours.length <= 10⁴ 
// 0 <= hours[i] <= 16 
// 
//
// Related Topics 栈 数组 哈希表 前缀和 单调栈 👍 581 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [1124]Longest Well-Performing Interval
 */
public class LongestWellPerformingInterval {
    public static void main(String[] args) {
        Solution solution = new LongestWellPerformingInterval().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestWPI(int[] hours) {
            int[] performs = new int[hours.length];
            Map<Integer, Integer> map = new HashMap<>();
            // 处理边界
            map.put(0, -1);
            int ans = 0;
            for (int i = 0, sum = 0, size = hours.length; i < size; i++) {
                sum += hours[i] > 8 ? 1 : -1;

                if (sum > 0) {
                    ans = i + 1;
                } else {
                    // 为什么是减1？ 因为1 -1 -1 1 这样的数据是“慢慢”增长和减少的
                    // 比如：
                    // 数组为：   -1 -1 1 1
                    // 前缀和为： -1 -2 -1 0 当前处于最后一个元素，前缀和为0，此时我应该去寻找前缀和为0-1也就是-1的元素
                    // 为什么不是-2？因为-2一定是从-1变过来的，所以-2前面一定还至少有一个-1
                    if (map.containsKey(sum - 1)) {
                        ans = Math.max(ans, i - map.get(sum - 1));
                    }
                }
                // 不存在才记录，这样能保持获取的i最小
                if (!map.containsKey(sum)) {
                    map.put(sum, i);
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}