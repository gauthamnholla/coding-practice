class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // dp[t] = number of people who learn the secret on day t
        long[] dp = new long[n + 1];
        dp[1] = 1; // On day 1, exactly one person knows the secret

        long share = 0; // Running count of people who are currently able to share
        long MOD = (long)1e9 + 7; // Large prime to keep values within integer limits

        // Process each day from day 2 to n
        for (int t = 2; t <= n; t++) {
            // When t - delay > 0, people who learned the secret 'delay' days ago can now share
            if (t - delay > 0)
                share = (share + dp[t - delay] + MOD) % MOD;

            // When t - forget > 0, people who learned the secret 'forget' days ago forget it
            if (t - forget > 0)
                share = (share - dp[t - forget] + MOD) % MOD;

            // On day t, exactly 'share' new people learn the secret
            dp[t] = share;
        }

        long know = 0; // Final count of people who still remember the secret on day n

        // People who learned the secret within the last 'forget' days still remember
        for (int i = n - forget + 1; i <= n; i++)
            know = (know + dp[i]) % MOD;

        return (int)know;
    }
}
