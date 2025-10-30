package nowcode.class56;

import java.io.*;

public class Code01_UnionFindNowCoder {

    public static int n, times;

    public static int MAX = 1000001;
    public static final int[] FATHER = new int[MAX];
    public static final int[] SIZE = new int[MAX];

    public static final int[] STACK = new int[MAX];
    public static int sl;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(System.out)) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                build(n);
                st.nextToken();
                times = (int) st.nval;
                for (int i = 0, op, x, y; i < times; i++) {
                    st.nextToken();
                    op = (int) st.nval;
                    st.nextToken();
                    x = (int) st.nval;
                    st.nextToken();
                    y = (int) st.nval;

                    if (op == 1) {
                        if (isSameSet(x, y)) {
                            pw.println("Yes");
                        } else {
                            pw.println("No");
                        }
                    } else if (op == 2) {
                        union(x, y);
                    }
                }
            }

        }
    }

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            FATHER[i] = i;
        }
    }

    public static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x != y) {
            if (SIZE[x] <= SIZE[y]) {
                FATHER[x] = FATHER[y];
                SIZE[y] += SIZE[x];
            } else {
                FATHER[y] = FATHER[x];
                SIZE[x] += SIZE[y];
            }
        }
    }

    public static int find(int a) {
        //  int f;
        // 递归扁平化 有溢出风险
        /*if (a != FATHER[a]) {
            f = find(a);
            FATHER[a] = f;
            SIZE[f] += SIZE[a];
            a = f;
        }*/

        // 使用Stack进行扁平化处理
        while (a != FATHER[a]) {
            STACK[sl++] = a;
            a = FATHER[a];
        }

        while (sl > 0) {
            int i = STACK[--sl];
            FATHER[i] = a;
            SIZE[a] += SIZE[i];
        }

        return a;
    }

}
