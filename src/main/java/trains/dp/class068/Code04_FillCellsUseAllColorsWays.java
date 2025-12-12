package trains.dp.class068;

// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证
public class Code04_FillCellsUseAllColorsWays {
    public static void main(String[] args) {

    }

    public static final int MOD = 1000000007;
    public static int fillColor(int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        // dp[i - 1][j] * m
        // dp[i - 1][j - 1] * (m - (j - 1))
        return 0;
    }
}
