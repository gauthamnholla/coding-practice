public class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k - 1 + maxPts) return 1.0;

        double[] dp = new double[n + 1];
        dp[0] = 1.0;              // initialize the first state
        double windowSum = 1.0;   // current sum of dp values that feed next states
        double ans = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = windowSum / maxPts;

            if (i < k) {
                // i is a state that can still draw; include it in the window
                windowSum += dp[i];
            } else {
                // terminal state: add to answer
                ans += dp[i];
            }

            // slide window: remove dp[i - maxPts] only if that index was < k (i.e., was added)
            int j = i - maxPts;
            if (j >= 0 && j < k) {
                windowSum -= dp[j];
            }
        }
        return ans;
    }
}
