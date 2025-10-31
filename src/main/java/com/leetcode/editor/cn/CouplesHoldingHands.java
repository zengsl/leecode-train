//There are n couples sitting in 2n seats arranged in a row and want to hold 
//hands. 
//
// The people and seats are represented by an integer array row where row[i] is 
//the ID of the person sitting in the iᵗʰ seat. The couples are numbered in order,
// the first couple being (0, 1), the second couple being (2, 3), and so on with 
//the last couple being (2n - 2, 2n - 1). 
//
// Return the minimum number of swaps so that every couple is sitting side by 
//side. A swap consists of choosing any two people, then they stand up and switch 
//seats. 
//
// 
// Example 1: 
//
// 
//Input: row = [0,2,1,3]
//Output: 1
//Explanation: We only need to swap the second (row[1]) and third (row[2]) 
//person.
// 
//
// Example 2: 
//
// 
//Input: row = [3,2,0,1]
//Output: 0
//Explanation: All couples are already seated side by side.
// 
//
// 
// Constraints: 
//
// 
// 2n == row.length 
// 2 <= n <= 30 
// 0 <= row[i] < 2n 
// All the elements of row are unique. 
// 
//
// Related Topics 贪心 深度优先搜索 广度优先搜索 并查集 图 👍 584 👎 0


package com.leetcode.editor.cn;

/**
 * [765]Couples Holding Hands
 */
public class CouplesHoldingHands {
    public static void main(String[] args) {
        Solution solution = new CouplesHoldingHands().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final int MAX = 30;
        public static int[] father = new int[MAX];
        public static int sets;

        public int minSwapsCouples(int[] row) {
            int size = row.length;
            int couple = size >> 1;
            build(couple);
            for (int i = 0; i < size; i += 2) {
                union(row[i] >> 1, row[i + 1] >> 1);
            }
            return couple - sets;
        }

        public static void build(int coupleNum) {
            sets = coupleNum;
            for (int i = 0; i < coupleNum; i++) {
                father[i] = i;
            }
        }

        public static void union(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                father[a] = b;
                sets--;
            }
        }

        public static int find(int x) {
            int f = x;
            if (x != father[x]) {
                f = find(father[x]);
                father[x] = f;
            }
            return f;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}