package luogu.class61;

import java.io.*;
import java.util.Arrays;

// 待编写 http://github.com/algorithmzuo/algorithm-journey/blob/main/src/class061/Code02_PrimStatic.java
public class Code02_PrimStatic2 {

    public static int n, m;
    public static final int POINT_MAX = 5001;
    public static final int EDGE_MAX = 400001;
    public static final int[] HEAD = new int[POINT_MAX];
    public static final int[] NEXT = new int[EDGE_MAX];
    public static final int[] TO = new int[EDGE_MAX];
    public static final int[] WEIGHT = new int[EDGE_MAX];
    public static int cnt;

    public static final int WHERE_INIT = -1;
    public static final int WHERE_USED = -2;
    // init -1, used -2 ,or >=0
    public static final int[] WHERE = new int[POINT_MAX];
    public static final int[][] HEAP = new int[POINT_MAX][2];
    public static int heapSize;

    public static int pointCnt;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                build(n);

                st.nextToken();
                m = (int) st.nval;
                for (int i = 0, f, t, w; i < m; i++) {
                    st.nextToken();
                    f = (int) st.nval;
                    st.nextToken();
                    t = (int) st.nval;
                    st.nextToken();
                    w = (int) st.nval;
                    // build graph
                    addEdge(f, t, w);
                    addEdge(t, f, w);
                }
                int ans = prime();
                writer.println(pointCnt == n ? ans : "orz");
//                System.out.println("ans:" + ans);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static int prime() {
        int ans = 0;
        pointCnt = 1;
        WHERE[1] = WHERE_USED;

        for (int nextEdge = HEAD[1], nextTo; nextEdge != 0; nextEdge = NEXT[nextEdge]) {
            nextTo = TO[nextEdge];
            if (WHERE[nextTo] == WHERE_INIT) {
                WHERE[nextTo] = heapSize;
                HEAP[heapSize++] = new int[]{nextTo, WEIGHT[nextEdge]};
                heapInsert(heapSize - 1);
            } else if (WHERE[nextTo] >= 0) {
                int where = WHERE[nextTo];
                if (HEAP[where][1] > WEIGHT[nextEdge]) {
                    HEAP[where][1] = WEIGHT[nextEdge];
                    heapInsert(where);
                }
            }
        }


        while (heapSize > 0) {
            int[] e = pop();
//            System.out.println("e:" + Arrays.toString(e));
            pointCnt++;
            ans += e[1];
            for (int nextEdge = HEAD[e[0]], nextTo; nextEdge != 0; nextEdge = NEXT[nextEdge]) {
                nextTo = TO[nextEdge];
                if (WHERE[nextTo] == WHERE_INIT) {
                    WHERE[nextTo] = heapSize;
                    HEAP[heapSize++] = new int[]{nextTo, WEIGHT[nextEdge]};
                    heapInsert(heapSize - 1);
                } else if (WHERE[nextTo] >= 0) {
                    int where = WHERE[nextTo];
                    if (HEAP[where][1] > WEIGHT[nextEdge]) {
                        HEAP[where][1] = WEIGHT[nextEdge];
                        heapInsert(where);
                    }
                }
            }
        }
        return ans;
    }

    public static void build(int n) {
        cnt = 0;
        for (int i = 1; i <= n; i++) {
            HEAD[i] = 0;
            WHERE[i] = WHERE_INIT;
        }

        heapSize = 0;
        pointCnt = 0;
    }

    public static void addEdge(int f, int t, int w) {
        int edgeId = ++cnt;
        NEXT[edgeId] = HEAD[f];
        HEAD[f] = edgeId;
        TO[edgeId] = t;
        WEIGHT[edgeId] = w;
    }

    public static int[] pop() {
        int[] e = HEAP[0];
//        HEAP[0] = HEAP[--heapSize];
        swap(0, --heapSize);
        heapify(0);
        WHERE[e[0]] = WHERE_USED;
        return e;
    }

    public static void heapInsert(int index) {
        while (HEAP[(index - 1) / 2][1] > HEAP[index][1]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int index) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && HEAP[left + 1][1] < HEAP[left][1] ? left + 1 : left;
            if (HEAP[best][1] >= HEAP[index][1]) {
                break;
            }
            swap(best, index);
            index = best;
            left = 2 * index + 1;
        }
    }

    public static void swap(int i, int j) {
        WHERE[HEAP[i][0]] = j;
        WHERE[HEAP[j][0]] = i;

        int[] tmp = HEAP[i];
        HEAP[i] = HEAP[j];
        HEAP[j] = tmp;
    }
}
