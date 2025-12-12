package nowcode.class67;

import java.io.*;
import java.util.Arrays;

public class Code05_NodenHeightNotLargerThanm {

    public static final int MOD = 1000000007;
    public static int n, m;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(System.out)) {
            StreamTokenizer st = new StreamTokenizer(br);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                // total
                n = (int) st.nval;
                st.nextToken();
                // <= m
                m = (int) st.nval;
                int ans = compute4();
//                System.out.println(ans);
                pw.println(ans);
            }
        }
    }

    public static int compute1() {
        return Math.toIntExact(f1(n, m));
    }

    public static long f1(int total, int level) {

        if (total == 0) {
            return 1;
        }

        if (level == 0) {
            return 0;
        }

        long ans = 0;
        int rest = total - 1;
        // 头节点需要占用一个数量和一层，所以子节点数量是0～rest
        for (int i = 0; i <= rest; i++) {
            ans += (f1(i, level - 1) * f1(total - 1 - i, level - 1)) % MOD;
        }
        return ans % MOD;
    }

    public static int compute2() {
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return Math.toIntExact(f2(n, m, dp));
    }

    public static long f2(int total, int level, long[][] dp) {
        if (total == 0) {
            return 1;
        }

        if (level == 0) {
            return 0;
        }

        if (dp[total][level] != -1) {
            return dp[total][level];
        }

        long ans = 0;
        // 头节点需要占用一个数量和一层，所以子节点数量是0～rest
        for (int i = 0; i < total; i++) {
            ans += (f2(i, level - 1, dp) * f2(total - 1 - i, level - 1, dp)) % MOD;
        }
        dp[total][level] = ans % MOD;
        return dp[total][level];
    }

    public static int compute3() {
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            // 0列都是0
            for (int j = 1; j <= m; j++) {
                long ans = 0;
                for (int k = 0; k < i; k++) {
                    ans += (dp[k][j - 1] * dp[i - 1 - k][j - 1]) % MOD;
                }
                dp[i][j] = ans % MOD;
            }
        }
        return Math.toIntExact(dp[n][m]);
    }

    public static int compute4() {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int j = 1; j <= m; j++) {
            for (int i = n; i >= 1; i--) {
                // 0列都是0
                long ans = 0;
                for (int k = 0; k < i; k++) {
                    ans += (dp[k] * dp[i - 1 - k]) % MOD;
                }
                dp[i] = ans % MOD;
            }
        }
        return Math.toIntExact(dp[n]);
    }
}
