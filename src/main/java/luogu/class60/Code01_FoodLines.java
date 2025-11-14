package luogu.class60;

import java.io.*;

public class Code01_FoodLines {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(bufferedReader);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                int n = (int) st.nval;
                init(n);
                st.nextToken();
                int m = (int) st.nval;
                for (int i = 0, to, from; i < m; i++) {
                    st.nextToken();
                    from = (int) st.nval;
                    st.nextToken();
                    to = (int) st.nval;
                    // 创建边
                    addEdge(from, to);
                }

                for (int i = 1; i <= n; i++) {
                    if (INDEGREE[i] == 0) {
                        DEQUE[r++] = i;
                        LEN[i] = 1;
                    }
                }
                int ans = 0;
                while (l < r) {
                    int e = DEQUE[l++];
                    if (HEAD[e] == 0) {
                        ans = (ans + LEN[e]) % MOD;
                    } else {
                        for (int edge = HEAD[e]; edge != 0; edge = NEXT[edge]) {
                            int to = TO[edge];
                            LEN[to] = (LEN[to] + LEN[e]) % MOD;
                            if (--INDEGREE[to] == 0) {
                                DEQUE[r++] = to;
                            }
                        }
                    }

                }
                System.out.println("ans: " + ans);
                writer.println(ans);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static final int MOD = 80112002;
    public static final int MAX_POINT = 5001;
    public static final int MAX_EDGE = 500000;
    public static int[] HEAD = new int[MAX_POINT];
    public static int[] NEXT = new int[MAX_EDGE];
    public static int[] TO = new int[MAX_EDGE];
    public static int[] INDEGREE = new int[MAX_POINT];
    public static int[] LEN = new int[MAX_POINT];
    public static int cnt;

    public static int[] DEQUE = new int[MAX_POINT];
    public static int l, r;

    public static void init(int n) {
        cnt = l = r = 0;
        for (int i = 1; i <= n; i++) {
            HEAD[i] = 0;
            INDEGREE[i] = 0;
            LEN[i] = 0;
        }
    }

    public static void addEdge(int from, int to) {
        NEXT[++cnt] = HEAD[from];
        HEAD[from] = cnt;
        TO[cnt] = to;
        INDEGREE[to]++;
    }
}
