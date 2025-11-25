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
//all the shortest transformation sequences from beginWord to endWord, or an empty 
//list if no such sequence exists. Each sequence should be returned as a list of 
//the words [beginWord, s1, s2, ..., sk]. 
//
// 
// Example 1: 
//
// 
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog",
//"lot","log","cog"]
//Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//Explanation: There are 2 shortest transformation sequences:
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
//"hit" -> "hot" -> "lot" -> "log" -> "cog"
// 
//
// Example 2: 
//
// 
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog",
//"lot","log"]
//Output: []
//Explanation: The endWord "cog" is not in wordList, therefore there is no 
//valid transformation sequence.
// 
//
// 
// Constraints: 
//
// 
// 1 <= beginWord.length <= 5 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 500 
// wordList[i].length == beginWord.length 
// beginWord, endWord, and wordList[i] consist of lowercase English letters. 
// beginWord != endWord 
// All the words in wordList are unique. 
// The sum of all shortest transformation sequences does not exceed 10⁵. 
// 
//
// Related Topics 广度优先搜索 哈希表 字符串 回溯 👍 767 👎 0


package com.leetcode.editor.cn;

import java.util.*;

/**
 * [126]Word Ladder II
 */
public class WordLadderIi {
    public static void main(String[] args) {
        Solution solution = new WordLadderIi().new Solution();
        System.out.println(solution.findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static Set<String> dict;
        public static Set<String> CURR_LEVEL = new HashSet<>();
        public static Set<String> NEXT_LEVEL = new HashSet<>();
        public static final Map<String, List<String>> ADJACENCY_TABLE = new HashMap<>();

        public static final LinkedList<String> PATH = new LinkedList<>();
        public static final List<List<String>> ANS = new ArrayList<>();

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            init(wordList);
            if (!dict.contains(endWord)) {
                return ANS;
            }
            if (bfs(beginWord, endWord)) {
                dfs(endWord, beginWord);
            }
            return ANS;
        }

        public static void init(List<String> wordList) {
            dict = new HashSet<>(wordList);
            CURR_LEVEL.clear();
            NEXT_LEVEL.clear();
            ADJACENCY_TABLE.clear();

            PATH.clear();
            ANS.clear();
        }

        public static boolean bfs(String begin, String aim) {
            boolean find = false;
            CURR_LEVEL.add(begin);
            while (!CURR_LEVEL.isEmpty()) {
                dict.removeAll(CURR_LEVEL);
                for (String word : CURR_LEVEL) {
                    char[] charArray = word.toCharArray();
                    for (int i = 0, len = word.length(); i < len; i++) {
                        // backup
                        char old = charArray[i];
                        for (int j = 0; j < 26; j++) {
                            if (j != old - 'a') {
                                charArray[i] = (char) (j + 'a');
                                String newWord = String.valueOf(charArray);
                                if (newWord.equals(aim)) {
                                    find = true;
                                }
                                if (dict.contains(newWord)) {
                                    ADJACENCY_TABLE.putIfAbsent(newWord, new ArrayList<>());
                                    ADJACENCY_TABLE.get(newWord).add(word);
                                    NEXT_LEVEL.add(newWord);
                                }
                            }
                        }
                        // restore
                        charArray[i] = old;
                    }
                }
                if (find) {
                    break;
                } else {
                    Set<String> tmp = CURR_LEVEL;
                    CURR_LEVEL = NEXT_LEVEL;
                    NEXT_LEVEL = tmp;
                    NEXT_LEVEL.clear();
                }
            }
            return find;
        }

        public static void dfs(String curr, String aim) {
            PATH.addFirst(curr);
            if (curr.equals(aim)) {
                ANS.add(new ArrayList<>(PATH));
            } else {
                for (String next : ADJACENCY_TABLE.get(curr)) {
                    dfs(next, aim);
                }
            }
            PATH.removeFirst();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}