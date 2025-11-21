package luogu.class61;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Code05_BusyCities {

    public static int n, m;
    public static final int MAX_EDGE = 8001;
    public static final int MAX_POINT = 301;
    public static final int[] FATHER = new int[MAX_POINT];
    public static final int[][] EDGES = new int[MAX_EDGE][3];

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                init(n);

                st.nextToken();
                m = (int) st.nval;
                for (int i = 0; i < m; i++) {
                    st.nextToken();
                    EDGES[i][0] = (int) st.nval;
                    st.nextToken();
                    EDGES[i][1] = (int) st.nval;
                    st.nextToken();
                    EDGES[i][2] = (int) st.nval;
                }
                Arrays.sort(EDGES, 0, m, Comparator.comparingInt(e -> e[2]));
                int edgeCnt = 0, ans = 0;
                for (int i = 0; i < m; i++) {
                    if (union(EDGES[i][0], EDGES[i][1])) {
                        ans = Math.max(ans, EDGES[i][2]);
                        edgeCnt++;
                    }
                    if (edgeCnt == n - 1) {
                        break;
                    }
                }
                writer.println(edgeCnt + " " + ans);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void init(int x) {
        for (int i = 1; i <= x; i++) {
            FATHER[i] = i;
        }
    }

    public static int find(int a) {
        if (FATHER[a] != a) {
            FATHER[a] = find(FATHER[a]);
        }
        return FATHER[a];
    }

    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            FATHER[fa] = fb;
            return true;
        }

        return false;
    }
}
