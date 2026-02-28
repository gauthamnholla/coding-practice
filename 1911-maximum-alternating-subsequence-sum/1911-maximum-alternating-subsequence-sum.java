class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        // dp[i][0] is for first i + 1 elements, the last element is the even element
        // dp[i][1] is for first i + 1 elements, the last element is the odd element
        // (the last element may or may not be nums[i])
        long[][] dp = new long[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = Long.MIN_VALUE;
            dp[i][1] = Long.MIN_VALUE;
        }
        dp[0][0] = nums[0];
        for (int i = 1; i < n; i++) {
            // not use nums[i], or nums[i] as the only element
            dp[i][0] = Math.max(nums[i], dp[i - 1][0]);
            if (dp[i - 1][1] != Long.MIN_VALUE) { // or use nums[i]
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + nums[i]);
            }

            // not use nums[i]
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][1]);
            if (dp[i - 1][0] != Long.MIN_VALUE) { // or use nums[i]
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] - nums[i]);
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}