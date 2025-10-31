//Two strings, X and Y, are considered similar if either they are identical or 
//we can make them equivalent by swapping at most two letters (in distinct 
//positions) within the string X. 
//
// For example, "tars" and "rats" are similar (swapping at positions 0 and 2), 
//and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", 
//or "arts". 
//
// Together, these form two connected groups by similarity: {"tars", "rats", 
//"arts"} and {"star"}. Notice that "tars" and "arts" are in the same group even 
//though they are not similar. Formally, each group is such that a word is in the 
//group if and only if it is similar to at least one other word in the group. 
//
// We are given a list strs of strings where every string in strs is an anagram 
//of every other string in strs. How many groups are there? 
//
// 
// Example 1: 
//
// 
//Input: strs = ["tars","rats","arts","star"]
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: strs = ["omv","ovm"]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= strs.length <= 300 
// 1 <= strs[i].length <= 300 
// strs[i] consists of lowercase letters only. 
// All words in strs have the same length and are anagrams of each other. 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 哈希表 字符串 👍 219 👎 0


package com.leetcode.editor.cn;

/**
 * [839]Similar String Groups
 */
public class SimilarStringGroups {
    public static void main(String[] args) {
        Solution solution = new SimilarStringGroups().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static int sets;
        public static final int MAX = 301;
        public static int[] father = new int[MAX];

        public int numSimilarGroups(String[] strs) {
            int size = strs.length;
            int sl = strs[0].length();
            build(size);
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    int a = find(i);
                    int b = find(j);
                    if (a != b) {
                        int diff = 0;
                        for (int c = 0; c < sl && diff < 3; c++) {
                            if (strs[i].charAt(c) != strs[j].charAt(c)) {
                                diff++;
                            }
                        }
                        if (diff == 0 || diff == 2) {
                            /*father[i] = father[j];
                            sets--;*/

                            //union(i, j);

                            father[a] = b;
                            sets--;
                        }
                    }
                }
            }

            return sets;
        }

        public static int find(int x) {
            int f = x;
            if (x != father[x]) {
                f = find(father[x]);
                father[x] = f;
            }
            return f;
        }

        public static void union(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b) {
                father[a] = b;
                sets--;
            }
        }

        public static void build(int n) {
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            sets = n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}