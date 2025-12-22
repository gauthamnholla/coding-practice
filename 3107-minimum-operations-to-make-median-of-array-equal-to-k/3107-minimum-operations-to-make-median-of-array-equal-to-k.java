class Solution {
        public long minOperationsToMakeMedianK(int[] A, int k) {
        int n = A.length;
        long res = 0;
        Arrays.sort(A);
        for (int i = 0; i <= n / 2; ++i)
            res += Math.max(0, A[i] - k);
        for (int i = n / 2; i < n; ++i)
            res += Math.max(0, k - A[i]);
        return res;
    }
}