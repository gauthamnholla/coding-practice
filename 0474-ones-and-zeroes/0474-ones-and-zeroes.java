class Solution {
    public int findMaxForm(String[] S, int M, int N) {
        int[][] dp = new int[M + 1][N + 1]; // dp[i][j] = max strings using i zeros and j ones

        // Go through each string
        for (String str : S) {
            int zeros = 0, ones = 0;
            // Count number of 0s and 1s in the string
            for (char c : str.toCharArray()) {
                if (c == '0') zeros++;
                else ones++;
            }

            // Update dp from bottom to top (to avoid reusing the same string)
            for (int i = M; i >= zeros; i--) {
                for (int j = N; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[M][N]; // Maximum number of strings that can be formed
    }
}
