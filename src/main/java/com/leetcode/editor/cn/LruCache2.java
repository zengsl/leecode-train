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
public class LruCache2 {
    public static void main(String[] args) {
        /*LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        int i = lruCache.get(2);
        System.out.println(i);*/

        /*LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 0);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));*/


       /* LRUCache lruCache = new LRUCache(1);
        lruCache.put(2, 1);
        lruCache.get(2);
        lruCache.put(3, 2);
        lruCache.get(2);
        System.out.println(lruCache.get(3));*/


        // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        LRUCache lruCache = new LRUCache(2);
        lruCache.get(2);
        lruCache.put(2, 6);
        lruCache.get(1);
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        lruCache.get(1);
        lruCache.get(2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class LRUCache {
        int size;
        Node head;
        Node tail;
        Map<Integer, Node> cache;

        public LRUCache(int capacity) {
            this.cache = new HashMap<>();
            this.size = capacity;
            head = tail = new Node(-1, -1);
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            setTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node == null) {
                if (cache.size() == size) {
                    evict();
                }
                node = new Node(key, value);
                cache.put(key, node);
            } else {
                node.value = value;
            }
            setTail(node);
        }

        private void evict() {
            // to be removed
            Node next = head.next;
            cache.remove(next.key);
            Node nextNext = next.next;
            if (nextNext == null) {
                head.next = null;
                next.pre = null;
                tail = head;
            } else {
                head.next = nextNext;
                nextNext.pre = head;
                next.pre = null;
                next.next = null;
            }
        }

        private void setTail(Node node) {
            if (node == tail) {
                return;
            }
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) {
                pre.next = next;
            }
            if (next != null) {
                next.pre = pre;
            }
            node.next = null;
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