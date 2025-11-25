//A transformation sequence from word beginWord to word endWord using a 
//dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
// 
//
// 
// Every adjacent pair of words differs by a single letter. 
// Every si for 1 <= i <= k is in wordList. Note that beginWord does not need 
//to be in wordList. 
// sk == endWord 
// 
//
// Given two words, beginWord and endWord, and a dictionary wordList, return 
//the number of words in the shortest transformation sequence from beginWord to 
//endWord, or 0 if no such sequence exists. 
//
// 
// Example 1: 
//
// 
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog",
//"lot","log","cog"]
//Output: 5
//Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -
//> "dog" -> cog", which is 5 words long.
// 
//
// Example 2: 
//
// 
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog",
//"lot","log"]
//Output: 0
//Explanation: The endWord "cog" is not in wordList, therefore there is no 
//valid transformation sequence.
// 
//
// 
// Constraints: 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord, endWord, and wordList[i] consist of lowercase English letters. 
// beginWord != endWord 
// All the words in wordList are unique. 
// 
//
// Related Topics 广度优先搜索 哈希表 字符串 👍 1496 👎 0


package com.leetcode.editor.cn;

import java.util.*;

/**
 * [127]Word Ladder
 */
public class WordLadder {
    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
        System.out.println(solution.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static Set<String> dict;
        public static Set<String> SMALL = new HashSet<>();
        public static Set<String> BIG = new HashSet<>();
        public static Set<String> NEXT = new HashSet<>();

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            init(wordList);
            if (!dict.contains(endWord)) {
                return 0;
            }
            return bfs(beginWord, endWord);
        }

        public static void init(List<String> wordList) {
            dict = new HashSet<>(wordList);
            SMALL.clear();
            BIG.clear();
        }

        public static int bfs(String begin, String aim) {
            SMALL.add(begin);
            BIG.add(aim);
            int level = 2;
            while (!SMALL.isEmpty()) {
                dict.removeAll(SMALL);

                for (String word : SMALL) {
                    char[] charArray = word.toCharArray();
                    for (int i = 0, len = word.length(); i < len; i++) {
                        // backup
                        char old = charArray[i];
                        for (int j = 0; j < 26; j++) {
                            if (j != old - 'a') {
                                charArray[i] = (char) (j + 'a');
                                String newWord = String.valueOf(charArray);
                                if (BIG.contains(newWord)) {
                                    return level;
                                }
                                if (dict.contains(newWord)) {
                                    NEXT.add(newWord);
                                }
                            }
                        }
                        // restore
                        charArray[i] = old;
                    }
                }

                Set<String> tmp;
                if (NEXT.size() <= BIG.size()) {
                    tmp = SMALL;
                    SMALL = NEXT;
                } else {
                    tmp = SMALL;
                    SMALL = BIG;
                    BIG = NEXT;
                }
                NEXT = tmp;
                NEXT.clear();

                level++;
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}