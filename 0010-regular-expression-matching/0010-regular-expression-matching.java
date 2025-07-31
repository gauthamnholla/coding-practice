public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true; // both empty

        // Initialize dp[0][j]
        for (int j = 2; j <= n; j+=2) {
            if (p.charAt(j-1) == '*' && dp[0][j-2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            dp[i][0] = false; // pattern empty but s not empty, can't match
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j-1);
                if (pc == '*') {
                    // Zero of preceding element
                    dp[i][j] = dp[i][j-2];
                    // One or more if s matches preceding element in p
                    if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        dp[i][j] |= dp[i-1][j];
                    }
                } else {
                    // Match if current chars equal or pattern is '.'
                    if (pc == s.charAt(i-1) || pc == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
