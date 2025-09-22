package trains;

import java.io.*;

public class Trie1 {

    private static class TrieNode {

        Integer pass;

        Integer end;

        TrieNode[] lines;

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

    private TrieNode root;

    public Trie1() {
        root = new TrieNode();
    }

    public void insert(String word) {
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

    public void erase(String word) {
        if (countWordsEqualTo(word) == 0) {
            return;
        }
        int length = word.length();
        TrieNode curr = root;
        curr.pass--;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            curr = curr.lines[index];
            curr.pass--;
        }
        curr.end--;
    }

    public int countWordsStartingWith(String word) {
        int length = word.length();
        TrieNode curr = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.lines[index] == null) {
                return 0;
            }
        }
        return curr.pass;
    }

    public int countWordsEqualTo(String word) {
        int length = word.length();
        TrieNode curr = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (curr.lines[index] == null) {
                return 0;
            }
        }
        return curr.end;
    }

    public static void main(String[] args) throws IOException {
        Trie1 trie1 = new Trie1();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));) {
            String line;
            while ((line = in.readLine()) != null) {
                int m = Integer.parseInt(line);
                for (int i = 1; i <= m; i++) {
                    String[] splits = in.readLine().split(" ");
                    int op = Integer.parseInt(splits[0]);
                    if (op == 1) {
                        trie1.insert(splits[1]);
                    } else if (op == 2) {
                        trie1.erase(splits[1]);
                    } else if (op == 3) {
                        out.println(trie1.countWordsEqualTo(splits[1]) > 0 ? "YES" : "NO");
                    } else if (op == 4) {
                        out.println(trie1.countWordsStartingWith(splits[1]));
                    }
                }
            }
        }
    }
}
