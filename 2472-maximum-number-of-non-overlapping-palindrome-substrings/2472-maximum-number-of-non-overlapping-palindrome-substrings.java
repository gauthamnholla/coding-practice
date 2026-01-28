class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // Step 1: Precompute palindrome table
        boolean[][] isPal = new boolean[n][n];

        for (int center = 0; center < n; center++) {
            // Odd-length palindromes
            for (int l = center, r = center; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                isPal[l][r] = true;
            }
            // Even-length palindromes
            for (int l = center, r = center + 1; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                isPal[l][r] = true;
            }
        }

        // Step 2: DP
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            // Skip character
            dp[i] = dp[i - 1];

            // Try ending palindrome at i-1
            for (int j = i - k; j >= 0; j--) {
                if (isPal[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}
