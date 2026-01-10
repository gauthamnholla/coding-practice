class Solution {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int MOD = 1000000007;

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = (dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1]) % MOD;
                } else {
                    int l = i + 1, r = j - 1;
                    while (l <= r && s.charAt(l) != s.charAt(i)) l++;
                    while (l <= r && s.charAt(r) != s.charAt(j)) r--;

                    if (l > r) { 
                        dp[i][j] = (2 * dp[i+1][j-1] + 2) % MOD;
                    } else if (l == r) { 
                        dp[i][j] = (2 * dp[i+1][j-1] + 1) % MOD;
                    } else { 
                        dp[i][j] = (2 * dp[i+1][j-1] - dp[l+1][r-1]) % MOD;
                    }
                }
                if (dp[i][j] < 0) dp[i][j] += MOD;
            }
        }

        return dp[0][n-1];
    }
}