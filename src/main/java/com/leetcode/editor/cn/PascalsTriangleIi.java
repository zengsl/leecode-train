//Given an integer rowIndex, return the rowIndexᵗʰ (0-indexed) row of the 
//Pascal's triangle. 
//
// In Pascal's triangle, each number is the sum of the two numbers directly 
//above it as shown: 
// 
// 
// Example 1: 
// Input: rowIndex = 3
//Output: [1,3,3,1]
// 
// Example 2: 
// Input: rowIndex = 0
//Output: [1]
// 
// Example 3: 
// Input: rowIndex = 1
//Output: [1,1]
// 
// 
// Constraints: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
// Follow up: Could you optimize your algorithm to use only O(rowIndex) extra 
//space? 
//
// Related Topics 数组 动态规划 👍 559 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * [119]Pascal's Triangle II
 */
public class PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangleIi().new Solution();
        System.out.println(solution.getRow(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> lastRow = new ArrayList<>();
            List<Integer> resultRow = new ArrayList<>();
            int currIndex = 0;
            while (currIndex <= rowIndex) {
                resultRow = new ArrayList<>();
                for (int i = 0; i <= currIndex; i++) {
                    if (lastRow.isEmpty()) {
                        resultRow.add(1);
                    } else {
                        int count = (i - 1 >= 0 ? lastRow.get(i - 1) : 0) + (i <= currIndex - 1 ? lastRow.get(i) : 0);
                        resultRow.add(count);
                    }
                }
//                lastRow.clear();
                lastRow = resultRow;
                currIndex++;
            }
            return resultRow;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}