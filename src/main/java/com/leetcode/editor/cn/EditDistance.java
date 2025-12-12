//Given two strings word1 and word2, return the minimum number of operations 
//required to convert word1 to word2. 
//
// You have the following three operations permitted on a word: 
//
// 
// Insert a character 
// Delete a character 
// Replace a character 
// 
//
// 
// Example 1: 
//
// 
//Input: word1 = "horse", word2 = "ros"
//Output: 3
//Explanation: 
//horse -> rorse (replace 'h' with 'r')
//rorse -> rose (remove 'r')
//rose -> ros (remove 'e')
// 
//
// Example 2: 
//
// 
//Input: word1 = "intention", word2 = "execution"
//Output: 5
//Explanation: 
//intention -> inention (remove 't')
//inention -> enention (replace 'i' with 'e')
//enention -> exention (replace 'n' with 'x')
//exention -> exection (replace 'n' with 'c')
//exection -> execution (insert 'u')
// 
//
// 
// Constraints: 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 and word2 consist of lowercase English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 3808 👎 0


package com.leetcode.editor.cn;

/**
 * [72]Edit Distance
 */
public class EditDistance {
    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            return editDistance1(word1, word2, 1, 1, 1);
        }

        // a增, b删, c改
        public int editDistance1(String word1, String word2, int a, int b, int c) {
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();
            int n = chars1.length;
            int m = chars2.length;
            //i, j情况分析：
            // i不要: dp[i -1][j] + 1(删除操作)
            // i要:  chars1[i-1] == chars2[j-1] 则 dp[i-1][j-1]
            //      chars1[i-1] != chars2[j-1] 则 dp[i-1][j-1] + 1 （替换操作）
            // dp[i][j-1] + 插入

            // 分别描述两字符串在不同前缀串之间的关系：
            // word1的长度为0 ~ n
            // word2的长度为0 ~ m
            int[][] dp = new int[n + 1][m + 1];
            // n = 0, 根据m有关
            // m = 0，根据n有关
            for (int i = 0; i <= n; i++) {
                dp[i][0] = i * b;
            }
            for (int i = 1; i <= m; i++) {
                dp[0][i] = i * a;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1, ans1, ans2, ans3, ans4; j <= m; j++) {
                    // 删除操作，不使用dp[i][j]的结果，也就是word1中i-1位置字符去除
                    ans1 = dp[i - 1][j] + b;
                    ans2 = Integer.MAX_VALUE;
                    // 当前长度下，最后一个字符相同的情况下
                    if (chars1[i - 1] == chars2[j - 1]) {
                        ans2 = dp[i - 1][j - 1];
                    }
                    // 当前长度下，最后一个字符不相同的情况下。需要增加替换代价
                    ans3 = dp[i - 1][j - 1] + c;
                    // 当前长度下，在word1后插入一个字符。所以word1在i长度时，只需要编辑成word2在j-1长度的情况，而j长度位置（j-1）通过插入解决。
                    ans4 = dp[i][j - 1] + a;
                    dp[i][j] = Math.min(Math.min(ans1, ans2), Math.min(ans3, ans4));
                }
            }
            return dp[n][m];
        }

        // a增, b删, c改
        public int editDistance2(String word1, String word2, int a, int b, int c) {
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();
            int n = chars1.length;
            int m = chars2.length;
            //i, j情况分析：
            // i不要: dp[i -1][j] + 1(删除操作)
            // i要:  chars1[i-1] == chars2[j-1] 则 dp[i-1][j-1]
            //      chars1[i-1] != chars2[j-1] 则 dp[i-1][j-1] + 1 （替换操作）
            // dp[i][j-1] + 插入

            // 分别描述两字符串在不同前缀串之间的关系：
            // word1的长度为0 ~ n
            // word2的长度为0 ~ m
            int[] dp = new int[m + 1];
            // n = 0, 根据m有关
            // m = 0，根据n有关
            // 初始化第一行
            for (int i = 1; i <= m; i++) {
                dp[i] = i * a;
            }
            // 依赖： 上，左上，左
            for (int i = 1, leftTop; i <= n; i++) {
                // 初始化做上，其实就是标准dp中的dp[i][0]，需要*删除代价
                leftTop = (i - 1) * b;
                dp[0] = i * b;
                for (int j = 1, backup, ans1, ans2, ans3, ans4; j <= m; j++) {
                    backup = dp[j];
                    // 删除操作，不使用dp[i][j]的结果，也就是word1中i-1位置字符去除
                    ans1 = dp[j] + b;
                    ans2 = Integer.MAX_VALUE;
                    // 当前长度下，最后一个字符相同的情况下
                    if (chars1[i - 1] == chars2[j - 1]) {
                        ans2 = leftTop;
                    }
                    // 当前长度下，最后一个字符不相同的情况下。需要增加替换代价
                    ans3 = leftTop + c;
                    // 当前长度下，在word1后插入一个字符。所以word1在i长度时，只需要编辑成word2在j-1长度的情况，而j长度位置（j-1）通过插入解决。
                    ans4 = dp[j - 1] + a;
                    dp[j] = Math.min(Math.min(ans1, ans2), Math.min(ans3, ans4));
                    leftTop = backup;
                }
            }
            return dp[m];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}