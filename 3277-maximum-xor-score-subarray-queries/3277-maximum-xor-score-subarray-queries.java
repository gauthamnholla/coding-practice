class Solution {
        public int[] maximumSubarrayXor(int[] A, int[][] queries) {
        int n = A.length, m = queries.length;
        int[][] xor = new int[n][n], score = new int[n][n];
        for (int i = 0; i < n; ++i)
            score[i][i] = xor[i][i] = A[i];
        for (int d = 1; d < n; ++d)
            for (int i = 0, j = d; i < n - d; ++i, ++j)
                score[i][j] = Math.max(Math.max(score[i][j - 1], score[i + 1][j]), xor[i][j] = xor[i][j - 1] ^ xor[i + 1][j]);
        int[] res = new int[m];
        for (int i = 0; i < m; ++i)
            res[i] = score[queries[i][0]][queries[i][1]];
        return res;
    }
}