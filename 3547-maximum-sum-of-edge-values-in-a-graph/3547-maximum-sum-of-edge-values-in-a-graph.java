class Solution {
    public long maxScore(int n, int[][] edges) {
        long x = (n - 1) / 2;
        long y = n / 2 - 1;

        long oddSum = x * (x + 1) * (2 * x + 1) / 3 * 2 - x;
        long evenSum = y * (y + 1) * (y + 2) / 3 * 4;

        long ans = oddSum + evenSum + n * (n - 1L);
        if (edges.length == n) {
            ans += 2;
        }

        return ans;
    }
}