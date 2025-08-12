public class Solution {
    public int numberOfWays(int n, int x) {
        final int MOD = 1_000_000_007;
        java.util.List<Integer> powers = new java.util.ArrayList<>();
        int base = 1;
        // Safer exponentiation (no Math.pow precision issue)
        while (true) {
            int pow = 1;
            for (int i = 0; i < x; i++) pow *= base;
            if (pow > n) break;
            powers.add(pow);
            base++;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int p : powers) {
            for (int sum = n; sum >= p; sum--) {
                dp[sum] = (dp[sum] + dp[sum - p]) % MOD;
            }
        }
        return dp[n];
    }
}

