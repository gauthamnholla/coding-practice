class Solution {
        public int countOfPairs(int[] A) {
        int n = A.length, m = 1001, mod = 1000000007, dp[] = new int[m];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            int d = Math.max(0, A[i] - A[i - 1]), dp2[] = new int[m];
            for (int j = d; j < A[i] + 1; ++j) {
                dp2[j] = ((j > 0 ? dp2[j - 1] : 0) + dp[j - d]) % mod;
            }
            dp = dp2;
        }

        int res = 0;
        for (int j = 0; j <= A[n - 1]; ++j)
            res = (res + dp[j]) % mod;
        return res;
    }
}