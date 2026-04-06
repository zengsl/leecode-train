package luogu.class56;

import java.io.*;
import java.util.Arrays;

public class UnionSet {
    private static int N, M;
    private static int[] FATHER, SIZE;

    public static void main(String[] args) throws IOException {
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out))) {
            StreamTokenizer tokenizer = new StreamTokenizer(inputStream);
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                N = (int) tokenizer.nval;
                init();
                tokenizer.nextToken();
                M = (int) tokenizer.nval;
                while (M-- > 0) {
                    tokenizer.nextToken();
                    int operate = (int)tokenizer.nval;
                    tokenizer.nextToken();
                    int a = (int)tokenizer.nval;
                    tokenizer.nextToken();
                    int b = (int)tokenizer.nval;
                    if (operate == 1) {
                        union(a, b);
                    } else {
                        boolean res = isSameSet(a, b);
                        out.println(res ? "Y" : "N");
                    }
                }
                out.flush();
            }
        }

    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            return;
        }
        if (SIZE[fa] < SIZE[fb]) {
            FATHER[fa] = fb;
            SIZE[fb] += SIZE[fa];
        } else {
            FATHER[fb] = fa;
            SIZE[fa] += SIZE[fb];
        }

    }

    public static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static int find(int x) {
        if (x != FATHER[x]) {
            FATHER[x] = find(FATHER[x]);
        }
        return FATHER[x];
    }

    public static void init() {
        FATHER = new int[N + 1];
        SIZE = new int[N + 1];
        Arrays.fill(SIZE, 1);
        for (int i = 1; i <= N; i++) {
            FATHER[i] = i;
        }
    }
}
