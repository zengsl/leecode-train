//Design a data structure that follows the constraints of a Least Recently Used 
//(LRU) cache. 
//
// Implement the LRUCache class: 
//
// 
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
//
// int get(int key) Return the value of the key if the key exists, otherwise 
//return -1. 
// void put(int key, int value) Update the value of the key if the key exists. 
//Otherwise, add the key-value pair to the cache. If the number of keys exceeds 
//the capacity from this operation, evict the least recently used key. 
// 
//
// The functions get and put must each run in O(1) average time complexity. 
//
// 
// Example 1: 
//
// 
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10⁴ 
// 0 <= value <= 10⁵ 
// At most 2 * 10⁵ calls will be made to get and put. 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 3585 👎 0


package com.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [146]LRU Cache
 */
public class LruCache {
    public static void main(String[] args) {
        /*LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        int i = lruCache.get(2);
        System.out.println(i);*/

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class LRUCache {
        int size;
        Node head;
        Node tail;
        Map<Integer, Node> cache;

        public LRUCache(int capacity) {
            this.size = capacity;
            // init dummy node
            head = tail = new Node(-1, -1);
            cache = new HashMap<>();
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            } else {
                // update
                updateNode(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            Node node;
            if (cache.containsKey(key)) {
                node = cache.get(key);
                node.value = value;
            } else {
                if (cache.size() == size) {
                    // remove
                    removeOld();
                }
                node = new Node(key, value);
                cache.put(key, node);
            }
            // update  node
            updateNode(node);
        }

        private void removeOld() {
            Node next = head.next;
            Node third = next.next;
            if (third != null) {
                third.pre = head;
                head.next = third;
            } else {
                head.next = null;
                tail = head;
            }
            cache.remove(next.key);

            // help gc
            next.next = null;
            next.pre = null;
        }

        private void updateNode(Node node) {
            if (node.pre == null) {
                setTail(node);
            } else if (node != tail) {
                Node next = node.next;
                Node pre = node.pre;
                pre.next = next;
                next.pre = pre;
                setTail(node);
            }
        }

        private void setTail(Node node) {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }


        static private class Node {
            public int key;
            public int value;
            public Node pre;
            public Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}