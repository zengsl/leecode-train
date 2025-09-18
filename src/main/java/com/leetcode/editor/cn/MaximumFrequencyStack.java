//Design a stack-like data structure to push elements to the stack and pop the 
//most frequent element from the stack. 
//
// Implement the FreqStack class: 
//
// 
// FreqStack() constructs an empty frequency stack. 
// void push(int val) pushes an integer val onto the top of the stack. 
// int pop() removes and returns the most frequent element in the stack. 
// 
// If there is a tie for the most frequent element, the element closest to the 
//stack's top is removed and returned. 
// 
// 
//
// 
// Example 1: 
//
// 
//Input
//["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", 
//"pop", "pop"]
//[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
//Output
//[null, null, null, null, null, null, null, 5, 7, 5, 4]
//
//Explanation
//FreqStack freqStack = new FreqStack();
//freqStack.push(5); // The stack is [5]
//freqStack.push(7); // The stack is [5,7]
//freqStack.push(5); // The stack is [5,7,5]
//freqStack.push(7); // The stack is [5,7,5,7]
//freqStack.push(4); // The stack is [5,7,5,7,4]
//freqStack.push(5); // The stack is [5,7,5,7,4,5]
//freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [
//5,7,5,7,4].
//freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is 
//closest to the top. The stack becomes [5,7,5,4].
//freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [
//5,7,4].
//freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is 
//closest to the top. The stack becomes [5,7].
// 
//
// 
// Constraints: 
//
// 
// 0 <= val <= 10⁹ 
// At most 2 * 10⁴ calls will be made to push and pop. 
// It is guaranteed that there will be at least one element in the stack before 
//calling pop. 
// 
//
// Related Topics 栈 设计 哈希表 有序集合 👍 437 👎 0


package com.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [895]Maximum Frequency Stack
 */
public class MaximumFrequencyStack {
    public static void main(String[] args) {
        FreqStack freqStack = new MaximumFrequencyStack().new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class FreqStack {

        Map<Integer, List<Integer>> freqValueStack;
        Map<Integer, Integer> valueFreqMap;
        int maxFreq;

        public FreqStack() {
            freqValueStack = new HashMap<>();
            valueFreqMap = new HashMap<>();
            maxFreq = 0;
        }

        public void push(int val) {
            Integer count = valueFreqMap.getOrDefault(val, 0);
            valueFreqMap.put(val, ++count);
            // update stack
            List<Integer> cntList = freqValueStack.computeIfAbsent(count, key -> new ArrayList<>());
            cntList.add(val);
            // update max frequency
            maxFreq = Math.max(count, maxFreq);
        }

        public int pop() {
            if (maxFreq == 0) {
                return -1;
            }
            List<Integer> valueList = freqValueStack.get(maxFreq);
            int val = valueList.removeLast();
            int newFreq = maxFreq - 1;
            if (newFreq == 0) {
                valueFreqMap.remove(val);
            } else {
                valueFreqMap.put(val, maxFreq - 1);
            }
            if (valueList.isEmpty()) {
                freqValueStack.remove(maxFreq);
                --maxFreq;
            }
            return val;
        }
    }

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
//leetcode submit region end(Prohibit modification and deletion)

}