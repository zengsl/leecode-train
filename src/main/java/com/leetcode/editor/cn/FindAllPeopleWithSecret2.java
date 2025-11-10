//You are given an integer n indicating there are n people numbered from 0 to n 
//- 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] 
//= [xi, yi, timei] indicates that person xi and person yi have a meeting at 
//timei. A person may attend multiple meetings at the same time. Finally, you are 
//given an integer firstPerson. 
//
// Person 0 has a secret and initially shares the secret with a person 
//firstPerson at time 0. This secret is then shared every time a meeting takes place with 
//a person that has the secret. More formally, for every meeting, if a person xi 
//has the secret at timei, then they will share the secret with person yi, and vice 
//versa. 
//
// The secrets are shared instantaneously. That is, a person may receive the 
//secret and share it with people in other meetings within the same time frame. 
//
// Return a list of all the people that have the secret after all the meetings 
//have taken place. You may return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
//Output: [0,1,2,3,5]
//Explanation:
//At time 0, person 0 shares the secret with person 1.
//At time 5, person 1 shares the secret with person 2.
//At time 8, person 2 shares the secret with person 3.
//At time 10, person 1 shares the secret with person 5.​​​​
//Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
// 
//
// Example 2: 
//
// 
//Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
//Output: [0,1,3]
//Explanation:
//At time 0, person 0 shares the secret with person 3.
//At time 2, neither person 1 nor person 2 know the secret.
//At time 3, person 3 shares the secret with person 0 and person 1.
//Thus, people 0, 1, and 3 know the secret after all the meetings.
// 
//
// Example 3: 
//
// 
//Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
//Output: [0,1,2,3,4]
//Explanation:
//At time 0, person 0 shares the secret with person 1.
//At time 1, person 1 shares the secret with person 2, and person 2 shares the 
//secret with person 3.
//Note that person 2 can share the secret at the same time as receiving it.
//At time 2, person 3 shares the secret with person 4.
//Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
// 
//
// 
// Constraints: 
//
// 
// 2 <= n <= 10⁵ 
// 1 <= meetings.length <= 10⁵ 
// meetings[i].length == 3 
// 0 <= xi, yi <= n - 1 
// xi != yi 
// 1 <= timei <= 10⁵ 
// 1 <= firstPerson <= n - 1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 排序 👍 77 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * [2092]Find All People With Secret
 */
public class FindAllPeopleWithSecret2 {

    public static void main(String[] args) {
        Solution solution = new FindAllPeopleWithSecret2().new Solution();
        System.out.println(solution.findAllPeople(6, new int[][]{
                {1, 2, 5}, {2, 3, 8}, {1, 5, 10}
        }, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static int MAXN = 100001;

        public static int[] father = new int[MAXN];

        // 集合的标签信息 : 设置集合的一些属性
        // 属性在哪？secret[代表元素] 代表集合的属性
        public static boolean[] secret = new boolean[MAXN];

        public static void build(int n, int first) {
            for (int i = 0; i < n; i++) {
                father[i] = i;
                secret[i] = false;
            }
            father[first] = 0;
            secret[0] = true;
        }

        public static int find(int i) {
            if (i != father[i]) {
                father[i] = find(father[i]);
            }
            return father[i];
        }

        public static void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                father[fx] = fy;
                secret[fy] |= secret[fx];
            }
        }

        // 会议排序 : m * log m
        // 处理过程 : O(m)
        // 收集答案 : O(n)
        public static List<Integer> findAllPeople(int n, int[][] meetings, int first) {
            build(n, first);
            // {0 : 专家   1 : 专家编号   2 : 时刻}
            Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
            int size = meetings.length;
            for (int l = 0, r; l < size;) {
                r = l;
                while (r + 1 < size && meetings[l][2] == meetings[r + 1][2]) {
                    r++;
                }
                // l....r这些会议，一定是一个时刻
                for (int i = l; i <= r; i++) {
                    union(meetings[i][0], meetings[i][1]);
                }
                // 有小的撤销行为，但这不是可撤销并查集
                // 只是每一批没有知道秘密的专家重新建立集合而已
                for (int i = l, a, b; i <= r; i++) {
                    a = meetings[i][0];
                    b = meetings[i][1];
                    if (!secret[find(a)]) {
                        father[a] = a;
                    }
                    if (!secret[find(b)]) {
                        father[b] = b;
                    }
                }
                l = r + 1;
            }
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (secret[find(i)]) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}