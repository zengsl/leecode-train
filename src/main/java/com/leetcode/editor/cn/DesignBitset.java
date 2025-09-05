//A Bitset is a data structure that compactly stores bits. 
//
// Implement the Bitset class: 
//
// 
// Bitset(int size) Initializes the Bitset with size bits, all of which are 0. 
// void fix(int idx) Updates the value of the bit at the index idx to 1. If the 
//value was already 1, no change occurs. 
// void unfix(int idx) Updates the value of the bit at the index idx to 0. If 
//the value was already 0, no change occurs. 
// void flip() Flips the values of each bit in the Bitset. In other words, all 
//bits with value 0 will now have value 1 and vice versa. 
// boolean all() Checks if the value of each bit in the Bitset is 1. Returns 
//true if it satisfies the condition, false otherwise. 
// boolean one() Checks if there is at least one bit in the Bitset with value 1.
// Returns true if it satisfies the condition, false otherwise. 
// int count() Returns the total number of bits in the Bitset which have value 1
//. 
// String toString() Returns the current composition of the Bitset. Note that 
//in the resultant string, the character at the iᵗʰ index should coincide with the 
//value at the iᵗʰ bit of the Bitset. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["Bitset", "fix", "fix", "flip", "all", "unfix", "flip", "one", "unfix", 
//"count", "toString"]
//[[5], [3], [1], [], [], [0], [], [], [0], [], []]
//Output
//[null, null, null, null, false, null, null, true, null, 2, "01010"]
//
//Explanation
//Bitset bs = new Bitset(5); // bitset = "00000".
//bs.fix(3);     // the value at idx = 3 is updated to 1, so bitset = "00010".
//bs.fix(1);     // the value at idx = 1 is updated to 1, so bitset = "01010". 
//bs.flip();     // the value of each bit is flipped, so bitset = "10101". 
//bs.all();      // return False, as not all values of the bitset are 1.
//bs.unfix(0);   // the value at idx = 0 is updated to 0, so bitset = "00101".
//bs.flip();     // the value of each bit is flipped, so bitset = "11010". 
//bs.one();      // return True, as there is at least 1 index with value 1.
//bs.unfix(0);   // the value at idx = 0 is updated to 0, so bitset = "01010".
//bs.count();    // return 2, as there are 2 bits with value 1.
//bs.toString(); // return "01010", which is the composition of bitset.
// 
//
// 
// Constraints: 
//
// 
// 1 <= size <= 10⁵ 
// 0 <= idx <= size - 1 
// At most 10⁵ calls will be made in total to fix, unfix, flip, all, one, count,
// and toString. 
// At least one call will be made to all, one, count, or toString. 
// At most 5 calls will be made to toString. 
// 
//
// Related Topics 设计 数组 哈希表 字符串 👍 42 👎 0


package com.leetcode.editor.cn;

/**
 * [2166]Design Bitset
 */
public class DesignBitset {
    public static void main(String[] args) {
        /*Bitset bs = new DesignBitset().new Bitset(5);
        bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
        bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
        bs.flip();     // 翻转每一位上的值，此时 bitset = "10101" 。
        bs.all();      // 返回 False ，bitset 中的值不全为 1 。
        bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
        bs.flip();     // 翻转每一位上的值，此时 bitset = "11010" 。
        bs.one();      // 返回 True ，至少存在一位的值为 1 。
        bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
        bs.count();    // 返回 2 ，当前有 2 位的值为 1 。
        System.out.println(bs); // 返回 "01010" ，即 bitset 的当前组成情况。

*/
        Bitset bs2 = new DesignBitset().new Bitset(90);
        bs2.unfix(0);
        bs2.unfix(62);
        bs2.unfix(72);
        bs2.flip();
        System.out.println("当前："+bs2);
        bs2.flip();
        System.out.println("当前："+bs2);
        bs2.fix(11);
        System.out.println("当前："+bs2);
        bs2.unfix(80);
        System.out.println("当前："+bs2);
        bs2.unfix(80);
        System.out.println("正确：000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000");
        System.out.println("当前："+bs2);
        /*bs2.fix(87);
        System.out.println(bs2);
        bs2.fix(61);
        bs2.unfix(0);
        System.out.println(bs2);*/




    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Bitset {

        int[] bitMap;
        boolean isNotReverse;
        int size;
        int one;
        int zero;
        int length = 32;

        public Bitset(int size) {
            this.bitMap = new int[(size + (length - 1)) / length];
            this.isNotReverse = true;
            this.size = size;
            this.zero = size;
            this.one = 0;
        }

        // 0 <= n < size
        public void fix(int n) {
            int idx = n / length;
            int bit = n % length;
            if (isNotReverse) {
                if ((bitMap[idx] & (1 << bit)) == 0) {
                    bitMap[idx] |= (1 << bit);
                    zero--;
                    one++;
                }
            } else {
                if ((bitMap[idx] & (1 << bit)) != 0) {
                    bitMap[idx] ^= (1 << bit);
                    zero--;
                    one++;
                }
            }
        }


        public void unfix(int n) {
            int idx = n / length;
            int bit = n % length;
            if (isNotReverse) {
                if ((bitMap[idx] & (1 << bit)) != 0) {
                    bitMap[idx] ^= (1 << bit);
                    zero++;
                    one--;
                }
            } else {
                if ((bitMap[idx] & (1 << bit)) == 0) {
                    bitMap[idx] |= (1 << bit);
                    zero++;
                    one--;
                }
            }
        }

        public void flip() {
            isNotReverse = !isNotReverse;
            int tmp = zero;
            zero = one;
            one = tmp;
        }

        public boolean all() {
            return one == size;
        }

        public boolean one() {
            return one >= 1;
        }

        public int count() {
            return one;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                int idx = i / length;
                int bit = i % length;
                int result = (bitMap[idx] >> bit) & 1;
                sb.append(isNotReverse ? result : result ^ 1);
            }
            return sb.toString();
        }
    }

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */
//leetcode submit region end(Prohibit modification and deletion)

}