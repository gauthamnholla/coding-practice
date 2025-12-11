class Solution {
    public int earliestFinishTime(int[] a, int[] b, int[] c, int[] d) {
        int res = Integer.MAX_VALUE;

        int n = a.length;
        int minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minEnd = Math.min(minEnd, a[i] + b[i]);
        }
        int m = c.length;

        for (int i = 0; i < m; i++) {
            res = Math.min(res, d[i] + Math.max(minEnd, c[i]));
        }

        minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minEnd = Math.min(minEnd, c[i] + d[i]);
        }

        for (int i = 0; i < n; i++) {
            res = Math.min(res, b[i] + Math.max(minEnd, a[i]));
        }

        return res;
    }
}