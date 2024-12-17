//Given an integer numRows, return the first numRows of Pascal's triangle. 
//
// In Pascal's triangle, each number is the sum of the two numbers directly 
//above it as shown: 
// 
// 
// Example 1: 
// Input: numRows = 5
//Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
// Example 2: 
// Input: numRows = 1
//Output: [[1]]
// 
// 
// Constraints: 
//
// 
// 1 <= numRows <= 30 
// 
//
// Related Topics 数组 动态规划 👍 1206 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * [118]Pascal's Triangle
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        solution.generate(5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            if (numRows == 0) {
                return null;
            }
            List<List<Integer>> result = new ArrayList<>();
            result.add(List.of(1));
            if (numRows == 1) {
                return result;
            }
            int currRow = 2;
            while (currRow <= numRows) {
                int lastRow = currRow - 1;
                List<Integer> rowResult = new ArrayList<>();
                for (int i = 0; i < currRow; i++) {
                    int sum = 0;
                    if (i - 1 >= 0) {
                        sum += result.get(lastRow - 1).get(i - 1);
                    }

                    if (i < lastRow  ) {
                        sum += result.get(lastRow - 1).get(i);
                    }
                    rowResult.add(sum);
                }
                result.add(rowResult);
                currRow++;
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}