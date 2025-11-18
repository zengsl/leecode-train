package luogu.class61;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Code01_Kruskal {

    public static int n, m;
    public static final int POINT_MAX = 5001;
    public static final int EDGE_MAX = 400001;
    public static int[][] EDGES = new int[EDGE_MAX][3];

    public static int[] FATHER = new int[POINT_MAX];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
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

                for (int i = 1; i <= n; i++) {
                    FATHER[i] = i;
                }
                // sort
                Arrays.sort(EDGES, 0, m, Comparator.comparingInt(e -> e[2]));
                int ans = 0, edgeCnt = 0;
                for (int i = 0; i < m; i++) {
                    if (union(EDGES[i][0], EDGES[i][1])) {
                        ans += EDGES[i][2];
                        edgeCnt++;
//                        System.out.println("EDGE:" + Arrays.toString(EDGES[i]));
                    }
                }
                writer.println(edgeCnt == n - 1 ? ans : "orz");
//                System.out.println("ans:" + ans);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
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

    public static int find(int x) {
        if (x != FATHER[x]) {
            FATHER[x] = find(FATHER[x]);
        }
        return FATHER[x];
    }
}
