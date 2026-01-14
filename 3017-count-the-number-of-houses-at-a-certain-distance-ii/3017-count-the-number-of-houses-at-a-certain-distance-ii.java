class Solution {
        public long[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            int t = x; x = y; y = t;
        }
        long[] A = new long[n];
        for (int i = 1; i <= n; ++i) {
            A[0] += 2;                                             // go left and right
            A[Math.min(i - 1, Math.abs(i - y) + x)]--;             // reach 1 then stop
            A[Math.min(n - i, Math.abs(i - x) + 1 + n - y)]--;     // reach n then stop
            A[Math.min(Math.abs(i - x), Math.abs(y - i) + 1)]++;   // reach x then split
            A[Math.min(Math.abs(i - x) + 1, Math.abs(y - i))]++;   // reach y then split
            int r = Math.max(x - i, 0) + Math.max(i - y, 0);
            A[r + (y - x + 0) / 2]--;                              // i -> x -> y <- x
            A[r + (y - x + 1) / 2]--;                              // i -> y -> x <- y
        }
        for (int i = 1; i < n; ++i)
            A[i] += A[i - 1];
        return A;
    }
}