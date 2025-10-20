package com.leetcode.editor.cn;

import java.io.*;

public class Class51Code03_RobotPassThroughBuilding {

    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            int min = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
                max = Math.max(max, arr[i]);
            }
            out.println(compute(min, max));
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int compute(int min, int max) {

        int ans = Integer.MAX_VALUE;
        int l = min, r = max, mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (f(mid, max)) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static boolean f(int initNum, int max) {
        boolean success = true;
        long sum = initNum;
        for (int i = 1; i <= n; i++) {
            if (!((sum += (sum - arr[i])) >= 0)) {
                success = false;
                break;
            } else if (sum > max) {
                // sum大于max之后，会一直满足条件
                break;
            }
        }
        return success;
    }
}
