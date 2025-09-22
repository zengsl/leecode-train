package trains;

import java.io.*;

/**
 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
 *
 * @author zengsl
 */
public class Trie1Main {

    private static class TrieNode {

        int pass;

        int end;

        TrieNode[] lines = new TrieNode[26];

        public Integer getPass() {
            return pass;
        }

        public void setPass(Integer pass) {
            this.pass = pass;
        }

        public Integer getEnd() {
            return end;
        }

        public void setEnd(Integer end) {
            this.end = end;
        }

        public TrieNode[] getLines() {
            return lines;
        }

        public void setLines(TrieNode[] lines) {
            this.lines = lines;
        }
    }

    private static TrieNode root;

    public static void insert(String word) {
        root.pass++;
        int length = word.length();
        TrieNode curr = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.lines[index] == null) {
                curr.lines[index] = new TrieNode();
            }
            curr = curr.lines[index];
            curr.pass++;
        }
        curr.end++;
    }

    public static void erase(String word) {
        if (countWordsEqualTo(word) == 0) {
            return;
        }
        int length = word.length();
        TrieNode curr = root;
        curr.pass--;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (--curr.lines[index].pass == 0) {
                curr.lines[index] = null;
                return;
            }
            curr = curr.lines[index];
        }
        curr.end--;
    }

    public static int countWordsStartingWith(String word) {
        int length = word.length();
        TrieNode curr = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.lines[index] == null) {
                return 0;
            }
            curr = curr.lines[index];
        }
        return curr.pass;
    }

    public static int countWordsEqualTo(String word) {
        int length = word.length();
        TrieNode curr = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.lines[index] == null) {
                return 0;
            }
            curr = curr.lines[index];
        }
        return curr.end;
    }

    public static void main(String[] args) throws IOException {
        root = new TrieNode();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));) {
            String line;
            while ((line = in.readLine()) != null) {
                int m = Integer.parseInt(line);
                for (int i = 1; i <= m; i++) {
                    String[] splits = in.readLine().split(" ");
                    int op = Integer.parseInt(splits[0]);
                    if (op == 1) {
                        insert(splits[1]);
                    } else if (op == 2) {
                        erase(splits[1]);
                    } else if (op == 3) {
                        out.println(countWordsEqualTo(splits[1]) > 0 ? "YES" : "NO");
                    } else if (op == 4) {
                        out.println(countWordsStartingWith(splits[1]));
                    }
                }
            }
        }
    }
}
