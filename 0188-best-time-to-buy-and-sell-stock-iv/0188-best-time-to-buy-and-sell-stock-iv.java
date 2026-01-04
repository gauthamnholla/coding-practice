class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0)
            return 0;

        if (k >= n / 2) {
            int prf = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    prf += prices[i] - prices[i - 1];
            }
            return prf;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int maxdiffff = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxdiffff);
                maxdiffff = Math.max(maxdiffff, dp[i - 1][j] - prices[j]);
            }
        }

        return dp[k][n - 1];
    }
}