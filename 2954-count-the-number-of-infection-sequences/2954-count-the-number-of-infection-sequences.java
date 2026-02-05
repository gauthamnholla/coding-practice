class Solution {
    // Taken from the 11ms sample Java Solution
    static final int m = 100000;
    static long[] fact = new long[m + 1];
    static long[] inv_fact = new long[m + 1];
    static int mod = (int) 1e9 + 7;
    static boolean init = false;

    public int numberOfSequence(int n, int[] sick) {
        // The init == 0 section is taken from the 11ms sample Java Solution
        if (!init) {
            init = true;
            fact[0] = 1;
            for (int i = 1; i <= m; ++i) {
                fact[i] = fact[i - 1] * i % mod;
            }
            inv_fact[m] = modPow((int) fact[m], mod - 2, mod);
            for (int i = m - 1; i > 0; --i) {
                inv_fact[i] = inv_fact[i + 1] * (i + 1) % mod;
            }
            inv_fact[0] = 1;
        }
        long totalCount = fact[n - sick.length];
        int len, twoCount = 0;
        for (int i = 1 ; i < sick.length ; i ++) {
            len = sick[i] - sick[i - 1] - 1;
            twoCount += Math.max(0, len - 1);
            totalCount *= inv_fact[len];
            totalCount %= mod;
        }
        totalCount *= modPow(2, twoCount, mod);
        totalCount %= mod;
        totalCount *= inv_fact[sick[0]];
        totalCount %= mod;
        totalCount *= inv_fact[n - sick[sick.length - 1] - 1];
        totalCount %= mod;
        return (int) totalCount;
    }

    // Taken from the 11ms sample Java Solution
    private int modPow(int x, int y, int mod) {
        if (y == 0) {
            return 1;
        }
        long p = modPow(x, y / 2, mod) % mod;
        p = (p * p) % mod;
        return y % 2 == 1 ? (int)(p * x % mod) : (int) p;
    }
}