package trains;

import java.io.*;
import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
 *
 * @author zengsl
 */
public class Trie2Main {

    public static int MAXN = 150001;
    public static int[][] tree = new int[MAXN][26];
    public static int[] pass = new int[MAXN];
    public static int[] end = new int[MAXN];
    private static int cnt;

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, size = word.length(); i < size; i++) {
            // 不存在就初始化
            if (tree[cur][word.charAt(i) - 'a'] == 0) {
                tree[cur][word.charAt(i) - 'a'] = ++cnt;
            }
            pass[cur = tree[cur][word.charAt(i) - 'a']]++;
        }
        end[cur]++;
    }

    public static void erase(String word) {
        if (countWordsEqualTo(word) == 0) {
            return;
        }
        int cur = 1;
        pass[cur]--;
        for (int i = 0, size = word.length(); i < size; i++) {
            if (--pass[tree[cur][word.charAt(i) - 'a']] == 0) {
                tree[cur][word.charAt(i) - 'a'] = 0;
                return;
            }
            cur = tree[cur][word.charAt(i) - 'a'];
        }
        end[cur]--;
    }

    public static int countWordsStartingWith(String word) {
        int cur = 1;
        for (int i = 0, size = word.length(); i < size; i++) {
            if ((cur = tree[cur][word.charAt(i) - 'a']) == 0) {
                return 0;
            }
        }
        return pass[cur];
    }

    public static int countWordsEqualTo(String word) {
        int cur = 1;
        for (int i = 0, size = word.length(); i < size; i++) {
            if ((cur = tree[cur][word.charAt(i) - 'a']) == 0) {
                return 0;
            }
        }
        return end[cur];
    }

    public static void build() {
        cnt = 1;
    }

    public static void clear() {
        Arrays.fill(pass, 0);
        Arrays.fill(end, 0);
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            String line;
            build();
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
            clear();
        }
    }
}
