class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[r] = maximum sum with remainder r (mod 3)
        final int NEG_INF = Integer.MIN_VALUE / 4;
        int[] dp = new int[]{0, NEG_INF, NEG_INF};

        for (int x : nums) {
            int m = x % 3;
            int[] next = new int[]{dp[0], dp[1], dp[2]}; // copy current
            for (int r = 0; r < 3; r++) {
                if (dp[r] == NEG_INF) continue;
                int nr = (r + m) % 3;
                next[nr] = Math.max(next[nr], dp[r] + x);
            }
            dp = next;
        }
        return Math.max(0, dp[0]); // dp[0] may be 0 if no valid selection except empty set
    }
}
