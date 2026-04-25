//A trie (pronounced as "try") or prefix tree is a tree data structure used to 
//efficiently store and retrieve keys in a dataset of strings. There are various 
//applications of this data structure, such as autocomplete and spellchecker. 
//
// Implement the Trie class: 
//
// 
// Trie() Initializes the trie object. 
// void insert(String word) Inserts the string word into the trie. 
// boolean search(String word) Returns true if the string word is in the trie (
//i.e., was inserted before), and false otherwise. 
// boolean startsWith(String prefix) Returns true if there is a previously 
//inserted string word that has the prefix prefix, and false otherwise. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//Output
//[null, null, true, false, true, null, true]
//
//Explanation
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // return True
//trie.search("app");     // return False
//trie.startsWith("app"); // return True
//trie.insert("app");
//trie.search("app");     // return True
// 
//
// 
// Constraints: 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word and prefix consist only of lowercase English letters. 
// At most 3 * 10⁴ calls in total will be made to insert, search, and 
//startsWith. 
// 
//
// Related Topics 设计 字典树 哈希表 字符串 👍 1945 👎 0


package com.leetcode.editor.cn;

/**
 *
 * [208]Implement Trie (Prefix Tree)
 *
 */
public class ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new ImplementTriePrefixTree().new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));// return True
        System.out.println(trie.search("app")); // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app")); // return True
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        private final Node root;

        public Trie() {
            this.root = new Node();
            this.root.end = -1;
        }

        static class Node {
            private int pass;
            private int end;
            private Node[] next;

            public Node() {
                this.pass = 0;
                this.end = 0;
                this.next = new Node[26];
            }
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            Node curr = root;
            for (char c : chars) {
                if (curr.next[c - 'a'] == null) {
                    curr.next[c - 'a'] = new Node();
                }
                curr.next[c - 'a'].pass++;
                curr = curr.next[c - 'a'];
            }
            curr.end++;
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Node curr = root;
            for (char c : chars) {
                if (curr.next[c - 'a'] == null) {
                    return false;
                }
                curr = curr.next[c - 'a'];
            }
            return curr.end > 0;
        }

        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Node curr = root;
            for (char c : chars) {
                if (curr.next[c - 'a'] == null) {
                    return false;
                }
                curr = curr.next[c - 'a'];
            }
            return curr.pass > 0;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}