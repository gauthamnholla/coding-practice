class Solution {
    private long C(int n, int r) {
        if (r < 0 || r > n) return 0;
        long res = 1;
        r = Math.min(r, n - r);
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
        }
        return res;
    }

    public String kthSmallestPath(int[] destination, int k) {
        int rows = destination[0];
        int cols = destination[1];
        int n = rows + cols;

        int cntH = cols, cntV = rows;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (cntH > 0) {
                long num = C(n - i - 1, cntH - 1);
                if (k <= num) {
                    sb.append('H');
                    cntH--;
                } else {
                    sb.append('V');
                    k -= num;
                    cntV--;
                }
            } else {
                sb.append('V');
                cntV--;
            }
        }
        return sb.toString();
    }
}