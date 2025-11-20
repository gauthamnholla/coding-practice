class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) return false;

        // dp[j] = whether s3[0..i+j-1) can be formed using s1[0..i) and s2[0..j)
        boolean[] dp = new boolean[m + 1];

        // base i = 0: only s2 contributes
        dp[0] = true;
        for (int j = 1; j <= m; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= n; i++) {
            // update dp[0] for current i: only s1 contributes
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= m; j++) {
                char c = s3.charAt(i + j - 1);
                boolean fromS1 = dp[j] && s1.charAt(i - 1) == c;     // dp[j] is old dp[i-1][j]
                boolean fromS2 = dp[j - 1] && s2.charAt(j - 1) == c; // dp[j-1] is dp[i][j-1] after update
                dp[j] = fromS1 || fromS2;
            }
        }

        return dp[m];
    }
}
