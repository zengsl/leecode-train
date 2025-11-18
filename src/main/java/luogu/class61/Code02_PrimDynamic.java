package luogu.class61;

import java.io.*;
import java.util.*;

public class Code02_PrimDynamic {

    public static int n, m;
    public static final int POINT_MAX = 5001;
    public static final int EDGE_MAX = 400001;
    public static int[][] EDGES = new int[EDGE_MAX][3];
    public static List<List<int[]>> ADJACENCY_TABLE = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                build(n);

                st.nextToken();
                m = (int) st.nval;
                for (int i = 0; i < m; i++) {
                    st.nextToken();
                    EDGES[i][0] = (int) st.nval;
                    st.nextToken();
                    EDGES[i][1] = (int) st.nval;
                    st.nextToken();
                    EDGES[i][2] = (int) st.nval;
                    // init
                    ADJACENCY_TABLE.get(EDGES[i][0]).add(new int[]{EDGES[i][1], EDGES[i][2]});
                    ADJACENCY_TABLE.get(EDGES[i][1]).add(new int[]{EDGES[i][0], EDGES[i][2]});
                }
                PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
                queue.addAll(ADJACENCY_TABLE.get(1));

                boolean[] set = new boolean[n + 1];
                set[1] = true;
                int ans = 0, pointCnt = 1;
                while (!queue.isEmpty()){
                    int[] edge = queue.poll();
//                    System.out.println("edge:" + Arrays.toString(edge));
                    int next = edge[0];
                    int w = edge[1];
                    if (!set[next]) {
                        set[next] = true;
                        pointCnt++;
                        ans += w;
                        queue.addAll(ADJACENCY_TABLE.get(next));
                    }
                }
                writer.println(pointCnt == n ? ans : "orz");
                System.out.println("ans:" + ans);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void build(int n) {
        ADJACENCY_TABLE.clear();
        for (int i = 0; i <= n; i++) {
            ADJACENCY_TABLE.add(new ArrayList<>());
        }
    }


}
