class Solution {
        public int minFlips(int[][] A) {
        int res = 0, one = 0, diff = 0, m = A.length, n = A[0].length;
        // Handle quadrants
        for (int i = 0; i < m / 2; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                int v = A[i][j] + A[i][n - 1 - j] + A[m - 1 - i][j] + A[m - 1 - i][n - 1 - j];
                res += Math.min(v, 4 - v);
            }
        }
        // Handle middle column
        if (n % 2 > 0) {
            for (int i = 0; i < m / 2; ++i) {
                diff += A[i][n / 2] ^ A[m - 1 - i][n / 2];
                one += A[i][n / 2] + A[m - 1 - i][n / 2];
            }
        }
        // Handle middle row
        if (m % 2 > 0) {
            for (int j = 0; j < n / 2; ++j) {
                diff += A[m / 2][j] ^ A[m / 2][n - 1 - j];
                one += A[m / 2][j] + A[m / 2][n - 1 - j];
            }
        }
        // Handle center point
        if (n % 2 > 0 && m % 2 > 0) {
            res += A[m / 2][n / 2];
        }
        // Divisible by 4
        if (diff == 0 && one % 4 > 0) {
            res += 2;
        }
        return res + diff;
    }
}