class Solution {
    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;

        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for(int j=0; j<=k; j++){
                dp[i][j] = -100000000;
            }
            dp[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            int maxPrev = -100000000;
            for (int i = 1; i <= n; i++) {
                if (i >= m) {
                    maxPrev = Math.max(maxPrev, dp[i - m][j - 1] - pre[i - m]);
                    dp[i][j] = Math.max(dp[i - 1][j], pre[i] + maxPrev);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][k];
    }
}