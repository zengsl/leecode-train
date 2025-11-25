package luogu.class63;

import java.io.*;
import java.util.Arrays;
// 秒
// https://www.nowcoder.com/practice/bf877f837467488692be703735db84e6?tpId=98&tqId=32831&qru=/ta/2019test/question-ranking
// https://www.luogu.com.cn/problem/P4799
public class Code02_SnacksWaysBuyTickets {
    public static int MAXN = 40;
    public static int MAX_HALF = 1 << 20;

    public static int n;
    public static long m;
    public static long[] arr = new long[MAXN];
    public static long[] ls = new long[MAX_HALF];
    public static long[] rs = new long[MAX_HALF];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                n = (int) st.nval;
                st.nextToken();
                m = (long) st.nval;
                for (int i = 0; i < n; i++) {
                    st.nextToken();
                    arr[i] = (long) st.nval;
                }
                writer.println(compute());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static int compute() {
        int lSize = f(0, n >> 1, ls, 0, 0, m);
        int rSize = f(n >> 1, n, rs, 0, 0, m);
        Arrays.sort(ls, 0, lSize);
        Arrays.sort(rs, 0, rSize);

        int sum = 0;
        for (int r = rSize - 1, l = 0; r >= 0; r--) {
            while (l < lSize && ls[l] + rs[r] <= m) {
                l++;
            }
            sum += l;
        }
        return sum;
    }

    public static int f(int curr, int end, long[] sumArr, long sum, int i, long max) {
        if (sum > max) {
            return i;
        }

        if (curr == end) {
            sumArr[i++] = sum;
        } else {
            i = f(curr + 1, end, sumArr, sum, i, max);
            i = f(curr + 1, end, sumArr, sum + arr[curr], i, max);
        }
        return i;
    }
}
