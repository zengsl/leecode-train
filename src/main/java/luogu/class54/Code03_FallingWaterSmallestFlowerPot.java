package luogu.class54;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Code03_FallingWaterSmallestFlowerPot {
    public static int n;
    public static int d;
    public static final int MAX = 100001;

    public static int[][] arr = new int[MAX][2];
    public static int length;

    public static int[] MAX_DE_QUEUE = new int[MAX];
    public static int maxHead, maxTail;

    public static int[] MIN_DE_QUEUE = new int[MAX];
    public static int minHead, minTail;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            d = (int) in.nval;
//            System.out.println();
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
//                System.out.println("i =  " + i + " : " + arr[i][0] + " , " + arr[i][1]);
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    static int compute() {
        // reset
        maxHead = maxTail = minHead = minTail = 0;
        // sort by position
        Arrays.sort(arr, 0, n, Comparator.comparingInt(a -> a[0]));

        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && !isOk()) {
                push(r++);
            }
            if (isOk()) {
                ans = Math.min(ans, arr[r - 1][0] - arr[l][0]);
            }
            pop(l);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    static boolean isOk() {
        int max = maxHead == maxTail ? 0 : arr[MAX_DE_QUEUE[maxHead]][1];
        int min = minHead == minTail ? 0 : arr[MIN_DE_QUEUE[minHead]][1];
        return max - min >= d;
    }

    static void push(int i) {
        while (maxTail > maxHead && arr[i][1] >= arr[MAX_DE_QUEUE[maxTail - 1]][1]) {
            maxTail--;
        }
        MAX_DE_QUEUE[maxTail++] = i;

        while (minTail > minHead && arr[i][1] <= arr[MIN_DE_QUEUE[minTail - 1]][1]) {
            minTail--;
        }
        MIN_DE_QUEUE[minTail++] = i;
    }

    static void pop(int i) {
        if (i == MAX_DE_QUEUE[maxHead]) {
            maxHead++;
        }

        if (i == MIN_DE_QUEUE[minHead]) {
            minHead++;
        }
    }
}
