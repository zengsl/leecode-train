//Given an m x n board of characters and a list of strings words, return all 
//words on the board. 
//
// Each word must be constructed from letters of sequentially adjacent cells, 
//where adjacent cells are horizontally or vertically neighboring. The same letter 
//cell may not be used more than once in a word. 
//
// 
// Example 1: 
// 
// 
//Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i",
//"f","l","v"]], words = ["oath","pea","eat","rain"]
//Output: ["eat","oath"]
// 
//
// Example 2: 
// 
// 
//Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//Output: []
// 
//
// 
// Constraints: 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] is a lowercase English letter. 
// 1 <= words.length <= 3 * 10⁴ 
// 1 <= words[i].length <= 10 
// words[i] consists of lowercase English letters. 
// All the strings of words are unique. 
// 
//
// Related Topics 字典树 数组 字符串 回溯 矩阵 👍 957 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [212]Word Search II
 */
public class WordSearchIi {
    public static void main(String[] args) {
        Solution solution = new WordSearchIi().new Solution();
        Solution.findWords(new char[][]{
                {'a', 'a'}
        }, new String[]{"aaa"});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static List<String> findWords(char[][] board, String[] words) {
            List<String> ans = new ArrayList<>();
            build(words);
            for (int i = 0, size = board.length; i < size; i++) {
                for (int j = 0, size2 = board[0].length; j < size2; j++) {
                    bfs(board, i, j, 1, ans);
                }
            }
            clear();
            return ans;
        }

        public static int bfs(char[][] board, int i, int j, int treeNo, List<String> ans) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '0') {
                return 0;
            }
            char tmp = board[i][j];
            treeNo = tree[treeNo][tmp - 'a'];
            if (pass[treeNo] == 0) {
                return 0;
            }

            int find = 0;
            if (end[treeNo] != null) {
                ans.add(end[treeNo]);
                end[treeNo] = null;
                find++;
            }

            board[i][j] = '0';
            find += bfs(board, i, j + 1, treeNo, ans);
            find += bfs(board, i, j - 1, treeNo, ans);
            find += bfs(board, i + 1, j, treeNo, ans);
            find += bfs(board, i - 1, j, treeNo, ans);

            pass[treeNo] -= find;
            board[i][j] = tmp;
            return find;
        }


        public static boolean find(String word) {
            int curr = 1;
            for (int i = 0, size = word.length(); i < size; i++) {
                if (tree[curr][word.charAt(i) - 'a'] == 0) {
                    tree[curr][word.charAt(i) - 'a'] = ++cnt;
                }
                curr = tree[curr][word.charAt(i) - 'a'];
            }
            return end[curr] != null;
        }

        public static void insert(String word) {
            int curr = 1;
            pass[curr]++;
            for (int i = 0, size = word.length(); i < size; i++) {
                if (tree[curr][word.charAt(i) - 'a'] == 0) {
                    tree[curr][word.charAt(i) - 'a'] = ++cnt;
                }
                curr = tree[curr][word.charAt(i) - 'a'];
                pass[curr]++;
            }
            end[curr] = word;
        }

        public static void build(String[] words) {
            cnt = 1;
            for (String word : words) {
                insert(word);
            }
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                Arrays.fill(tree[i], 0);
            }
            Arrays.fill(end, null);
            cnt = 0;
        }


        static int MAX = 300000;
        static int cnt;
        static int[][] tree = new int[MAX][26];
        static int[] pass = new int[MAX];
        static String[] end = new String[MAX];
    }
//leetcode submit region end(Prohibit modification and deletion)

}