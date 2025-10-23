package nowcode.class52;

import java.io.*;

public class Code01_LeftRightLess {
    public static int MAXN = 1000001;

    public static int[] arr = new int[MAXN];

    // 存index
    public static int[] stack = new int[MAXN];

    public static int[][] ans = new int[MAXN][2];

    // r 栈大小
    public static int n, r;


    //  7
    // 3 4 1 5 6 2 7
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            compute();
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    private static void compute() {
        r = 0;
        for (int i = 0; i < n; i++) {
            // 比栈顶元素大的话就加入
            if (r == 0 || arr[i] > arr[stack[r - 1]]) {
                stack[r++] = i;
                continue;
            }

            // 比栈顶元素小的话就慢慢弹出
            while (r > 0 && arr[i] <= arr[stack[r - 1]]) {
                int index = stack[--r];
                // 当前栈顶作为左侧
                ans[index][0] = r == 0 ? -1 : stack[r - 1];
                ans[index][1] = i;
            }
            stack[r++] = i;
        }

        while (r > 0) {
            int index = stack[--r];
            ans[index][0] = r == 0 ? -1 : stack[r - 1];
            ans[index][1] = -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (ans[i][1] != -1 && arr[i] <= arr[ans[i][1]]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }
}
