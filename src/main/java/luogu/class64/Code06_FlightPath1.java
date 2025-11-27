package luogu.class64;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code06_FlightPath1 {

    public static int n, m, k, s, t;

    public static final int MAX_POINT = 10001;
    public static final int MAX_EDGE = 100001;
    // 每一节点的第一条边
    public static final int[] HEAD = new int[MAX_POINT];
    // 每条边的下一个兄弟边
    public static final int[] NEXT = new int[MAX_EDGE];
    // 每条边对应的目标点
    public static final int[] TO = new int[MAX_EDGE];
    // 边权重
    public static final int[] WEIGHT = new int[MAX_EDGE];
    // 边ID
    public static int edgeId;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                st.nextToken();
                m = (int) st.nval;
                st.nextToken();
                k = (int) st.nval;
                st.nextToken();
                s = (int) st.nval;
                st.nextToken();
                t = (int) st.nval;
                init();
                for (int i = 0, from, to, weight; i < m; i++) {
                    st.nextToken();
                    from = (int) st.nval;
                    st.nextToken();
                    to = (int) st.nval;
                    st.nextToken();
                    weight = (int) st.nval;

                    addEdge(from, to, weight);
                    addEdge(to, from, weight);
                }
                int ans = dijkstra();
                /*System.out.println();
                System.out.println("ans: " + ans);*/
                writer.println(ans);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static int dijkstra() {
        // city free cost
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] costs = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        boolean[][] visited = new boolean[n][k + 1];
        // city free cost
        heap.add(new int[]{s, k, 0});
        costs[s][k] = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int city = cur[0];
            int free = cur[1];
            int cost = cur[2];
            if (visited[city][free]) {
                continue;
            }
            visited[city][free] = true;
            if (city == t) {
                return cost;
            }

            for (int nextEdge = HEAD[city], nextCity, nextCost; nextEdge > 0; nextEdge = NEXT[nextEdge]) {
                nextCity = TO[nextEdge];
                nextCost = WEIGHT[nextEdge];
                // 用
                if (free > 0 && !visited[nextCity][free - 1] && costs[nextCity][free - 1] > cost) {
                    costs[nextCity][free - 1] = cost;
                    heap.add(new int[]{nextCity, free - 1, cost});
                }
                // 不用
                if (!visited[nextCity][free] && costs[nextCity][free] > cost + nextCost) {
                    costs[nextCity][free] = cost + nextCost;
                    heap.add(new int[]{nextCity, free, cost + nextCost});
                }
            }
        }
        return -1;
    }

    public static void init() {
        edgeId = 0;
        for (int i = 0; i < n; i++) {
            HEAD[i] = 0;
        }
    }

    public static void addEdge(int from, int to, int weight) {
        NEXT[++edgeId] = HEAD[from];
        HEAD[from] = edgeId;
        TO[edgeId] = to;
        WEIGHT[edgeId] = weight;
    }
}
