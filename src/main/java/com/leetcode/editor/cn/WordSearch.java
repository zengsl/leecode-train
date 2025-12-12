//Given an m x n grid of characters board and a string word, return true if 
//word exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cells, 
//where adjacent cells are horizontally or vertically neighboring. The same letter 
//cell may not be used more than once. 
//
// 
// Example 1: 
// 
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "ABCCED"
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "SEE"
//Output: true
// 
//
// Example 3: 
// 
// 
//Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
// "ABCB"
//Output: false
// 
//
// 
// Constraints: 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board and word consists of only lowercase and uppercase English letters. 
// 
//
// 
// Follow up: Could you use search pruning to make your solution faster with a 
//larger board? 
//
// Related Topics 深度优先搜索 数组 字符串 回溯 矩阵 👍 2099 👎 0


package com.leetcode.editor.cn;

/**
 * [79]Word Search
 */
public class WordSearch {
    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();
        System.out.println(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
        System.out.println(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"));
        System.out.println(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (f(board, i, j, word.toCharArray(), 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean f(char[][] board, int i, int j, char[] ws, int k) {
            if (k == ws.length) {
                return true;
            }

            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != ws[k]) {
                return false;
            }

            char backup = board[i][j];
            board[i][j] = 0;
            boolean ans = f(board, i + 1, j, ws, k + 1)
                    || f(board, i - 1, j, ws, k + 1)
                    || f(board, i, j + 1, ws, k + 1)
                    || f(board, i, j - 1, ws, k + 1);
            board[i][j] = backup;
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}