class Solution {
    public int stoneGameVII(int[] S) {
        int N = S.length;
        int[] dp = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            int total = S[i];
            for (int j = i + 1; j < N; j++) {
                total += S[j];
                dp[j] = Math.max(total - S[i] - dp[j], total - S[j] - dp[j-1]);
            }
        }
        return dp[N-1];
    }
}