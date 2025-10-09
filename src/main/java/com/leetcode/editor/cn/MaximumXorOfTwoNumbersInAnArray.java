//Given an integer array nums, return the maximum result of nums[i] XOR nums[j],
// where 0 <= i <= j < n. 
//
// 
// Example 1: 
//
// 
//Input: nums = [3,10,5,25,2,8]
//Output: 28
//Explanation: The maximum result is 5 XOR 25 = 28.
// 
//
// Example 2: 
//
// 
//Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//Output: 127
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 10⁵ 
// 0 <= nums[i] <= 2³¹ - 1 
// 
//
// Related Topics 位运算 字典树 数组 哈希表 👍 729 👎 0


package com.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * [421]Maximum XOR of Two Numbers in an Array
 */
public class MaximumXorOfTwoNumbersInAnArray {
    public static void main(String[] args) {

//        System.out.println(Integer.highestOneBit(10));
//        System.out.println(Integer.numberOfLeadingZeros(8));

//        Solution solution = new MaximumXorOfTwoNumbersInAnArray().new Solution();
//        System.out.println(Solution.findMaximumXOR(new int[]{2, 4}));
        System.out.println(Solution.findMaximumXOR2(new int[]{2, 4}));
//        int x = 10;
//        System.out.println((x >> 3) << 3);
//        System.out.println(x & (1 << 3));
//        System.out.println(2 & (1));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 前缀树
        public static int findMaximumXOR(int[] nums) {
            build(nums);
            int max = 0;
            for (int num : nums) {
                max = Math.max(max, maxXor(num));
            }
            clear();
            return max;
        }

        private static int cnt;
        private static final int[][] tree = new int[3000001][2];

        private static int maxBit;

        public static void build(int[] nums) {
            cnt = 1;
            int maxNum = 0;
            for (int num : nums) {
                maxNum = Math.max(num, maxNum);
            }
            maxBit = 31 - Integer.numberOfLeadingZeros(maxNum);
            for (int num : nums) {
                insert(num);
            }
        }

        public static void clear() {
            for (int i = 0; i < cnt; i++) {
                tree[i][0] = tree[i][1] = 0;
            }
            cnt = 0;
            maxBit = 0;
        }

        public static int maxXor(int num) {
            int cur = 1;
            int ans = 0;
            for (int i = maxBit, want, bit; i >= 0; i--) {
                bit = (num >> i) & 1;
                want = 1 ^ bit;
                // 不存在就只能回退
                if (tree[cur][want] == 0) {
                    want = want ^ 1;
                }

                if (tree[cur][want] == 0) {
                    break;
                }
                ans |= (want ^ bit) << i;
                cur = tree[cur][want];
            }
            return ans;
        }

        public static void insert(int num) {
            int cur = 1;
            for (int i = maxBit, path; i >= 0; i--) {
                path = (num >> i) & 1;
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
            }
        }


        // 用哈希表的做法
        public static int findMaximumXOR2(int[] nums) {
            int ans = 0;
            int max = 0;
            for (int num : nums) {
                max = Math.max(num, max);
            }
            int highBit = 31 - Integer.numberOfLeadingZeros(max);
            Set<Integer> set = new HashSet<>();
            for (int i = highBit; i >= 0; i--) {
                int better = ans | 1 << i;
                set.clear();
                for (int num : nums) {
                    num = num >> i << i;
                    set.add(num);
                    if (set.contains(better ^ num)) {
                        ans = better;
                        break;
                    }
                }
            }//0010 0
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


}