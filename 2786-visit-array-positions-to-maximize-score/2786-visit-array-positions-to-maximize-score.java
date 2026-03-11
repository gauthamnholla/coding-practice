class Solution {
        public long maxScore(int[] A, int x) {
        long dp[] = new long[] { -x, -x}, n = A.length;
        dp[A[0] & 1] = A[0];
        for (int i = 1; i < n; i++)
            dp[A[i] & 1] = Math.max(dp[A[i] & 1], dp[A[i] & 1 ^ 1] - x) + A[i];
        return Math.max(dp[0], dp[1]);
    }
}