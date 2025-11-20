class Solution {
    private static final long MOD = 1_000_000_007L;

    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;

        // total sum
        long total = 0;
        for (int v : arr) total += v;

        // max prefix sum
        long pref = Long.MIN_VALUE;
        long cur = 0;
        for (int v : arr) {
            cur += v;
            pref = Math.max(pref, cur);
        }

        // max suffix sum
        long suff = Long.MIN_VALUE;
        cur = 0;
        for (int i = n - 1; i >= 0; i--) {
            cur += arr[i];
            suff = Math.max(suff, cur);
        }

        // max subarray sum for single array (Kadane)
        long maxSingle = kadane(arr, 1);

        if (k == 1) {
            long ans = Math.max(0L, maxSingle);
            return (int)(ans % MOD);
        }

        // max subarray sum for array concatenated twice
        long maxDouble = kadane(arr, 2);

        long candidate1 = maxDouble;
        long candidate2 = suff + pref + Math.max(0L, total) * (k - 2L);

        long ans = Math.max(candidate1, candidate2);
        ans = Math.max(ans, 0L);

        return (int)(ans % MOD);
    }

    // compute max subarray sum over t concatenations (t = 1 or 2)
    private long kadane(int[] arr, int t) {
        long best = Long.MIN_VALUE;
        long cur = 0;
        int n = arr.length;
        for (int i = 0; i < n * t; i++) {
            int val = arr[i % n];
            cur = Math.max(cur + val, val);
            best = Math.max(best, cur);
        }
        return best;
    }
}
