//Design a data structure to store the strings' count with the ability to 
//return the strings with minimum and maximum counts. 
//
// Implement the AllOne class: 
//
// 
// AllOne() Initializes the object of the data structure. 
// inc(String key) Increments the count of the string key by 1. If key does not 
//exist in the data structure, insert it with count 1. 
// dec(String key) Decrements the count of the string key by 1. If the count of 
//key is 0 after the decrement, remove it from the data structure. It is 
//guaranteed that key exists in the data structure before the decrement. 
// getMaxKey() Returns one of the keys with the maximal count. If no element 
//exists, return an empty string "". 
// getMinKey() Returns one of the keys with the minimum count. If no element 
//exists, return an empty string "". 
// 
//
// Note that each function must run in O(1) average time complexity. 
//
// 
// Example 1: 
//
// 
//Input
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", 
//"getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//Output
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//Explanation
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "leet"
// 
//
// 
// Constraints: 
//
// 
// 1 <= key.length <= 10 
// key consists of lowercase English letters. 
// It is guaranteed that for each call to dec, key is existing in the data 
//structure. 
// At most 5 * 10⁴ calls will be made to inc, dec, getMaxKey, and getMinKey. 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 337 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [432]All O`one Data Structure
 *
 * @author zengsl
 */
public class AllOoneDataStructure {
    public static void main(String[] args) {
        AllOne allOne = new AllOoneDataStructure().new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.dec("hello");
        allOne.dec("hello");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class AllOne {

        class Node {
            int cnt;
            Set<String> keys;
            Node pre;
            Node next;

            public Node(int cnt) {
                this.cnt = cnt;
                keys = new HashSet<>();
            }
        }

        private Map<String, Node> map;
        Node head;
        Node tail;

        public AllOne() {
            head = new Node(0);
            tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.pre = head;
            map = new HashMap<>();
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                Node newNode;
                if (node.cnt + 1 == node.next.cnt) {
                    newNode = node.next;
                    newNode.keys.add(key);
                } else {
                    newNode = new Node(node.cnt + 1);
                    newNode.keys.add(key);
                    insertPos(node, newNode);
                }
                map.put(key, newNode);
                node.keys.remove(key);
                if (node.keys.isEmpty()) {
                    remove(node);
                }
            } else {
                if (head.next.cnt == 1) {
                    head.next.keys.add(key);
                    map.put(key, head.next);
                } else {
                    Node newNode = new Node(1);
                    newNode.keys.add(key);
                    map.put(key, newNode);
                    insertPos(head, newNode);
                }
            }
        }

        public void dec(String key) {
            if (!map.containsKey(key)) {
                return;
            }
            Node node = map.get(key);
            // 不存在
            if (node.cnt - 1 == 0) {
                map.remove(key);
            } else {
                Node pre = node.pre;
                if (pre.cnt == node.cnt - 1) {
                    pre.keys.add(key);
                    map.put(key, pre);
                } else {
                    Node newNode = new Node(node.cnt - 1);
                    newNode.keys.add(key);
                    map.put(key, newNode);
                    insertPos(pre, newNode);
                }
            }
            node.keys.remove(key);
            if (node.keys.isEmpty()) {
                remove(node);
            }
        }

        public String getMaxKey() {
            Set<String> keys = tail.pre.keys;
            return keys.isEmpty() ? "" : keys.iterator().next();
        }

        public String getMinKey() {
            Set<String> keys = head.next.keys;
            return keys.isEmpty() ? "" : keys.iterator().next();
        }


        private void insertPos(Node curr, Node newNode) {
            curr.next.pre = newNode;
            newNode.next = curr.next;
            curr.next = newNode;
            newNode.pre = curr;
        }

        private void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
//leetcode submit region end(Prohibit modification and deletion)

}