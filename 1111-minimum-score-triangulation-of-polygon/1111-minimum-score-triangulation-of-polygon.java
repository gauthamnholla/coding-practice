class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n]; 
        // dp[i][j] = minimum triangulation score of polygon section from i to j

        // length of sub-polygon (at least 3 vertices to form a triangle)
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int best = Integer.MAX_VALUE;

                // choose a third point k between i and j to form triangle (i, k, j)
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k]                 // triangulate (i...k)
                             + dp[k][j]                 // triangulate (k...j)
                             + values[i] * values[k] * values[j]; // cost of triangle (i,k,j)
                    best = Math.min(best, cost);
                }
                dp[i][j] = best;
            }
        }

        // whole polygon result
        return n == 0 ? 0 : dp[0][n - 1];
    }
}
