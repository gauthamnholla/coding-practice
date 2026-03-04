class Solution {
        public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices)
            dp[p[0]][p[1]] = p[2];
        for (int w = 1; w <= m; ++w) {
            for (int h = 1; h <= n; ++h) {
                for (int a = 1; a <= w / 2; ++a)
                    dp[w][h] = Math.max(dp[w][h], dp[a][h] + dp[w - a][h]);
                for (int a = 1; a <= h / 2; ++a)
                    dp[w][h] = Math.max(dp[w][h], dp[w][a] + dp[w][h - a]);
            }
        }
        return dp[m][n];
    }
}