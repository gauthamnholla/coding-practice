class Solution {
        public long minCost(int[] a, int[] b, long k) {
        long res1 = 0, res2 = 0;
        for (int i = 0; i < a.length; ++i) {
            res1 += Math.abs(a[i] - b[i]);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; ++i) {
            res2 += Math.abs(a[i] - b[i]);
        }
        return Math.min(res1, res2 + k);
    }
}