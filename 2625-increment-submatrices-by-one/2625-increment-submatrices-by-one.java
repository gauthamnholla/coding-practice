class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 1][n + 1]; // 2D difference array

        // Step 1: Apply difference updates for each query
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            diff[r1][c1]++;           // Add 1 at top-left
            diff[r2 + 1][c1]--;       // Subtract 1 below the range
            diff[r1][c2 + 1]--;       // Subtract 1 right of the range
            diff[r2 + 1][c2 + 1]++;   // Add back the overlapping corner
        }

        // Step 2: Build the final matrix using prefix sums
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int above = i > 0 ? mat[i - 1][j] : 0;
                int left = j > 0 ? mat[i][j - 1] : 0;
                int diag = i > 0 && j > 0 ? mat[i - 1][j - 1] : 0;

                // Reconstruct actual value at (i, j)
                mat[i][j] = diff[i][j] + above + left - diag;
            }
        }

        return mat; // Final updated matrix
    }
}
