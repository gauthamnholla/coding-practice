class Solution {
        public long nthSmallest(long n, int k) {
        long[][] comb = new long[51][51];
        for (int i = 0; i <= 50; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
        long res = 0;
        for (int i = 49; i >= 0; i--) {
            long c = comb[i][k];
            if (n > c) {
                res |= (1L << i);
                n -= c;
                if (--k == 0) break;
            }
        }
        return res;
    }
}